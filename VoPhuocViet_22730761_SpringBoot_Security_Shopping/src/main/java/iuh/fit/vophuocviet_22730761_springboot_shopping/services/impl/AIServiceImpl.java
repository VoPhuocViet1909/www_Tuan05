package iuh.fit.vophuocviet_22730761_springboot_shopping.services.impl;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Product;
import iuh.fit.vophuocviet_22730761_springboot_shopping.reposities.ProductRepository;
import iuh.fit.vophuocviet_22730761_springboot_shopping.services.AIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final ChatModel chatModel;
    private final ProductRepository productRepository;

    @Override
    public String askQuestion(String question) {
        try {
            log.info("📩 Nhận câu hỏi từ user: {}", question);

            // Lấy danh sách sản phẩm từ database
            List<Product> products = productRepository.findAll();

            if (products.isEmpty()) {
                return "❌ Hiện tại chưa có sản phẩm nào trong hệ thống. Vui lòng thêm sản phẩm trước khi sử dụng AI Assistant.";
            }

            // Format giá tiền theo VND
            NumberFormat vndFormat = NumberFormat.getInstance(new Locale("vi", "VN"));

            String context = products.stream()
                    .map(p -> String.format("- %s (Giá: %s đ, Còn hàng: %s, Loại: %s)",
                            p.getName(),
                            vndFormat.format(p.getPrice()),
                            p.isInStock() ? "Có" : "Không",
                            p.getCategory() != null ? p.getCategory().getName() : "Chưa phân loại"
                    ))
                    .collect(Collectors.joining("\n"));

            String prompt = String.format("""
                Bạn là trợ lý bán hàng thông minh của cửa hàng.
                
                QUAN TRỌNG:
                - Chỉ trả lời dựa trên danh sách sản phẩm bên dưới
                - Không được tự bịa thông tin về sản phẩm không có trong danh sách
                - Nếu không có thông tin, hãy nói rõ "Hiện tại chúng tôi chưa có sản phẩm này"
                - Trả lời bằng tiếng Việt, thân thiện và chuyên nghiệp
                - Sử dụng emoji phù hợp để câu trả lời sinh động hơn
                
                DANH SÁCH SẢN PHẨM HIỆN CÓ:
                %s
                
                CÂU HỎI CỦA KHÁCH HÀNG:
                %s
                
                Hãy trả lời một cách chi tiết, hữu ích và thân thiện.
                """, context, question);

            log.info("🤖 Đang gọi Gemini AI...");
            String response = chatModel.call(prompt);
            log.info("✅ Nhận được phản hồi từ AI: {}", response.substring(0, Math.min(100, response.length())) + "...");

            return response;

        } catch (Exception e) {
            log.error("❌ LỖI khi gọi AI service: ", e);

            // Phân loại lỗi cụ thể
            if (e.getMessage() != null && e.getMessage().contains("API key")) {
                return "❌ Lỗi xác thực API key. Vui lòng kiểm tra lại cấu hình trong application.properties";
            } else if (e.getMessage() != null && e.getMessage().contains("Connection")) {
                return "❌ Không thể kết nối đến Google Gemini API. Vui lòng kiểm tra kết nối internet.";
            } else {
                return "❌ Xin lỗi, hiện tại không thể kết nối đến dịch vụ AI. Chi tiết lỗi: " + e.getMessage();
            }
        }
    }
}
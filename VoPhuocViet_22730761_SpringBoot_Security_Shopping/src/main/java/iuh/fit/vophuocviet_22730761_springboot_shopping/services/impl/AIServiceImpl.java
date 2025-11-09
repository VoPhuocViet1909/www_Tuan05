package iuh.fit.vophuocviet_22730761_springboot_shopping.services.impl;

import iuh.fit.vophuocviet_22730761_springboot_shopping.entities.Product;
import iuh.fit.vophuocviet_22730761_springboot_shopping.reposities.ProductRepository;
import iuh.fit.vophuocviet_22730761_springboot_shopping.services.AIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

import java.util.List;
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
            List<Product> products = productRepository.findAll();
            String context = products.stream()
                    .map(p -> "- " + p.getName() + " (Giá: " + p.getPrice() + " đ)")
                    .collect(Collectors.joining("\n"));

            String prompt = """
                Bạn là trợ lý bán hàng của cửa hàng.
                Chỉ được trả lời dựa trên danh sách sản phẩm sau. Không tự bịa thông tin.
                Danh sách sản phẩm:
                %s

                Câu hỏi:
                %s
                """.formatted(context, question);

            return chatModel.call(prompt);
        } catch (Exception e) {
            log.error("Lỗi khi gọi AI service: ", e);
            return "Xin lỗi, hiện tại không thể kết nối đến dịch vụ AI. Vui lòng thử lại sau.";
        }
    }
}
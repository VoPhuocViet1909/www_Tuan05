package iuh.fit.vophuocviet_22730761_springboot_shopping.services.impl;

import iuh.fit.se.TranNgocHuyen_Tuan08.model.Product;
import iuh.fit.se.TranNgocHuyen_Tuan08.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

import java.util.List;

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
                    .map(p -> "- " + p.getName() + " (Giá: " + p.getPrice() + "đ)")
                    .reduce("", (a, b) -> a + "\n" + b);
            String prompt = """
                Bạn là trợ lý bán hàng trong cửa hàng.
                Bạn chỉ được trả lời dựa trên danh sách sản phẩm dưới đây.
                ❗ Không được tự bịa thông tin.
                Danh sách sản phẩm:
                %s

                Câu hỏi từ khách:
                %s
                """.formatted(context, question);
            return chatModel.call(prompt);
        } catch (Exception e) {
            log.error("Lỗi khi gọi AI service: ", e);
            return "Xin lỗi, hiện tại không thể kết nối đến dịch vụ AI. Vui lòng thử lại sau.";
        }
    }

}
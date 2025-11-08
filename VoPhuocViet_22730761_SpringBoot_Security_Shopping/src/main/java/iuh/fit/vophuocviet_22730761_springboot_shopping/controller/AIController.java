package iuh.fit.vophuocviet_22730761_springboot_shopping.controller;

import iuh.fit.vophuocviet_22730761_springboot_shopping.services.AIService;
import iuh.fit.vophuocviet_22730761_springboot_shopping.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;
    private final ProductService productService;

    @GetMapping("/assistant")
    public String showAIAssistant(Model model) {
        model.addAttribute("pageTitle", "AI Assistant");
        return "ai-assistant";
    }

    @GetMapping("/chat/{message}")
    @ResponseBody
    public ResponseEntity<String> chat(@PathVariable("message") String message) {
        try {
            String answer = aiService.askQuestion(message);
            return ResponseEntity.ok(answer);
        } catch (Exception e) {
            return ResponseEntity.ok("Lỗi: " + e.getMessage());
        }
    }
}
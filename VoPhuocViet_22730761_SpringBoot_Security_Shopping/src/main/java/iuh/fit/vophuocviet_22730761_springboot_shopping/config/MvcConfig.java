package iuh.fit.vophuocviet_22730761_springboot_shopping.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // có thể thiết lập trong application.properties, mặc định là "uploads"
    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // ánh xạ /uploads/** -> folder trên filesystem
        String location = Paths.get(uploadDir).toAbsolutePath().toUri().toString(); // "file:/.../"
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(location);
    }
}
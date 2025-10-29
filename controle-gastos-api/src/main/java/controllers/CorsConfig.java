package controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    //CORS (Cross-Origin Resource Sharing) é uma proteção do navegador que bloqueia requisições entre diferentes origens (domínios/portas)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")  // Permite requisições do Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos HTTP permitidos
                .allowedHeaders("*")  // Permite todos os headers (incluindo Authorization para JWT)
                .allowCredentials(true)  // Permite envio de cookies/credenciais
                .maxAge(3600);  // Cache da configuração CORS por 1 hora
    }
}

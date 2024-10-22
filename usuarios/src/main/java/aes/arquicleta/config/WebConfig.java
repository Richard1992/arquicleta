package aes.arquicleta.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Permite CORS para todas las rutas que comienzan con /api/
                .allowedOrigins("http://localhost:4200") // Cambia esto si tu frontend está en otro puerto
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Permitir cualquier cabecera
                .allowCredentials(true); // Permitir credenciales si es necesario
    }
}

package com.TaskManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures the application's Cross-Origin Resource Sharing (CORS) settings.
 * This class provides a global CORS configuration, avoiding the need to annotate
 * each controller with @CrossOrigin.
 */
@Configuration
public class CorsConfig {
    
    /**
     * Defines the CORS configuration bean.
     *
     * @return a WebMvcConfigurer with the defined CORS mappings.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Adds CORS mappings to the registry.
             * This configuration allows requests from any origin, with common HTTP methods and all headers.
             * For production, it's recommended to restrict allowedOrigins to the specific frontend domain.
             * @param registry the CorsRegistry to add the mapping to.
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply to all endpoints
                             .allowedOrigins("*") // Allow all origins (for development)
                             .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed methods
                             .allowedHeaders("*") // Allow all headers
                             .allowCredentials(false);
            }
        };
    }
}

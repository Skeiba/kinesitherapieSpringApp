package com.miniProjet.kinesitherapie.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class contains the configuration settings for the application.
 * It defines beans and other configuration elements that are used across the application.
 *
 * <p>Guidelines for adding new configurations:</p>
 * <ul>
 *     <li>Declare beans or settings that need to be globally accessible in the application.</li>
 *     <li>Use descriptive and meaningful names for methods to indicate the purpose of the configuration.</li>
 *     <li>Ensure proper annotations (e.g., @Configuration, @Bean) are used to mark configuration classes and beans.</li>
 *     <li>Keep configurations specific to their purpose; if the configuration is module-specific, place it in the corresponding module's package.</li>
 * </ul>
 */

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    };
}

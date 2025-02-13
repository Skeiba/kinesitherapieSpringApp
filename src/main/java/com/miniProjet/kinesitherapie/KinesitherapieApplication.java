package com.miniProjet.kinesitherapie;

import com.miniProjet.kinesitherapie.config.RsakeysConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)
public class KinesitherapieApplication {

	public static void main(String[] args) {
		SpringApplication.run(KinesitherapieApplication.class, args);
	}

}

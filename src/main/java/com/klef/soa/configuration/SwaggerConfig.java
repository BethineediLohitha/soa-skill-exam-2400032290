package com.klef.soa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig
{
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

	@Bean
	public OpenAPI customOpenAPI()
	{
		return new OpenAPI()
				.info(new Info()
						.title("GST Record Service API")
						.version("1.0")
						.description("REST API Documentation for GST Record Microservice")
						.contact(new Contact()
								.name("KLUniversity")
								.email("demo@example.com")));
	}
}
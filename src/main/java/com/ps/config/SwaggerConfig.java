package com.ps.config;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket demoApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.protocols(new HashSet<>(Arrays.asList("http", "https"))).select()
				.apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				.build()
				.apiInfo(buildApiInfo());
	}

	public ApiInfo buildApiInfo() {
		return new ApiInfoBuilder().title("Product Management Application")
				.description("curd operation for product management")
				.license("License by Dhananjay")
				.licenseUrl("#")
				.version("1.0.0")
				.termsOfServiceUrl("#")
				.contact(new Contact("Dhananjaya ", "www.realspeed.com", "dhananjaya.singhar@gmail.com"))
				.build();
	}
	
	
}

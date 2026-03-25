package com.jfdetails.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController // 1. Dizemos ao Spring que esta classe vai responder a requisições Web
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	// 2. Criamos uma rota simples para teste
	@GetMapping("/teste")
	public String testeServidor() {
		return "Motor da JF Details rodando com sucesso!";
	}
}
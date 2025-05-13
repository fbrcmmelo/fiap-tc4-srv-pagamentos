package com.fiap.tc4_srv_gateway_pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Tc4SrvGatewayPagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tc4SrvGatewayPagamentoApplication.class, args);
	}

}

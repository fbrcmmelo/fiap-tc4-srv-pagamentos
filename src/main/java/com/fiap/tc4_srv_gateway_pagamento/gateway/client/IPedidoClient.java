package com.fiap.tc4_srv_gateway_pagamento.gateway.client;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoOut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "pedido", url = "srv-pedido-consumer:8081")
public interface IPedidoClient {

    @PostMapping("consumer/solicitacao-atualizada")
    void notificarAtualizacaoPagamento(@RequestBody SolicitacaoPagamentoOut solicitacaoPagamentoOut);
}

package com.fiap.tc4_srv_gateway_pagamento.gateway;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoOut;
import com.fiap.tc4_srv_gateway_pagamento.gateway.client.IPedidoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProducerGatewayImpl implements ProducerGateway {

    private final IPedidoClient pedidoClient;

    @Override
    public void notificarSolicitacaoAtualizada(SolicitacaoPagamentoOut solicitacaoPagamento) {
        try {
            this.pedidoClient.notificarAtualizacaoPagamento(solicitacaoPagamento);
        } catch (Exception e) {
            log.error("Falha ao notificar atualização de pagamento: {}", e.getMessage());
        }
    }
}

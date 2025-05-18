package com.fiap.tc4_srv_gateway_pagamento.gateway;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoOut;
import com.fiap.tc4_srv_gateway_pagamento.gateway.client.IPedidoClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ProducerGatewayImplTest {

    private IPedidoClient pedidoClient;
    private ProducerGatewayImpl producerGateway;

    @BeforeEach
    void setUp() {
        pedidoClient = mock(IPedidoClient.class);
        producerGateway = new ProducerGatewayImpl(pedidoClient);
    }

    @Test
    void shouldNotifySolicitacaoAtualizada() {
        SolicitacaoPagamentoOut solicitacao = new SolicitacaoPagamentoOut("id", null);

        producerGateway.notificarSolicitacaoAtualizada(solicitacao);

        verify(pedidoClient).notificarAtualizacaoPagamento(solicitacao);
    }

    @Test
    void shouldLogErrorWhenNotificationFails() {
        SolicitacaoPagamentoOut solicitacao = new SolicitacaoPagamentoOut("id", null);
        doThrow(new RuntimeException("fail")).when(pedidoClient).notificarAtualizacaoPagamento(any());

        producerGateway.notificarSolicitacaoAtualizada(solicitacao);

        verify(pedidoClient).notificarAtualizacaoPagamento(solicitacao);
    }
}

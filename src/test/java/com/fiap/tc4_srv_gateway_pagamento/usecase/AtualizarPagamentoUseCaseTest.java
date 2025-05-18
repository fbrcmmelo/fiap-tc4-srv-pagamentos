package com.fiap.tc4_srv_gateway_pagamento.usecase;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamento;
import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoOut;
import com.fiap.tc4_srv_gateway_pagamento.gateway.PagamentoGatewayImpl;
import com.fiap.tc4_srv_gateway_pagamento.gateway.ProducerGatewayImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AtualizarPagamentoUseCaseTest {

    private PagamentoGatewayImpl pagamentoGateway;
    private ProducerGatewayImpl producerGateway;
    private AtualizarPagamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        pagamentoGateway = mock(PagamentoGatewayImpl.class);
        producerGateway = mock(ProducerGatewayImpl.class);
        useCase = new AtualizarPagamentoUseCase(pagamentoGateway, producerGateway);
    }

    @Test
    void shouldUpdateStatus() {
        String id1 = "id1";
        when(pagamentoGateway.buscarSolicitao(id1)).thenReturn(new SolicitacaoPagamento("100", "1234"));

        useCase.atualizarStatus(id1);

        verify(pagamentoGateway, times(1)).atualizarStatusPagamento(any(SolicitacaoPagamento.class));
        verify(producerGateway, times(1)).notificarSolicitacaoAtualizada(any(SolicitacaoPagamentoOut.class));
    }
}

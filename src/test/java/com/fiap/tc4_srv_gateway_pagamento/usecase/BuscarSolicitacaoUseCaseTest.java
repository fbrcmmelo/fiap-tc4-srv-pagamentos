package com.fiap.tc4_srv_gateway_pagamento.usecase;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamento;
import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoOut;
import com.fiap.tc4_srv_gateway_pagamento.gateway.PagamentoGatewayImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BuscarSolicitacaoUseCaseTest {

    private PagamentoGatewayImpl pagamentoGateway;
    private BuscarSolicitacaoUseCase useCase;

    @BeforeEach
    void setUp() {
        pagamentoGateway = mock(PagamentoGatewayImpl.class);
        useCase = new BuscarSolicitacaoUseCase(pagamentoGateway);
    }

    @Test
    void shouldReturnSolicitacaoPagamentoOut() {
        when(pagamentoGateway.buscarSolicitao("id1")).thenReturn(new SolicitacaoPagamento("id1", "1234"));

        SolicitacaoPagamentoOut result = useCase.buscarSolicitacaoPor("id1");

        assertThat(result).isNotNull();
        verify(pagamentoGateway).buscarSolicitao("id1");
    }
}

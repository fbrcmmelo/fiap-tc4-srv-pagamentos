package com.fiap.tc4_srv_gateway_pagamento.usecase;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamento;
import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoIn;
import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoOut;
import com.fiap.tc4_srv_gateway_pagamento.gateway.PagamentoGatewayImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SolicitarPagamentoUseCaseTest {

    private PagamentoGatewayImpl pagamentoGateway;
    private SolicitarPagamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        pagamentoGateway = mock(PagamentoGatewayImpl.class);
        useCase = new SolicitarPagamentoUseCase(pagamentoGateway);
    }

    @Test
    void shouldSolicitPagamentoAndReturnOut() {
        var solicitacao = new SolicitacaoPagamentoIn("100", "1234");
        when(pagamentoGateway.solicitarPagamento(any())).thenReturn(new SolicitacaoPagamento(solicitacao.valor(),
                solicitacao.numeroCartao()));

        SolicitacaoPagamentoOut result = useCase.solicitar(solicitacao);

        assertThat(result).isNotNull();
        verify(pagamentoGateway).solicitarPagamento(any());
    }
}

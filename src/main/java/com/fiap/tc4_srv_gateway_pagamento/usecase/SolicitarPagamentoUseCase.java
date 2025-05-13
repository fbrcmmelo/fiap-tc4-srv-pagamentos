package com.fiap.tc4_srv_gateway_pagamento.usecase;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamento;
import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoIn;
import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoOut;
import com.fiap.tc4_srv_gateway_pagamento.gateway.PagamentoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SolicitarPagamentoUseCase implements ISolicitarPagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;

    @Override
    public SolicitacaoPagamentoOut solicitar(SolicitacaoPagamentoIn solicitacaoIn) {
        final var solicitacao = new SolicitacaoPagamento(solicitacaoIn.valor(), solicitacaoIn.numeroCartao());
        final var pagamentoSolicitado = this.pagamentoGateway.solicitarPagamento(solicitacao);

        return pagamentoSolicitado.toSolicitacaoPagamentoOut();
    }
}

package com.fiap.tc4_srv_gateway_pagamento.usecase;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoOut;
import com.fiap.tc4_srv_gateway_pagamento.gateway.PagamentoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuscarSolicitacaoUseCase implements IBuscarSolicitacaoUseCase {

    private final PagamentoGateway pagamentoGateway;

    @Override
    public SolicitacaoPagamentoOut buscarSolicitacaoPor(String solicitacaoId) {
        if (solicitacaoId == null || solicitacaoId.isEmpty()) {
            throw new IllegalArgumentException("ID da solicitação não pode ser nulo ou vazio");
        }
        return this.pagamentoGateway.buscarSolicitao(solicitacaoId).toSolicitacaoPagamentoOut();
    }
}

package com.fiap.tc4_srv_gateway_pagamento.gateway;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamento;
import com.fiap.tc4_srv_gateway_pagamento.gateway.jpa.PagamentoEntity;
import com.fiap.tc4_srv_gateway_pagamento.gateway.jpa.PagamentoMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PagamentoGatewayImpl implements PagamentoGateway {

    private final PagamentoMongoRepository pagamentoMongoRepository;

    @Override
    public SolicitacaoPagamento solicitarPagamento(SolicitacaoPagamento solicitacao) {
        return this.pagamentoMongoRepository.save(new PagamentoEntity(solicitacao)).toSolicitacaoPagamento();
    }

    @Override
    public SolicitacaoPagamento buscarSolicitao(String solicitacaoId) {
        return this.pagamentoMongoRepository.findById(solicitacaoId)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"))
                .toSolicitacaoPagamento();
    }

    @Override
    public void atualizarStatusPagamento(SolicitacaoPagamento encontrado) {
        this.pagamentoMongoRepository.save(new PagamentoEntity(encontrado));
    }
}

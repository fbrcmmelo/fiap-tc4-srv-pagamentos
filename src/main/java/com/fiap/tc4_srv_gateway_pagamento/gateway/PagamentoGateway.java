package com.fiap.tc4_srv_gateway_pagamento.gateway;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamento;

public interface PagamentoGateway {
    SolicitacaoPagamento solicitarPagamento(SolicitacaoPagamento solicitacao);
    SolicitacaoPagamento buscarSolicitao(String solicitacaoId);
    void atualizarStatusPagamento(SolicitacaoPagamento pagamento);
}

package com.fiap.tc4_srv_gateway_pagamento.domain;


import java.time.Instant;

public record SolicitacaoPagamentoOut(
        String solicitacaoId,
        StatusPagamento statusPagamento,
        String valor,
        String numeroCartao,
        Instant criadoEm,
        Instant atualizadoEm) {

    public SolicitacaoPagamentoOut(String solicitacaoId, StatusPagamento statusPagamento) {
        this(solicitacaoId, statusPagamento, null, null, null, null);
    }
}

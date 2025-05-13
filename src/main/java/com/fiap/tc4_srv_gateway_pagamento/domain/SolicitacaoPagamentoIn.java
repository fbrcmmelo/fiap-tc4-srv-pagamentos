package com.fiap.tc4_srv_gateway_pagamento.domain;

public record SolicitacaoPagamentoIn(
        String valor,
        String numeroCartao
) {}

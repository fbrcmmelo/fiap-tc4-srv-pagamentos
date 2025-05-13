package com.fiap.tc4_srv_gateway_pagamento.usecase;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoOut;

public interface IBuscarSolicitacaoUseCase {
    SolicitacaoPagamentoOut buscarSolicitacaoPor(String solicitacaoId);
}

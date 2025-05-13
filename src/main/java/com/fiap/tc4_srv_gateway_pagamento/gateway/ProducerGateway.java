package com.fiap.tc4_srv_gateway_pagamento.gateway;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoOut;

public interface ProducerGateway {
    void notificarSolicitacaoAtualizada(SolicitacaoPagamentoOut solicitacaoPagamento);
}

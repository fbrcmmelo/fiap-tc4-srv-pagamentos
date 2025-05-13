package com.fiap.tc4_srv_gateway_pagamento.usecase;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoIn;
import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoOut;

public interface ISolicitarPagamentoUseCase {

    SolicitacaoPagamentoOut solicitar(SolicitacaoPagamentoIn solicitacaoPagamentoIn);
}

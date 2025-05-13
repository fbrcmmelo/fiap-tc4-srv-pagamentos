package com.fiap.tc4_srv_gateway_pagamento.usecase;

import com.fiap.tc4_srv_gateway_pagamento.domain.StatusPagamento;
import com.fiap.tc4_srv_gateway_pagamento.gateway.PagamentoGateway;
import com.fiap.tc4_srv_gateway_pagamento.gateway.ProducerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class AtualizarPagamentoUseCase implements IAtualizarPagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;
    private final ProducerGateway producerGateway;
    private Random random = new Random();

    @Override
    public void atualizarStatus(String solicitacaoId) {
        var solicitacaoPagamento = this.pagamentoGateway.buscarSolicitao(solicitacaoId);
        StatusPagamento[] values = StatusPagamento.values();

        int randomIndex = random.nextInt(values.length);
        StatusPagamento status = values[randomIndex];
        solicitacaoPagamento.atualizarStatus(status);

        this.pagamentoGateway.atualizarStatusPagamento(solicitacaoPagamento);
        this.producerGateway.notificarSolicitacaoAtualizada(solicitacaoPagamento.toSolicitacaoPagamentoOut());
    }
}

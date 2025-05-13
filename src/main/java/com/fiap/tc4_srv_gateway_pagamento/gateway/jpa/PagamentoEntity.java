package com.fiap.tc4_srv_gateway_pagamento.gateway.jpa;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamento;
import com.fiap.tc4_srv_gateway_pagamento.domain.StatusPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document("pagamento")
@NoArgsConstructor
public class PagamentoEntity {

    @Id
    private String solicitacaoId;
    private String valor;
    private String numeroCartao;
    private StatusPagamento statusPagamento;
    private Instant criadoEm;
    private Instant atualizadoEm;

    public PagamentoEntity(SolicitacaoPagamento solicitacaoPagamento) {
        this.solicitacaoId = solicitacaoPagamento.getSolicitacaoId();
        this.valor = solicitacaoPagamento.getValor();
        this.numeroCartao = solicitacaoPagamento.getNumeroCartao();
        this.statusPagamento = solicitacaoPagamento.getStatus();
        this.criadoEm = solicitacaoPagamento.getCriadoEm();
        this.atualizadoEm = solicitacaoPagamento.getAtualizadoEm();
    }

    public SolicitacaoPagamento toSolicitacaoPagamento() {
        return new SolicitacaoPagamento(this);
    }

}

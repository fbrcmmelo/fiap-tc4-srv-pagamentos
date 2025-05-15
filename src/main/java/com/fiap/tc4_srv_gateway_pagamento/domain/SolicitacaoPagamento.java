package com.fiap.tc4_srv_gateway_pagamento.domain;

import com.fiap.tc4_srv_gateway_pagamento.gateway.jpa.PagamentoEntity;
import lombok.Getter;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

@Getter
public class SolicitacaoPagamento {

    private String solicitacaoId;
    private StatusPagamento status;
    private String valor;
    private String numeroCartao;
    private Instant criadoEm;
    private Instant atualizadoEm;

    public SolicitacaoPagamento(String valor, String numeroCartao) {
        final var valorParam = Optional.ofNullable(valor).orElse("0");
        final var numeroCartaoParam = Optional.ofNullable(numeroCartao).orElse("0");

        if (valorParam.isBlank() || numeroCartaoParam.isBlank()) {
            throw new IllegalArgumentException("Valor e número do cartão não podem ser nulos ou vazios");
        }

        this.numeroCartao = numeroCartao;
        this.valor = valor;
        this.status = StatusPagamento.ABERTO;
        this.criadoEm = Instant.now();
    }

    public SolicitacaoPagamento(PagamentoEntity pagamentoEntity) {
        this.solicitacaoId = pagamentoEntity.getSolicitacaoId();
        this.valor = pagamentoEntity.getValor();
        this.numeroCartao = pagamentoEntity.getNumeroCartao();
        this.status = pagamentoEntity.getStatusPagamento();
        this.criadoEm = pagamentoEntity.getCriadoEm();
        this.atualizadoEm = pagamentoEntity.getAtualizadoEm();
    }

    public void atualizarStatus(StatusPagamento status) {
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo");
        }

        if (Arrays.asList(StatusPagamento.FECHADO_SEM_CREDITO,
                StatusPagamento.FECHADO_COM_SUCESSO,
                StatusPagamento.FECHADO_SEM_ESTOQUE).contains(this.status)) {
            throw new IllegalArgumentException("Status não pode ser alterarado");
        }

        this.status = status;
        this.atualizadoEm = Instant.now();
    }

    public SolicitacaoPagamentoOut toSolicitacaoPagamentoOut() {
        return new SolicitacaoPagamentoOut(this.solicitacaoId, this.status);
    }
}

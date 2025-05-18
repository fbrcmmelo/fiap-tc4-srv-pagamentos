package com.fiap.tc4_srv_gateway_pagamento.domain;

import com.fiap.tc4_srv_gateway_pagamento.gateway.jpa.PagamentoEntity;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SolicitacaoPagamentoTest {

    @Test
    void shouldCreateWithValidParams() {
        SolicitacaoPagamento solicitacao = new SolicitacaoPagamento("100", "1234567890123456");
        assertThat(solicitacao.getValor()).isEqualTo("100");
        assertThat(solicitacao.getNumeroCartao()).isEqualTo("1234567890123456");
        assertThat(solicitacao.getStatus()).isEqualTo(StatusPagamento.ABERTO);
        assertThat(solicitacao.getCriadoEm()).isNotNull();
    }

    @Test
    void shouldThrowIfValorOrNumeroCartaoIsBlank() {
        assertThatThrownBy(() -> new SolicitacaoPagamento("", "1234"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new SolicitacaoPagamento("100", ""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldUpdateStatus() {
        SolicitacaoPagamento solicitacao = new SolicitacaoPagamento("100", "1234");
        solicitacao.atualizarStatus(StatusPagamento.FECHADO_COM_SUCESSO);
        assertThat(solicitacao.getStatus()).isEqualTo(StatusPagamento.FECHADO_COM_SUCESSO);
        assertThat(solicitacao.getAtualizadoEm()).isNotNull();
    }

    @Test
    void shouldThrowIfStatusIsNull() {
        SolicitacaoPagamento solicitacao = new SolicitacaoPagamento("100", "1234");
        assertThatThrownBy(() -> solicitacao.atualizarStatus(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldThrowIfStatusIsClosed() {
        SolicitacaoPagamento solicitacao = new SolicitacaoPagamento("100", "1234");
        solicitacao.atualizarStatus(StatusPagamento.FECHADO_COM_SUCESSO);
        assertThatThrownBy(() -> solicitacao.atualizarStatus(StatusPagamento.ABERTO))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldCreateFromPagamentoEntity() {
        PagamentoEntity entity = new PagamentoEntity();
        entity.setSolicitacaoId("id1");
        entity.setValor("200");
        entity.setNumeroCartao("9999");
        entity.setStatusPagamento(StatusPagamento.ABERTO);
        entity.setCriadoEm(Instant.now());
        entity.setAtualizadoEm(Instant.now());

        SolicitacaoPagamento solicitacao = new SolicitacaoPagamento(entity);
        assertThat(solicitacao.getSolicitacaoId()).isEqualTo("id1");
        assertThat(solicitacao.getValor()).isEqualTo("200");
        assertThat(solicitacao.getNumeroCartao()).isEqualTo("9999");
        assertThat(solicitacao.getStatus()).isEqualTo(StatusPagamento.ABERTO);
        assertThat(solicitacao.getCriadoEm()).isNotNull();
        assertThat(solicitacao.getAtualizadoEm()).isNotNull();
    }

    @Test
    void shouldConvertToSolicitacaoPagamentoOut() {
        SolicitacaoPagamento solicitacao = new SolicitacaoPagamento("100", "1234");
        solicitacao.atualizarStatus(StatusPagamento.FECHADO_COM_SUCESSO);
        SolicitacaoPagamentoOut out = solicitacao.toSolicitacaoPagamentoOut();
        assertThat(out.solicitacaoId()).isNull(); // not set in constructor
        assertThat(out.statusPagamento()).isEqualTo(StatusPagamento.FECHADO_COM_SUCESSO);
    }
}

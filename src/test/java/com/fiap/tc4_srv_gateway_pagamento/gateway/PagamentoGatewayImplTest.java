package com.fiap.tc4_srv_gateway_pagamento.gateway;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamento;
import com.fiap.tc4_srv_gateway_pagamento.domain.StatusPagamento;
import com.fiap.tc4_srv_gateway_pagamento.gateway.jpa.PagamentoEntity;
import com.fiap.tc4_srv_gateway_pagamento.gateway.jpa.PagamentoMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PagamentoGatewayImplTest {

    private PagamentoMongoRepository repository;
    private PagamentoGatewayImpl gateway;

    @BeforeEach
    void setUp() {
        repository = mock(PagamentoMongoRepository.class);
        gateway = new PagamentoGatewayImpl(repository);
    }

    @Test
    void shouldSavePagamento() {
        SolicitacaoPagamento solicitacaoPagamento = new SolicitacaoPagamento("valor", "id1");
        PagamentoEntity pagamentoEntity = new PagamentoEntity(solicitacaoPagamento);
        pagamentoEntity.setSolicitacaoId("id1");

        when(repository.save(any())).thenReturn(pagamentoEntity);

        SolicitacaoPagamento saved = gateway.solicitarPagamento(solicitacaoPagamento);

        assertThat(saved.getSolicitacaoId()).isEqualTo("id1");
        verify(repository).save(any());
    }

    @Test
    void shouldFindBySolicitacaoId() {
        PagamentoEntity entity = new PagamentoEntity();
        entity.setSolicitacaoId("id2");
        when(repository.findById("id2")).thenReturn(Optional.of(entity));

        SolicitacaoPagamento result = gateway.buscarSolicitao("id2");

        assertThat(result).isNotNull();
        assertThat(result.getSolicitacaoId()).isEqualTo("id2");
        verify(repository).findById("id2");
    }

    @Test
    void shouldUpdateStatus() {
        PagamentoEntity entity = new PagamentoEntity();
        entity.setSolicitacaoId("id3");
        entity.setStatusPagamento(StatusPagamento.ABERTO);
        entity.setAtualizadoEm(Instant.now());

        SolicitacaoPagamento pagamento = new SolicitacaoPagamento(entity);

        when(repository.findById("id3")).thenReturn(Optional.of(entity));
        when(repository.save(any())).thenReturn(entity);

        gateway.atualizarStatusPagamento(pagamento);

        verify(repository).save(any());
    }
}

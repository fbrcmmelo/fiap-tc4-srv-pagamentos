package com.fiap.tc4_srv_gateway_pagamento.controller;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoIn;
import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoOut;
import com.fiap.tc4_srv_gateway_pagamento.usecase.IAtualizarPagamentoUseCase;
import com.fiap.tc4_srv_gateway_pagamento.usecase.IBuscarSolicitacaoUseCase;
import com.fiap.tc4_srv_gateway_pagamento.usecase.ISolicitarPagamentoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pagamento")
public class PagamentoController {

    private final ISolicitarPagamentoUseCase solicitarPagamentoUseCase;
    private final IAtualizarPagamentoUseCase atualizarPagamentoUseCase;
    private final IBuscarSolicitacaoUseCase buscarSolitacaoUseCase;

    @PostMapping
    public ResponseEntity<SolicitacaoPagamentoOut> solicitar(@RequestBody SolicitacaoPagamentoIn input) {
        var solicitacao = this.solicitarPagamentoUseCase.solicitar(input);
        return ResponseEntity.ok(solicitacao);
    }

    @PostMapping("/{solicitacaoId}/atualizar")
    public ResponseEntity<Void> atualizar(@PathVariable String solicitacaoId) {
        this.atualizarPagamentoUseCase.atualizarStatus(solicitacaoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{solicitacaoId}")
    public ResponseEntity<SolicitacaoPagamentoOut> getSolicitacaoById(@PathVariable String solicitacaoId) {
        var solicitacao = this.buscarSolitacaoUseCase.buscarSolicitacaoPor(solicitacaoId);
        return ResponseEntity.ok(solicitacao);
    }

}

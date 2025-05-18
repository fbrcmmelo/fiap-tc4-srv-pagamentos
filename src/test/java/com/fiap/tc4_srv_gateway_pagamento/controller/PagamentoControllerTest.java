package com.fiap.tc4_srv_gateway_pagamento.controller;

import com.fiap.tc4_srv_gateway_pagamento.domain.SolicitacaoPagamentoOut;
import com.fiap.tc4_srv_gateway_pagamento.domain.StatusPagamento;
import com.fiap.tc4_srv_gateway_pagamento.usecase.IAtualizarPagamentoUseCase;
import com.fiap.tc4_srv_gateway_pagamento.usecase.IBuscarSolicitacaoUseCase;
import com.fiap.tc4_srv_gateway_pagamento.usecase.ISolicitarPagamentoUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PagamentoControllerTest {

    @Mock
    private ISolicitarPagamentoUseCase solicitarPagamentoUseCase;

    @Mock
    private IAtualizarPagamentoUseCase atualizarPagamentoUseCase;

    @Mock
    private IBuscarSolicitacaoUseCase buscarSolitacaoUseCase;

    @InjectMocks
    private PagamentoController pagamentoController;

    private MockMvc mockMvc;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        this.openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pagamentoController).build();
    }

    @AfterEach
    void tearDown()
            throws Exception {
        this.openMocks.close();
    }

    @Test
    void testSolicitar()
            throws Exception {
        SolicitacaoPagamentoOut out = new SolicitacaoPagamentoOut("solicitacaoId", StatusPagamento.ABERTO);
        Mockito.when(solicitarPagamentoUseCase.solicitar(any())).thenReturn(out);

        MvcResult result = mockMvc.perform(post("/pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"someField\":\"someValue\"}"))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertThat(response).contains("solicitacaoId");
        assertThat(response).contains("ABERTO");
    }

    @Test
    void testAtualizar()
            throws Exception {
        MvcResult result = mockMvc.perform(post("/pagamento/123/atualizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEmpty();
        Mockito.verify(atualizarPagamentoUseCase).atualizarStatus("123");
    }

    @Test
    void testGetSolicitacaoById()
            throws Exception {
        SolicitacaoPagamentoOut out = new SolicitacaoPagamentoOut("id", StatusPagamento.FECHADO_COM_SUCESSO);
        Mockito.when(buscarSolitacaoUseCase.buscarSolicitacaoPor("123")).thenReturn(out);

        MvcResult result = mockMvc.perform(get("/pagamento/123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertThat(response).contains("id");
        assertThat(response).contains("FECHADO_COM_SUCESSO");
    }
}

package com.fiap.tc4_srv_gateway_pagamento.gateway.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoMongoRepository extends MongoRepository<PagamentoEntity, String> {
}

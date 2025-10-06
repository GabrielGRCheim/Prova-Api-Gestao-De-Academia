package senai.gabriel.demo.dto;

import java.time.LocalDate;

import senai.gabriel.demo.entity.Pagamento.formaPagamento;

public record PagamentoDTO(long id, String nome_aluno, LocalDate data_pagemento, Double valor_pago, String status, formaPagamento forma_pagamento){}

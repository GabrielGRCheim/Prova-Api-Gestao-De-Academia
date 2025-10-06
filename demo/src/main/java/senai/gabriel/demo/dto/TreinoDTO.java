package senai.gabriel.demo.dto;

import senai.gabriel.demo.entity.Treino.nivelTreino;

public record TreinoDTO(long id, String nome, String descricao, nivelTreino nivel) {

}

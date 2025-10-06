package senai.gabriel.demo.dto;

import senai.gabriel.demo.entity.Treino.nivelTreino;

public record CriarTreinoDTO(String nome, String descricao, nivelTreino nivel) {

}

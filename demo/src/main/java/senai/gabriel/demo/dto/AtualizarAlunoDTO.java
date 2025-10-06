package senai.gabriel.demo.dto;

import java.time.LocalDate;

public record AtualizarAlunoDTO(String nome, LocalDate data_nascimento, Long plano_id) {

}

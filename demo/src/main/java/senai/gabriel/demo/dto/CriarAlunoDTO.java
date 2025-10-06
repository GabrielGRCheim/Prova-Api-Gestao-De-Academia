package senai.gabriel.demo.dto;

import java.time.LocalDate;

public record CriarAlunoDTO(String nome, String cpf, LocalDate data_nascimento, Long plano_id) {

}

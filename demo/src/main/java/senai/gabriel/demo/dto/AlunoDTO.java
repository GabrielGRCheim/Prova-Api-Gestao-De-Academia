package senai.gabriel.demo.dto;

import java.time.LocalDate;

public record AlunoDTO(long id, String nome, String cpf, LocalDate data_nascimento, boolean ativo, Long plano_id) {
}

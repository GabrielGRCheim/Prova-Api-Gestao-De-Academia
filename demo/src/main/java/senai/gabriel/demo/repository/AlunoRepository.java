package senai.gabriel.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import senai.gabriel.demo.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
    Optional<Aluno> findByCpf(String cpf);

}

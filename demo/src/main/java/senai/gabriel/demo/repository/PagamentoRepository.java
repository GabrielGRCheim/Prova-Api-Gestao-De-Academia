package senai.gabriel.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import senai.gabriel.demo.entity.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByAlunoId(Long id);

}

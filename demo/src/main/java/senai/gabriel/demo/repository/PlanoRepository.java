package senai.gabriel.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import senai.gabriel.demo.entity.Plano;

public interface PlanoRepository extends JpaRepository<Plano, Long>{
    
}

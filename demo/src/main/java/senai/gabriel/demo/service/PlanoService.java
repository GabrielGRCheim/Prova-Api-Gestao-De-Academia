package senai.gabriel.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import senai.gabriel.demo.dto.CriarPlanoDTO;
import senai.gabriel.demo.dto.PlanoDTO;
import senai.gabriel.demo.entity.Plano;
import senai.gabriel.demo.repository.PlanoRepository;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    @Transactional
    public PlanoDTO criarPlano(CriarPlanoDTO planoDTO) {
        Plano plano = new Plano();
        plano.setNome(planoDTO.nome());
        plano.setValor_mensal(planoDTO.valor_mensal());

        Plano planoSalvo = planoRepository.save(plano);
        return toDTO(planoSalvo);
    }

    @Transactional
    public PlanoDTO buscarPlanoPorId(Long id) {
        Plano plano = findById(id);
        return toDTO(plano);
    }

    @Transactional
    public List<PlanoDTO> listarPlanos() {
        return planoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PlanoDTO atualizarPlano(Long id, CriarPlanoDTO planoDTO) {
        Plano plano = findById(id);

        plano.setNome(planoDTO.nome());
        plano.setValor_mensal(planoDTO.valor_mensal());

        Plano planoAtualizado = planoRepository.save(plano);
        return toDTO(planoAtualizado);
    }

    @Transactional
    public void deletarPlano(Long id) {
        if (!planoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado com id: " + id);
        }
        planoRepository.deleteById(id);
    }

    private Plano findById(Long id) {
        if (!planoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado com id: " + id);
        }
        return planoRepository.getReferenceById(id);
    }

    private PlanoDTO toDTO(Plano plano) {
        return new PlanoDTO(plano.getId(), plano.getNome(), plano.getValor_mensal());
    }
}

package senai.gabriel.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import senai.gabriel.demo.dto.CriarTreinoDTO;
import senai.gabriel.demo.dto.TreinoDTO;
import senai.gabriel.demo.entity.Treino;
import senai.gabriel.demo.repository.TreinoRepository;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;

    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
    }

    @Transactional
    public TreinoDTO criarTreino(CriarTreinoDTO treinoDTO) {
        Treino treino = new Treino();
        treino.setNome(treinoDTO.nome());
        treino.setDescricao(treinoDTO.descricao());
        treino.setNivel(treinoDTO.nivel());

        Treino treinoSalvo = treinoRepository.save(treino);
        return toDTO(treinoSalvo);
    }

    @Transactional
    public TreinoDTO buscarTreinoPorId(Long id) {
        Treino treino = findTreinoById(id);
        return toDTO(treino);
    }

    @Transactional
    public List<TreinoDTO> listarTreinos() {
        if (treinoRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há treinos salvos!");
        }
        return treinoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TreinoDTO atualizarTreino(Long id, CriarTreinoDTO treinoDTO) {
        Treino treino = findTreinoById(id);
        treino.setNome(treinoDTO.nome());
        treino.setDescricao(treinoDTO.descricao());
        treino.setNivel(treinoDTO.nivel());

        Treino treinoAtualizado = treinoRepository.save(treino);
        return toDTO(treinoAtualizado);
    }

    @Transactional
    public void deletarTreino(Long id) {
        Treino treino = findTreinoById(id);

        if (!treino.getAlunos().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Não é possível excluir o treino, pois ele está associado a " + treino.getAlunos().size()
                            + " aluno(s).");
        }

        treinoRepository.delete(treino);
    }

    private Treino findTreinoById(Long id) {
        if (!treinoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Não foi possivel encontrar o Treino pelo ID: " + id);
        }

        return treinoRepository.getReferenceById(id);
    }

    private TreinoDTO toDTO(Treino treino) {
        return new TreinoDTO(treino.getId(), treino.getNome(), treino.getDescricao(), treino.getNivel());
    }
}

package senai.gabriel.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import senai.gabriel.demo.dto.AlunoDTO;
import senai.gabriel.demo.dto.AtualizarAlunoDTO;
import senai.gabriel.demo.dto.CriarAlunoDTO;
import senai.gabriel.demo.dto.VincularAlunoTreinoDTO;
import senai.gabriel.demo.entity.Aluno;
import senai.gabriel.demo.entity.Treino;
import senai.gabriel.demo.repository.AlunoRepository;
import senai.gabriel.demo.repository.PagamentoRepository;
import senai.gabriel.demo.repository.PlanoRepository;
import senai.gabriel.demo.repository.TreinoRepository;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PlanoRepository planoRepository;
    private final TreinoRepository treinoRepository;

    public AlunoService(AlunoRepository alunoRepository, PlanoRepository planoRepository,
            TreinoRepository treinoRepository, PagamentoRepository pagamentoRepository) {
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
        this.treinoRepository = treinoRepository;
    }

    @Transactional
    public AlunoDTO criarAluno(CriarAlunoDTO alunoDTO) throws Exception {
        if (!(alunoDTO.cpf().length() == 11)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O CPF: " + alunoDTO.cpf() + " é invalido digite novamente com apenas números!");
        }
        if (alunoRepository.findByCpf(alunoDTO.cpf()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O CPF: " + alunoDTO.cpf() + " ja esta cadastrado");
        }

        if (!planoRepository.findById(alunoDTO.plano_id()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "O Plano " + alunoDTO.plano_id() + " não foi encontrado");
        }
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.nome());
        aluno.setCpf(alunoDTO.cpf());
        aluno.setData_nascimento(alunoDTO.data_nascimento());
        aluno.setPlano(planoRepository.getReferenceById(alunoDTO.plano_id()));
        aluno.setAtivo(true);

        Aluno alunoSalvo = alunoRepository.save(aluno);
        return toDTO(alunoSalvo);
    }

    @Transactional()
    public AlunoDTO buscarAlunoPorId(Long id) {
        if (!alunoRepository.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado pelo ID: " + id);
        }
        Aluno aluno = alunoRepository.getReferenceById(id);
        return toDTO(aluno);
    }

    @Transactional
    public List<AlunoDTO> listarAlunos() {
        if (alunoRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há alunos registrados!");
        }
        return alunoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AlunoDTO atualizarAluno(Long id, AtualizarAlunoDTO alunoDTO) {
        Aluno aluno = findById(id);
        if (!planoRepository.findById(alunoDTO.plano_id()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "O Plano " + alunoDTO.plano_id() + " não foi encontrado");
        }

        aluno.setNome(alunoDTO.nome());
        aluno.setData_nascimento(alunoDTO.data_nascimento());
        aluno.setPlano(planoRepository.getReferenceById(alunoDTO.plano_id()));

        Aluno alunoAtualizado = alunoRepository.save(aluno);
        return toDTO(alunoAtualizado);
    }

    @Transactional
    public void inativarAluno(Long id) {
        Aluno aluno = findById(id);
        aluno.setAtivo(false);
        alunoRepository.save(aluno);
    }

    @Transactional
    public void ativarAluno(Long id) {
        Aluno aluno = findById(id);
        aluno.setAtivo(true);
        alunoRepository.save(aluno);
    }

    @Transactional
    public void vincularTreino(VincularAlunoTreinoDTO dto) {
        Aluno aluno = findById(dto.aluno_id());
        if (!treinoRepository.findById(dto.treino_id()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Treino não encotrado pelo ID: " + dto.treino_id());
        }
        Treino treino = treinoRepository.getReferenceById(dto.treino_id());

        aluno.getTreinos().add(treino);
        alunoRepository.save(aluno);
    }

    private Aluno findById(Long id) {
        if (!alunoRepository.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado pelo ID: " + id);
        }
        return alunoRepository.getReferenceById(id);
    }

    private AlunoDTO toDTO(Aluno aluno) {
        Long planoId = (aluno.getPlano() != null) ? aluno.getPlano().getId() : null;

        return new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getData_nascimento(),
                aluno.isAtivo(),
                planoId);
    }
}

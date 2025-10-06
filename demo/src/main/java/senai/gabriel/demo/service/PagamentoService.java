package senai.gabriel.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import senai.gabriel.demo.dto.AtualizarPagamentoDTO;
import senai.gabriel.demo.dto.PagamentoDTO;
import senai.gabriel.demo.entity.Aluno;
import senai.gabriel.demo.entity.Pagamento;
import senai.gabriel.demo.repository.AlunoRepository;
import senai.gabriel.demo.repository.PagamentoRepository;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final AlunoRepository alunoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, AlunoRepository alunoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public PagamentoDTO registrarPagamento(Long aluno_id) {

        Aluno aluno = findAlunoById(aluno_id);

        if (aluno.getPlano() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O aluno: " + aluno_id + ", não possui um plano ativo");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setAluno(aluno);
        pagamento.setData_pagemento(LocalDate.now());
        pagamento.setValor_pago(aluno.getPlano().getValor_mensal());
        pagamento.setForma_pagamento(Pagamento.formaPagamento.DINHEIRO);
        pagamento.setStatus("PENDENTE");

        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
        return toDTO(pagamentoSalvo);
    }

    public PagamentoDTO atraso(Long id) {
        Pagamento pagamento = findPagamentoById(id);
        if (pagamento.getStatus().contains("PAGO")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A cobrança não pode ser alterada para ATRASADA pois já esta paga! ID: " + id);
        }
        pagamento.setStatus("ATRASADO");
        pagamentoRepository.save(pagamento);
        return toDTO(pagamento);
    }

    @Transactional
    public PagamentoDTO pagarCobraca(Long id, AtualizarPagamentoDTO pagamentoDTO) {
        Pagamento pagamento = findPagamentoById(id);
        pagamento.setForma_pagamento(pagamentoDTO.forma_pagamento());
        pagamento.setStatus("PAGO");
        pagamentoRepository.save(pagamento);
        return toDTO(pagamento);

    }

    @Transactional
    public List<PagamentoDTO> listarPagamentosPorAluno(Long alunoId) {
        // garante que o aluno existe
        findAlunoById(alunoId);

        // busca pagamentos do aluno
        return pagamentoRepository.findByAlunoId(alunoId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Pagamento findPagamentoById(Long id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cobraça não foi encontrada pelo ID: " + id);
        }

        return pagamentoRepository.getReferenceById(id);
    }

    private Aluno findAlunoById(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aluno não encontrado pelo ID: " + id));
    }

    private PagamentoDTO toDTO(Pagamento pagamento) {
        return new PagamentoDTO(
                pagamento.getId(),
                pagamento.getAluno().getNome(),
                pagamento.getData_pagemento(),
                pagamento.getValor_pago(),
                pagamento.getStatus(),
                pagamento.getForma_pagamento());
    }
}

package senai.gabriel.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import senai.gabriel.demo.dto.AtualizarPagamentoDTO;
import senai.gabriel.demo.dto.PagamentoDTO;
import senai.gabriel.demo.service.PagamentoService;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/aluno/{alunoId}")
    public ResponseEntity<PagamentoDTO> registrarPagamento(@PathVariable Long alunoId) {
        PagamentoDTO novoPagamento = pagamentoService.registrarPagamento(alunoId);
        return new ResponseEntity<>(novoPagamento, HttpStatus.CREATED);
    }

    @PostMapping("/cobraca/{id}")
    public ResponseEntity<PagamentoDTO> pagarCobraca(@PathVariable Long id,
            @RequestBody AtualizarPagamentoDTO pagamentoDTO) {
        PagamentoDTO novoPagamento = pagamentoService.pagarCobraca(id, pagamentoDTO);
        return new ResponseEntity<>(novoPagamento, HttpStatus.CREATED);
    }

    @PostMapping("/atraso/{id}")
    public ResponseEntity<PagamentoDTO> atraso(@PathVariable Long id) {
        PagamentoDTO novoPagamento = pagamentoService.atraso(id);
        return new ResponseEntity<>(novoPagamento, HttpStatus.CREATED);
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<PagamentoDTO>> listarPagamentosPorAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok(pagamentoService.listarPagamentosPorAluno(alunoId));
    }

}

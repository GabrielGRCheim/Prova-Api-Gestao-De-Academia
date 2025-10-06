package senai.gabriel.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import senai.gabriel.demo.dto.AlunoDTO;
import senai.gabriel.demo.dto.AtualizarAlunoDTO;
import senai.gabriel.demo.dto.CriarAlunoDTO;
import senai.gabriel.demo.dto.VincularAlunoTreinoDTO;
import senai.gabriel.demo.service.AlunoService;

@RestController
@RequestMapping("/api/v1/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> criarAluno(@RequestBody CriarAlunoDTO alunoDTO) throws Exception {
        AlunoDTO novoAluno = alunoService.criarAluno(alunoDTO);
        return new ResponseEntity<>(novoAluno, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarAlunoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.buscarAlunoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listarAlunos() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable Long id, @RequestBody AtualizarAlunoDTO alunoDTO) {
        return ResponseEntity.ok(alunoService.atualizarAluno(id, alunoDTO));
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarAluno(@PathVariable Long id) {
        alunoService.inativarAluno(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Void> ativarAluno(@PathVariable Long id) {
        alunoService.ativarAluno(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/vincular-treino")
    public ResponseEntity<Void> vincularTreino(@RequestBody VincularAlunoTreinoDTO dto) {
        alunoService.vincularTreino(dto);
        return ResponseEntity.ok().build();
    }
}

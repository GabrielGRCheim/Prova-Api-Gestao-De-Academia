package senai.gabriel.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import senai.gabriel.demo.dto.CriarTreinoDTO;
import senai.gabriel.demo.dto.TreinoDTO;
import senai.gabriel.demo.service.TreinoService;

@RestController
@RequestMapping("/api/v1/treinos")
public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @PostMapping
    public ResponseEntity<TreinoDTO> criarTreino(@RequestBody CriarTreinoDTO treinoDTO) {
        TreinoDTO novoTreino = treinoService.criarTreino(treinoDTO);
        return new ResponseEntity<>(novoTreino, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinoDTO> buscarTreinoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(treinoService.buscarTreinoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<TreinoDTO>> listarTreinos() {
        return ResponseEntity.ok(treinoService.listarTreinos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinoDTO> atualizarTreino(@PathVariable Long id, @RequestBody CriarTreinoDTO treinoDTO) {
        return ResponseEntity.ok(treinoService.atualizarTreino(id, treinoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTreino(@PathVariable Long id) {
        treinoService.deletarTreino(id);
        return ResponseEntity.noContent().build();
    }

}

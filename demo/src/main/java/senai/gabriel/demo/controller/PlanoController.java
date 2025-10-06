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

import senai.gabriel.demo.dto.CriarPlanoDTO;
import senai.gabriel.demo.dto.PlanoDTO;
import senai.gabriel.demo.service.PlanoService;

@RestController
@RequestMapping("/api/v1/planos")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @PostMapping
    public ResponseEntity<PlanoDTO> criarPlano(@RequestBody CriarPlanoDTO planoDTO) {
        PlanoDTO novoPlano = planoService.criarPlano(planoDTO);
        return new ResponseEntity<>(novoPlano, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> buscarPlanoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(planoService.buscarPlanoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PlanoDTO>> listarPlanos() {
        return ResponseEntity.ok(planoService.listarPlanos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoDTO> atualizarPlano(@PathVariable Long id, @RequestBody CriarPlanoDTO planoDTO) {
        return ResponseEntity.ok(planoService.atualizarPlano(id, planoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable Long id) {
        planoService.deletarPlano(id);
        return ResponseEntity.noContent().build();
    }
}

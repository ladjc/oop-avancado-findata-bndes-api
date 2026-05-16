package br.com.fatec.findatabndesapi.controller;


import br.com.fatec.findatabndesapi.model.ResumoCargaDTO;
import br.com.fatec.findatabndesapi.service.OperacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/operacoes")
public class OperacaoController {
    private OperacaoService operacaoService;

    public OperacaoController(OperacaoService operacaoService) {
        this.operacaoService = operacaoService;
    }

    @PostMapping("/carga")
    public ResponseEntity<String> cargaCsv(@RequestParam("arquivo") MultipartFile arquivo) {
        operacaoService.carregarCsv(arquivo);

        return ResponseEntity.status(HttpStatus.CREATED).body(arquivo.getOriginalFilename() + " carregado com sucesso.");
    }

    @GetMapping("/bases")
    public List<ResumoCargaDTO> listarBases() {

        return operacaoService.listarBases();
    }

    @DeleteMapping("/carga/{nCarga}")
    public ResponseEntity<String> deletarCarga(
            @PathVariable Long nCarga
    ) {

        return operacaoService.deletarCarga(nCarga);
    }

}

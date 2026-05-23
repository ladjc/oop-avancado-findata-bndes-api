package br.com.fatec.findatabndesapi.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.fatec.findatabndesapi.model.Operacao;
import br.com.fatec.findatabndesapi.model.ResumoCargaDTO;
import br.com.fatec.findatabndesapi.service.OperacaoService;

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
    @GetMapping
    public List<Operacao> listarOperacoes(
            @RequestParam(required = false) Long base
    ) {
        return operacaoService.listarOperacoes(base);
    }
    }

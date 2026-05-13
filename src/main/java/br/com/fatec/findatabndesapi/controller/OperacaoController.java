package br.com.fatec.findatabndesapi.controller;


import br.com.fatec.findatabndesapi.service.OperacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

}

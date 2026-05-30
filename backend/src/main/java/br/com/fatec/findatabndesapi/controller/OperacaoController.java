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

        try{
            operacaoService.carregarCsv(arquivo);

            return ResponseEntity.status(HttpStatus.CREATED).body(arquivo.getOriginalFilename() + " carregado com sucesso.");
        }catch(Exception ex){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao carregar CSV:\n" + ex.getMessage());
        }


    }

    @GetMapping("/bases")
    public ResponseEntity<List<ResumoCargaDTO>> listarBases() {

        List<ResumoCargaDTO> cargas = operacaoService.listarBases();

        if (cargas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cargas);
    }

    @DeleteMapping("/carga/{nCarga}")
    public ResponseEntity<String> deletarCarga(
            @PathVariable Long nCarga
    ) {

        boolean deletado = operacaoService.deletarCarga(nCarga);

        if (!deletado) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Número da base inválida.");
        }

        return ResponseEntity.ok(
                "Registros da base " + nCarga + " deletados."
        );
    }

    @GetMapping
    public ResponseEntity<List<Operacao>> listarOperacoes(
            @RequestParam(required = false) Long base
    ) {

        List<Operacao> operacoes =
                operacaoService.listarOperacoes(base);

        if (operacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(operacoes);
    }
}

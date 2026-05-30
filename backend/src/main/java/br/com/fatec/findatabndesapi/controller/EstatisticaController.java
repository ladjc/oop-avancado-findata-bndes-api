package br.com.fatec.findatabndesapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.findatabndesapi.model.MediaJurosSetorDTO;
import br.com.fatec.findatabndesapi.model.MediaJurosUfDTO;
import br.com.fatec.findatabndesapi.model.MediaValorPorteDTO;
import br.com.fatec.findatabndesapi.model.MediaValorSetorDTO;
import br.com.fatec.findatabndesapi.model.MediaValorUfDTO;
import br.com.fatec.findatabndesapi.model.ParticipacaoUfDTO;
import br.com.fatec.findatabndesapi.model.QuantidadeOperacoesSituacaoDTO;
import br.com.fatec.findatabndesapi.model.QuantidadeOperacoesUfDTO;
import br.com.fatec.findatabndesapi.model.SomaValorPorteDTO;
import br.com.fatec.findatabndesapi.model.SomaValorUfDTO;
import br.com.fatec.findatabndesapi.model.TopClienteDTO;
import br.com.fatec.findatabndesapi.service.EstatisticaService;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {

    private final EstatisticaService service;

    public EstatisticaController(
            EstatisticaService service
    ) {
        this.service = service;
    }

    @GetMapping("/media-valor-uf")
    public ResponseEntity<List<MediaValorUfDTO>> mediaValorUf(
            @RequestParam("base") Long base
    ) {
        return ResponseEntity.ok(service.mediaValorUf(base));
    }

    @GetMapping("/media-valor-porte")
    public ResponseEntity<List<MediaValorPorteDTO>> mediaValorPorte(
            @RequestParam("base") Long base
    ) {
        return ResponseEntity.ok(service.mediaValorPorte(base));
    }

    @GetMapping("/soma-valor-uf")
    public ResponseEntity<List<SomaValorUfDTO>> somaValorUf(
            @RequestParam("base") Long base
    ) {
        return ResponseEntity.ok(service.somaValorUf(base));
    }

    @GetMapping("/soma-valor-porte")
    public ResponseEntity<List<SomaValorPorteDTO>> somaValorPorte(
            @RequestParam("base") Long base
    ) {
        return ResponseEntity.ok(service.somaValorPorte(base));
    }

    @GetMapping("/quantidade-total-operacoes")
    public ResponseEntity<Long> quantidadeTotalOperacoes(
            @RequestParam("base") Long base
    ) {
        return ResponseEntity.ok(
                service.quantidadeTotalOperacoes(base)
        );
    }

    @GetMapping("/quantidade-operacoes-uf")
    public ResponseEntity<List<QuantidadeOperacoesUfDTO>>
    quantidadeOperacoesPorUf(
            @RequestParam("base") Long base
    ) {
        return ResponseEntity.ok(
                service.quantidadeOperacoesPorUf(base)
        );
    }

    @GetMapping("/quantidade-operacoes-situacao")
    public ResponseEntity<List<QuantidadeOperacoesSituacaoDTO>>
    quantidadeOperacoesPorSituacao(
            @RequestParam("base") Long base
    ) {
        return ResponseEntity.ok(
                service.quantidadeOperacoesPorSituacao(base)
        );
    }

    @GetMapping("/media-juros-geral")
    public ResponseEntity<Double> mediaJurosGeral(
            @RequestParam("base") Long base
    ) {
        return ResponseEntity.ok(
                service.mediaJurosGeral(base)
        );
    }

    @GetMapping("/media-juros-uf")
    public ResponseEntity<List<MediaJurosUfDTO>>
    mediaJurosPorUf(
            @RequestParam("base") Long base
    ) {
        return ResponseEntity.ok(
                service.mediaJurosPorUf(base)
        );
    }

    @GetMapping("/media-juros-setor")
    public ResponseEntity<List<MediaJurosSetorDTO>>
    mediaJurosPorSetor(
            @RequestParam("base") Long base
    ) {
        return ResponseEntity.ok(
                service.mediaJurosPorSetor(base)
        );
    }

    @GetMapping("/media-valor-setor")
    public ResponseEntity<List<MediaValorSetorDTO>>
    mediaValorPorSetor(
            @RequestParam("base") Long base
    ) {
        return ResponseEntity.ok(
                service.mediaValorPorSetor(base)
        );
    }

    @GetMapping("/top-clientes")
    public ResponseEntity<List<TopClienteDTO>> topClientes(
            @RequestParam("base") Long base
    ) {
        return ResponseEntity.ok(
                service.topClientes(base)
        );
    }

    @GetMapping("/participacao-uf")
    public ResponseEntity<List<ParticipacaoUfDTO>>
    participacaoPorUf(
            @RequestParam("base") Long base
    ) {
        return ResponseEntity.ok(
                service.participacaoPorUf(base)
        );
    }
}
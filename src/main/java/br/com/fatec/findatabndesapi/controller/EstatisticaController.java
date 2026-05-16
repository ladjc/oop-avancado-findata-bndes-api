package br.com.fatec.findatabndesapi.controller;

import br.com.fatec.findatabndesapi.model.*;
import br.com.fatec.findatabndesapi.service.EstatisticaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<MediaValorUfDTO> mediaValorUf(
            @RequestParam(required = false)
            Long base
    ) {

        return service.mediaValorUf(base);
    }

    @GetMapping("/media-valor-porte")
    public List<MediaValorPorteDTO> mediaValorPorte(
            @RequestParam(required = false)
            Long base
    ) {

        return service.mediaValorPorte(base);
    }

    @GetMapping("/soma-valor-uf")
    public List<SomaValorUfDTO> somaValorUf(
            @RequestParam(required = false)
            Long base
    ) {

        return service.somaValorUf(base);
    }

    @GetMapping("/soma-valor-porte")
    public List<SomaValorPorteDTO> somaValorPorte(
            @RequestParam(required = false)
            Long base
    ) {

        return service.somaValorPorte(base);
    }

    @GetMapping("/quantidade-total-operacoes")
    public Long quantidadeTotalOperacoes(
            @RequestParam(required = false)
            Long base
    ) {

        return service.quantidadeTotalOperacoes(base);
    }

    @GetMapping("/quantidade-operacoes-uf")
    public List<QuantidadeOperacoesUfDTO>
    quantidadeOperacoesPorUf(
            @RequestParam(required = false)
            Long base
    ) {

        return service.quantidadeOperacoesPorUf(base);
    }

    @GetMapping("/quantidade-operacoes-situacao")
    public List<QuantidadeOperacoesSituacaoDTO>
    quantidadeOperacoesPorSituacao(
            @RequestParam(required = false)
            Long base
    ) {

        return service.quantidadeOperacoesPorSituacao(base);
    }

    @GetMapping("/media-juros-geral")
    public Double mediaJurosGeral(
            @RequestParam(required = false)
            Long base
    ) {

        return service.mediaJurosGeral(base);
    }

    @GetMapping("/media-juros-uf")
    public List<MediaJurosUfDTO> mediaJurosPorUf(
            @RequestParam(required = false)
            Long base
    ) {

        return service.mediaJurosPorUf(base);
    }

    @GetMapping("/media-juros-setor")
    public List<MediaJurosSetorDTO> mediaJurosPorSetor(
            @RequestParam(required = false)
            Long base
    ) {

        return service.mediaJurosPorSetor(base);
    }

    @GetMapping("/media-valor-setor")
    public List<MediaValorSetorDTO> mediaValorPorSetor(
            @RequestParam(required = false)
            Long base
    ) {

        return service.mediaValorPorSetor(base);
    }

    @GetMapping("/top-clientes")
    public List<TopClienteDTO> topClientes(
            @RequestParam(required = false)
            Long base
    ) {

        return service.topClientes(base);
    }

    @GetMapping("/participacao-uf")
    public List<ParticipacaoUfDTO> participacaoPorUf(
            @RequestParam(required = false)
            Long base
    ) {

        return service.participacaoPorUf(base);
    }
}
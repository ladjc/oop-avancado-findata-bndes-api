package br.com.fatec.findatabndesapi.controller;

import br.com.fatec.findatabndesapi.model.*;
import br.com.fatec.findatabndesapi.service.EstatisticaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<MediaValorUfDTO> mediaValorUf() {

        return service.mediaValorUf();
    }

    @GetMapping("/media-valor-porte")
    public List<MediaValorPorteDTO> mediaValorPorte() {

        return service.mediaValorPorte();
    }

    @GetMapping("/soma-valor-uf")
    public List<SomaValorUfDTO> somaValorUf() {

        return service.somaValorUf();
    }

    @GetMapping("/soma-valor-porte")
    public List<SomaValorPorteDTO> somaValorPorte() {

        return service.somaValorPorte();
    }

    @GetMapping("/quantidade-total-operacoes")
    public Long quantidadeTotalOperacoes() {

        return service.quantidadeTotalOperacoes();
    }

    @GetMapping("/quantidade-operacoes-uf")
    public List<QuantidadeOperacoesUfDTO>
    quantidadeOperacoesPorUf() {

        return service.quantidadeOperacoesPorUf();
    }

    @GetMapping("/quantidade-operacoes-situacao")
    public List<QuantidadeOperacoesSituacaoDTO>
    quantidadeOperacoesPorSituacao() {

        return service.quantidadeOperacoesPorSituacao();
    }

    @GetMapping("/media-juros-geral")
    public Double mediaJurosGeral() {

        return service.mediaJurosGeral();
    }

    @GetMapping("/media-juros-uf")
    public List<MediaJurosUfDTO> mediaJurosPorUf() {

        return service.mediaJurosPorUf();
    }

    @GetMapping("/media-juros-setor")
    public List<MediaJurosSetorDTO> mediaJurosPorSetor() {

        return service.mediaJurosPorSetor();
    }

    @GetMapping("/media-valor-setor")
    public List<MediaValorSetorDTO> mediaValorPorSetor() {

        return service.mediaValorPorSetor();
    }

    @GetMapping("/top-clientes")
    public List<TopClienteDTO> topClientes() {

        return service.topClientes();
    }

    @GetMapping("/participacao-uf")
    public List<ParticipacaoUfDTO> participacaoPorUf() {

        return service.participacaoPorUf();
    }


}
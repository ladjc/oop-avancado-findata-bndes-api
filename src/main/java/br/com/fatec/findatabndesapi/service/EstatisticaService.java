package br.com.fatec.findatabndesapi.service;

import br.com.fatec.findatabndesapi.model.*;
import br.com.fatec.findatabndesapi.repository.OperacaoRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class EstatisticaService {

    private final OperacaoRepository repository;

    public EstatisticaService(
            OperacaoRepository repository
    ) {

        this.repository = repository;
    }

    public List<MediaValorUfDTO> mediaValorUf() {

        return repository.mediaValorPorUf();
    }

    public List<MediaValorPorteDTO> mediaValorPorte() {

        return repository.mediaValorPorPorte();
    }

    public List<SomaValorUfDTO> somaValorUf() {

        return repository.somaValorPorUf();
    }

    public List<SomaValorPorteDTO> somaValorPorte() {

        return repository.somaValorPorPorte();
    }

    public Long quantidadeTotalOperacoes() {

        return repository.quantidadeTotalOperacoes();
    }

    public List<QuantidadeOperacoesUfDTO> quantidadeOperacoesPorUf() {

        return repository.quantidadeOperacoesPorUf();
    }

    public List<QuantidadeOperacoesSituacaoDTO>
    quantidadeOperacoesPorSituacao() {

        return repository.quantidadeOperacoesPorSituacao();
    }
    public Double mediaJurosGeral() {

        return BigDecimal
                .valueOf(repository.mediaJurosGeral())
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public List<MediaJurosUfDTO> mediaJurosPorUf() {

        return repository.mediaJurosPorUf();
    }

    public List<MediaJurosSetorDTO> mediaJurosPorSetor() {

        return repository.mediaJurosPorSetor();
    }

    public List<MediaValorSetorDTO> mediaValorPorSetor() {

        return repository.mediaValorPorSetor();
    }

    public List<TopClienteDTO> topClientes() {

        return repository.topClientes();
    }

    public List<ParticipacaoUfDTO> participacaoPorUf() {

        return repository.participacaoPorUf();
    }


}
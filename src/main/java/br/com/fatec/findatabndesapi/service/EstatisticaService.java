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

    public List<MediaValorUfDTO> mediaValorUf(
            Long base
    ) {

        if (base == null) {

            return repository.mediaValorPorUf();
        }

        return repository.mediaValorPorUf(base);
    }

    public List<MediaValorPorteDTO> mediaValorPorte(
            Long base
    ) {

        if (base == null) {

            return repository.mediaValorPorPorte();
        }

        return repository.mediaValorPorPorte(base);
    }

    public List<SomaValorUfDTO> somaValorUf(
            Long base
    ) {

        if (base == null) {

            return repository.somaValorPorUf();
        }

        return repository.somaValorPorUf(base);
    }

    public List<SomaValorPorteDTO> somaValorPorte(
            Long base
    ) {

        if (base == null) {

            return repository.somaValorPorPorte();
        }

        return repository.somaValorPorPorte(base);
    }

    public Long quantidadeTotalOperacoes(
            Long base
    ) {

        if (base == null) {

            return repository.quantidadeTotalOperacoes();
        }

        return repository.quantidadeTotalOperacoes(base);
    }

    public List<QuantidadeOperacoesUfDTO>
    quantidadeOperacoesPorUf(
            Long base
    ) {

        if (base == null) {

            return repository.quantidadeOperacoesPorUf();
        }

        return repository.quantidadeOperacoesPorUf(base);
    }

    public List<QuantidadeOperacoesSituacaoDTO>
    quantidadeOperacoesPorSituacao(
            Long base
    ) {

        if (base == null) {

            return repository.quantidadeOperacoesPorSituacao();
        }

        return repository.quantidadeOperacoesPorSituacao(base);
    }

    public Double mediaJurosGeral(
            Long base
    ) {

        Double valor;

        if (base == null) {

            valor = repository.mediaJurosGeral();

        } else {

            valor = repository.mediaJurosGeral(base);
        }

        return BigDecimal
                .valueOf(valor)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public List<MediaJurosUfDTO> mediaJurosPorUf(
            Long base
    ) {

        if (base == null) {

            return repository.mediaJurosPorUf();
        }

        return repository.mediaJurosPorUf(base);
    }

    public List<MediaJurosSetorDTO> mediaJurosPorSetor(
            Long base
    ) {

        if (base == null) {

            return repository.mediaJurosPorSetor();
        }

        return repository.mediaJurosPorSetor(base);
    }

    public List<MediaValorSetorDTO> mediaValorPorSetor(
            Long base
    ) {

        if (base == null) {

            return repository.mediaValorPorSetor();
        }

        return repository.mediaValorPorSetor(base);
    }

    public List<TopClienteDTO> topClientes(
            Long base
    ) {

        if (base == null) {

            return repository.topClientes();
        }

        return repository.topClientes(base);
    }

    public List<ParticipacaoUfDTO> participacaoPorUf(
            Long base
    ) {

        if (base == null) {

            return repository.participacaoPorUf();
        }

        return repository.participacaoPorUf(base);
    }
}
package br.com.fatec.findatabndesapi.model;

import java.time.LocalDateTime;

public class ResumoCargaDTO {

    private Long nCarga;

    private LocalDateTime dataCarga;

    private Long quantidadeOperacoes;

    public ResumoCargaDTO(
            Long nCarga,
            LocalDateTime dataCarga,
            Long quantidadeOperacoes
    ) {

        this.nCarga = nCarga;
        this.dataCarga = dataCarga;
        this.quantidadeOperacoes = quantidadeOperacoes;
    }

    public Long getNCarga() {
        return nCarga;
    }

    public void setNCarga(Long nCarga) {
        this.nCarga = nCarga;
    }

    public LocalDateTime getDataCarga() {
        return dataCarga;
    }

    public void setDataCarga(LocalDateTime dataCarga) {
        this.dataCarga = dataCarga;
    }

    public Long getQuantidadeOperacoes() {
        return quantidadeOperacoes;
    }

    public void setQuantidadeOperacoes(
            Long quantidadeOperacoes
    ) {

        this.quantidadeOperacoes = quantidadeOperacoes;
    }
}
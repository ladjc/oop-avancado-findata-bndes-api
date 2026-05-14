package br.com.fatec.findatabndesapi.model;

public class QuantidadeOperacoesSituacaoDTO {

    private String situacaoDaOperacao;

    private Long quantidade;

    public QuantidadeOperacoesSituacaoDTO(
            String situacaoDaOperacao,
            Long quantidade
    ) {

        this.situacaoDaOperacao = situacaoDaOperacao;
        this.quantidade = quantidade;
    }

    public String getSituacaoDaOperacao() {
        return situacaoDaOperacao;
    }

    public void setSituacaoDaOperacao(
            String situacaoDaOperacao
    ) {

        this.situacaoDaOperacao = situacaoDaOperacao;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
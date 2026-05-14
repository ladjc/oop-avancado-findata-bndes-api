package br.com.fatec.findatabndesapi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SomaValorUfDTO {

    private String uf;

    private BigDecimal somaValor;

    public SomaValorUfDTO(
            String uf,
            BigDecimal somaValor
    ) {

        this.uf = uf;

        this.somaValor = somaValor
                .setScale(2, RoundingMode.HALF_UP);
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public BigDecimal getSomaValor() {
        return somaValor;
    }

    public void setSomaValor(BigDecimal somaValor) {
        this.somaValor = somaValor;
    }
}
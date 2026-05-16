package br.com.fatec.findatabndesapi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ParticipacaoUfDTO {

    private String uf;

    private Double percentual;

    public ParticipacaoUfDTO(
            String uf,
            Double percentual
    ) {

        this.uf = uf;

        this.percentual = BigDecimal
                .valueOf(percentual)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }
}
package br.com.fatec.findatabndesapi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MediaValorUfDTO {

    private String uf;

    private Double mediaValor;

    public MediaValorUfDTO(
            String uf,
            Double mediaValor
    ) {

        this.uf = uf;

        this.mediaValor = BigDecimal.valueOf(mediaValor)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Double getMediaValor() {
        return mediaValor;
    }

    public void setMediaValor(Double mediaValor) {
        this.mediaValor = mediaValor;
    }
}
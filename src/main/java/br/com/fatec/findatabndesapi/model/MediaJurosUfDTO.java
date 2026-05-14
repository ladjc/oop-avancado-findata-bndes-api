package br.com.fatec.findatabndesapi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MediaJurosUfDTO {

    private String uf;

    private Double mediaJuros;

    public MediaJurosUfDTO(
            String uf,
            Double mediaJuros
    ) {

        this.uf = uf;

        this.mediaJuros = BigDecimal
                .valueOf(mediaJuros)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Double getMediaJuros() {
        return mediaJuros;
    }

    public void setMediaJuros(Double mediaJuros) {
        this.mediaJuros = mediaJuros;
    }
}
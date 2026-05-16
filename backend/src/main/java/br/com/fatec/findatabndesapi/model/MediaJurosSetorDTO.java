package br.com.fatec.findatabndesapi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MediaJurosSetorDTO {

    private String setorCnae;

    private Double mediaJuros;

    public MediaJurosSetorDTO(
            String setorCnae,
            Double mediaJuros
    ) {

        this.setorCnae = setorCnae;

        this.mediaJuros = BigDecimal
                .valueOf(mediaJuros)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public String getSetorCnae() {
        return setorCnae;
    }

    public void setSetorCnae(String setorCnae) {
        this.setorCnae = setorCnae;
    }

    public Double getMediaJuros() {
        return mediaJuros;
    }

    public void setMediaJuros(Double mediaJuros) {
        this.mediaJuros = mediaJuros;
    }
}
package br.com.fatec.findatabndesapi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MediaValorSetorDTO {

    private String setorCnae;

    private Double mediaValor;

    public MediaValorSetorDTO(
            String setorCnae,
            Double mediaValor
    ) {

        this.setorCnae = setorCnae;

        this.mediaValor = BigDecimal
                .valueOf(mediaValor)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public String getSetorCnae() {
        return setorCnae;
    }

    public void setSetorCnae(String setorCnae) {
        this.setorCnae = setorCnae;
    }

    public Double getMediaValor() {
        return mediaValor;
    }

    public void setMediaValor(Double mediaValor) {
        this.mediaValor = mediaValor;
    }
}
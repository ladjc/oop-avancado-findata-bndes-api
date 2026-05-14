package br.com.fatec.findatabndesapi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MediaValorPorteDTO {

    private String porteDoCliente;

    private Double mediaValor;

    public MediaValorPorteDTO(
            String porteDoCliente,
            Double mediaValor
    ) {

        this.porteDoCliente = porteDoCliente;

        this.mediaValor = BigDecimal.valueOf(mediaValor)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public String getPorteDoCliente() {
        return porteDoCliente;
    }

    public void setPorteDoCliente(String porteDoCliente) {
        this.porteDoCliente = porteDoCliente;
    }

    public Double getMediaValor() {
        return mediaValor;
    }

    public void setMediaValor(Double mediaValor) {
        this.mediaValor = mediaValor;
    }
}
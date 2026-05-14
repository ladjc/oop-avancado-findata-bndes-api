package br.com.fatec.findatabndesapi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SomaValorPorteDTO {

    private String porteDoCliente;

    private BigDecimal somaValor;

    public SomaValorPorteDTO(
            String porteDoCliente,
            BigDecimal somaValor
    ) {

        this.porteDoCliente = porteDoCliente;

        this.somaValor = somaValor
                .setScale(2, RoundingMode.HALF_UP);
    }

    public String getPorteDoCliente() {
        return porteDoCliente;
    }

    public void setPorteDoCliente(String porteDoCliente) {
        this.porteDoCliente = porteDoCliente;
    }

    public BigDecimal getSomaValor() {
        return somaValor;
    }

    public void setSomaValor(BigDecimal somaValor) {
        this.somaValor = somaValor;
    }
}
package br.com.fatec.findatabndesapi.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TopClienteDTO {

    private String cliente;

    private BigDecimal totalDesembolsado;

    public TopClienteDTO(
            String cliente,
            BigDecimal totalDesembolsado
    ) {

        this.cliente = cliente;

        this.totalDesembolsado = totalDesembolsado
                .setScale(2, RoundingMode.HALF_UP);
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTotalDesembolsado() {
        return totalDesembolsado;
    }

    public void setTotalDesembolsado(
            BigDecimal totalDesembolsado
    ) {

        this.totalDesembolsado = totalDesembolsado;
    }
}
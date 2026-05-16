package br.com.fatec.findatabndesapi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "operacoes")
public class Operacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente")
    private String cliente;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "uf")
    private String uf;

    @Column(name = "porte_do_cliente")
    private String porteDoCliente;

    @Column(name = "natureza_do_cliente")
    private String naturezaDoCliente;

    @Column(name = "setor_cnae")
    private String setorCnae;

    @Column(name = "data_da_contratacao")
    private LocalDate dataDaContratacao;

    @Column(name = "valor_desembolsado_reais")
    private BigDecimal valorDesembolsadoReais;

    @Column(name = "juros")
    private Double juros;

    @Column(name = "prazo_amortizacao_meses")
    private Integer prazoAmortizacaoMeses;

    @Column(name = "situacao_da_operacao")
    private String situacaoDaOperacao;

    @Column(name = "n_carga")
    private Long numeroCarga;

    @Column(name = "data_carga")
    private LocalDateTime dataCarga;

    public Operacao() {
    }


    public Long getNumeroCarga() {
        return numeroCarga;
    }

    public void setNumeroCarga(Long nCarga) {
        this.numeroCarga = nCarga;
    }

    public LocalDateTime getDataCarga() {
        return dataCarga;
    }

    public void setDataCarga(LocalDateTime dataCarga) {
        this.dataCarga = dataCarga;
    }


    public Long getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getPorteDoCliente() {
        return porteDoCliente;
    }

    public void setPorteDoCliente(String porteDoCliente) {
        this.porteDoCliente = porteDoCliente;
    }

    public String getNaturezaDoCliente() {
        return naturezaDoCliente;
    }

    public void setNaturezaDoCliente(String naturezaDoCliente) {
        this.naturezaDoCliente = naturezaDoCliente;
    }

    public String getSetorCnae() {
        return setorCnae;
    }

    public void setSetorCnae(String setorCnae) {
        this.setorCnae = setorCnae;
    }

    public LocalDate getDataDaContratacao() {
        return dataDaContratacao;
    }

    public void setDataDaContratacao(LocalDate dataDaContratacao) {
        this.dataDaContratacao = dataDaContratacao;
    }

    public BigDecimal getValorDesembolsadoReais() {
        return valorDesembolsadoReais;
    }

    public void setValorDesembolsadoReais(BigDecimal valorDesembolsadoReais) {
        this.valorDesembolsadoReais = valorDesembolsadoReais;
    }

    public Double getJuros() {
        return juros;
    }

    public void setJuros(Double juros) {
        this.juros = juros;
    }

    public Integer getPrazoAmortizacaoMeses() {
        return prazoAmortizacaoMeses;
    }

    public void setPrazoAmortizacaoMeses(Integer prazoAmortizacaoMeses) {
        this.prazoAmortizacaoMeses = prazoAmortizacaoMeses;
    }

    public String getSituacaoDaOperacao() {
        return situacaoDaOperacao;
    }

    public void setSituacaoDaOperacao(String situacaoDaOperacao) {
        this.situacaoDaOperacao = situacaoDaOperacao;
    }
}
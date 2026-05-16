package br.com.fatec.findatabndesapi.repository;

import br.com.fatec.findatabndesapi.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OperacaoRepository
        extends JpaRepository<Operacao, Long> {

    // =========================
    // MÉDIA VALOR UF
    // =========================

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.MediaValorUfDTO(
            o.uf,
            AVG(o.valorDesembolsadoReais)
        )
        FROM Operacao o
        GROUP BY o.uf
        ORDER BY o.uf
    """)
    List<MediaValorUfDTO> mediaValorPorUf();

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.MediaValorUfDTO(
            o.uf,
            AVG(o.valorDesembolsadoReais)
        )
        FROM Operacao o
        WHERE o.numeroCarga = :base
        GROUP BY o.uf
        ORDER BY o.uf
    """)
    List<MediaValorUfDTO> mediaValorPorUf(
            @Param("base") Long base
    );

    // =========================
    // MÉDIA VALOR PORTE
    // =========================

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.MediaValorPorteDTO(
            o.porteDoCliente,
            AVG(o.valorDesembolsadoReais)
        )
        FROM Operacao o
        GROUP BY o.porteDoCliente
        ORDER BY o.porteDoCliente
    """)
    List<MediaValorPorteDTO> mediaValorPorPorte();

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.MediaValorPorteDTO(
            o.porteDoCliente,
            AVG(o.valorDesembolsadoReais)
        )
        FROM Operacao o
        WHERE o.numeroCarga = :base
        GROUP BY o.porteDoCliente
        ORDER BY o.porteDoCliente
    """)
    List<MediaValorPorteDTO> mediaValorPorPorte(
            @Param("base") Long base
    );

    // =========================
    // SOMA VALOR UF
    // =========================

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.SomaValorUfDTO(
            o.uf,
            SUM(o.valorDesembolsadoReais)
        )
        FROM Operacao o
        GROUP BY o.uf
        ORDER BY o.uf
    """)
    List<SomaValorUfDTO> somaValorPorUf();

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.SomaValorUfDTO(
            o.uf,
            SUM(o.valorDesembolsadoReais)
        )
        FROM Operacao o
        WHERE o.numeroCarga = :base
        GROUP BY o.uf
        ORDER BY o.uf
    """)
    List<SomaValorUfDTO> somaValorPorUf(
            @Param("base") Long base
    );

    // =========================
    // SOMA VALOR PORTE
    // =========================

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.SomaValorPorteDTO(
            o.porteDoCliente,
            SUM(o.valorDesembolsadoReais)
        )
        FROM Operacao o
        GROUP BY o.porteDoCliente
        ORDER BY o.porteDoCliente
    """)
    List<SomaValorPorteDTO> somaValorPorPorte();

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.SomaValorPorteDTO(
            o.porteDoCliente,
            SUM(o.valorDesembolsadoReais)
        )
        FROM Operacao o
        WHERE o.numeroCarga = :base
        GROUP BY o.porteDoCliente
        ORDER BY o.porteDoCliente
    """)
    List<SomaValorPorteDTO> somaValorPorPorte(
            @Param("base") Long base
    );

    // =========================
    // QUANTIDADE TOTAL
    // =========================

    @Query("""
        SELECT COUNT(o)
        FROM Operacao o
    """)
    Long quantidadeTotalOperacoes();

    @Query("""
        SELECT COUNT(o)
        FROM Operacao o
        WHERE o.numeroCarga = :base
    """)
    Long quantidadeTotalOperacoes(
            @Param("base") Long base
    );

    // =========================
    // QUANTIDADE POR UF
    // =========================

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.QuantidadeOperacoesUfDTO(
            o.uf,
            COUNT(o)
        )
        FROM Operacao o
        GROUP BY o.uf
        ORDER BY o.uf
    """)
    List<QuantidadeOperacoesUfDTO> quantidadeOperacoesPorUf();

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.QuantidadeOperacoesUfDTO(
            o.uf,
            COUNT(o)
        )
        FROM Operacao o
        WHERE o.numeroCarga = :base
        GROUP BY o.uf
        ORDER BY o.uf
    """)
    List<QuantidadeOperacoesUfDTO> quantidadeOperacoesPorUf(
            @Param("base") Long base
    );

    // =========================
    // QUANTIDADE POR SITUAÇÃO
    // =========================

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.QuantidadeOperacoesSituacaoDTO(
            o.situacaoDaOperacao,
            COUNT(o)
        )
        FROM Operacao o
        GROUP BY o.situacaoDaOperacao
        ORDER BY o.situacaoDaOperacao
    """)
    List<QuantidadeOperacoesSituacaoDTO> quantidadeOperacoesPorSituacao();

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.QuantidadeOperacoesSituacaoDTO(
            o.situacaoDaOperacao,
            COUNT(o)
        )
        FROM Operacao o
        WHERE o.numeroCarga = :base
        GROUP BY o.situacaoDaOperacao
        ORDER BY o.situacaoDaOperacao
    """)
    List<QuantidadeOperacoesSituacaoDTO> quantidadeOperacoesPorSituacao(
            @Param("base") Long base
    );

    // =========================
    // MÉDIA JUROS GERAL
    // =========================

    @Query("""
        SELECT AVG(o.juros)
        FROM Operacao o
    """)
    Double mediaJurosGeral();

    @Query("""
        SELECT AVG(o.juros)
        FROM Operacao o
        WHERE o.numeroCarga = :base
    """)
    Double mediaJurosGeral(
            @Param("base") Long base
    );

    // =========================
    // MÉDIA JUROS UF
    // =========================

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.MediaJurosUfDTO(
            o.uf,
            AVG(o.juros)
        )
        FROM Operacao o
        GROUP BY o.uf
        ORDER BY o.uf
    """)
    List<MediaJurosUfDTO> mediaJurosPorUf();

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.MediaJurosUfDTO(
            o.uf,
            AVG(o.juros)
        )
        FROM Operacao o
        WHERE o.numeroCarga = :base
        GROUP BY o.uf
        ORDER BY o.uf
    """)
    List<MediaJurosUfDTO> mediaJurosPorUf(
            @Param("base") Long base
    );

    // =========================
    // MÉDIA JUROS SETOR
    // =========================

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.MediaJurosSetorDTO(
            o.setorCnae,
            AVG(o.juros)
        )
        FROM Operacao o
        GROUP BY o.setorCnae
        ORDER BY o.setorCnae
    """)
    List<MediaJurosSetorDTO> mediaJurosPorSetor();

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.MediaJurosSetorDTO(
            o.setorCnae,
            AVG(o.juros)
        )
        FROM Operacao o
        WHERE o.numeroCarga = :base
        GROUP BY o.setorCnae
        ORDER BY o.setorCnae
    """)
    List<MediaJurosSetorDTO> mediaJurosPorSetor(
            @Param("base") Long base
    );

    // =========================
    // MÉDIA VALOR SETOR
    // =========================

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.MediaValorSetorDTO(
            o.setorCnae,
            AVG(o.valorDesembolsadoReais)
        )
        FROM Operacao o
        GROUP BY o.setorCnae
        ORDER BY o.setorCnae
    """)
    List<MediaValorSetorDTO> mediaValorPorSetor();

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.MediaValorSetorDTO(
            o.setorCnae,
            AVG(o.valorDesembolsadoReais)
        )
        FROM Operacao o
        WHERE o.numeroCarga = :base
        GROUP BY o.setorCnae
        ORDER BY o.setorCnae
    """)
    List<MediaValorSetorDTO> mediaValorPorSetor(
            @Param("base") Long base
    );

    // =========================
    // TOP CLIENTES
    // =========================

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.TopClienteDTO(
            o.cliente,
            SUM(o.valorDesembolsadoReais)
        )
        FROM Operacao o
        GROUP BY o.cliente
        ORDER BY SUM(o.valorDesembolsadoReais) DESC
    """)
    List<TopClienteDTO> topClientes();

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.TopClienteDTO(
            o.cliente,
            SUM(o.valorDesembolsadoReais)
        )
        FROM Operacao o
        WHERE o.numeroCarga = :base
        GROUP BY o.cliente
        ORDER BY SUM(o.valorDesembolsadoReais) DESC
    """)
    List<TopClienteDTO> topClientes(
            @Param("base") Long base
    );

    // =========================
    // PARTICIPAÇÃO UF
    // =========================

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.ParticipacaoUfDTO(
            o.uf,
            (
                SUM(o.valorDesembolsadoReais) * 100.0 /
                (
                    SELECT SUM(op.valorDesembolsadoReais)
                    FROM Operacao op
                )
            )
        )
        FROM Operacao o
        GROUP BY o.uf
        ORDER BY SUM(o.valorDesembolsadoReais) DESC
    """)
    List<ParticipacaoUfDTO> participacaoPorUf();

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.ParticipacaoUfDTO(
            o.uf,
            (
                SUM(o.valorDesembolsadoReais) * 100.0 /
                (
                    SELECT SUM(op.valorDesembolsadoReais)
                    FROM Operacao op
                    WHERE op.numeroCarga = :base
                )
            )
        )
        FROM Operacao o
        WHERE o.numeroCarga = :base
        GROUP BY o.uf
        ORDER BY SUM(o.valorDesembolsadoReais) DESC
    """)
    List<ParticipacaoUfDTO> participacaoPorUf(
            @Param("base") Long base
    );

    // =========================
    // CARGAS
    // =========================

    @Query("""
        SELECT COALESCE(MAX(o.numeroCarga), 0)
        FROM Operacao o
    """)
    Long buscarUltimaCarga();

    @Query("""
        SELECT new br.com.fatec.findatabndesapi.model.ResumoCargaDTO(
            o.numeroCarga,
            MIN(o.dataCarga),
            COUNT(o)
        )
        FROM Operacao o
        GROUP BY o.numeroCarga
        ORDER BY o.numeroCarga DESC
    """)
    List<ResumoCargaDTO> listarBases();

    boolean existsByNumeroCarga(
            Long numeroCarga
    );

    @Transactional
    void deleteByNumeroCarga(
            Long numeroCarga
    );
}
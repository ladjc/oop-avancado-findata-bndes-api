package br.com.fatec.findatabndesapi.repository;

import br.com.fatec.findatabndesapi.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OperacaoRepository
        extends JpaRepository<Operacao, Long> {

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
                SELECT COUNT(o)
                FROM Operacao o
            """)
    Long quantidadeTotalOperacoes();

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
                SELECT AVG(o.juros)
                FROM Operacao o
            """)
    Double mediaJurosGeral();

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
                SELECT new br.com.fatec.findatabndesapi.model.ParticipacaoUfDTO(
                    o.uf,(
                        SUM(o.valorDesembolsadoReais) * 100.0 /
                        (SELECT SUM(op.valorDesembolsadoReais)
                         FROM Operacao op)
                    )
                )
                FROM Operacao o
                GROUP BY o.uf
                ORDER BY SUM(o.valorDesembolsadoReais) DESC
            """)
    List<ParticipacaoUfDTO> participacaoPorUf();

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
package br.com.fatec.findatabndesapi.service;

import br.com.fatec.findatabndesapi.model.Operacao;
import br.com.fatec.findatabndesapi.repository.OperacaoRepository;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OperacaoService {

    private final OperacaoRepository repository;

    public OperacaoService(OperacaoRepository repository) {
        this.repository = repository;
    }

    public void carregarCsv(MultipartFile arquivo) {

        try {

            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();

            CSVReader reader = new CSVReaderBuilder(
                    new InputStreamReader(arquivo.getInputStream(), "windows-1252")
            )
                    .withCSVParser(parser)
                    .build();

            String[] linha;

            // pula cabeçalho
            reader.readNext();

            List<Operacao> lote = new ArrayList<>();

            final int TAMANHO_LOTE = 1000;

            while ((linha = reader.readNext()) != null) {

                // ignora linha vazia
                if (linha.length == 0) {
                    continue;
                }

                // valida quantidade mínima de colunas
                if (linha.length < 11) {
                    continue;
                }

                Operacao operacao = new Operacao();

                operacao.setCliente(
                        linha[0].trim()
                );

                operacao.setCpfCnpj(
                        linha[1].trim()
                );

                operacao.setUf(
                        linha[2].trim()
                );

                operacao.setPorteDoCliente(
                        linha[3].trim()
                );

                operacao.setNaturezaDoCliente(
                        linha[4].trim()
                );

                operacao.setSetorCnae(
                        linha[5].trim()
                );

                operacao.setDataDaContratacao(
                        LocalDate.parse(
                                linha[6].trim()
                        )
                );

                // tratamento valor monetário brasileiro
                String valor = linha[7]
                        .trim()
                        .replace(".", "")
                        .replace(",", ".");

                if (valor.isEmpty()) {
                    valor = "0";
                }

                operacao.setValorDesembolsadoReais(
                        new BigDecimal(valor)
                );

                // tratamento decimal brasileiro
                String juros = linha[8]
                        .trim()
                        .replace(",", ".");

                if (juros.isEmpty()) {
                    juros = "0";
                }

                operacao.setJuros(
                        Double.parseDouble(juros)
                );

                operacao.setPrazoAmortizacaoMeses(
                        Integer.parseInt(
                                linha[9].trim()
                        )
                );

                operacao.setSituacaoDaOperacao(
                        linha[10].trim()
                );

                lote.add(operacao);

                // salva em batch
                if (lote.size() >= TAMANHO_LOTE) {

                    repository.saveAll(lote);

                    lote.clear();
                }
            }

            // salva restante
            if (!lote.isEmpty()) {

                repository.saveAll(lote);
            }

            reader.close();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao processar arquivo CSV",
                    e
            );
        }
    }
}
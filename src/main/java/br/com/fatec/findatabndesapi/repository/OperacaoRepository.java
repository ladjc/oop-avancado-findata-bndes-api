package br.com.fatec.findatabndesapi.repository;

import br.com.fatec.findatabndesapi.model.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperacaoRepository extends JpaRepository<Operacao, Long> {
}

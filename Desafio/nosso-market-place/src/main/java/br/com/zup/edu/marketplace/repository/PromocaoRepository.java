package br.com.zup.edu.marketplace.repository;

import br.com.zup.edu.marketplace.model.Promocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocaoRepository extends JpaRepository<Promocao, Long> {
}

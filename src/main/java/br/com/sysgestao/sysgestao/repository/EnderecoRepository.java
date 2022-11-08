package br.com.sysgestao.sysgestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sysgestao.sysgestao.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}

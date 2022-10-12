package br.com.sysgestao.sysgestao.repository;

import br.com.sysgestao.sysgestao.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
package br.com.sysgestao.sysgestao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.sysgestao.sysgestao.domain.Cliente;
import br.com.sysgestao.sysgestao.domain.Endereco;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired // instancia a classe quando necessário.
    private ClienteRepository clienteRepository;

    @Test
    public void testAddCliente() {
        Endereco endereco = new Endereco("Av. Goiás", "Cental", "Goiãnia", "Go", "74675000");
        Cliente cliente = new Cliente("Nelson", "07465476432", "nelson_mendes@live.com", endereco);

        Cliente saveClient = clienteRepository.save(cliente);
        assertThat(saveClient).isNotNull();
        assertThat(saveClient.getId()).isGreaterThan(0);
        assertThat(saveClient.getEndereco()).isNotNull();
        assertThat(saveClient.getEndereco().getId()).isGreaterThan(0);
    }
}

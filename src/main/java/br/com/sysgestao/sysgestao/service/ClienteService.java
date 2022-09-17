package br.com.sysgestao.sysgestao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysgestao.sysgestao.domain.Cliente;
import br.com.sysgestao.sysgestao.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).get();
    }

    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente) {
        return clienteRepository.saveAndFlush(cliente);
    }

    public void deleteClientById(Long id) {
        clienteRepository.deleteById(id);
    }
}

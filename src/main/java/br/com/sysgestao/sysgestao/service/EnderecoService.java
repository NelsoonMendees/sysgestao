package br.com.sysgestao.sysgestao.service;

import org.springframework.stereotype.Service;

import br.com.sysgestao.sysgestao.domain.Endereco;
import br.com.sysgestao.sysgestao.error.NotFoundException;
import br.com.sysgestao.sysgestao.repository.EnderecoRepository;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public void deleteById(Endereco endereco) {
        if (!enderecoRepository.existsById(endereco.getId())) {
            throw new NotFoundException("Endereço não encontrado: id= " + endereco.getId());
        }
        enderecoRepository.delete(endereco);
    }

}

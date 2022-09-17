package br.com.sysgestao.sysgestao.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sysgestao.sysgestao.domain.Cliente;
import br.com.sysgestao.sysgestao.domain.Endereco;
import br.com.sysgestao.sysgestao.service.ClienteService;

@RestController
@RequestMapping("/api/v1/Clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {

        List<Cliente> cliente = clienteService.findAll();

        if (cliente.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id) {
        Cliente cliente;

        try {
            cliente = clienteService.findById(id);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public Cliente postNewCliente(@RequestBody Cliente cliente) {
        clienteService.save(cliente);
        return cliente;
    }

    @PutMapping
    public ResponseEntity<Cliente> putCliente(@RequestBody Cliente cliente) {
        try {
            clienteService.findById(cliente.getId());
            clienteService.update(cliente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }

    @PatchMapping("/{id}/endereco")
    public ResponseEntity<Cliente> atualizarEndereco(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
        Cliente cliente;

        try {
            cliente = clienteService.findById(id);
            cliente.setEndereco(endereco);
            clienteService.update(cliente);

            return ResponseEntity.ok().body(cliente);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarClientePorId(@PathVariable("id") Long id) {

        try {
            clienteService.findById(id);
            clienteService.deleteClientById(id);
            return ResponseEntity.noContent().build();
            
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

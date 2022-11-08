package br.com.sysgestao.sysgestao.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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
import br.com.sysgestao.sysgestao.domain.model_assembler.ClienteModelAssembler;
import br.com.sysgestao.sysgestao.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler clienteModelAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> obterTodosClientes() {
        List<Cliente> clientes = clienteService.findAll();

        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<Cliente>> listaEntityModel = new ArrayList<>();

        // Para cada cliente -> EntityModel, adiciona o entitymodel dentro da
        // listaEntityModel

        listaEntityModel.addAll(clientes.stream().map(clienteModelAssembler::toModel).toList());

        CollectionModel<EntityModel<Cliente>> collectionModel;
        collectionModel = CollectionModel.of(listaEntityModel);
        collectionModel.add(linkTo(methodOn(ClienteResource.class).obterTodosClientes()).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterPorId(@PathVariable("id") Long id) {
        Cliente cliente;
        cliente = clienteService.findById(id);

        EntityModel<Cliente> entityModelCliente = clienteModelAssembler.toModel(cliente);

        return ResponseEntity.ok(entityModelCliente);
    }

    // Método HTTP PUT serve para atualizar um objeto/recurso já existente;
    // Verificar antes a existência do recurso a ser atualizado
    // PQ se não existir, o método ele vai gravar/criar um novo recurso ==> HTTP
    // POST
    @PutMapping
    public ResponseEntity<EntityModel<Cliente>> atualizarCliente(@RequestBody Cliente cliente) {
        clienteService.findById(cliente.getId());
        clienteService.update(cliente);

        EntityModel<Cliente> entityModelCliente = clienteModelAssembler.toModel(cliente);

        return ResponseEntity.ok(entityModelCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarClientePorId(@PathVariable("id") Long id) {
        clienteService.deletePorId(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EntityModel<Cliente>> saveCliente(@RequestBody @Valid Cliente cliente) {

        clienteService.save(cliente);

        URI uri = linkTo(methodOn(ClienteResource.class).obterPorId(cliente.getId())).withSelfRel().toUri();

        EntityModel<Cliente> entityModelCliente = clienteModelAssembler.toModel(cliente);

        return ResponseEntity.created(uri).body(entityModelCliente);
    }

    @PatchMapping("/{id}/endereco")
    public ResponseEntity<EntityModel<Cliente>> atualizarEnderecoDoCliente(@PathVariable("id") Long idCliente,
            Endereco endereco) {
        Cliente cliente = clienteService.updatePartial(idCliente, endereco);

        EntityModel<Cliente> entityModelCliente = clienteModelAssembler.toModel(cliente);
        return ResponseEntity.ok(entityModelCliente);

    }

}

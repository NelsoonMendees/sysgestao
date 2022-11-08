package br.com.sysgestao.sysgestao.domain.model_assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import br.com.sysgestao.sysgestao.domain.Cliente;
import br.com.sysgestao.sysgestao.resource.ClienteResource;

@Configuration
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public EntityModel<Cliente> toModel(Cliente cliente) {

        EntityModel<Cliente> entityModelCliente = EntityModel.of(cliente);

        entityModelCliente.add(linkTo(methodOn(ClienteResource.class).obterPorId(cliente.getId())).withSelfRel());

        entityModelCliente.add(linkTo(methodOn(ClienteResource.class).atualizarEnderecoDoCliente(cliente.getId(), null))
                .withRel("endereços"));

        entityModelCliente.add(
                linkTo(methodOn(ClienteResource.class).obterTodosClientes()).withRel(IanaLinkRelations.COLLECTION));

        return entityModelCliente;
    }

}

package br.com.sysgestao.sysgestao.domain;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cliente extends RepresentationModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O nome deve ser obrigatório!")
    private String nome;
    @CPF
    @NotNull(message = "O CPF deve ser obrigatório!")
    private String cpf;
    @Email
    private String email;
    @Range(min = 1, max =  100, message = "A idade deve estar entre 1 a 100 anos!")
    private Integer idade;

       // Cliente conhece endereço
    @OneToOne(cascade = CascadeType.ALL)// deleta cliente e endereço
    @JoinColumn(name = "end_id")// cria a COLUNA que relaciona o id cliente e endereço
    private Endereco endereco;

    public Cliente() {
    }


    public Cliente(String nome, String cpf, String email, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Integer getIdade() {
        return idade;
    }
    public void setIdade(Integer idade) {
        this.idade = idade;
    }


}

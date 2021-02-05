package br.com.zup.ecomerce.produto;

import javax.validation.constraints.NotBlank;

public class NovaCaracteristicasRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaProduto paraCategoria(Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }
}

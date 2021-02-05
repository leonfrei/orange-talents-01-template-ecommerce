package br.com.zup.ecomerce.produto;

import br.com.zup.ecomerce.categoria.Categoria;
import br.com.zup.ecomerce.categoria.CategoriaRepository;
import br.com.zup.ecomerce.categoria.NovaCategoriaRequest;
import br.com.zup.ecomerce.compartilhado.ExistValue;
import br.com.zup.ecomerce.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NovoProdutoRequest {

    @NotBlank
    private String nome;

    @Positive
    private BigDecimal valor;

    @Positive
    private Integer quantidadeDisponivel;

    @Size(min = 3)
    @Valid
    private List<NovaCaracteristicasRequest> caracteristicas;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @ExistValue(domainClass = Categoria.class, fieldName = "id", message = "Esta categoria não existe")
    private Long idCategoria;

    @Size(min = 1)
    private List<Foto> fotos;

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public  List<NovaCaracteristicasRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Produto paraProduto(CategoriaRepository categoriaRepository, Usuario usuario) {
        Categoria categoria = categoriaRepository.findById(idCategoria).get();
        return new Produto(nome, valor, quantidadeDisponivel, caracteristicas, descricao, categoria, usuario);
    }

    public Set<String> temCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();
        for (NovaCaracteristicasRequest caracteristica : caracteristicas){
            if (nomesIguais.add(caracteristica.getNome())){
                resultados.add(caracteristica.getNome());
            }
        }
        return resultados;
    }
}

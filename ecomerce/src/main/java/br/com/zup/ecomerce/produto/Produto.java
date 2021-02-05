package br.com.zup.ecomerce.produto;

import br.com.zup.ecomerce.categoria.Categoria;
import br.com.zup.ecomerce.categoria.NovaCategoriaRequest;
import br.com.zup.ecomerce.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal valor;

    @Min(0)
    private Integer quantidadeDisponivel;

    @OneToMany(mappedBy = "produto")
    @Size(min = 3)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @ManyToOne
    @NotNull
    private Categoria categoria;

    private LocalDateTime instanteCadastrado = LocalDateTime.now();

    @ManyToOne
    @NotNull
    private Usuario usuario;

    @OneToMany
    @Size(min = 1)
    private List<Foto> fotos;

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull @DecimalMin(value = "0.01") BigDecimal valor, @Min(0) Integer quantidadeDisponivel, @Size(min = 3) Collection<NovaCaracteristicasRequest> caracteristicas, @NotBlank @Length(max = 1000) String descricao, @NotNull Categoria categoria, @NotNull Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.paraCategoria(this)).collect(Collectors.toSet()));
        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no minimo 3 caracteristicas");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id) && nome.equals(produto.nome) && valor.equals(produto.valor) && quantidadeDisponivel.equals(produto.quantidadeDisponivel) && caracteristicas.equals(produto.caracteristicas) && descricao.equals(produto.descricao) && categoria.equals(produto.categoria) && instanteCadastrado.equals(produto.instanteCadastrado) && usuario.equals(produto.usuario) && fotos.equals(produto.fotos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, valor, quantidadeDisponivel, caracteristicas, descricao, categoria, instanteCadastrado, usuario, fotos);
    }
}

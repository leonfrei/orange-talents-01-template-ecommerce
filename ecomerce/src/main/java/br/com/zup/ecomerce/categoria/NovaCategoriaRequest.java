package br.com.zup.ecomerce.categoria;

import br.com.zup.ecomerce.compartilhado.ExistValue;
import br.com.zup.ecomerce.compartilhado.UniqueValue;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Categoria já cadastrada, podendo ter apenas uma.")
    private String nome;

    @ExistValue(domainClass = Categoria.class, fieldName = "id", message = "Esta categoria não existe")
    private Long idCategoriaMae;

    public NovaCategoriaRequest(@NotBlank String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public Optional<Long> getIdCategoriaMae() {
        return Optional.ofNullable(idCategoriaMae);
    }

    public Categoria paraCategoria(CategoriaRepository categoriaRepository){
        return getIdCategoriaMae().map(idCategoria -> new Categoria(nome, categoriaRepository.getOne(idCategoria))).orElseGet(() -> new Categoria(nome));
    }
}

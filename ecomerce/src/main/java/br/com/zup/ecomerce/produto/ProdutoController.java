package br.com.zup.ecomerce.produto;

import br.com.zup.ecomerce.categoria.CategoriaRepository;
import br.com.zup.ecomerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest, @AuthenticationPrincipal Usuario usuario){
        Produto produto = novoProdutoRequest.paraProduto(categoriaRepository, usuario);
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }

}

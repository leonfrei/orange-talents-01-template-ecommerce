package br.com.zup.ecomerce.produto;

import br.com.zup.ecomerce.usuario.NovoUsuarioRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeCaracteristicaComNomeIgualValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NovoProdutoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }
        NovoProdutoRequest novoProdutoRequest = (NovoProdutoRequest) target;
        Set<String> nomesIguais = novoProdutoRequest.temCaracteristicasIguais();
        if (!nomesIguais.isEmpty()){
            errors.rejectValue("caracteristicas", null, "Você tem caracteristicas iguais");
        }
    }
}

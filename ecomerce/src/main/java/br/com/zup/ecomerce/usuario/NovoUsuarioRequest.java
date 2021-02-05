package br.com.zup.ecomerce.usuario;

import br.com.zup.ecomerce.compartilhado.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioRequest {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login", message = "Email já cadastrado, podendo ser usado só por um usuário.")
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public NovoUsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario paraUsuario() {
        return new Usuario(login, new Senha(senha));
    }
}

package br.com.zup.ecomerce.produto;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

public class Foto {

    @URL
    @NotBlank
    private String url;

    @Deprecated
    public Foto() {
    }

    public Foto(@URL @NotBlank String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

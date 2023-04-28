package com.livraria.eaglebookstore.application;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

public class Result {

    private String mensagem;
    private boolean sucesso;

    public Result(String mensagem) {
        this.sucesso = true;
        this.mensagem = mensagem;
    }

    public Result(Set<? extends ConstraintViolation<?>> violations) {
        this.sucesso = false;
        this.mensagem = violations.stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.joining(", "));
    }

    public String getMensagem() {
        return mensagem;
    }

    public boolean isSucesso() {
        return sucesso;
    }

}

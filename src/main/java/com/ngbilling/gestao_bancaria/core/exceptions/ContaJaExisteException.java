package com.ngbilling.gestao_bancaria.core.exceptions;

public class ContaJaExisteException extends RuntimeException {

    public ContaJaExisteException(String mensagem) {
        super(mensagem);
    }
}

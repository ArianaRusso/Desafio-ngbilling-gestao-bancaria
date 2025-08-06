package com.ngbilling.gestao_bancaria.core.exceptions;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;

public class ContaJaExisteException extends RuntimeException {

    public ContaJaExisteException(String mensagem) {
    }
}

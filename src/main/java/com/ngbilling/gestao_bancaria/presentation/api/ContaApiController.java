package com.ngbilling.gestao_bancaria.presentation.api;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.infrastructure.controllers.ContaController;
import com.ngbilling.gestao_bancaria.presentation.dtos.ContaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
public class ContaApiController {

    ContaController controller;

    @PostMapping
    public ResponseEntity<ContaResponse> criarConta() {
        ContaBancaria conta = controller.criarConta();
        return ResponseEntity.status(HttpStatus.CREATED).body(new ContaResponse(conta.getNumeroConta(), conta.getSaldo()));
    }

    @GetMapping("/{numero_conta}")
    public ResponseEntity<ContaResponse> consultar(@PathVariable Integer numero_conta) {
        ContaBancaria conta = controller.consultar(numero_conta);
        if (conta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new ContaResponse(conta.getNumeroConta(), conta.getSaldo()));
    }
}

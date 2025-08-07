package com.ngbilling.gestao_bancaria.presentation.api;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.core.enums.FormaDePagamento;
import com.ngbilling.gestao_bancaria.core.exceptions.ContaNaoEncontradaException;
import com.ngbilling.gestao_bancaria.core.exceptions.SaldoInsuficienteException;
import com.ngbilling.gestao_bancaria.infrastructure.controllers.TransacaoController;
import com.ngbilling.gestao_bancaria.presentation.dtos.ContaDto;
import com.ngbilling.gestao_bancaria.presentation.dtos.TransacaoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacao")
public class TransacaoApiController {

    private final TransacaoController controller;

    @PostMapping
    public ResponseEntity<ContaDto> criarTransacao(@RequestBody TransacaoRequest request) {
        try {
            FormaDePagamento forma = FormaDePagamento.fromCodigo(request.tipoTransacao());
            ContaBancaria conta = controller.processarPagamento(request.numeroConta(), request.valor(), forma);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ContaDto(conta.getNumeroConta(), conta.getSaldo()));

        } catch (ContaNaoEncontradaException | SaldoInsuficienteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

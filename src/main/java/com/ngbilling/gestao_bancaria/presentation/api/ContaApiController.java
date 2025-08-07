package com.ngbilling.gestao_bancaria.presentation.api;

import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import com.ngbilling.gestao_bancaria.infrastructure.controllers.ContaController;
import com.ngbilling.gestao_bancaria.infrastructure.mappers.ContaMapper;
import com.ngbilling.gestao_bancaria.presentation.dtos.ContaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/conta")
public class ContaApiController {

    private final ContaController controller;
    private final ContaMapper mapper;

    @PostMapping
    public ResponseEntity<ContaDto> criarConta(@RequestBody ContaDto contaDto) {
        ContaBancaria conta = mapper.dtoToConta(contaDto);
        ContaBancaria contaSalva = controller.criarConta(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ContaDto(contaSalva.getNumeroConta(), contaSalva.getSaldo()));
    }

    @GetMapping("/{numero_conta}")
    public ResponseEntity<ContaDto> consultar(@PathVariable Integer numero_conta) {
        ContaBancaria conta = controller.consultar(numero_conta);
        if (conta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(new ContaDto(conta.getNumeroConta(), conta.getSaldo()));
    }
}

package com.ngbilling.gestao_bancaria.infrastructure.persistence;


import com.ngbilling.gestao_bancaria.core.entities.ContaBancaria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_CONTA")
public class ContaBancariaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer numeroConta;

    private BigDecimal saldo;

    public ContaBancariaEntity (ContaBancaria contaBancaria){
        this.id = contaBancaria.getId();
        this.numeroConta = contaBancaria.getNumeroConta();
        this.saldo = contaBancaria.getSaldo();
    }
}

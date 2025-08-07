package com.ngbilling.gestao_bancaria.infrastructure.config;

import com.ngbilling.gestao_bancaria.core.gateways.ContaGateway;
import com.ngbilling.gestao_bancaria.core.usecases.ConsultarContaUseCase;
import com.ngbilling.gestao_bancaria.core.usecases.CriarContaUseCase;
import com.ngbilling.gestao_bancaria.core.usecases.ProcessarTransacaoUseCase;
import com.ngbilling.gestao_bancaria.core.usecases.impl.ContaServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ContaServiceImpl contaService(ContaGateway gateway) {
        return new ContaServiceImpl(gateway);
    }

    @Bean
    public ConsultarContaUseCase consultarContaUseCase(ContaServiceImpl service) {
        return service;
    }

    @Bean
    public CriarContaUseCase criarContaUseCase(ContaServiceImpl service) {
        return service;
    }

    @Bean
    public ProcessarTransacaoUseCase processarTransacaoUseCase(ContaServiceImpl service) {
        return service;
    }
}

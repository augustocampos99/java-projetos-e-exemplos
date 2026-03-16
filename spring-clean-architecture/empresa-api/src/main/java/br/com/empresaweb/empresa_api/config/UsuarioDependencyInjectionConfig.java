package br.com.empresaweb.empresa_api.config;

import br.com.empresaweb.empresa_api.application.usecases.*;
import br.com.empresaweb.empresa_api.infra.gateways.FuncionarioEntityMapper;
import br.com.empresaweb.empresa_api.infra.gateways.FuncionarioRepositoryImpl;
import br.com.empresaweb.empresa_api.infra.persistence.FuncionarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioDependencyInjectionConfig {

    @Bean
    ListarFuncionario injectListarFuncionario(FuncionarioRepositoryImpl funcionarioRepository) {
        return new ListarFuncionario(funcionarioRepository);
    }

    @Bean
    BuscarFuncionarioPorID injectBuscarFuncionarioPorID(FuncionarioRepositoryImpl funcionarioRepository) {
        return new BuscarFuncionarioPorID(funcionarioRepository);
    }

    @Bean
    CriarFuncionario injectCriarFuncionario(FuncionarioRepositoryImpl funcionarioRepository) {
        return new CriarFuncionario(funcionarioRepository);
    }

    @Bean
    AtualizarFuncionario injectAtualizarFuncionario(FuncionarioRepositoryImpl funcionarioRepository) {
        return new AtualizarFuncionario(funcionarioRepository);
    }

    @Bean
    DeletarFuncionario injectDeletarFuncionario(FuncionarioRepositoryImpl funcionarioRepository) {
        return new DeletarFuncionario(funcionarioRepository);
    }

    @Bean
    FuncionarioRepositoryImpl criarFuncionarioRepository(FuncionarioRepository funcionarioRepository, FuncionarioEntityMapper mapper) {
        return new FuncionarioRepositoryImpl(funcionarioRepository, mapper);
    }

    @Bean
    FuncionarioEntityMapper criarMapper() {
        return new FuncionarioEntityMapper();
    }

}

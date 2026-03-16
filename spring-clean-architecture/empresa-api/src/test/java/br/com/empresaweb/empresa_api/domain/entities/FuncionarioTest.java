package br.com.empresaweb.empresa_api.domain.entities;

import br.com.empresaweb.empresa_api.domain.enums.StatusEnum;
import br.com.empresaweb.empresa_api.domain.vo.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class FuncionarioTest {

    @Test
    public void deveLancarExceptionParaCpfInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Funcionario(UUID.randomUUID(), "123456", "José Campos", "Programador", StatusEnum.TEMPORARIO)
            );
    }

    @Test
    public void deveCriarUmFuncionarioValido() {
        var funcionario = new Funcionario(UUID.randomUUID(), "43053318762", "José Campos", "Programador", StatusEnum.TEMPORARIO);
        Assertions.assertEquals("43053318762", funcionario.getCpf());
        Assertions.assertEquals("José Campos", funcionario.getNome());
        Assertions.assertEquals("Programador", funcionario.getCargo());
        Assertions.assertEquals(StatusEnum.TEMPORARIO, funcionario.getStatus());
    }

    @Test
    public void deveCriarUsuarioComFactory() {
        var factory = new FuncionarioFactory();
        var funcionario = factory.criarFuncionarioBase(UUID.randomUUID(),"43053318762", "José Campos", "Programador", StatusEnum.TEMPORARIO)
                .adicionarEndereco("04776000", "Rua fulano de taus", "25A")
                .build();

        Assertions.assertEquals("43053318762", funcionario.getCpf());
        Assertions.assertEquals("José Campos", funcionario.getNome());
        Assertions.assertEquals("Programador", funcionario.getCargo());
        Assertions.assertEquals(StatusEnum.TEMPORARIO, funcionario.getStatus());

        Assertions.assertEquals("04776000", funcionario.getEndereco().getCep());
        Assertions.assertEquals("Rua fulano de taus", funcionario.getEndereco().getLogradouro());
        Assertions.assertEquals("25A", funcionario.getEndereco().getNumero());
    }

}

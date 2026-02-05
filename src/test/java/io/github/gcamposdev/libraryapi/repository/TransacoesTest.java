package io.github.gcamposdev.libraryapi.repository;

import io.github.gcamposdev.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    @Test
    /*
    Commit -> confirmar as alterações
    Rollback -> desfazer as alterações
    */
    public void transacaoSimples(){
        transacaoService.executar();
    }

    @Test
    public void transacaoEstadoManaged(){
        transacaoService.atualizacaoSemAtualizar();
    }
}

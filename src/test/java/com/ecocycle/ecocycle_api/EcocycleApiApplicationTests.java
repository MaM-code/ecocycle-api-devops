package com.ecocycle.ecocycle_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EcocycleApiApplicationTests {
    @Test
    void applicationClassIsLoadable() {
        // Teste básico de carregamento da classe principal, sem iniciar o contexto Oracle.
        org.junit.jupiter.api.Assertions.assertNotNull(
            com.ecocycle.ecocycle_api.ecocycle_api.EcocycleApiApplication.class
        );
    }
}

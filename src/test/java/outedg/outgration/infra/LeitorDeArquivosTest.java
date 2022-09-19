package outedg.outgration.infra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeitorDeArquivosTest {
    @Test
    void deve_ler_o_arquivo() {
        var nomeDoArquivo = "src/test/java/outedg/outgration/infra/arquivo.sql";
        var leitorDeArquivo = new LeitorDeArquivos();

        var sqlDoArquivo = leitorDeArquivo.ler(nomeDoArquivo);

        Assertions.assertEquals("[Olá]", sqlDoArquivo);
    }
}

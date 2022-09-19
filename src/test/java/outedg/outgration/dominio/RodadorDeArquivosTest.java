package outedg.outgration.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

public class RodadorDeArquivosTest {
    private String sql;
    private ILeitorDeArquivos leitorDeArquivo;
    private String nomeDoArquivo;
    private JdbcTemplate jdbc;
    private Rodador rodador;

    @BeforeEach
    void init() {
        sql = "SQL";
        leitorDeArquivo = Mockito.mock(ILeitorDeArquivos.class);
        nomeDoArquivo = "nome do arquivo.sql";
        jdbc = Mockito.mock(JdbcTemplate.class);
        rodador = new Rodador(jdbc, leitorDeArquivo);
    }

    @Test
    void deve_obter_arquivo_no_disco_e_executar() {

    }

    @Test
    void deve_ler_arquivo_texto() {
        Mockito.when(leitorDeArquivo.ler(nomeDoArquivo)).thenReturn(sql);

        rodador.rodar(nomeDoArquivo);

        Mockito.verify(leitorDeArquivo, Mockito.times(1)).ler(nomeDoArquivo);
    }

    @Test
    void deve_executar_o_sql_do_conteudo_do_arquivo() {
        Mockito.when(leitorDeArquivo.ler(nomeDoArquivo)).thenReturn(sql);

        rodador.rodar(nomeDoArquivo);

        Mockito.verify(jdbc, Mockito.times(1)).execute(sql);
    }
}

package outedg.outgration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.lang.model.util.Types;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MigrateTest {

    @Captor
    ArgumentCaptor<String> captor;
    private Migrate migrador;
    @Mock
    private IRodadorDeArquivo rodador;
    @Mock
    private IDefinidorDeArquivosNaoLidos definidor;
    private List<String> arquivos;
    private String segundoArquivo;
    private String primeiroArquivo;

    @BeforeEach
    public void init() {
        primeiroArquivo = "primeiroarquivo.sql";
        segundoArquivo = "segundoarquivo.sql";
        arquivos = List.of(primeiroArquivo, segundoArquivo);

        Mockito.when(definidor.obter()).thenReturn(arquivos);

        migrador = new Migrate(rodador, definidor);
    }

    @Test
    public void deve_rodar_um_arquivo_selecionado() {

        migrador.migrate();

        Mockito.verify(rodador, Mockito.times(1)).rodar(primeiroArquivo);
        Mockito.verify(rodador, Mockito.times(1)).rodar(segundoArquivo);
    }

    @Test
    public void deve_executar_somente_arquivo_que_ainda_nao_foram_executados() {

        migrador.migrate();

        Mockito.verify(rodador, Mockito.times(2)).rodar(captor.capture());
        Assertions.assertEquals(captor.getAllValues().get(0), primeiroArquivo);
        Assertions.assertEquals(captor.getAllValues().get(1), segundoArquivo);
    }

    @Test
    public void deve_arquivos_ainda_nao_lidos() {

        migrador.migrate();

        Mockito.verify(definidor, Mockito.times(1)).obter();
    }
}

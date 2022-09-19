package outedg.outgration.dominio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import outedg.outgration.Migrate;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MigrateTest {

    @Captor
    ArgumentCaptor<String> captor;
    private Migrate migrador;
    @Mock
    private IRodadorDeArquivo rodador;
    @Mock
    private IDefinidorDeArquivosNaoLidos definidorDeArquivosNaoLidos;
    @Mock
    private IRepositorioDeVersoes repositorioDeVersao;
    private List<String> arquivos;
    private String segundoArquivo;
    private String primeiroArquivo;

    @BeforeEach
    public void init() {
        primeiroArquivo = "primeiroarquivo.sql";
        segundoArquivo = "segundoarquivo.sql";
        arquivos = List.of(primeiroArquivo, segundoArquivo);
        Mockito.when(definidorDeArquivosNaoLidos.definir()).thenReturn(arquivos);
        repositorioDeVersao = Mockito.mock(IRepositorioDeVersoes.class);
        migrador = new Migrate(rodador, definidorDeArquivosNaoLidos, repositorioDeVersao);
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

        Mockito.verify(definidorDeArquivosNaoLidos, Mockito.times(1)).definir();
    }

    @Test
    public void se_rodou_tem_que_salvar() {

        migrador.migrate();

        Mockito.verify(repositorioDeVersao, Mockito.times(2)).save(Mockito.any(Versao.class));
    }
}

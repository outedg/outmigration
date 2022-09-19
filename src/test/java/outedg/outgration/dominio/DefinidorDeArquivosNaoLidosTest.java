package outedg.outgration.dominio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class DefinidorDeArquivosNaoLidosTest {

    private DefinidorDeArquivosNaoLidos definidor;
    private IRepositorioDeArquivos repositorioDeArquivos;
    private IRepositorioDeVersoes repositorioDeVersoes;

    @BeforeEach
    void init() {
        repositorioDeArquivos = Mockito.mock(IRepositorioDeArquivos.class);
        repositorioDeVersoes = Mockito.mock(IRepositorioDeVersoes.class);
        definidor = new DefinidorDeArquivosNaoLidos(repositorioDeArquivos, repositorioDeVersoes);
    }

    //TODO: Testar quando tiver executado script e não tiver aquivo;
    @Test
    void se_o_script_estiver_uma_versao_a_frente_deve_definir_apenas_um_script() {
        var v1 = "V1.sql";
        var v2 = "V2.sql";
        Mockito.when(repositorioDeArquivos.obter()).thenReturn(List.of(v1, v2));
        Mockito.when(repositorioDeVersoes.findTopByOrderByIdDesc()).thenReturn(new Versao(v1));
        var arquivos = definidor.definir();

        Assertions.assertEquals(1, arquivos.size());
    }

    @Test
    void deve_obter_os_arquivo_do_diretorio() {

        definidor.definir();

        Mockito.verify(repositorioDeArquivos, Mockito.times(1)).obter();
    }

    @Test
    void se_ultimo_arquivo_esta_na_versao_dois_e_foi_executado_a_versao_um_deve_rodar_a_vesao_2() {
        var arquivoVersaoDois = "V2__asdf.sql";
        var arquivoVersaoUm = "V1_asdf.sql";
        var versao1 = new Versao(arquivoVersaoUm);
        Mockito.when(repositorioDeVersoes.findTopByOrderByIdDesc()).thenReturn(versao1);
        Mockito.when(repositorioDeArquivos.obter()).thenReturn(List.of(arquivoVersaoUm, arquivoVersaoDois));

        var arquivosDefinidos = definidor.definir();

        Assertions.assertEquals(arquivoVersaoDois, arquivosDefinidos.get(0));
    }

    @Test
    void se_ultimo_arquivo_esta_na_versao_3_e_foi_executado_a_versao_um_deve_definir_a_vesao_2_e_3() {
        var arquivoVersaoUm = "V1_asdf.sql";
        var arquivoVersaoDois = "V2__asdf.sql";
        var arquivoVersaoTres = "V3__asdf.sql";
        var versao1 = new Versao(arquivoVersaoUm);
        Mockito.when(repositorioDeVersoes.findTopByOrderByIdDesc()).thenReturn(versao1);
        Mockito.when(repositorioDeArquivos.obter()).thenReturn(List.of(arquivoVersaoUm, arquivoVersaoDois, arquivoVersaoTres));

        var arquivosDefinidos = definidor.definir();

        Assertions.assertEquals(arquivoVersaoDois, arquivosDefinidos.get(0));
        Assertions.assertEquals(arquivoVersaoTres, arquivosDefinidos.get(1));
    }


    @Test
    void se_ultimo_arquivo_esta_na_versao_3_e_nada_foi_executado_a_versao_um_deve_definir_a_vesao_1_2_e_3() {
        var arquivoVersaoUm = "V1_asdf.sql";
        var arquivoVersaoDois = "V2__asdf.sql";
        var arquivoVersaoTres = "V3__asdf.sql";
        Mockito.when(repositorioDeVersoes.findTopByOrderByIdDesc()).thenReturn(null);
        Mockito.when(repositorioDeArquivos.obter()).thenReturn(List.of(arquivoVersaoUm, arquivoVersaoDois, arquivoVersaoTres));

        var arquivosDefinidos = definidor.definir();

        Assertions.assertEquals(arquivoVersaoUm, arquivosDefinidos.get(0));
        Assertions.assertEquals(arquivoVersaoDois, arquivosDefinidos.get(1));
        Assertions.assertEquals(arquivoVersaoTres, arquivosDefinidos.get(2));
    }

    @Test
    void se_nao_tem_versao_nao_deve_definir() {
        var arquivos = definidor.definir();

        Assertions.assertTrue(arquivos.isEmpty());
    }
}

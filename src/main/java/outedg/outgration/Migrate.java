package outedg.outgration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import outedg.outgration.dominio.IDefinidorDeArquivosNaoLidos;
import outedg.outgration.dominio.IRepositorioDeVersoes;
import outedg.outgration.dominio.IRodadorDeArquivo;
import outedg.outgration.dominio.Versao;

import javax.transaction.Transactional;

@ShellComponent
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Migrate {

    private final IRodadorDeArquivo rodadorDeArquivo;
    private final IDefinidorDeArquivosNaoLidos definidorDeArquivosNaoLidos;
    private final IRepositorioDeVersoes repositorioDeVersoes;

    @ShellMethod()
    @Transactional
    public void migrate() {
        var arquivos = definidorDeArquivosNaoLidos.definir();
        for (var arquivo : arquivos) {
            //TODO: Se rodou tem a atualizar a versão;
            rodadorDeArquivo.rodar(arquivo);
            repositorioDeVersoes.save(new Versao(arquivo));
            System.out.println(AnsiOutput.toString(AnsiColor.GREEN, arquivo, AnsiColor.DEFAULT));
        }
    }
}

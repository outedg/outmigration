package outedg.outgration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Migrate {

    private final IRodadorDeArquivo rodadorDeArquivo;
    private final IDefinidorDeArquivosNaoLidos definidorDeArquivosNaoLidos;


    @ShellMethod()
    public int migrate() {
        var arquivos = definidorDeArquivosNaoLidos.obter();
        for (var arquivo : arquivos) {
            rodadorDeArquivo.rodar(arquivo);
        }
        return 1;
    }
}

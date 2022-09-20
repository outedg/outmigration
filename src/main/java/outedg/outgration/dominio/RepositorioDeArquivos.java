package outedg.outgration.dominio;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RepositorioDeArquivos implements IRepositorioDeArquivos {

    public List<String> obter() {
        var dir = "./src/main/resources/db/migrate/123";
        var arquivos = new File(dir).listFiles();

        //TODO: testar quando não tem arquivo no diretorio
        arquivos = arquivos == null ? new File[0] : arquivos;
        return Stream.of(arquivos)
                .filter(file -> !file.isDirectory())
                .map(File::getPath)
                .collect(Collectors.toList());
    }
}

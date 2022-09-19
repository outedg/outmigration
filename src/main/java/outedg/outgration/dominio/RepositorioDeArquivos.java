package outedg.outgration.dominio;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RepositorioDeArquivos implements IRepositorioDeArquivos {

    public List<String> obter() {
        var dir = "./src/main/resources/db/migrate/";
        var arquivos = new File(dir).listFiles();
        for (var arquivo : arquivos) {
            dir = arquivo.getName();
        }

        return Stream.of(arquivos)
                .filter(file -> !file.isDirectory())
                .map(File::getPath)
                .collect(Collectors.toList());
    }
}

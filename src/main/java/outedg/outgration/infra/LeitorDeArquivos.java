package outedg.outgration.infra;

import org.springframework.stereotype.Service;
import outedg.outgration.dominio.ILeitorDeArquivos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class LeitorDeArquivos implements ILeitorDeArquivos {
    @Override
    public String ler(String nomeDoArquivo) {
        var path = Paths.get(nomeDoArquivo);

        try {
            return Files.readAllLines(path).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

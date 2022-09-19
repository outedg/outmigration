package outedg.outgration.dominio;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefinidorDeArquivosNaoLidos implements IDefinidorDeArquivosNaoLidos {
    private final IRepositorioDeArquivos repositorioDeArquivos;
    private final IRepositorioDeVersoes repositorioDeVersoes;

    public List<String> definir() {
        var versao = repositorioDeVersoes.findTopByOrderByIdDesc();
        var arquivos = repositorioDeArquivos.obter();
        var arquivosEncontrados = new ArrayList<String>();
        var achou = false;
        for (var arquivo : arquivos.stream().sorted().collect(Collectors.toList())) {
            var nomeDaVersaoAtual = versao == null ? "" : versao.nome;
            if (arquivo.equals(nomeDaVersaoAtual) || nomeDaVersaoAtual == "")
                achou = true;

            if (achou && !arquivo.equals(nomeDaVersaoAtual))
                arquivosEncontrados.add(arquivo);
        }
        return arquivosEncontrados;
    }
}

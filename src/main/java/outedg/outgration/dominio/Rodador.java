package outedg.outgration.dominio;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Rodador implements IRodadorDeArquivo {

    private final JdbcTemplate executador;
    private final ILeitorDeArquivos leitorDeArquivo;

//    public Rodador(JdbcTemplate executador, ILeitorDeArquivos leitorDeArquivo) {
//        this.executador = executador;
//        this.leitorDeArquivo = leitorDeArquivo;
//    }

    public void rodar(String nomeDoArquivo) {
        var sql = leitorDeArquivo.ler(nomeDoArquivo);
        //TODO: testar remoção de cochetes;
        sql = sql.replace("[", "");
        sql = sql.replace("]", "");
        executador.execute(sql);
    }
}

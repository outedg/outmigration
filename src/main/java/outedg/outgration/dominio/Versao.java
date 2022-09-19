package outedg.outgration.dominio;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Versao {
    public String nome;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public Versao(String nome) {
        this.nome = nome;
    }

    protected Versao() {

    }
}

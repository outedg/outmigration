package outedg.outgration.dominio;

import org.springframework.data.repository.CrudRepository;

public interface IRepositorioDeVersoes extends CrudRepository<Versao, Long> {
    Versao findTopByOrderByIdDesc();
}

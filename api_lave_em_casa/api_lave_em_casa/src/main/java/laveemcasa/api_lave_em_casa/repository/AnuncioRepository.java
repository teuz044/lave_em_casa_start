package laveemcasa.api_lave_em_casa.repository;

import laveemcasa.api_lave_em_casa.models.Anuncios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncios, Integer> {


}

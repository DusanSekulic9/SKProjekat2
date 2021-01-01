package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Avion;
import app.entity.Let;

@Repository
public interface LetRepository extends JpaRepository<Let, Long> {

	List<Let> findByAvion(Avion avion);

	List<Let> findByPocetnaDestinacija(String pocetnaDestinacija);

	List<Let> findByKrajnjaDestinacija(String krajnjaDestinacija);

	List<Let> findByDuzinaLeta(String duzinaLeta);

	List<Let> findByCena(int cena);

}

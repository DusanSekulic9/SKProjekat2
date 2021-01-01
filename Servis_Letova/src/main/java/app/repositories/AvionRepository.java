package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Avion;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Long> {

	Avion findByNaziv(String naziv);

}

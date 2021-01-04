package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.KreditnaKartica;
import app.entities.User;

@Repository
public interface KreditnaKarticaRepo extends JpaRepository<KreditnaKartica, Long>{

	KreditnaKartica findByUser(User u);
}

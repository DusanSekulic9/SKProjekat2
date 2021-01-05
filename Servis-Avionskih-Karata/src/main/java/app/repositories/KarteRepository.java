package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.Karta;

@Repository
public interface KarteRepository extends JpaRepository<Karta, Long>{
	
	List<Karta> findAllByIdUser(Long userId);
	
	List<Karta> findAllByIdLet(Long letId);
}

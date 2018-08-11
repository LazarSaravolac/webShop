package jwd.wafepa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Art;
import jwd.wafepa.model.Osoba;

@Repository
public interface OsobaRepository extends JpaRepository<Osoba, Long> {
	@Query("SELECT a FROM Osoba a WHERE "
			+ "(:ime IS NULL or a.ime like :ime )"
			)
	Page<Osoba> pretraga(
			@Param("ime") String ime,
			Pageable pageRequest);
}

package jwd.wafepa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Art;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {

	@Query("SELECT a FROM Art a WHERE "
			+ "(:naziv IS NULL or a.naziv like :naziv )"
			)
	Page<Art> pretraga(
			@Param("naziv") String naziv,
			Pageable pageRequest);
}

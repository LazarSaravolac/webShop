package jwd.wafepa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Artikli;

@Repository
public interface ArtikliRepository extends PagingAndSortingRepository<Artikli, Long> {
	Page<Artikli> findByOsobaId(Long osobaId, Pageable pageRequest);
	
	@Query("SELECT a FROM Artikli a WHERE "
			+ "(:naziv IS NULL or a.naziv like :naziv ) AND "
			+ "(:idOsobe IS NULL or a.osoba.id = :idOsobe )"
			)
	Page<Artikli> pretraga(
			@Param("naziv") String naziv,
			@Param("idOsobe") Long idOsobe,
			Pageable pageRequest);
}

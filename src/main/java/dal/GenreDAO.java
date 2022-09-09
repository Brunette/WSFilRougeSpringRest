package dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bo.Genre;

@Repository
public interface GenreDAO extends JpaRepository<Genre, Integer> {
	Genre findByName(String name);

	@Query(value = "Select g from Genre g where g.name in :names")
	List<Genre> findByNames(@Param("names") List<String> names);

}
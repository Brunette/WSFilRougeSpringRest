package dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bo.Film;

@Repository
public interface FilmDAO extends JpaRepository<Film, Integer> {

}
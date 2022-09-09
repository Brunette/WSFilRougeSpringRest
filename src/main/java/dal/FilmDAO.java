package dal;

import java.util.List;

import bo.Film;

public interface FilmDAO {
	List<Film> selectAll();

	Film selectById(int id);

	void insert(Film film);

	void update(Film film);

	void deleteById(int id);
}

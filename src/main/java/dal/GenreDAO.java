package dal;

import java.util.List;

import bo.Genre;

public interface GenreDAO {
	public List<Genre> selectAll();

	public Genre selectByName(String name);

	public List<Genre> selectByNames(List<String> names);
}

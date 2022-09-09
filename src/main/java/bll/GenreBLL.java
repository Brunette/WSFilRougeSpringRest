package bll;

import java.util.List;

import bo.Genre;
import dal.GenreDAO;
import dal.GenreDAOHibernateImpl;

public class GenreBLL {
	private GenreDAO dao;

	public GenreBLL() {
		dao = new GenreDAOHibernateImpl();
	}

	public List<Genre> selectAll() {
		return dao.selectAll();
	}

	public Genre selectByName(String name) {
		return dao.selectByName(name);
	}

	public List<Genre> selectByNames(List<String> names) {
		return dao.selectByNames(names);
	}
}

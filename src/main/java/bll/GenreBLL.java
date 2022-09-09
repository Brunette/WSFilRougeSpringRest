package bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.Genre;
import dal.GenreDAO;

@Service
public class GenreBLL {
	@Autowired
	private GenreDAO dao;

	public GenreBLL() {
	}

	public List<Genre> selectAll() {
		return dao.findAll();
	}

	public Genre selectByName(String name) {
		return dao.findByName(name);
	}

	public List<Genre> selectByNames(List<String> names) {
		return dao.findByNames(names);
	}

	public boolean contains(int id) {
		return dao.existsById(id);
	}
}

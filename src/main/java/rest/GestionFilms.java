package rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bll.FilmBLL;
import bo.Film;

@RestController
@RequestMapping(path = "films")
public class GestionFilms {
	@Autowired
	private FilmBLL bllFilm;

	public GestionFilms() {
		bllFilm = new FilmBLL();
	}

	@GetMapping
	public ResponseEntity<List<Film>> getFilms() {
		return new ResponseEntity<List<Film>>(bllFilm.selectAll(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Film> getFilmById(@PathParam("id") int id) {
		return new ResponseEntity<Film>(bllFilm.selectById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Film> ajouterFilm(@RequestBody Film f) {
		bllFilm.insert(f);
		return new ResponseEntity<Film>(f, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	public void supprimerFilm(@PathParam("id") int id) {
		bllFilm.delete(id);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Film> modifyFilm(@PathVariable("id") int id, @RequestBody Film f) {
		if (bllFilm.contains(id)) {
			f.setId(id);
			bllFilm.update(f);
			return new ResponseEntity<Film>(f, HttpStatus.OK);
		} else {
			return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
		}
	}
}

package rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bll.FilmBLL;
import bll.GenreBLL;
import bo.Film;
import bo.Genre;

@Path("/films")
public class GestionFilms {
	private FilmBLL bllFilm;
	private GenreBLL bllGenre;

	public GestionFilms() {
		bllFilm = new FilmBLL();
		bllGenre = new GenreBLL();
	}

	@GET
	public List<Film> getFilms() {
		return bllFilm.selectAll();
	}

	@GET
	@Path("/{cle : \\d+}")
	public Film getFilmById(@PathParam("cle") int id) {
		return bllFilm.selectById(id);
	}

	@POST
	public Film ajouterFilm(@FormParam("name") String title, @FormParam("info") String info,
			@FormParam("duration") int duration, @FormParam("thumblink") String thumblink,
			@FormParam("genres") String genrecsv) {
		Film film = new Film();
		film.setName(title);
		film.setInfo(info);
		film.setDuration(duration);
		film.setThumbLink(thumblink);
		List<String> genreStrs = new ArrayList<>(Arrays.asList(genrecsv.split(",")));
		List<Genre> genres = bllGenre.selectByNames(genreStrs);
//		System.out.println("Printing Genres:");
//		for (Genre current : genres) {
//			System.out.println(current.getName());
//		}
		film.setGenres(genres);
		try {
			bllFilm.insert(film);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("INSERTING " + title);
		return film;
	}

	@DELETE
	@Path("/{id : \\d+}")
	public void supprimerFilm(@PathParam("id") int id) {
		bllFilm.deleteById(id);
	}

	@PUT
	@Path("/{id : \\d+}")
	public Film modifierFilm(@PathParam("id") int id, @FormParam("name") String title, @FormParam("info") String info,
			@FormParam("duration") Integer duration, @FormParam("thumblink") String thumblink,
			@FormParam("genres") String genrecsv) {
		Film film = bllFilm.selectById(id);
		if (title != null)
			film.setName(title);
		if (info != null)
			film.setInfo(info);
		if (duration != null)
			film.setDuration(duration.intValue());
		if (thumblink != null)
			film.setThumbLink(thumblink);
		if (genrecsv != null) {
			List<String> genreStrs = new ArrayList<>(Arrays.asList(genrecsv.split(",")));
			List<Genre> genres = bllGenre.selectByNames(genreStrs);
			film.setGenres(genres);
		}
		try {
			bllFilm.update(film);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

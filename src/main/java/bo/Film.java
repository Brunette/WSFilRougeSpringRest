package bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "films")
@NamedQueries({ @NamedQuery(name = "trouverTousFilms", query = "SELECT f FROM Film f"),
		@NamedQuery(name = "trouverFilmById", query = "SELECT f FROM Film f WHERE f.id = :id"),
		@NamedQuery(name = "supprimerFilmById", query = "DELETE from Film WHERE id = :id") })
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "info")
	private String info;
	@Column(name = "duration")
	private int duration;
	@Column(name = "thumbLink")
	private String thumbLink;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "asso_genres_films", joinColumns = { @JoinColumn(name = "filmId") }, inverseJoinColumns = {
			@JoinColumn(name = "genre_id") })
	private List<Genre> genres = new ArrayList<Genre>();

	public Film() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getThumbLink() {
		return thumbLink;
	}

	public void setThumbLink(String thumbLink) {
		this.thumbLink = thumbLink;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

}

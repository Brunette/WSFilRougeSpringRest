package dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import bo.Film;

public class FilmDAOHibernateImpl implements FilmDAO {

	private EntityManagerFactory emf;

	public FilmDAOHibernateImpl() {
		emf = Persistence.createEntityManagerFactory("user_bdd");
	}

	@Override
	public List<Film> selectAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Film> query = em.createNamedQuery("trouverTousFilms", Film.class);
		List<Film> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public Film selectById(int id) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Film> query = em.createNamedQuery("trouverFilmById", Film.class);
		query.setParameter("id", id);
		Film resultat = query.getSingleResult();
		em.close();
		return resultat;
	}

	@Override
	public void insert(Film film) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			System.out.println("DAO INSERT film " + film.getName());
			em.persist(film);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		em.close();
	}

	@Override
	public void update(Film film) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.merge(film);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		em.close();
	}

	public void deleteById(int id) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("supprimerFilmById");
		query.setParameter("id", id);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		em.close();
	}

}

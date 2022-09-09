package dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import bo.Genre;

public class GenreDAOHibernateImpl implements GenreDAO {

	private EntityManagerFactory emf;

	public GenreDAOHibernateImpl() {
		emf = Persistence.createEntityManagerFactory("user_bdd");
	}

	@Override
	public List<Genre> selectAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Genre> query = em.createNamedQuery("trouverTousGenres", Genre.class);
		List<Genre> resultat = query.getResultList();
		em.close();
		return resultat;
	}

	@Override
	public Genre selectByName(String name) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Genre> query = em.createNamedQuery("trouverGenreByName", Genre.class);
		query.setParameter("name", name);
		Genre resultat = query.getSingleResult();
		em.close();
		return resultat;
	}

	@Override
	public List<Genre> selectByNames(List<String> names) {
		EntityManager em = emf.createEntityManager();
		String queryStr = "SELECT g FROM Genre g WHERE ";
		System.out.println("selectByNames");
		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
			queryStr += "g.name = :name" + i;
			if (i != names.size() - 1)
				queryStr += " OR ";
		}
		TypedQuery<Genre> query = em.createQuery(queryStr, Genre.class);
		for (int i = 0; i < names.size(); i++) {
			query.setParameter("name" + i, names.get(i));
		}
		List<Genre> resultat = query.getResultList();
		em.close();
		return resultat;
	}
}

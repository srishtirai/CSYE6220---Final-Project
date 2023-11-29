package com.csye6220.finalprojectesd.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.csye6220.finalprojectesd.config.HibernateConfig;
import com.csye6220.finalprojectesd.model.Movie;
import com.csye6220.finalprojectesd.model.Showtime;

@Component
public class ShowtimeDAOImplementation implements ShowtimeDAO {

	private final SessionFactory sessionFactory;

    public ShowtimeDAOImplementation() {
        this.sessionFactory = HibernateConfig.buildSessionFactory();
    }
    
	@Override
	public void saveShowtime(Showtime showtime) {
		Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(showtime);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}

	@Override
	public List<Showtime> getAllShowtimesByMovie(Movie movie) {
		 try (Session session = sessionFactory.openSession()) {
	            return session.createQuery("FROM Showtime WHERE movie.movieId = :movieId", Showtime.class)
	            		.setParameter("movieId", movie.getMovieId())
	            		.getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	}

	@Override
	public void updateShowtime(Showtime showtime) {
		Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(showtime);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}

	@Override
	public void deleteShowtime(Showtime showtime) {
		Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(showtime);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}

}

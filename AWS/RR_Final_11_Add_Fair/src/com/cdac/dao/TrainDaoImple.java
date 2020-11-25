package com.cdac.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cdac.dto.Train;

@Repository
public class TrainDaoImple implements ITrainDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void addTrain(Train train) {
		hibernateTemplate.execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				session.save(train);
				tr.commit();
				session.flush();
				session.clear();
				return null;
			}

		});

	}

	@Override
	public void deleteTrain(int trainId) {
		hibernateTemplate.execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				session.delete(new Train(trainId));
				tr.commit();
				session.flush();
				session.close();
				return null;
			}

		});

	}

	@Override
	public Train selectTrain(int trainId) {
		Train train = hibernateTemplate.execute(new HibernateCallback<Train>() {

			@Override
			public Train doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				Train trx = (Train) session.get(Train.class, trainId);
				tr.commit();
				session.flush();
				session.close();
				return trx;
			}
		});
		return train;
	}

	@Override
	public void upateTrain(Train train) {
		hibernateTemplate.execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session) throws HibernateException {
				Transaction transaction = session.beginTransaction();
				session.update(train);
				transaction.commit();
				session.flush();
				session.close();
				return null;
			}

		});
	}

	@Override
	public List<Train> selectAll() {

		List<Train> trains = hibernateTemplate.execute(new HibernateCallback<List<Train>>() {

			@Override
			public List<Train> doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from Train");
				List<Train> tList = q.list();
				tr.commit();
				session.flush();
				session.close();
				return tList;
			}
		});

		return trains;
	}

	@Override
	public List<Train> selectTrain(String source, String destination, String dateOfTravel) {
		List<Train> train = hibernateTemplate.execute(new HibernateCallback<List<Train>>() {

			@Override
			public List<Train> doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from Train where departs=? and reach=? and date_of_travel=?");
				q.setString(0, source);
				q.setString(1, destination);
				q.setString(2, dateOfTravel);
				List<Train> tList = q.list();
				tr.commit();
				session.flush();
				session.close();
				return tList;
			}
		});
		return train;

	}

	@Override
	public boolean updateSeats(int trainId, int seats, String seatType) {
		boolean b = hibernateTemplate.execute(new HibernateCallback<Boolean>() {

			@Override
			public Boolean doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("update Train set " + seatType + "=? where train_id=?");
				q.setInteger(0, seats);
				q.setInteger(1, trainId);
				int flag = q.executeUpdate();
				tr.commit();
				session.flush();
				session.close();
				return true;

			}
		});
		return true;
	}
}

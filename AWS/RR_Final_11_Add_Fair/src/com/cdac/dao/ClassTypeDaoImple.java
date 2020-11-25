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

import com.cdac.dto.ClassType;

@Repository
public class ClassTypeDaoImple implements IClassType {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public double getPriceofClass(String className, int trainId) {
		double price = hibernateTemplate.execute(new HibernateCallback<Double>() {

			@Override
			public Double doInHibernate(Session session) throws HibernateException {
				System.out.println("class Name " + className + " " + trainId);
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from ClassType where train_id =? and class_name=?");
				q.setInteger(0, trainId);
				q.setString(1, className);
				List<ClassType> li = q.list();
				System.out.println("List " + li);
				ClassType classUser = (ClassType) li.get(0);
				double price = classUser.getPrice();

				tr.commit();
				session.flush();
				session.close();
				return price;
			}
		});
		return price;

	}

	@Override
	public boolean updateFair(String className, int trainId, double price) {
		boolean flag = hibernateTemplate.execute(new HibernateCallback<Boolean>() {

			@Override
			public Boolean doInHibernate(Session session) throws HibernateException {
				System.out.println(className + " " + trainId + " " + price);
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("update ClassType set price =? where train_id =? and class_name=?");
				q.setDouble(0, price);
				q.setInteger(1, trainId);
				q.setString(2, className);
				q.executeUpdate();
				tr.commit();
				session.flush();
				session.close();
				return true;
			}
		});
		return true;

	}

	@Override
	public void addFair(String className, int trainId, double price) {
		hibernateTemplate.execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session) throws HibernateException {
				System.out.println(className + " " + trainId + " " + price);
				Transaction tr = session.beginTransaction();

				ClassType classType = new ClassType();
				classType.setClassName(className);
				classType.setPrice(price);
				classType.setTrainId(trainId);
//				Query q = session.createQuery("insert into ClassType (class_name,train_id,price) values(?,?,?)");
//				q.setString(0, className);
//				q.setInteger(1, trainId);
//				q.setDouble(2, price);
//				q.executeUpdate();
				session.save(classType);
				tr.commit();
				session.flush();
				session.close();
				return null;

			}
		});

	}

	@Override
	public boolean findFair(String className, int trainId) {
		boolean p = hibernateTemplate.execute(new HibernateCallback<Boolean>() {

			@Override
			public Boolean doInHibernate(Session session) throws HibernateException {
				System.out.println("class Name " + className + " " + trainId);
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from ClassType where train_id =? and class_name=?");
				q.setInteger(0, trainId);
				q.setString(1, className);
				List<ClassType> li = q.list();
				boolean flag = false;
				if (!li.isEmpty()) {
					flag = true;
				}

				tr.commit();
				session.flush();
				session.close();
				return flag;
			}
		});
		return p;

	}

}
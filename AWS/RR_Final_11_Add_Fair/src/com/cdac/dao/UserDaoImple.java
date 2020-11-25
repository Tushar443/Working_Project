package com.cdac.dao;

import java.util.Base64;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cdac.dto.User;

@Repository
public class UserDaoImple implements IUserDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public boolean insertUser(User user) {
		boolean flag = hibernateTemplate.execute(new HibernateCallback<Boolean>() {

			@Override
			public Boolean doInHibernate(Session session) throws HibernateException {
				boolean flag = false;
				boolean dbUser = checkUser(user);

				if (dbUser) {
					flag = false;
				} else {
					Transaction tr = session.beginTransaction();
					String encryptedPass = getEncryptPass(user.getUserPass());
					user.setUserPass(encryptedPass);
					session.save(user);
					flag = true;
					tr.commit();
					session.flush();
					session.clear();
				}
				return flag;
			}
		});
		return flag;

	}

	protected String getEncryptPass(String userPass) {
		return Base64.getEncoder().encodeToString(userPass.getBytes());
	}

	@Override
	public boolean checkUser(User user) {
		boolean b = hibernateTemplate.execute(new HibernateCallback<Boolean>() {

			@Override
			public Boolean doInHibernate(Session session) throws HibernateException {

				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from User where user_name =?");
				q.setString(0, user.getUserName());
				boolean flag = false;
				List<User> li = q.list();
				if (!li.isEmpty()) {
					User newUser = li.get(0);
					String decryptedPass = getDecryptPass(newUser.getUserPass());
					if (user.getUserPass().equalsIgnoreCase(decryptedPass)) {
						flag = true;
						user.setUserId(li.get(0).getUserId());
						tr.commit();
						session.flush();
						session.close();
					}
				}
				return flag;

			}
		});

		return b;
	}

	@Override
	public boolean checkAdmin(User user) {
		boolean b = hibernateTemplate.execute(new HibernateCallback<Boolean>() {

			@Override
			public Boolean doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from User where user_name =?");
				q.setString(0, user.getUserName());

				boolean flag = false;
				List<User> li = q.list();
				User dbUser = li.get(0);
				user.setUserId(dbUser.getUserId());
				tr.commit();
				if (!li.isEmpty()) {
					String decryptedPass = getDecryptPass(dbUser.getUserPass());
					if (user.getUserPass().equalsIgnoreCase(decryptedPass)
							&& user.getUserName().equalsIgnoreCase("admin")) {
						flag = true;
					}
				}
				session.flush();
				session.close();

				return flag;

			}
		});
		System.out.println(b);
		return b;
	}

	protected String getDecryptPass(String userPass) {
		return new String(Base64.getMimeDecoder().decode(userPass));
	}

	@Override
	public boolean updateUser(String seatType, int noOfPassengers, double totalPrice, int trainId, int userId) {
		boolean b = hibernateTemplate.execute(new HibernateCallback<Boolean>() {

			@Override
			public Boolean doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery(
						"update User set seat_type=?,no_of_pass=?,total_price=?,train_id=? where user_id=?");

				q.setString(0, seatType);
				q.setInteger(1, noOfPassengers);
				q.setDouble(2, totalPrice);
				q.setInteger(3, trainId);
				q.setInteger(4, userId);
				int flag = q.executeUpdate();
				tr.commit();
				session.flush();
				session.close();
				return true;

			}
		});
		System.out.println(b);
		return b;

	}

	@Override
	public User findUser(int userId) {
		User user = hibernateTemplate.execute(new HibernateCallback<User>() {
			@Override
			public User doInHibernate(Session session) throws HibernateException {

				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from User where user_id =?");
				q.setInteger(0, userId);
				boolean flag = false;
				List<User> li = q.list();
				User newUser = null;
				if (!li.isEmpty()) {
					newUser = li.get(0);
					tr.commit();
					session.flush();
					session.close();
				}

				return newUser;
			}

		});

		return user;

	}

	@Override
	public List<User> passengerList(int trainId) {
		List<User> list = hibernateTemplate.execute(new HibernateCallback<List<User>>() {
			@Override
			public List<User> doInHibernate(Session session) throws HibernateException {

				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from User where trainId =?");
				q.setInteger(0, trainId);
				List<User> li = q.list();
				tr.commit();
				session.flush();
				session.close();
				return li;
			}

		});

		return list;
	}
}
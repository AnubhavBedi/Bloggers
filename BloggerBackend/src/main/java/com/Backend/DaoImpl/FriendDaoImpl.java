package com.Backend.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Backend.Friend;
import com.Backend.User;
import com.Backend.Dao.FriendDao;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> suggestedUsers(String email) {
		Session session=sessionFactory.getCurrentSession();
		String queryString="select * from user_tab where email in "
				+ "(select email from user_tab where email!=? "
				+ "minus "
				+ "(select toId_email from friend_tab where fromId_email=? "
				+ "union "
				+ "select fromId_email from friend_tab where toId_email=? )) and role='Role_User' ";
				
				
		SQLQuery query=session.createSQLQuery(queryString);
		query.setString(0, email);
		query.setString(1, email);
		query.setString(2, email);
		query.addEntity(User.class);
		List<User> suggestedUsers=query.list();
		return suggestedUsers;
	}

	@Override
	public void addFriend(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		session.save(friend);
		
	}

	@Override
	public List<Friend> pendingRequests(String toIdEmail) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend where status=? and toId.email=?");
		query.setCharacter(0, 'P');
		query.setString(1, toIdEmail);
		List<Friend> pendingRequests=query.list();
		return pendingRequests;
	}

	@Override
	public void acceptRequest(Friend request) {
		Session session=sessionFactory.getCurrentSession();
		request.setStatus('A');
		session.update(request);
		
	}

	@Override
	public void deleteRequest(Friend request) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(request);
		
	}

	@Override
	
	public List<Friend> listOfFriends(String email) {
		try{
		Session session=sessionFactory.getCurrentSession();
	
		Query query1=session.createQuery
		("select toId from Friend f where f.fromId.email=? and f.status=?");
	
		query1.setString(0, email);
		query1.setCharacter(1, 'A');
		List<Friend> friendList1=query1.list();
		
		Query query2=session.createQuery("select fromId from Friend f1 where f1.toId.email=? and f1.status=?");
		query2.setString(0, email);
		query2.setCharacter(1, 'A');
		
		List<Friend> friendList2=query2.list();
		
		
		
		System.out.println("friendList1 = "+friendList1);
		System.out.println("friendList2 = "+friendList2);
		
		
			
		friendList1.addAll(friendList2);
		return friendList1;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	
	

}

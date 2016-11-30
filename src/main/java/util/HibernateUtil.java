package util;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil
{
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory()
	{
		if (sessionFactory == null)
		{
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		}
		
		return sessionFactory;
	}
	
	public static Session getSession()
	{
		if (sessionFactory == null)
			sessionFactory = getSessionFactory();
		return sessionFactory.openSession();
	}
	
	public static void insert(Object o)
	{
		Session session = getSession();
		session.beginTransaction();
		session.save(o);
		session.getTransaction().commit();
		session.close();
	}
	
	public static void update(Object o)
	{
		Session session = getSession();
		session.beginTransaction();
		session.update(o);
		session.getTransaction().commit();
		session.close();
	}
	
	private static void delete(Object o)
	{
		Session session = getSession();
		session.beginTransaction();
		session.delete(o);
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("rawtypes")
	public static void deleteById(Class c, int id)
	{
		delete(load(c, id));
	}
	
	@SuppressWarnings("rawtypes")
	public static Object load(Class c, int id)
	{
		Session session = getSession();
		session.beginTransaction();
		
		Object o = session.load(c, id);
		Hibernate.initialize(o);
		
		session.close();
		
		return o;
	}
	
	@SuppressWarnings("rawtypes")
	public static List loadAll(Class c)
	{
		Session session = getSession();
		session.beginTransaction();
		
		String hql = "FROM " + c.getName();
		Query query = session.createQuery(hql);
		List results = query.list();
		
		session.close();
		return results;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getNRowByColumn(Class c, String columnName, int n, boolean fromLatest)
	{
		Session session = getSession();
		session.beginTransaction();
		
		String hql = "FROM " + c.getName() + " ORDER BY " + columnName;
		if (fromLatest) hql += " DESC";
		else hql += " ASC";
		hql += " LIMIT " + n;
				
		List<Object> last = session.createQuery(hql).list();
		
		if (last != null) return last.get(0);
		else return null;
	}
	
	private HibernateUtil() { }
}

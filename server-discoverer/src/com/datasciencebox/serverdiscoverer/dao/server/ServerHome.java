package com.datasciencebox.serverdiscoverer.dao.server;

// Generated 26/04/2014 1:03:11 PM by Hibernate Tools 3.4.0.CR1

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.jar.JarException;

import javax.naming.InitialContext;
import javax.transaction.Transaction;

import oracle.jdbc.driver.OracleSQLException;
import oracle.net.ns.NetException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Server.
 * @see com.datasciencebox.serverdiscoverer.dao.server.Server
 * @author Hibernate Tools
 */
public class ServerHome {

	private static final Log log = LogFactory.getLog(ServerHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();
	
	protected SessionFactory getSessionFactory() {
		try {
			
//			InitialContext initialContext = new InitialContext();
//			sessionFactory = (SessionFactory)  initialContext.lookup("java:/hibernate/SessionFactory");
//			Configuration cfg = new Configuration();
//			cfg.configure();
//			sf = cfg.buildSessionFactory();
//			initialContext.bind("SessionFactory", sf);
//			return sf;
			
//			return (SessionFactory) new InitialContext()
//					.lookup("SessionFactory");
			
//			System.out.println(new File(".").getAbsolutePath());	
			return new Configuration().configure().buildSessionFactory(); 
			 
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}
	
	public void add(Server instance) {
		log.debug("adding a new Server instance");
				
		try {

			Session session = sessionFactory.getCurrentSession();
			org.hibernate.Transaction trans = session.beginTransaction();
			session.saveOrUpdate(instance);			
			session.flush();
			trans.commit();
			session.getSessionFactory().close();
			//session.close();							
			
		} catch(org.hibernate.exception.ConstraintViolationException e) {
			// just continue
			System.out.println("Record already exists in db, ignoring...");
		} catch(org.hibernate.exception.GenericJDBCException e) {
			log.error("JDBC error: ", e);	
		} catch(Exception e) {
			log.error("Could not open SessionFactory session", e);			
				
		} finally {
			
		}
		
	}

	public void persist(Server transientInstance) {
		log.debug("persisting Server instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public void attachDirty(Server instance) {
		log.debug("attaching dirty Server instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Server instance) {
		log.debug("attaching clean Server instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Server persistentInstance) {
		log.debug("deleting Server instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Server merge(Server detachedInstance) {
		log.debug("merging Server instance");
		try {
			Server result = (Server) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Server findById(long id) {
		log.debug("getting Server instance with id: " + id);
		try {
			Server instance = (Server) sessionFactory
					.getCurrentSession()
					.get("com.datasciencebox.serverdiscoverer.dao.server.Server",
							id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Server instance) {
		log.debug("finding Server instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"com.datasciencebox.serverdiscoverer.dao.server.Server")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}

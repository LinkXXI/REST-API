package org.rogue.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Kevin on 2015-03-28.
 * <p>
 * Work in progress, likely will not be used.
 */
public class GenericDAO<T extends Serializable> {
    //Since static final variables are:
    // A: created and initialized at compile time and
    // B: only have a single instance no matter how many instances you make,
    // this DAO should not be a burden to be created and used on demand instead of as a global var.
    private static final Configuration CONFIGURATION = new Configuration().configure();
    private static final ServiceRegistry SERVICE_REGISTRY = new StandardServiceRegistryBuilder().applySettings(CONFIGURATION.getProperties()).build();
    private static final SessionFactory SESSION_FACTORY = CONFIGURATION.buildSessionFactory(SERVICE_REGISTRY);

    private final Class<T> clazz;

    public GenericDAO(Class<T> t) {
        this.clazz = t;
    }

    /**
     * Get an instance of T by id;
     * <p>
     * Optionals are a way of not returning null if the object is not found.
     * Optionals contain a method called isPresent that allows you to check if the object exists and fail gracefully instead of comparing to not null.
     * They also support lambda expressions in a similar method called ifPresent.
     *
     * @param id the id to find
     * @return Optional of T
     */
    @SuppressWarnings("unchecked")
    public Optional<T> getById(int id) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Optional returnVal = Optional.ofNullable(session.get(clazz, id));

        session.getTransaction().commit();
        session.close();
        return returnVal;
    }

    /**
     * Query for all of T
     *
     * @return Optional list of T
     */
    @SuppressWarnings("unchecked")
    public Optional<List<T>> getAll() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Optional returnVal = Optional.ofNullable(session.createCriteria(clazz).list());

        session.getTransaction().commit();
        session.close();

        return returnVal;
    }

    /**
     * Create and persist an instance of T
     *
     * @param entity the entity to save
     * @return True if successful, otherwise false.
     */
    public boolean create(T entity) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        try {
            session.save(entity);

            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            session.close();
            return false;
        }
    }

    /**
     * Update the given entity
     *
     * @param entity entity to update
     * @return true if successful, otherwise false.
     */
    public boolean update(T entity) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        try {
            session.merge(entity);

            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            session.close();
            return false;
        }
    }

    /**
     * Delete given entity
     *
     * @param entity entity to delete
     * @return true if successful, otherwise false.
     */
    public boolean delete(T entity) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        try {
            session.delete(entity);

            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            session.close();
            return false;
        }
    }

    /**
     * Delete given entity by id
     *
     * @param id id to delete
     * @return true if successful, otherwise false.
     */
    public boolean deleteById(int id) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        try {

            session.delete(session.get(clazz, id));

            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            session.close();
            return false;
        }
    }

    /**
     * Execute a named query with numeric paramerters generically.
     *
     * @param namedQuery query to execute
     * @param values     values of the parameters for the query in order
     * @return Optional list of T
     */
    @SuppressWarnings("unchecked")
    public Optional<List<T>> executeNamedQuery(String namedQuery, Object... values) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        Query query = session.getNamedQuery(namedQuery);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }

        Optional opt = Optional.ofNullable(query.list());
        session.getTransaction().commit();
        session.close();
        return opt;
    }

    /**
     * Execute a named query with named parameters generically.
     *
     * @param namedQuery named query to execute
     * @param params     Map of String representing parameter name and Object representing the value
     * @return optional List of T
     */
    @SuppressWarnings("unchecked")
    public Optional<List<T>> executeNamedQuery(String namedQuery, Map<String, Object> params) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        Query query = session.getNamedQuery(namedQuery);
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }

        Optional opt = Optional.ofNullable(query.list());
        session.getTransaction().commit();
        session.close();
        return opt;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahibernate;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author alu2014044
 */
public class HibernateUtil {
    
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    
    
    
    
    
    
   public static SessionFactory createSessionFactory(){
       Configuration configuration = new Configuration();
       configuration.configure();
       serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
       sessionFactory = configuration.buildSessionFactory((org.hibernate.service.ServiceRegistry) serviceRegistry);
   
       return sessionFactory;
   
   }
   
   
    
}

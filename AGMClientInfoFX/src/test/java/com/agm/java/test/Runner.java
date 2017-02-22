package com.agm.java.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.agm.dao.impl.HibernateUtil;
import com.agm.model.Contact;

public class Runner {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
         
        List<Contact> contacts = session.createQuery("select contact_id from contact").list();
         
        session.close();
         
        for (Contact st : contacts) {
            System.out.println(st.getId()+". "+st.getFirstName()+" ");
        }
	}

}

package com.nikhil.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.nikhil.model.EmployeeEntity;

@Component
public class EmployeeHibernateSearchInit implements ApplicationListener<ContextRefreshedEvent> {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		SearchSession searchSession = Search.session( entityManager ); 

		MassIndexer indexer = searchSession.massIndexer( EmployeeEntity.class ).threadsToLoadObjects( 7 ); 
		try {
			indexer.startAndWait();
		} catch (InterruptedException e) {
			System.out.println("Error occured trying to build Hibernate Search indexes " + e.toString());
		}
	}
}

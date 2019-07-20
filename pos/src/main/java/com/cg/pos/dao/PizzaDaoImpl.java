package com.cg.pos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.pos.dto.PizzaDetails;
import com.cg.pos.exceptions.PizzaException;

/**
 * Dao class to fetch PIZZA DETAILS according to his storeid
 * 
 * @author PRAKHAR
 *
 */
@Transactional
@Repository

public class PizzaDaoImpl implements PizzaDao {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<PizzaDetails> getPizzaDetails(String store) throws PizzaException {
		List<PizzaDetails> pizzaList = new ArrayList<PizzaDetails>();

		TypedQuery<PizzaDetails> q = manager.createQuery(
				"select p from PizzaDetails p inner join  StoreDetails s on p.storeID=s.storeId and  s.storeName=:storeName",
				PizzaDetails.class);
		q.setParameter("storeName", store);

		pizzaList = q.getResultList();
		return pizzaList;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

}

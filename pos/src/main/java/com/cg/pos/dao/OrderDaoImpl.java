package com.cg.pos.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.pos.dto.CartDetails;
import com.cg.pos.dto.PizzaDetails;
import com.cg.pos.exceptions.PizzaException;
import com.cg.pos.utility.ExceptionMessage;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {
	@PersistenceContext
	EntityManager manager;

	@Override
	public PizzaDetails getData(String id) throws PizzaException {
		CartDetails cartDetails = null;
		TypedQuery<CartDetails> query = manager.createQuery(
				" select c from CartDetails c where c.quantity=(select max(cart.quantity) from CartDetails cart where cart.custId=: id)",
				CartDetails.class);
		query.setParameter("id", id);
		try {
			cartDetails = query.getSingleResult();
		} catch (NoResultException e) {
			throw new PizzaException(ExceptionMessage.MESSAGE12);
		}
		TypedQuery<PizzaDetails> quer = manager.createQuery(" select p from PizzaDetails p where p.itemId=:id",
				PizzaDetails.class);
		quer.setParameter("id", cartDetails.getItemId());
		return quer.getSingleResult();
	}

}

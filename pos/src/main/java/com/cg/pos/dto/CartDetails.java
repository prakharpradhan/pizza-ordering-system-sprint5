package com.cg.pos.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Bean class for cart
 * 
 * @author Prakhar
 *
 */
@Entity
@Table(name = "cart")
public class CartDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartid")
	@SequenceGenerator(name = "cartid", sequenceName = "cartid")
	@Column(name = "CARTID")
	private int cartId;
	@Column(name = "ITEMID")
	private int itemId;
	@Column(name = "CUSTID")
	private String custId;
	@Column(name = "QUANTITY")
	private int quantity;
	@Column(name = "DELVCHARGE")
	private double delievryCharges;
	@Column(name = "RESTCHARGE")
	private double restCharges;

	public double getRestCharges() {
		return restCharges;
	}

	public void setRestCharges(double restCharges) {
		this.restCharges = restCharges;
	}

	@Column(name = "TOTAL")
	private double total;

	// Getter and Setter to Access private Member
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getDelievryCharges() {
		return delievryCharges;
	}

	public void setDelievryCharges(double delievryCharges) {
		this.delievryCharges = delievryCharges;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return "\n Cart Id=" + cartId + "\n Quantity=" + quantity + "\n DelievryCharges=" + delievryCharges
				+ "\n Resturant Charges=" + restCharges + "\n Total=" + total;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
}

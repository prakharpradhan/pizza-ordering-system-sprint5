package com.cg.pos.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ordertable")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartid")
	@SequenceGenerator(name = "cartid", sequenceName = "cartid")
	@Column(name="ORDERID")
	private int orderId;
	@Column(name="CARTID")
	private int cartId;
	@Column(name="CUSTID")
	private String custId;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
}

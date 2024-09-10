package com.java1234.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ����ʵ��
 * @author Administrator
 *
 */
@Entity
@Table(name="t_flight")
public class Flight {

	private Integer id; // ���
	private String name; // ��������
	private String fromCity; // ��������
	private String toCity; // Ŀ�ĳ���
	private Date fromTime; // ����ʱ��
	private Date toTime; // ����ʱ��
	private int ecPrice; // ���ò�Ʊ��
	private int fcPrice; // ͷ�Ȳ�Ʊ�� 
	private int ecTicketTotal; // ���ò���Ʊ��
	private int fcTicketTotal; // ͷ�Ȳ���Ʊ��
	private int ecTicketRemain; // ���ò�ʣ��Ʊ��
	private int fcTicketRemain; // ͷ�Ȳ�ʣ��Ʊ��
	
	private Aircraft aircraft; // ʹ�õĿͻ�
	private String flightType; // �������� ���ں���/ ���ʺ���
	
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length=20)
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	
	@Column(length=20)
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	
	public Date getFromTime() {
		return fromTime;
	}
	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}
	public Date getToTime() {
		return toTime;
	}
	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}
	public int getEcPrice() {
		return ecPrice;
	}
	public void setEcPrice(int ecPrice) {
		this.ecPrice = ecPrice;
	}
	public int getFcPrice() {
		return fcPrice;
	}
	public void setFcPrice(int fcPrice) {
		this.fcPrice = fcPrice;
	}
	public int getEcTicketTotal() {
		return ecTicketTotal;
	}
	public void setEcTicketTotal(int ecTicketTotal) {
		this.ecTicketTotal = ecTicketTotal;
	}
	public int getFcTicketTotal() {
		return fcTicketTotal;
	}
	public void setFcTicketTotal(int fcTicketTotal) {
		this.fcTicketTotal = fcTicketTotal;
	}
	public int getEcTicketRemain() {
		return ecTicketRemain;
	}
	public void setEcTicketRemain(int ecTicketRemain) {
		this.ecTicketRemain = ecTicketRemain;
	}
	public int getFcTicketRemain() {
		return fcTicketRemain;
	}
	public void setFcTicketRemain(int fcTicketRemain) {
		this.fcTicketRemain = fcTicketRemain;
	}
	
	@ManyToOne
	@JoinColumn(name="aircraftId",updatable=false)
	public Aircraft getAircraft() {
		return aircraft;
	}
	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}
	
	@Column(length=20)
	public String getFlightType() {
		return flightType;
	}
	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}
	
	
	
}

package com.cenfotec.examen2V2.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TProduccion")
public class Produccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="id_finca")
	private Long id_finca;
	
	@Column(name="cant_huevos")
	private int cant_huevos;
	
	@Column(name="fecha")
	private LocalDate fecha = LocalDate.now();

	public Produccion(Long id_finca, int cant_huevos, LocalDate fecha) {
		this.id_finca = id_finca;
		this.cant_huevos = cant_huevos;
		this.fecha = fecha;
	}
	
	public Produccion() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_finca() {
		return id_finca;
	}

	public void setId_finca(Long id_finca) {
		this.id_finca = id_finca;
	}

	public int getCant_huevos() {
		return cant_huevos;
	}

	public void setCant_huevos(int cant_huevos) {
		this.cant_huevos = cant_huevos;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}

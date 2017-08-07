package net.gefco.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Maduracion {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int madu_codigo;
		private String madu_nombre;
		public Maduracion() {
			super();
			//  Auto-generated constructor stub
		}
		public Maduracion(int maduCodigo, String maduNombre) {
			super();
			madu_codigo = maduCodigo;
			madu_nombre = maduNombre;
		}
		public int getMadu_codigo() {
			return madu_codigo;
		}
		public void setMadu_codigo(int maduCodigo) {
			madu_codigo = maduCodigo;
		}
		public String getMadu_nombre() {
			return madu_nombre;
		}
		public void setMadu_nombre(String maduNombre) {
			madu_nombre = maduNombre;
		}	
		public void copiarValores(Maduracion aux) {
			madu_codigo = aux.madu_codigo;
			madu_nombre =	aux.madu_nombre;		
		}
		public void vaciar() {
			madu_codigo = 0;
			madu_nombre = null;		
		}
}

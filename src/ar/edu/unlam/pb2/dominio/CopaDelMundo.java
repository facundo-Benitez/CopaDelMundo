package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

import ar.edu.unlam.pb2.enums.Materiales;

public abstract class CopaDelMundo {
	private Long idCopa;
	private Materiales tipo;
	protected Double precio;
	
	public CopaDelMundo(Long idCopa, Materiales tipo, Double precio) {
	
		this.idCopa = idCopa;
		this.tipo = tipo;
		this.precio = precio;
	}

	public Long getIdCopa() {
		return idCopa;
	}

	public Materiales getTipo() {
		return tipo;
	}

	public Double getPrecio() {
		return precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCopa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CopaDelMundo other = (CopaDelMundo) obj;
		return Objects.equals(idCopa, other.idCopa);
	}
	
	public abstract Double obtenerPrecioCopa();
	
}

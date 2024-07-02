package ar.edu.unlam.pb2.dominio;

import ar.edu.unlam.pb2.enums.Materiales;

public class CopaDelMundoEstandar extends CopaDelMundo{
	private Integer stock;
	public CopaDelMundoEstandar(Long idCopa, Materiales tipo, Double precio,Integer stock) {
		super(idCopa, tipo, precio);
		this.stock=stock;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	@Override
	public Double obtenerPrecioCopa() {
		Double manoDeObra=0.20;
		Double precioTotal=(this.precio)+(this.precio*manoDeObra);
		return precioTotal;
	}
	
	@Override
	public String toString() {
		return "CopaDelMundoEstandar [stock=" + stock + "]";
	}

	
}

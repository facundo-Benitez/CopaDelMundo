package ar.edu.unlam.pb2.dominio;

import ar.edu.unlam.pb2.enums.Colores;
import ar.edu.unlam.pb2.enums.Materiales;

public class CopaDelMundoPersonalizada extends CopaDelMundo {

	private Colores color;
	
	public CopaDelMundoPersonalizada(Long idCopa, Materiales tipo, Double precio,Colores color) {
		super(idCopa, tipo, precio);
		this.color=color;
	}

	public Colores getColor() {
		return color;
	}

	@Override
	public Double obtenerPrecioCopa() {
		Double manoDeObra=0.15;
		Double precioTotal=(this.precio)+(this.precio*manoDeObra);
		
		switch (color) {
		case CAOBA:
			precioTotal=precioTotal+(this.precio*0.05);
			break;
		case CEDRO:
			precioTotal=precioTotal+(this.precio*0.10);
			break;
		case ROBLE_OSCURO:
			precioTotal=precioTotal+(this.precio*0.15);
			break;
		}
		return precioTotal;
	}
	

}

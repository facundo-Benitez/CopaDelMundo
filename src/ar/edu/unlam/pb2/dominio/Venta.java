package ar.edu.unlam.pb2.dominio;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Venta {
	
	private Cliente cliente;
	private Map<CopaDelMundo,Integer> reporteVenta;
	
	public Venta(Cliente cliente) {
		
		this.cliente = cliente;
		this.reporteVenta=new HashMap<>();
	
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Map<CopaDelMundo, Integer> getReporteVenta() {
		return reporteVenta;
	}

	public void agregarCopaAVenta(CopaDelMundo comprada,Integer cantidadComprada) {
		this.reporteVenta.put(comprada, cantidadComprada);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		return Objects.equals(cliente, other.cliente);
	}	
	
}

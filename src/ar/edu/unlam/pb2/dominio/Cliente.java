package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class Cliente implements Comparable<Cliente>{

	private String dniCliente;
	private String nombreCliente;
	private String apellidoCliente;
	
	
	public Cliente(String dniCliente, String nombreCliente, String apellidoCliente) {

		this.dniCliente = dniCliente;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
	}
	
	public String getDniCliente() {
		return dniCliente;
	}
	
	public String getNombreCliente() {
		return nombreCliente;
	}
	
	public String getApellidoCliente() {
		return apellidoCliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dniCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dniCliente, other.dniCliente);
	}

	@Override
	public int compareTo(Cliente o) {
		return this.dniCliente.compareTo(o.getDniCliente());
	}	
	
}

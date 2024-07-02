package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class FabricaDeCopasDelMundo {
	private List<CopaDelMundo>copas;
	private Set<Cliente>clientes;
	private Set<Venta> ventas;
	
	public FabricaDeCopasDelMundo() {
		this.copas=new ArrayList<>();
		this.clientes=new HashSet<>();
		this.ventas=new HashSet<>();

	}

	public Boolean agregarCopaDelMundo(CopaDelMundo copaDelMundo) {
		return this.copas.add(copaDelMundo);
	}

	public Boolean agregarCliente(Cliente cliente) throws ClienteDuplicadoException {
		if(this.clientes.contains(cliente)) {
			throw new ClienteDuplicadoException("No se permiten clientes duplicados");
		}
		return this.clientes.add(cliente);
	}

	public List<CopaDelMundo> obtenerCopasDelMundoEstandar() {
		List<CopaDelMundo>copasEstandar=new ArrayList<>();
		for (CopaDelMundo copaDelMundo : copas) {
			if(copaDelMundo instanceof CopaDelMundoEstandar) {
				copasEstandar.add(((CopaDelMundoEstandar)copaDelMundo));
			}
		}
		return copasEstandar;
	}

	public CopaDelMundo obtenerCopaDelMundoPorId(Long id) {
		 CopaDelMundo copaBuscada=null;
		for (CopaDelMundo copaDelMundo : copas) {
			if(copaDelMundo.getIdCopa().equals(id)) {
				return copaBuscada=copaDelMundo;
			}
		}
		return copaBuscada;
	}
	

	public Boolean agregarVenta(Venta nuevaVenta) {
		return this.ventas.add(nuevaVenta);
	}

	public void agregarCopaDelMundoEstandarAVentaDeCliente(Cliente clienteDeVenta, Long idCopaDelMundo,Integer cantidadAVender) {
		CopaDelMundoEstandar copaBuscada=(CopaDelMundoEstandar)this.obtenerCopaDelMundoPorId(idCopaDelMundo);
		Venta ventaBuscada=obtenerVentaPorCliente(clienteDeVenta);

		if(ventaBuscada!=null && copaBuscada.getStock()>=cantidadAVender) {
			ventaBuscada.agregarCopaAVenta(copaBuscada, cantidadAVender);
			copaBuscada.setStock((copaBuscada.getStock())-cantidadAVender);
			
		}
		Venta venta1=new Venta(clienteDeVenta);
		this.agregarVenta(venta1);
	}

	public void agregarCopaDelMundoPersonalizadaAVentaDeCliente(Cliente clienteDeVenta, Long idCopaDelMundo) {
		CopaDelMundoPersonalizada copaBuscada=(CopaDelMundoPersonalizada)this.obtenerCopaDelMundoPorId(idCopaDelMundo);
		Venta ventaBuscada=obtenerVentaPorCliente(clienteDeVenta);
		
		if(ventaBuscada!=null) {
			ventaBuscada.agregarCopaAVenta(copaBuscada,(Integer)1);
			this.eliminarCopaPersonalizada(copaBuscada);
			
		}
		Venta venta1=new Venta(clienteDeVenta);
		this.agregarVenta(venta1);
	}

	public Double obtenerPrecioDeCopaDelMundoPersonalizada(Long id)  {
		CopaDelMundoPersonalizada copaBuscada=((CopaDelMundoPersonalizada)this.obtenerCopaDelMundoPorId(id));
		Double precioTotal=0.0;
		precioTotal=copaBuscada.obtenerPrecioCopa();
		return precioTotal;
	}
	
	public Map<Cliente, Double> obtenerTotalDePrecioDeCopasDelMundoEstandarVendidasAClientesOrdenadasPorCliente() {
	    Map<Cliente, Double> resumenPorCliente = new TreeMap<>();
	    for (Venta venta : this.ventas) {
	        Cliente cliente = venta.getCliente();
	        Double total = 0.0;
	        for (Map.Entry<CopaDelMundo, Integer> datoCompra : venta.getReporteVenta().entrySet()) {
	            CopaDelMundo copa = datoCompra.getKey();
	            if (copa instanceof CopaDelMundoEstandar) {
	                Integer cantidad = datoCompra.getValue();
	                total += copa.obtenerPrecioCopa() * cantidad;
	            }
	        }
	        resumenPorCliente.put(cliente, total);
	    }
	    return resumenPorCliente;
	}
	
	private Venta obtenerVentaPorCliente(Cliente cliente) {
		Venta ventaBuscada=null;
		for (Venta ventaActual : ventas) {
			if(ventaActual.getCliente().equals(cliente)) {
				ventaBuscada=ventaActual;
			}
		}
		return ventaBuscada;
	}

	
	public Set<Venta> getVentas() {
		return ventas;
	}
	
	public List<CopaDelMundo> getCopas() {
		return copas;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public Boolean eliminarCopaPersonalizada (CopaDelMundoPersonalizada copaAEliminar) {
		return this.copas.remove(copaAEliminar);		
	}


}

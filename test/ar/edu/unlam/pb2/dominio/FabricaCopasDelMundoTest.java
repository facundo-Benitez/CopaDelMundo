package ar.edu.unlam.pb2.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import ar.edu.unlam.pb2.enums.Colores;
import ar.edu.unlam.pb2.enums.Materiales;

public class FabricaCopasDelMundoTest {

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoSePuedeAgregarUnaCopaDelMundoEstandar() {
		Long idCopa=01L;
		Materiales tipo=Materiales.PLASTICO; 
		Double precio=1000.00;
		Integer stock=10;
		
		FabricaDeCopasDelMundo fabrica=new FabricaDeCopasDelMundo();
		CopaDelMundoEstandar copaEstandar=new CopaDelMundoEstandar(idCopa,tipo,precio,stock);
		Boolean seAgrego=fabrica.agregarCopaDelMundo(copaEstandar);
		
		assertTrue(seAgrego);
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoSePuedeAgregarUnaCopaDelMundoPersonalizada() {
		Long idCopa=02L;
		Materiales tipo=Materiales.PLASTICO; 
		Double precio=1000.00;
		Colores color=Colores.CAOBA;
		
		FabricaDeCopasDelMundo fabrica=new FabricaDeCopasDelMundo();
		CopaDelMundoPersonalizada copaPersonalizada=new CopaDelMundoPersonalizada(idCopa,tipo,precio,color);
		Boolean seAgrego=fabrica.agregarCopaDelMundo(copaPersonalizada);
		
		assertTrue(seAgrego);
	}

	@Test (expected=ClienteDuplicadoException.class)
	public void dadoQueExisteUnaFabricaDeCopasDelMundoAlAgregarUnClienteExistenteSeLanzaUnaClienteDuplicadoException() throws ClienteDuplicadoException {
		String dniCliente="32.000.123";
		String nombreCliente="Juan";
		String apellidoCliente="Perez";
		
		FabricaDeCopasDelMundo fabrica=new FabricaDeCopasDelMundo();
		Cliente cliente=new Cliente(dniCliente, nombreCliente, apellidoCliente);
		fabrica.agregarCliente(cliente);
		Cliente clienteCopia=new Cliente(dniCliente, nombreCliente, apellidoCliente);
		fabrica.agregarCliente(clienteCopia);
	}

	@Test
	public void dadoQueExisteUnaFabricaQuePoseeCopasDelMundoSePuedenObtenerLasCopasDelMundoEstandar() {
		Long idCopa=01L;
		Materiales tipo=Materiales.PLASTICO; 
		Double precio=1000.00;
		Integer stock=10;
		Colores color=Colores.CAOBA;
		
		FabricaDeCopasDelMundo fabrica=new FabricaDeCopasDelMundo();
		CopaDelMundoEstandar copaEstandar=new CopaDelMundoEstandar(idCopa,tipo,precio,stock);
		fabrica.agregarCopaDelMundo(copaEstandar);
		CopaDelMundoPersonalizada copaPersonalizada=new CopaDelMundoPersonalizada(02L,tipo,precio,color);
		fabrica.agregarCopaDelMundo(copaPersonalizada);
		
		List<CopaDelMundo> copaEstandarBuscada=fabrica.obtenerCopasDelMundoEstandar();
		assertEquals(copaEstandar,copaEstandarBuscada.get(0));

	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoPuedoObtenerUnaCopaDelMundoPorSuId() {
		Long idCopa=01L;
		Materiales tipo=Materiales.PLASTICO; 
		Double precio=1000.00;
		Integer stock=10;
		Colores color=Colores.CAOBA;
		
		FabricaDeCopasDelMundo fabrica=new FabricaDeCopasDelMundo();
		CopaDelMundoEstandar copaEstandar=new CopaDelMundoEstandar(idCopa,tipo,precio,stock);
		fabrica.agregarCopaDelMundo(copaEstandar);
		CopaDelMundoPersonalizada copaPersonalizada=new CopaDelMundoPersonalizada(02L,tipo,precio,color);
		fabrica.agregarCopaDelMundo(copaPersonalizada);
		
		CopaDelMundoEstandar copaEstandarBuscada=(CopaDelMundoEstandar)fabrica.obtenerCopaDelMundoPorId(idCopa);
		
		assertEquals(copaEstandar,copaEstandarBuscada);
		
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoAlAgregarCincoCopasDelMundoAUnaVentaDeCopasDelMundoEstandarParaUnClienteSeDescuentanCincoUnidadesDelStockDeCopasDelMundoEstandar() throws ClienteDuplicadoException {
		Long idCopa=01L;
		Materiales tipo=Materiales.PLASTICO; 
		Double precio=1000.00;
		Integer stock=10;
		String dniCliente="32.000.111"; 
		String nombreCliente="Juan"; 
		String apellidoCliente="Perez";
		FabricaDeCopasDelMundo fabrica=new FabricaDeCopasDelMundo();
		CopaDelMundoEstandar copaEstandar=new CopaDelMundoEstandar(idCopa,tipo,precio,stock);
		fabrica.agregarCopaDelMundo(copaEstandar);
		Cliente cliente1=new Cliente(dniCliente, nombreCliente, apellidoCliente);
		fabrica.agregarCliente(cliente1);
		Venta nuevaVenta=new Venta(cliente1);
		fabrica.agregarVenta(nuevaVenta);
		fabrica.agregarCopaDelMundoEstandarAVentaDeCliente(cliente1, idCopa, 5);
		Integer stockEsperado=5;
		Integer stockObtenido=copaEstandar.getStock();
		
		assertEquals(stockEsperado,stockObtenido);		
		
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoAlAgregarUnaVentaDeCopasDelMundoPersonalizadaParaUnClienteSeRemueveLaCopaDelMundoPersonalizadaDeLaFabrica() throws ClienteDuplicadoException {
		Long idCopa=01L;
		Materiales tipo=Materiales.PLASTICO; 
		Double precio=1000.00;
		Colores color=Colores.CAOBA;
		String dniCliente="32.000.111"; 
		String nombreCliente="Juan"; 
		String apellidoCliente="Perez";
		FabricaDeCopasDelMundo fabrica=new FabricaDeCopasDelMundo();
		
		CopaDelMundoPersonalizada copaPersonalizada=new CopaDelMundoPersonalizada(02L,tipo,precio,color);
		fabrica.agregarCopaDelMundo(copaPersonalizada);		
		Cliente cliente1=new Cliente(dniCliente, nombreCliente, apellidoCliente);
		fabrica.agregarCliente(cliente1);
		Venta nuevaVenta=new Venta(cliente1);
		fabrica.agregarVenta(nuevaVenta);
		fabrica.agregarCopaDelMundoPersonalizadaAVentaDeCliente(cliente1, idCopa);
		
		CopaDelMundo copaObtenida = fabrica.obtenerCopaDelMundoPorId(idCopa);
		assertTrue(copaObtenida == null);
		
	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConCopasDelMundoPersonalizadasSePuedeObtenerElPrecioDeUnaCopaDelMundoPersonalizada() {
		Long idCopa=(long)02;
		Materiales tipo=Materiales.PLASTICO; 
		Double precio=1000.00;
		Colores color=Colores.CAOBA;

		FabricaDeCopasDelMundo fabrica=new FabricaDeCopasDelMundo();		
		CopaDelMundoPersonalizada copaPersonalizada=new CopaDelMundoPersonalizada(idCopa,tipo,precio,color);
		fabrica.agregarCopaDelMundo(copaPersonalizada);
		Double valorObtenido=fabrica.obtenerPrecioDeCopaDelMundoPersonalizada(idCopa);
		Double valorEsperado=1200.00;
		
		assertEquals(valorEsperado,valorObtenido);	

	}

	@Test
	public void dadoQueExisteUnaFabricaDeCopasDelMundoConVentasDeCopasDelMundoEstandarYPersonalizadasVendidasAClientesSePuedeObtenerUnMapaConClaveClienteYTotalDeVentasDeCopasEstandarOrdenadoPorCliente() throws ClienteDuplicadoException {
		Long idCopa=01L;
		Materiales tipo=Materiales.PLASTICO; 
		Double precio=1000.00;
		Integer stock=10;
		String dniCliente="32.000.111"; 
		String nombreCliente="Juan"; 
		String apellidoCliente="Perez";
		
		FabricaDeCopasDelMundo fabrica=new FabricaDeCopasDelMundo();
		
		CopaDelMundoEstandar copaEstandar=new CopaDelMundoEstandar(idCopa,tipo,precio,stock);
		fabrica.agregarCopaDelMundo(copaEstandar);
		
		Cliente cliente1=new Cliente(dniCliente, nombreCliente, apellidoCliente);
		fabrica.agregarCliente(cliente1);
		Venta nuevaVenta1=new Venta(cliente1);
		fabrica.agregarVenta(nuevaVenta1);
		fabrica.agregarCopaDelMundoEstandarAVentaDeCliente(cliente1, idCopa, 3);
		
		Cliente cliente2=new Cliente("20.000.111", "Maria", "Paz");
		fabrica.agregarCliente(cliente2);
		Venta nuevaVenta2=new Venta(cliente2);
		fabrica.agregarVenta(nuevaVenta2);
		fabrica.agregarCopaDelMundoEstandarAVentaDeCliente(cliente2, idCopa, 4);
		
		Cliente cliente3=new Cliente("45.111.222", "Alan", "Brito");
		fabrica.agregarCliente(cliente3);
		Venta nuevaVenta3=new Venta(cliente3);
		fabrica.agregarVenta(nuevaVenta3);
		fabrica.agregarCopaDelMundoEstandarAVentaDeCliente(cliente3, idCopa, 1);
		
		Map<Cliente, Double> obtenerTotalDePrecioDeCopasDelMundoEstandarVendidas=fabrica.obtenerTotalDePrecioDeCopasDelMundoEstandarVendidasAClientesOrdenadasPorCliente();
		int i=0;
		
		for (Map.Entry<Cliente, Double> resumen : obtenerTotalDePrecioDeCopasDelMundoEstandarVendidas.entrySet()) {
            Cliente key = resumen.getKey();
            Double valor = resumen.getValue();
			switch (i) {
			case 0:
				assertEquals(cliente2, key);
				assertEquals((Double)4800.00, valor,0.01);
				break;
			case 1:
				assertEquals(cliente1, key);
				assertEquals((Double)3600.00, valor,0.01);
				
				break;
			case 2:
				assertEquals(cliente3, key);
				assertEquals((Double)1200.00, valor,0.01);				
				break;
			default:
				break;
			}
		
			i++;
		}
	}
	
}

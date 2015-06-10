/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.chiri.ing.puc.integra7v2;

import java.io.Serializable;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.ClientRequest;

/**
 * REST Web Service
 *
 * @author fernando
 */
public class OrdenCompra implements Serializable{

    private String id;
    private Date fecha_creacion;
    private String canal;
    private String proveedor;
    private String  cliente;
    private String  sku;
    private int  cantidad;
    private int cantidad_despachada;
    private int  precio_unitario;
    private Date fecha_entrega;
    private Date[] fechas_despachos;
    private String estado;
    private String motivo_rechazo;
    private String motivo_anulacion;
    private String notas;
    private String id_factura;
    /**
     * Creates a new instance of OrdenCompra
     */
    private OrdenCompra(String id) {
        this.id = id;
    }

    public OrdenCompra() {
        
    }
    
    /**
     * Get instance of the OrdenCompra
     * @param id
     * @return 
     */
    public static OrdenCompra getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of OrdenCompra class.
        
        return new OrdenCompra(id);
    }

    /**
     * Retrieves representation of an instance of cl.chiri.ing.puc.integra7v2.OrdenCompra
     * @param id
     * @return an instance of java.lang.String
     * @throws java.lang.Exception
     */
    @GET
    @Produces("application/json")
    public Response get(String id) throws Exception {
        ClientRequest request;
        request = new ClientRequest("http://localhost:83/obtener/"+id);
        Response res;
        request.header("Content-Type", "application/json");
        res = request.get(String.class);
        return res;
        
    }
    /**
     * PUT method for updating or creating an instance of OrdenCompra
     * @param canal
     * @param cantidad
     * @param sku
     * @param proveedor
     * @param precioUnitario
     * @param fechaEntrega
     * @param notas
     * @return an HTTP response with content of the updated or created resource.
     * @throws java.lang.Exception
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(String canal, int cantidad, String sku, String proveedor, int precioUnitario,String fechaEntrega, String notas ) throws Exception {
    	ClientRequest request;
        request = new ClientRequest("http://localhost:83/crear");
        Response res;
        request.header("Content-Type", "application/json");
        request.accept("application/json");

        String input = "{\"cliente\":\"grupo7\",\"canal\": \""+canal+"\",\"cantidad\": \""+cantidad+"\",\"sku\": \""+sku+"\",\"proveedor\": \""+proveedor+"\",\"precioUnitario\":\""+precioUnitario+"\",\"notas\":\""+notas+"\",\"fechaEntrega\": \""+fechaEntrega + "\"}";
        request.body(MediaType.APPLICATION_JSON, input);
        res = request.put(String.class);
        return res; 
    }
    
    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response receipt(String id) throws Exception {
    	ClientRequest request;
        request = new ClientRequest("http://localhost:83/recepcionar/"+id);
        Response res;
        request.header("Content-Type", "application/json");
        request.accept("application/json");
        String input = "{}";
        request.body(MediaType.APPLICATION_JSON, input);
        res = request.post(String.class);
        return res;    	
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response reject(String id, String motivo) throws Exception {
    	ClientRequest request;
        request = new ClientRequest("http://localhost:83/rechazar/"+id);
        Response res;
        request.header("Content-Type", "application/json");
        request.accept("application/json");

        String input = "{\"rechazo\":\""+motivo+"\"}";
        request.body(MediaType.APPLICATION_JSON, input);
        //request.body(MediaType.WILDCARD_TYPE, canal)
        res = request.post(String.class);
        return res;    	
    }

    /**
     * DELETE method for resource OrdenCompra
     * @param id
     * @param motivo
     * @return 
     * @throws java.lang.Exception
     */
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public Response delete(String id, String motivo) throws Exception {
    	ClientRequest request;
        request = new ClientRequest("http://localhost:83/anular/"+id);
        Response res;
        request.accept("application/json");
        request.header("Content-Type", "application/json");
       
        String input = "{\"anulacion\":\""+motivo+"\"}";
        request.body(MediaType.APPLICATION_JSON, input);
        //request.body(MediaType.WILDCARD_TYPE, canal)
        res = request.delete(String.class);
        return res;    	
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad_despachada() {
        return cantidad_despachada;
    }

    public void setCantidad_despachada(int cantidad_despachada) {
        this.cantidad_despachada = cantidad_despachada;
    }

    public int getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(int precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Date[] getFechas_despachos() {
        return fechas_despachos;
    }

    public void setFechas_despachos(Date[] fechas_despachos) {
        this.fechas_despachos = fechas_despachos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo_rechazo() {
        return motivo_rechazo;
    }

    public void setMotivo_rechazo(String motivo_rechazo) {
        this.motivo_rechazo = motivo_rechazo;
    }

    public String getMotivo_anulacion() {
        return motivo_anulacion;
    }

    public void setMotivo_anulacion(String motivo_anulacion) {
        this.motivo_anulacion = motivo_anulacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getId_factura() {
        return id_factura;
    }

    public void setId_factura(String id_factura) {
        this.id_factura = id_factura;
    }
    
    
}

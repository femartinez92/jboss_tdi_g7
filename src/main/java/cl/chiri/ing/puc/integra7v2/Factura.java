/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.chiri.ing.puc.integra7v2;

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
public class Factura {

    private String id;
    private Date fecha_creacion;
    private String proveedor;
    private String  cliente;
    private int valor_bruto;
    private int iva;
    private int valor_total;
    private String  estado_pago;
    private Date  fecha_pago;
    private String  id_orden_compra;
    private String motivo_rechazo;
    private String  motivo_anulacion;
    /**
     * Creates a new instance of Factura
     */
    private Factura(String id) {
        this.id = id;
    }

    public Factura() {
    }

    /**
     * Get instance of the Factura
     */
    public static Factura getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of Factura class.
        return new Factura(id);
    }

    /**
     * Retrieves representation of an instance of cl.chiri.ing.puc.integra7v2.Factura
     * @param id
     * @return an instance of java.lang.String
     * @throws java.lang.Exception
     */
    @GET
    @Produces("application/json")
    public Response obtener_factura(String id) throws Exception {
        ClientRequest request;
        request = new ClientRequest("http://localhost:85/"+id);
        request.header("Content-Type", "application/json");
        Response res;
        res = request.get(String.class);
        return res;
    }

    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response pay_invoice(String id) throws Exception{
        ClientRequest request;
        request = new ClientRequest("http://localhost:85/pay");
        request.header("Content-Type", "application/json");
        request.accept("application/json");
        String input = "{\"id\":\""+id+"\"}";
        request.body(MediaType.APPLICATION_JSON,input);
        Response res;
        res = request.post(String.class);
        return res;
        
    }
    
    /**
     *
     * @param id_factura
     * @param motivo
     * @return
     * @throws Exception
     */
    @POST
    @Produces("application/json")
    public Response reject_invoice(String id_factura, String motivo) throws Exception {
        ClientRequest request;
        request = new ClientRequest("http://localhost:85/reject");
        request.header("Content-Type", "application/json");
        request.accept("application/json");
        String input;
        input = "{\"id\":\""+id_factura+"\",\"motivo\":\""+motivo+"\"}";
        request.body(MediaType.APPLICATION_JSON,input);
        Response res;
        res = request.post(String.class);
        return res;
    }
    
    
    @Produces("application/json")
    public Response cancel_invoice(String id_factura, String motivo) throws Exception {
        ClientRequest request;
        request = new ClientRequest("http://localhost:85/cancel");
        request.header("Content-Type", "application/json");
        request.accept("application/json");
        String input;
        input = "{\"id\":\""+id_factura+"\",\"motivo\":\""+motivo+"\"}";
        request.body(MediaType.APPLICATION_JSON,input);
        Response res;
        res = request.post(String.class);
        return res;
    }
    
    
    /**
     * PUT method for updating or creating an instance of Factura
     * @param orden_compra_id
     * @return 
     * @throws java.lang.Exception
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response emitir_factura(String orden_compra_id) throws Exception {
        ClientRequest request;
        request = new ClientRequest("http://localhost:85/");
        Response res;
        request.header("Content-Type", "application/json");
        request.accept("application/json");

        String input = "{\"oc\":\""+orden_compra_id+"\"}";
        request.body(MediaType.APPLICATION_JSON, input);
        res = request.put(String.class);
        return res; 
    }
    
        public Response boleta(String proveedor_id, String cliente_id, int total) throws Exception {
        ClientRequest request;
        request = new ClientRequest("http://localhost:85/boleta/");
        Response res;
        request.header("Content-Type", "application/json");
        request.accept("application/json");

        String input = "{\"proveedor\":\"" + proveedor_id + "\",\"cliente\":\"" + cliente_id + "\",\"total\":" + total + "}";
        request.body(MediaType.APPLICATION_JSON, input);
        res = request.put(String.class);
        return res;
    }

    /**
     * DELETE method for resource Factura
     */
    @DELETE
    public void delete() {
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

    public int getValor_bruto() {
        return valor_bruto;
    }

    public void setValor_bruto(int valor_bruto) {
        this.valor_bruto = valor_bruto;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public int getValor_total() {
        return valor_total;
    }

    public void setValor_total(int valor_total) {
        this.valor_total = valor_total;
    }

    public String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(String estado_pago) {
        this.estado_pago = estado_pago;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getId_orden_compra() {
        return id_orden_compra;
    }

    public void setId_orden_compra(String id_orden_compra) {
        this.id_orden_compra = id_orden_compra;
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

    
    
}

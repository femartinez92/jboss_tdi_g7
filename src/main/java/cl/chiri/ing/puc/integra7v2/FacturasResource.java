/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.chiri.ing.puc.integra7v2;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author fernando
 */
@Path("/facturas")
public class FacturasResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FacturasResource
     */
    public FacturasResource() {
    }

    /**
     * Retrieves representation of an instance of cl.chiri.ing.puc.integra7v2.FacturasResource
     * @param id
     * @return an instance of java.lang.String
     * @throws java.lang.Exception
     */
    @GET
    @Produces("application/json")
    @Path("/obtener/{id}")
    public Response getJson(@PathParam("id") String id ) throws Exception {
        Factura fac = new Factura();
        return fac.obtener_factura(id);
    }

    /**
     * POST method for creating an instance of Factura
     * @param id_factura
     * @return an HTTP response with content of the created resource
     * @throws java.lang.Exception
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("pay/{id_factura}")
    public Response pay(@PathParam("id_factura") String id_factura) throws Exception {
        Factura fac = new Factura();
        return fac.pay_invoice(id_factura);
    }
    
    /**
     *
     * @param id_oc
     * @return
     * @throws Exception
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/crear/{id_oc}")
    public Response create_invoice(@PathParam("id_oc") String id_oc) throws Exception{
        Factura fac = new Factura();
        return fac.emitir_factura(id_oc);
    } 
    
    /**
     *
     * @param id_factura
     * @param motivo
     * @return
     * @throws Exception
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/rechazar/{id_factura}")
    public Response reject(@PathParam("id_factura") String id_factura, @QueryParam("motivo") String motivo) throws Exception {
        Factura fac = new Factura();
        return fac.reject_invoice(id_factura, motivo);
    }
    
    /**
     *
     * @param id_factura
     * @param motivo
     * @return
     * @throws Exception
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/cancelar/{id_factura}")
    public Response cancel(@PathParam("id_factura") String id_factura, @QueryParam("motivo") String motivo) throws Exception {
        Factura fac = new Factura();
        return fac.cancel_invoice(id_factura, motivo);
    }
    
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/boleta")
    public Response boleta(@QueryParam("proveedor_id") String proveedor_id, @QueryParam("cliente_id") String cliente_id, @QueryParam("total") String total) throws Exception {
        Factura fac = new Factura();
        return fac.boleta(proveedor_id, cliente_id, Integer.parseInt(total));
    }
    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public Factura getFactura(@PathParam("id") String id) {
        return Factura.getInstance(id);
    }
}

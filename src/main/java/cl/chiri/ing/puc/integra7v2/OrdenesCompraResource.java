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
import javax.ws.rs.DELETE;
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
@Path("/oc")
public class OrdenesCompraResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of OrdenesCompraResource
     */
    public OrdenesCompraResource() {
    }

    /**
     * Retrieves representation of an instance of cl.chiri.ing.puc.integra7v2.OrdenesCompraResource
     * @param id
     * @return an instance of java.lang.String
     * @throws java.lang.Exception
     */
    @GET
    @Produces("application/json")
    @Path("/obtener/{id}")
    public Response get_order(@PathParam("id") String id ) throws Exception {
        OrdenCompra oc = new OrdenCompra();
        return oc.get(id);
    }
    
    /**
     *
     * @param canal
     * @param cantidad
     * @param sku
     * @param proveedor
     * @param precioUnitario
     * @param notas
     * @param fechaEntrega
     * @return
     * @throws Exception
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/crear")
    public Response create_order(@QueryParam("canal") String canal,@QueryParam("cantidad") int cantidad,@QueryParam("sku") String sku,@QueryParam("proveedor") String proveedor,@QueryParam("precioUnitario") int precioUnitario,@QueryParam("notas") String notas,@QueryParam("fechaEntrega") String fechaEntrega) throws Exception {
            OrdenCompra oc = new OrdenCompra();
            return oc.create(canal,cantidad,sku,proveedor,precioUnitario,fechaEntrega,notas);
    }


    /**
     * POST method for creating an instance of OrdenCompra
     * @param id
     * @return an HTTP response with content of the created resource
     * @throws java.lang.Exception
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/recepcionar/{id}")
    public Response receipt_order(@PathParam("id") String id ) throws Exception {
        OrdenCompra oc = new OrdenCompra();
        return oc.receipt(id);
    }
    
    /**
     *
     * @param id
     * @param motivo
     * @return
     * @throws Exception
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/rechazar/{id}")
    public Response reject_order(@PathParam("id") String id, @QueryParam("motivo") String motivo) throws Exception {
    	//Ver como se lee el request.body
    	OrdenCompra oc = new OrdenCompra();
        //return motivo;
        return oc.reject(id,motivo);
    }
    
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/anular/{id}")
    public Response delete_order(@PathParam("id") String id, @QueryParam("motivo") String motivo ) throws Exception {
    	OrdenCompra oc = new OrdenCompra();
        return oc.delete(id, motivo);
    }

    /**
     * Sub-resource locator method for {id}
     * @param id
     * @return 
     */
    @Path("{id}")
    public OrdenCompra getOrdenCompra(@PathParam("id") String id) {
        return OrdenCompra.getInstance(id);
    }
}
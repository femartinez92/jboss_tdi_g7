/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.chiri.ing.puc.integra7v2;

import cl.uc.integracion.banco.servicios.BadParametersException;
import cl.uc.integracion.banco.servicios.ErrorException;
import cl.uc.integracion.banco.servicios.Transaccion;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author fernando
 */
@Path("banco")
public class BancoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BancoResource
     */
    public BancoResource() {
    }
    
    /**
     *
     * @param id
     * @return
     * @throws BadParametersException
     * @throws ErrorException
     */
    @GET
    @Produces("application/json")
    @Path("/cuenta/{id}")
    public String obtener_cuenta(@PathParam("id") String id) throws BadParametersException, ErrorException{
       String res;
        try { // Call Web Service Operation
                cl.uc.integracion.banco.servicios.Cuenta_Service service = new cl.uc.integracion.banco.servicios.Cuenta_Service();
                cl.uc.integracion.banco.servicios.Cuenta port = service.getCuentaPort();
                // TODO initialize WS operation arguments here
                // TODO process result here
                cl.uc.integracion.banco.servicios.CuentaBancoArray result = port.getCuenta(id);
                res= "{\"saldo\":\""+ result.getItem().get(0).getSaldo()+"\"";
                res += ",\"id\":\""+result.getItem().get(0).getId()+"\"";
                res += ",\"grupo\":\""+result.getItem().get(0).getGrupo()+"\"";
                res += ",\"V\":\""+result.getItem().get(0).getV()+"\"}";

                
            } catch (Exception ex) {
                res = "{\"Excepcion\":\""+ ex.getMessage()+"\"}";// TODO handle custom exceptions here
            }
        
        return res;
    }
    
    @POST
    @Produces("application/json")
    @Path("/cartola")
    public String getCartola(@QueryParam("fecha_inicio") float fecha_inicio,
                               @QueryParam("fecha_termino") float fecha_termino,
                               @QueryParam("id") String id,
                               @QueryParam("limit") int limit){
        String res;
        try { // Call Web Service Operation
            cl.uc.integracion.banco.servicios.Cuenta_Service service = new cl.uc.integracion.banco.servicios.Cuenta_Service();
            cl.uc.integracion.banco.servicios.Cuenta port = service.getCuentaPort();
            // TODO initialize WS operation arguments here
            cl.uc.integracion.banco.servicios.GetCartola cartola = new cl.uc.integracion.banco.servicios.GetCartola();
            cartola.setFin(fecha_termino);
            cartola.setInicio(fecha_inicio);
            if (limit>0) {
                cartola.setLimit(limit);    
            }else{
                cartola.setLimit(100);
            }
            cartola.setId(id);

            // TODO process result here
            cl.uc.integracion.banco.servicios.Cartola result = port.getCartola(cartola);
            int size = result.getTransacciones().size();
            res = "[";
            for (int i = 0; i < size; i++) {
                Transaccion trx = result.getTransacciones().get(i);
                if(i!=0){
                    res += ",";
                }
                res += "{\"created_at\":\""+trx.getCreatedAt()+"\",";
                res += "\"updated_at\":\""+trx.getUpdatedAt()+"\",";
                res += "\"id\":\""+trx.getId()+"\",";
                res += "\"origen\":\""+trx.getOrigen()+"\",";
                res += "\"monto\":\""+trx.getMonto()+"\",";
                res += "\"destino\":\""+trx.getDestino()+"\"}";
              
            }
            res += "]";
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            res = "{\"Excepcion\":\""+ ex.getMessage()+"\"}";
        }
        return res;

    }
    
    @GET
    @Produces("application/json")
    @Path("/transaccion/{id}")
    public String obtener_transaccion(@PathParam("id") String id){
       String res;
       
       try { // Call Web Service Operation
           cl.uc.integracion.banco.servicios.Trx_Service service = new cl.uc.integracion.banco.servicios.Trx_Service();
           cl.uc.integracion.banco.servicios.Trx port = service.getTrxPort();
           // TODO initialize WS operation arguments here
           //java.lang.String id = "";
           // TODO process result here
           cl.uc.integracion.banco.servicios.TransaccionArray result = port.getTransaccion(id);
           Transaccion trx = result.getItem().get(0);
           res = "{\"created_at\":\""+trx.getCreatedAt()+"\",";
           res += "\"updated_at\":\""+trx.getUpdatedAt()+"\",";
           res += "\"id\":\""+trx.getId()+"\",";
           res += "\"origen\":\""+trx.getOrigen()+"\",";
           res += "\"monto\":\""+trx.getMonto()+"\",";
           res += "\"destino\":\""+trx.getDestino()+"\"}";
       } catch (Exception ex) {
           // TODO handle custom exceptions here
           res = "{\"Excepcion\":\""+ ex.getMessage()+"\"}";
       }
       return res;
    }
    
    @POST
    @Produces("application/json")
    @Path("/transferir")
    public String transferir(@QueryParam("origen") String origen,
                             @QueryParam("destino") String destino,
                             @QueryParam("monto") int monto){
        String res;
        
        try { // Call Web Service Operation
            cl.uc.integracion.banco.servicios.Trx_Service service = new cl.uc.integracion.banco.servicios.Trx_Service();
            cl.uc.integracion.banco.servicios.Trx port = service.getTrxPort();
            // TODO initialize WS operation arguments here
            cl.uc.integracion.banco.servicios.CrearTransaccion trx = new cl.uc.integracion.banco.servicios.CrearTransaccion();
            trx.setOrigen(origen);
            trx.setDestino(destino);
            trx.setMonto(monto);
            // TODO process result here
            cl.uc.integracion.banco.servicios.Transaccion result = port.crearTransaccion(trx);
            res = "{\"created_at\":\""+result.getCreatedAt()+"\",";
            res += "\"updated_at\":\""+result.getUpdatedAt()+"\",";
            res += "\"id\":\""+result.getId()+"\",";
            res += "\"origen\":\""+result.getOrigen()+"\",";
            res += "\"monto\":\""+result.getMonto()+"\",";
            res += "\"destino\":\""+result.getDestino()+"\"}";
        
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            res = "{\"Excepcion\":\""+ ex.getMessage()+"\"}";
        }

        return res;
        
    }
    
}

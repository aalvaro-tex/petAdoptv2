/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.json;

import com.alvaro.pse.petadopt.entities.Mascota;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author alvar
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class MascotaWriter  implements MessageBodyWriter<Mascota>{
    
    /**
     *
     * @param type
     * @param type1
     * @param antns
     * @param mt
     * @return
     */
    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return Mascota.class.isAssignableFrom(type);
    }

    /**
     *
     * @param t
     * @param type
     * @param genericType
     * @param annotations
     * @param mediaType
     * @return
     */
    @Override
    public long getSize(Mascota t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
                }

    /**
     *
     * @param t
     * @param type
     * @param type1
     * @param antns
     * @param mt
     * @param mm
     * @param out
     * @throws IOException
     * @throws WebApplicationException
     */
    @Override
    public void writeTo(Mascota t, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        JsonGenerator gen = Json.createGenerator(out);
        gen.writeStartObject()
                .write("nombre", t.getNombre())
                .write("especie", t.getEspecie())
                .write("raza", t.getRaza())
                .write("edad", t.getEdad())
                .write("estadoSalud", t.getEstadoSalud())
                .write("costeAdopcion", t.getCosteAdopcion())
                .write("foto", t.getFoto())
                .write("idRefugio", t.getIdRefugio())
                .write("fechaPublicacion", t.getFechaPublicacion())
                .write("idCliente", t.getIdCliente())
                .write("estado", t.getEstado())
                .write("fechaAdopcion", t.getFechaAdopcion())
                .write("fechaSolicitud", t.getFechaSolicitud())
                .writeEnd();
        gen.flush();
    }
    
    
}

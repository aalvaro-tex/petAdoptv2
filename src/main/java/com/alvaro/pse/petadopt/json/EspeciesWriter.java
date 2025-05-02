/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.json;

import com.alvaro.pse.petadopt.entities.Cliente;
import com.alvaro.pse.petadopt.entities.Especie;
import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.entities.Usuario;
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
public class EspeciesWriter implements MessageBodyWriter<Especie>{

    @Override
    public boolean isWriteable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return Especie.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Especie t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
                }

    @Override
    public void writeTo(Especie t, Class<?> type, Type type1, Annotation[] antns, MediaType mt, MultivaluedMap<String, Object> mm, OutputStream out) throws IOException, WebApplicationException {
        JsonGenerator gen = Json.createGenerator(out);
        gen.writeStartObject()
                .write("nombre", t.getNombre())
                .writeEnd();
        gen.flush();
    }
}
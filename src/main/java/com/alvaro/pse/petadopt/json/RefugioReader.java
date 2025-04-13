/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.json;

import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.entities.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author alvar
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class RefugioReader implements MessageBodyReader<Refugio> {

    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return Usuario.class.isAssignableFrom(type);
    }

    @Override
    public Refugio readFrom(Class<Refugio> type,
            Type genericType,
             Annotation[] annotations,
            MediaType mediaType,
             MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream)
            throws IOException, WebApplicationException {

        Refugio refugio = new Refugio();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "id":
                            refugio.setId(parser.getLong());
                            break;
                        case "cif":
                            refugio.setCif(parser.getString());
                            break;
                        case "domicilioSocial":
                            refugio.setDomicilioSocial(parser.getString());
                            break;
                        case "telefono":
                            refugio.setTelefono(parser.getString());
                            break;
                        case "nombre":
                            refugio.setNombre(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return refugio;
    }
}

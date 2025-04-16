/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.json;

import com.alvaro.pse.petadopt.entities.Cliente;
import com.alvaro.pse.petadopt.entities.HistoricoSolicitudes;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

/**
 *
 * @author alvar
 */
public class HistoricoSolicitudesReader implements MessageBodyReader<HistoricoSolicitudes>{

    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return HistoricoSolicitudes.class.isAssignableFrom(type);
    }

    @Override
    public HistoricoSolicitudes readFrom(Class<HistoricoSolicitudes> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream)
            throws IOException, WebApplicationException {
        
        HistoricoSolicitudes h = new HistoricoSolicitudes();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "id":
                            h.setId(parser.getLong());
                            break;
                        case "idCliente":
                            h.setIdCliente(parser.getLong());
                            break;
                        case "idRefugio":
                            h.setIdRefugio(parser.getLong());
                            break;
                        case "telefono":
                            h.setIdMascota(parser.getLong());
                            break;
                        case "estado":
                            h.setEstado(parser.getString());
                            break;
                        case "fechaModificacion":
                            h.setFechaModificacion(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return h;
    }
    
}

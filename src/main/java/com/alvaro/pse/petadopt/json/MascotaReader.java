/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.json;

import com.alvaro.pse.petadopt.entities.Mascota;
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
public class MascotaReader implements MessageBodyReader<Mascota> {
    
    /**
     *
     * @param type
     * @param type1
     * @param antns
     * @param mt
     * @return
     */
    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return Mascota.class.isAssignableFrom(type);
    }
    
    /**
     *
     * @param type
     * @param genericType
     * @param annotations
     * @param mediaType
     * @param httpHeaders
     * @param entityStream
     * @return
     * @throws IOException
     * @throws WebApplicationException
     */
    @Override
    public Mascota readFrom(Class<Mascota> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream)
            throws IOException, WebApplicationException {
        
        Mascota mascota = new Mascota();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "id":
                            mascota.setId(parser.getLong());
                            break;
                        case "nombre":
                            mascota.setNombre(parser.getString());
                            break;
                        case "especie":
                            mascota.setEspecie(parser.getString());
                            break;
                        case "raza":
                            mascota.setRaza(parser.getString());
                            break;
                        case "edad":
                            mascota.setEdad(parser.getInt());
                            break;
                        case "estadoSalud":
                            mascota.setEstadoSalud(parser.getString());
                            break;
                        case "costeAdopcion":
                            mascota.setCosteAdopcion(parser.getLong());
                            break;
                        case "foto":
                            mascota.setFoto(parser.getString());
                            break;
                        case "idRefugio":
                            mascota.setIdRefugio(parser.getLong());
                            break;
                        case "idCliente":
                            mascota.setIdCliente(parser.getLong());
                            break;
                        case "fechaPublicacion":
                            mascota.setFechaPublicacion(parser.getString());
                            break;
                        case "fechaAdopcion":
                            mascota.setFechaAdopcion(parser.getString());
                            break;
                        case "estado":
                            mascota.setEstado(parser.getString());
                            break;
                        case "fechaSolicitud":
                            mascota.setFechaSolicitud(parser.getString());
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return mascota;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.json;

import com.alvaro.pse.petadopt.entities.Chat;
import com.alvaro.pse.petadopt.solicitudes.AdoptanteDTO;
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
public class AdoptanteReader implements MessageBodyReader<AdoptanteDTO>{
    
    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return AdoptanteDTO.class.isAssignableFrom(type);
    }

    @Override
    public AdoptanteDTO readFrom(Class<AdoptanteDTO> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream)
            throws IOException, WebApplicationException {

        AdoptanteDTO adoptanteDto = new AdoptanteDTO();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "email":
                            adoptanteDto.setEmail(parser.getString());
                        case "id":
                            adoptanteDto.setId(parser.getLong());
                            break;
                        case "listaNegra":
                            adoptanteDto.setListaNegra(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return adoptanteDto;
    }
    
}

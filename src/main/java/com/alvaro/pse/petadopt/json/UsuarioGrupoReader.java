/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.json;

import com.alvaro.pse.petadopt.entities.UsuarioGrupo;
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
public class UsuarioGrupoReader implements MessageBodyReader<UsuarioGrupo>{
    
    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return UsuarioGrupo.class.isAssignableFrom(type);
    }

    @Override
    public UsuarioGrupo readFrom(Class<UsuarioGrupo> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream)
            throws IOException, WebApplicationException {

        UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "email":
                            usuarioGrupo.setEmail(parser.getString());
                        case "nombre_grupo":
                            usuarioGrupo.setNombreRol(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return usuarioGrupo;
    }
    
}

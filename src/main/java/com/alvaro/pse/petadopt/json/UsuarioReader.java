/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.json;

import com.alvaro.pse.petadopt.entities.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.stream.JsonParser;
import javax.json.Json;
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
public class UsuarioReader implements MessageBodyReader<Usuario> {

    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return Usuario.class.isAssignableFrom(type);
    }

    @Override
    public Usuario readFrom(Class<Usuario> type,
            Type genericType,
             Annotation[] annotations,
            MediaType mediaType,
             MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream)
            throws IOException, WebApplicationException {

        Usuario usuario = new Usuario();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "id":
                            usuario.setId(parser.getLong());
                            break;
                        case "email":
                            usuario.setEmail(parser.getString());
                            break;
                        case "password":
                            usuario.setPassword(parser.getString());
                            break;
                        case "foto":
                            usuario.setFoto(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return usuario;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.json;

import com.alvaro.pse.petadopt.entities.Cliente;
import com.alvaro.pse.petadopt.entities.Refugio;
import com.alvaro.pse.petadopt.entities.Usuario;
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
public class ClienteReader implements MessageBodyReader<Cliente> {

    @Override
    public boolean isReadable(Class<?> type, Type type1, Annotation[] antns, MediaType mt) {
        return Usuario.class.isAssignableFrom(type);
    }

    @Override
    public Cliente readFrom(Class<Cliente> type,
            Type genericType,
            Annotation[] annotations,
            MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders,
            InputStream entityStream)
            throws IOException, WebApplicationException {

        Cliente cliente = new Cliente();
        JsonParser parser = Json.createParser(entityStream);
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    switch (key) {
                        case "id":
                            cliente.setId(parser.getLong());
                            break;
                        case "nif":
                            cliente.setNif(parser.getString());
                            break;
                        case "domicilio":
                            cliente.setDomicilio(parser.getString());
                            break;
                        case "telefono":
                            cliente.setTelefono(parser.getString());
                            break;
                        case "nombre":
                            cliente.setNombre(parser.getString());
                            break;
                        case "apellidos":
                            cliente.setApellidos(parser.getString());
                            break;
                        case "fechaNacimiento":
                            cliente.setFechaNacimiento(parser.getString());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return cliente;
    }
}

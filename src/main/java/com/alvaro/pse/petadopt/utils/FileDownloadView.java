/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.utils;

import java.io.InputStream;
import java.util.function.Supplier;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.SerializableSupplier;

@Named
@RequestScoped
@ManagedBean
public class FileDownloadView {

    private StreamedContent file;

    public void download(final String ruta) {
        System.out.println("Entro");
        file = DefaultStreamedContent.builder()
                .name("downloaded_boromir.pdf")
                .contentType("application/pdf")
                .stream(new SerializableSupplier<InputStream>() {
                    @Override
                    public InputStream get() {
                        return getClass().getResourceAsStream("certificadoPlantilla.pdf");
                    }
                }).build();
    }

    public StreamedContent getFile() {
        return file;
    }

}

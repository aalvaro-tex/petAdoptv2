/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.utils;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author alvar
 */
@Named
@ManagedBean
@SessionScoped
public class PDFUtils implements Serializable {

    /**
     *
     * @param nombreCliente
     * @param nombreMascota
     * @param nombreRefugio
     * @param precio
     * @param idOperacion
     * @param fechaAdopcion
     * @param direccionRefugio
     * @param telefonoRefugio
     * @param base64firma
     * @return un objeto DefaultSreamedContent 
     * @throws IOException
     */
    public DefaultStreamedContent generateCertificadoAdopcionPDF(
            String nombreCliente,
            String nombreMascota,
            String nombreRefugio,
            String precio,
            String idOperacion,
            String fechaAdopcion,
            String direccionRefugio,
            String telefonoRefugio,
            String base64firma
    ) throws IOException {
        File file = new File("C:/Users/alvar/OneDrive/Documentos/NetBeansProjects/petAdoptv2/src/main/java/com/alvaro/pse/petadopt/utils/certificadoPlantilla.pdf");
        PDDocument document = PDDocument.load(file);
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

        // Nombre del cliente
        contentStream.beginText();
        //Setting the font to the Content stream  
        contentStream.setFont(PDType1Font.HELVETICA_BOLD_OBLIQUE, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        //Setting the position for the line 
        contentStream.newLineAtOffset(89, 772);
        String text = nombreCliente;
        //Adding text in the form of string 
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();

        // ID Operacion
        contentStream.beginText();
        //Setting the font to the Content stream  
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        //Setting the position for the line 
        contentStream.newLineAtOffset(305, 609);
        text = idOperacion;
        //Adding text in the form of string 
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();

        // Nombre mascota
        contentStream.beginText();
        //Setting the font to the Content stream  
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        //Setting the position for the line 
        contentStream.newLineAtOffset(305, 585);
        text = nombreMascota;
        //Adding text in the form of string 
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();

        // Coste
        contentStream.beginText();
        //Setting the font to the Content stream  
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        //Setting the position for the line 
        contentStream.newLineAtOffset(305, 513);
        text = precio + " euros";
        //Adding text in the form of string 
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();

        // Fecha adopción
        contentStream.beginText();
        //Setting the font to the Content stream  
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        //Setting the position for the line 
        contentStream.newLineAtOffset(305, 537);
        text = fechaAdopcion;
        //Adding text in the form of string 
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();

        // Nombre refugio
        contentStream.beginText();
        //Setting the font to the Content stream  
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        //Setting the position for the line 
        contentStream.newLineAtOffset(305, 561);
        text = nombreRefugio;
        //Adding text in the form of string 
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();

        // Telefono del refugio
        contentStream.beginText();
        //Setting the font to the Content stream  
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        //Setting the position for the line 
        contentStream.newLineAtOffset(402, (float) 431);
        text = "+34 " + telefonoRefugio;
        //Adding text in the form of string 
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();

        // Dirección del refugio
        contentStream.beginText();
        //Setting the font to the Content stream  
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        //Setting the position for the line 
        contentStream.newLineAtOffset(190, 453);
        text = direccionRefugio;
        //Adding text in the form of string 
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();

        // Valladolid
        contentStream.beginText();
        //Setting the font to the Content stream  
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        //Setting the position for the line 
        contentStream.newLineAtOffset(72, 96);
        text = "Valladolid";
        //Adding text in the form of string 
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();

        // Dia 
        contentStream.beginText();
        //Setting the font to the Content stream  
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        //Setting the position for the line 
        contentStream.newLineAtOffset(67, 69);
        text = fechaAdopcion.split("-")[0];
        //Adding text in the form of string 
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();

        // Mes 
        contentStream.beginText();
        //Setting the font to the Content stream  
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        //Setting the position for the line 
        contentStream.newLineAtOffset(100, 69);
        text = this.numberToMes(fechaAdopcion.split("-")[1]);
        //Adding text in the form of string 
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();

        // Año 
        contentStream.beginText();
        //Setting the font to the Content stream  
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.setNonStrokingColor(Color.BLACK);
        //Setting the position for the line 
        contentStream.newLineAtOffset(190, 69);
        text = fechaAdopcion.split("-")[2];
        //Adding text in the form of string 
        contentStream.showText(text);
        //Ending the content stream
        contentStream.endText();
        
        // Firma del cliente
        //System.out.println(base64firma);
        byte[] ba = java.util.Base64.getDecoder().decode(base64firma);
        PDImageXObject sigimg = PDImageXObject.createFromByteArray(document,ba,"signature");
        contentStream.drawImage(sigimg,260,45);
        
        //Closing the content stream
        contentStream.close();

        //Saving the document
        document.save(new File("C:/Users/alvar/OneDrive/Documentos/NetBeansProjects/petAdoptv2/src/main/webapp/resources/certificados/certificado_" + idOperacion + ".pdf"));

        //Closing the document
        document.close();
        
        try {
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PDFUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultStreamedContent pdf = DefaultStreamedContent.builder()
                .name("certificado_" + idOperacion + "_" + nombreMascota + ".pdf")
                .contentType("application/pdf")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/certificados/certificado_" +idOperacion + ".pdf"))
                .build();

        return pdf;
        
    }

    private String numberToMes(String monthNumber) {
        String monthName = "";
        switch (monthNumber) {
            case "01":
                monthName = "Enero";
                break;
            case "02":
                monthName = "Febrero";
                break;
            case "03":
                monthName = "Marzo";
                break;
            case "04":
                monthName = "Abril";
                break;
            case "05":
                monthName = "Mayo";
                break;
            case "06":
                monthName = "Junio";
                break;
            case "07":
                monthName = "Julio";
                break;
            case "08":
                monthName = "Agosto";
                break;
            case "09":
                monthName = "Septiembre";
                break;
            case "10":
                monthName = "Octubre";
                break;
            case "11":
                monthName = "Noviembre";
                break;
            case "12":
                monthName = "Diciembre";
                break;
            default:
                monthName = "Invalid month number";
        }
        return monthName;
    }
    
}

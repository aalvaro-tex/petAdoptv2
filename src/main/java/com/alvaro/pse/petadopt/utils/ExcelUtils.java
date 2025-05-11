/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alvaro.pse.petadopt.utils;

import com.alvaro.pse.petadopt.entities.HistoricoSolicitudes;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author alvar
 */
@Named
@ManagedBean
@SessionScoped
public class ExcelUtils implements Serializable {

    public DefaultStreamedContent generateHistorialExcel(HistoricoSolicitudes[] h) throws IOException {

        /*
         Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet("solicitudes_" + h[0].getIdRefugio());
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFFont font = ((HSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Id solicitud");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Fecha de modificación");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Id mascota");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("Id adoptante");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue("Estado final");
        headerCell.setCellStyle(headerStyle);

        //String idSolicitud = "ADP" + String.valueOf(h[0].getIdMascota()) + String.valueOf(h[0].getIdRefugio()) + String.valueOf(h[0].getIdCliente());
        // iteramos sobre cada elemento del array (las filas del excel)
        for (int ii = 0; ii < h.length; ii++) {

            // iteramos sobre las columnas
            HistoricoSolicitudes hs = h[ii];
            String idSolicitud = "ADP" + String.valueOf(hs.getIdMascota()) + String.valueOf(hs.getIdRefugio()) + String.valueOf(hs.getIdCliente());
            Row row = sheet.createRow(ii + 2);
            Cell cell = row.createCell(0);
            cell.setCellValue(idSolicitud);
            for (int jj = 1; jj < 4; jj++) {
                CellStyle style = workbook.createCellStyle();
                style.setWrapText(true);
                cell = row.createCell(jj);
                cell.setCellValue(hs.getAttrAtIdx(jj));
                cell.setCellStyle(style);
            }
        }

        FileOutputStream outputStream = new FileOutputStream("C:/Users/alvar/OneDrive/Documentos/NetBeansProjects/petAdoptv2/src/main/webapp/resources/solicitudes/solicitudes_" + h[0].getIdRefugio() + ".xls");
        workbook.write(outputStream);
        workbook.close();

        try {
            sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        */

        DefaultStreamedContent excel = DefaultStreamedContent.builder()
                .name("certificado_ADP241714.pdf")
                .contentType("application/pdf")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("resources/certificados/certificado_ADP241714.pdf"))
                .build();
        return excel;
    }

    /*
    protected void lOBExport2Excel(HistoricoSolicitudes[] h) throws Throwable {
        try {

            Workbook wb = new HSSFWorkbook();
            HSSFCellStyle styleHeader = (HSSFCellStyle) wb.createCellStyle();
            HSSFFont fontHeader = (HSSFFont) wb.createFont();
            styleHeader.setFont(fontHeader);
            Sheet sheet = wb.createSheet("sheet");

            Row header = sheet.createRow(0);

            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("Id solicitud");

            headerCell = header.createCell(1);
            headerCell.setCellValue("Fecha de modificación");

            headerCell = header.createCell(2);
            headerCell.setCellValue("Id mascota");

            headerCell = header.createCell(3);
            headerCell.setCellValue("Id adoptante");

            headerCell = header.createCell(4);
            headerCell.setCellValue("Estado final");

            for (int ii = 0; ii < h.length; ii++) {

                // iteramos sobre las columnas
                HistoricoSolicitudes hs = h[ii];
                String idSolicitud = "ADP" + String.valueOf(hs.getIdMascota()) + String.valueOf(hs.getIdRefugio()) + String.valueOf(hs.getIdCliente());
                Row row = sheet.createRow(ii + 2);
                Cell cell = row.createCell(0);
                cell.setCellValue(idSolicitud);
                for (int jj = 1; jj < 4; jj++) {
                    CellStyle style = wb.createCellStyle();
                    style.setWrapText(true);
                    cell = row.createCell(jj);
                    cell.setCellValue(hs.getAttrAtIdx(jj));
                    cell.setCellStyle(style);
                }
            }

            String excelFileName = getFileName("xls");

            FileOutputStream fos = new FileOutputStream(excelFileName);
            wb.write(fos);
            fos.flush();
            fos.close();

            InputStream stream = new BufferedInputStream(new FileInputStream(excelFileName));
            DefaultStreamedContent exportFile = new DefaultStreamedContent(stream, "application/xls", excelFileName);

        } catch (Exception e) {
            catchError(e);
        }

    }
*/

}

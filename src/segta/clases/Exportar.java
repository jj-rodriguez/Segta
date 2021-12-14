/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.clases;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Quales Group
 */
public class Exportar {

    public void ExportarExcel(List pool, List tambor, String fecha) throws IOException, ParseException {

        Pool poolSel = new Pool();
        Tambor tamborSel = new Tambor();

        JFileChooser chooser = new JFileChooser();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);        
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            
            try {
                File archivoXLS = new File(ruta);
                
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                HSSFCellStyle estilo = (HSSFCellStyle) libro.createCellStyle();

                Sheet hoja = libro.createSheet("Pool");
                Sheet hoja2 = libro.createSheet("Tambores");

                estilo.setAlignment(CellStyle.ALIGN_CENTER);
                
                estilo.setBorderLeft(CellStyle.FINE_DOTS);
                estilo.setBorderRight(CellStyle.FINE_DOTS);
                estilo.setBorderBottom(CellStyle.FINE_DOTS);
                estilo.setBorderTop(CellStyle.FINE_DOTS);
                
                

                //CARGO LA HOJA POOL
                hoja.setDisplayGridlines(false);
                Row fila = hoja.createRow(0);
                Cell celda = fila.createCell(0);
                celda.setCellValue("Fecha");
                celda.setCellStyle(estilo);
                celda = fila.createCell(1);
                celda.setCellValue("Pool");
                celda.setCellStyle(estilo);
                celda = fila.createCell(2);
                celda.setCellValue("Dextrina");
                celda.setCellStyle(estilo);
                celda = fila.createCell(3);
                celda.setCellValue("Humedad");
                celda.setCellStyle(estilo);
                celda = fila.createCell(4);
                celda.setCellValue("HMF");
                celda.setCellStyle(estilo);
                celda = fila.createCell(5);
                celda.setCellValue("Color");
                celda.setCellStyle(estilo);
                celda = fila.createCell(6);
                celda.setCellValue("Observaciones");
                celda.setCellStyle(estilo);

                int filaInicio = 1;
                for (int f = 0; f < pool.size(); f++) {
                    fila = hoja.createRow(filaInicio);
                    celda.setCellStyle(estilo);
                    filaInicio++;

                    celda = fila.createCell(0);
                    poolSel = (Pool) pool.get(f);
                    String fechaS = null;
                    java.util.Date fechad = poolSel.getFecha();
                    DateFormat d = new SimpleDateFormat("dd/MM/yy");
                    try {
                        fechaS = d.format(fechad);
                    } catch (Exception ex) {
                        Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    celda.setCellValue(fechaS);
                    celda.setCellStyle(estilo);
                    celda = fila.createCell(1);
                    celda.setCellStyle(estilo);
                    celda.setCellValue(poolSel.getNumero());
                    celda = fila.createCell(2);
                    celda.setCellStyle(estilo);
                    if (poolSel.getDextrina() == null) {
                        celda.setCellValue("");
                        celda.setCellStyle(estilo);
                    } else {
                        celda.setCellValue(poolSel.getDextrina());
                        celda.setCellStyle(estilo);
                    }
                    celda = fila.createCell(3);
                    if (poolSel.getHumedad() == null) {
                        celda.setCellValue("");
                        celda.setCellStyle(estilo);
                    } else {
                        celda.setCellValue(poolSel.getHumedad());
                        celda.setCellStyle(estilo);
                    }
                    celda = fila.createCell(4);
                    if (poolSel.getHmf() == null) {
                        celda.setCellValue("");
                        celda.setCellStyle(estilo);
                    } else {
                        celda.setCellValue(poolSel.getHmf());
                        celda.setCellStyle(estilo);
                    }
                    celda = fila.createCell(5);
                    if (poolSel.getColor() == null) {
                        celda.setCellValue("");
                        celda.setCellStyle(estilo);
                    } else {
                        celda.setCellValue(poolSel.getColor());
                        celda.setCellStyle(estilo);
                    }
                    celda = fila.createCell(6);
                    celda.setCellValue("");
                    celda.setCellStyle(estilo);

                }
                //CARGO LA HOJA TAMBORES
                hoja2.setDisplayGridlines(false);
                Row filaT = hoja2.createRow(0);
                Cell celdaT = filaT.createCell(0);
                celdaT.setCellValue("N°Tambor");
                celdaT.setCellStyle(estilo);
                celdaT = filaT.createCell(1);
                celdaT.setCellValue("Apicultor");
                celdaT.setCellStyle(estilo);
                celdaT = filaT.createCell(2);
                celdaT.setCellValue("N°Pool");
                celdaT.setCellStyle(estilo);
                celdaT = filaT.createCell(3);
                celdaT.setCellValue("Color");
                celdaT.setCellStyle(estilo);
                celdaT = filaT.createCell(4);
                celdaT.setCellValue("Dextrina");
                celdaT.setCellStyle(estilo);
                celdaT = filaT.createCell(5);
                celdaT.setCellValue("Humedad");
                celdaT.setCellStyle(estilo);
//               
                int filaInicio2 = 1;
                for (int f = 0; f < tambor.size(); f++) {
                    filaT = hoja2.createRow(filaInicio2);
                    filaInicio2++;

                    celdaT = filaT.createCell(0);
                    tamborSel = (Tambor) tambor.get(f);                    
                    celdaT.setCellValue(tamborSel.getNumero());
                    celdaT.setCellStyle(estilo);
                    celdaT = filaT.createCell(1);
                    celdaT.setCellValue(tamborSel.getIdProveedor().getRazonSocial());
                    celdaT.setCellStyle(estilo);
                    celdaT = filaT.createCell(2);
                    if (tamborSel.getIdPool() == null) {
                        celdaT.setCellValue("");
                        celdaT.setCellStyle(estilo);
                    } else {
                        celdaT.setCellValue(tamborSel.getIdPool().getNumero());
                        celdaT.setCellStyle(estilo);
                    }
                    celdaT = filaT.createCell(3);
                    if (tamborSel.getIdColor() != null) {
                        celdaT.setCellValue(tamborSel.getIdColor().getColor());
                        celdaT.setCellStyle(estilo);
                    } else {                        
                        celdaT.setCellValue("");
                        celdaT.setCellStyle(estilo);
                    }
                    celdaT = filaT.createCell(4);
                    if (tamborSel.getDextrina() == null) {
                        celdaT.setCellValue("");
                        celdaT.setCellStyle(estilo);
                    } else {
                        celdaT.setCellValue(tamborSel.getDextrina());
                        celdaT.setCellStyle(estilo);
                    }
                    celdaT = filaT.createCell(5);
                    if (tamborSel.getHumedad() == null) {
                        celdaT.setCellValue("");
                        celdaT.setCellStyle(estilo);
                    } else {
                        celdaT.setCellValue(tamborSel.getHumedad());
                        celdaT.setCellStyle(estilo);
                    }

                }
                libro.write(archivo);
                archivo.close();
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                
                throw e;
            }
        }
    }
}

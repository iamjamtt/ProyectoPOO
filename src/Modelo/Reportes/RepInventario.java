package Modelo.Reportes;

import Conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RepInventario {
    PreparedStatement ps;
    ResultSet rs;
    
    Conexion.conexion con = new conexion();
    Connection acceso;
    
    public void reporteInventario(){
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Inventario");
 
        try {
            InputStream is = new FileInputStream("src/img/logo.jpg");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            is.close();
 
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
 
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(1);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 2);
 
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);
 
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Inventario");
 
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));
 
            String[] cabecera = new String[]{"ID", "Codigo", "Descripcion", "Precio", "Stock"};
 
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
 
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
 
            Row filaEncabezados = sheet.createRow(4);
 
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
            
            int numFilaDatos = 5;
 
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
 
            acceso = con.conectar();
            ps = acceso.prepareStatement("SELECT idpro,codigopro,nombrepro,preciopro,stockpro FROM producto WHERE estadopro=1");
            rs = ps.executeQuery();
 
            int numCol = rs.getMetaData().getColumnCount();
 
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
 
                for (int a = 0; a < numCol; a++) {
 
                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }
                
                numFilaDatos++;
            }
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            
            sheet.setZoom(150);
            
            FileOutputStream fileOut = new FileOutputStream("RepInventario"+" "+generarFcha()+".xlsx");
            book.write(fileOut);
            fileOut.close();
            JOptionPane.showMessageDialog(null, "Reporte Inventario Generado");
 
        } catch (FileNotFoundException ex) {
              Logger.getLogger(RepInventario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
             Logger.getLogger(RepInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    String generarFcha(){
        Calendar cal = new GregorianCalendar();
        String fecha = "";
        if((cal.get(Calendar.MONTH)+1)<10){
            fecha = cal.get(Calendar.YEAR) + "-0" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
        }else if(cal.get(Calendar.DAY_OF_MONTH)<10){
            fecha = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-0" + cal.get(Calendar.DAY_OF_MONTH);
        }else if((cal.get(Calendar.MONTH)+1)<10 && cal.get(Calendar.DAY_OF_MONTH)<10){
            fecha = cal.get(Calendar.YEAR) + "-0" + (cal.get(Calendar.MONTH)+1) + "-0" + cal.get(Calendar.DAY_OF_MONTH);
        }else{
            fecha = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
        }
        
        return fecha;
    }
}

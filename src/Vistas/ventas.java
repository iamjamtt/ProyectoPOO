package Vistas;

import Entidad.Cliente;
import Entidad.Detalle;
import Entidad.NroCta;
import Entidad.Producto;
import Entidad.User;
import Entidad.Ventas;
import Modelo.ClienteDAO;
import Modelo.ProductoDAO;
import Modelo.VentasDAO;
import Modelo.genCod;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Desktop;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ventas extends javax.swing.JInternalFrame {

    Modelo.ClienteDAO cdao = new ClienteDAO();
    Entidad.NroCta n = new NroCta();
    Entidad.Cliente c = new Cliente();
    
    Modelo.VentasDAO vdao = new VentasDAO();
    Entidad.Ventas v = new Ventas();
    Entidad.Detalle d = new Detalle();
    
    Modelo.ProductoDAO pdao = new ProductoDAO();
    Entidad.Producto p = new Producto();
    
    Entidad.User u = new User();
    
    DefaultTableModel m = new DefaultTableModel();
    
    int cont = 0, iduser; double importeTotal;
    
    public ventas() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);
        txtctacli.requestFocus();
        generarSerie();
        generarFcha();
        compEmp();
        
        txtcli.setEditable(false);
        txtsaldocli.setEditable(false);
        
        txtpro.setEditable(false);
        txtprepro.setEditable(false);
        txtstockpro.setEditable(false);
        
        txttotalpago.setEditable(false);
        
        maxminSpinner();
    }
    
    void maxminSpinner(){
        SpinnerNumberModel sp = new SpinnerNumberModel();
        sp.setMaximum(5);
        sp.setMinimum(0);
        spncanpro.setModel(sp);
    }
    
    void generarSerie(){
        txtcodventas.setEditable(false);
        
        String serie = vdao.consultarCodigoVen();
        int j;
        if(serie == null){
            txtcodventas.setText("VEN001");
        }else{
            char r2=serie.charAt(3);
            char r3=serie.charAt(4);
            char r4=serie.charAt(5);
            String r="";
            r=""+r2+r3+r4;
            
            j=Integer.parseInt(r);
            genCod gen= new genCod();
            gen.generar(j);
            serie = "VEN"+gen.serie();  
            txtcodventas.setText(serie);
        }
    }
    
    void generarFcha(){
        txtfecventas.setEditable(false);
        
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
        
        txtfecventas.setText(fecha);
    }
    
    void compEmp(){
        txtempleado.setEditable(false);
        
        iduser = login.idu;
        System.out.println("id del login " + iduser);
        String username = login.username;
        String nomUser = login.name;
        String apeUser = login.ape;
        
        txtempleado.setText(nomUser + " " + apeUser + " - " + username);
    }

    void buscarCli(){
        String cta = txtctacli.getText();
        if(txtctacli.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campo cta del cliente vacio");
            txtctacli.requestFocus();
        }else{
            n = cdao.consultarctaDif(cta);
            if(n.getCodigocta() != null){
                c = cdao.consultarcliDif(n.getIdcli());
                txtcli.setText(c.getNombrecli() + " " + c.getApellidocli());
                txtsaldocli.setText("S/. " + n.getMontocta());
            }else{
                JOptionPane.showMessageDialog(null, "Campo cta del cliente incorrecta");
                txtcli.setText("");
                txtsaldocli.setText("");
                txtctacli.requestFocus();
            }
        }
    }
    
    void buscarPro(){
        String codpro = txtcodpro.getText();
        if(txtcodpro.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campo codigo del producto vacio");
            txtctacli.requestFocus();
        }else{
            p = pdao.consultarproDif(codpro);
            if(p.getCodpro() != null){
                txtpro.setText(p.getNompro());
                txtprepro.setText("S/. " + p.getPrepro());
                txtstockpro.setText("" + p.getStock());
            }else{
                JOptionPane.showMessageDialog(null, "Campo codigo del producto incorrecta");
                txtpro.setText("");
                txtprepro.setText("");
                txtstockpro.setText("");
                txtcodpro.requestFocus();
            }
        }
    }
    
    void totalaPagar(){
        importeTotal = 0;
        
        for (int i = 0; i < tabla.getRowCount(); i++) {
            double prepro = Double.parseDouble(tabla.getValueAt(i, 6).toString());
            
            importeTotal += prepro;
        }
        
        txttotalpago.setText("S/. "+importeTotal);
    }
    
    boolean compararCodPro(String CodComp){
        String cod = "";
        for (int i = 0; i < tabla.getRowCount(); i++) {
            cod = tabla.getValueAt(i, 2).toString();
            if(CodComp.equals(cod)){
                return true;
            }
        }
        
        return false;
    }
    
    void agregar(){
        if(txtctacli.getText().equals("") && txtcodpro.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campo de textos vacios");
        }else if(txtctacli.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campo cta del cliente vacio");
        }else if(txtcodpro.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campo codigo del producto vacio");   
        }else if(spncanpro.getValue().equals(0)){
            JOptionPane.showMessageDialog(null, "Ingrese una cantidad");   
        }else if(compararCodPro(txtcodpro.getText())==true){
            JOptionPane.showMessageDialog(null, "Producto ya ingresado, elimine el que tiene e ingrese nuevamente");   
        }else{    
            m = (DefaultTableModel) tabla.getModel();
            cont += 1;
            int idpro = p.getIdpro();
            String codpro = p.getCodpro();
            String nompro = txtpro.getText();
            double prepro = p.getPrepro();
            int canpro = Integer.parseInt(spncanpro.getValue().toString());
            int stockpro = Integer.parseInt(txtstockpro.getText());
            double prexcan = prepro * canpro;
            
            System.out.println("id pro " + idpro + "\t codpro " + codpro +"\t prepro " + prepro);
            
            ArrayList lis = new ArrayList();
            
            if(stockpro==0){
                JOptionPane.showMessageDialog(null, "El producto no cuenta con stock");
            }else if(canpro>stockpro){
                JOptionPane.showMessageDialog(null, "Stock insuficiente");
            }else{
                lis.add(cont);
                lis.add(idpro);
                lis.add(codpro);
                lis.add(nompro);
                lis.add(canpro);
                lis.add(prepro);
                lis.add(prexcan);
                Object[] o = new Object[7];
                o[0] = lis.get(0);
                o[1] = lis.get(1);
                o[2] = lis.get(2);
                o[3] = lis.get(3);
                o[4] = lis.get(4);
                o[5] = lis.get(5);
                o[6] = lis.get(6);
                m.addRow(o);
                tabla.setModel(m);
                
                totalaPagar();
                
                txtpro.setText("");
                txtprepro.setText("");
                txtcodpro.setText("");
                txtstockpro.setText("");
                spncanpro.setValue(0);
            }
        }
    }
    
    void desStock(){
        for(int i=0;i<tabla.getRowCount();i++)
        {
            String codpro = tabla.getValueAt(i, 2).toString();
            int cant = Integer.parseInt(tabla.getValueAt(i, 4).toString());
            int stock = vdao.retornarCantidad(codpro);
            System.out.println("Descontar Stock");
            System.out.println("codpro " + codpro);
            System.out.println("cant " + cant);
            System.out.println("stock " + stock);
            vdao.actualizarStock(codpro, cant, stock);
        }
    }
    
    void genventas(){
        double saldCta = n.getMontocta();
        System.out.println("Saldo 2 " + saldCta);
        
        if(txtctacli.getText().equals("") || tabla.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Campo de textos vacios");
        }else if(saldCta<importeTotal){
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar la compra");
        }else{    
            try {
                int idnrocta = n.getIdnrocta();
                System.out.println("ingresar ventas");
                System.out.println("idnrocta " + idnrocta);
                int idu = iduser;
                System.out.println("idu " + idu);
                String codigoven = txtcodventas.getText();
                System.out.println("codigoven " + codigoven);
                String fechaop = txtfecventas.getText();
                System.out.println("fechaope " + fechaop);
                double montoven = importeTotal;
                System.out.println("montoven " + montoven);
                int estadoven = 1;
                System.out.println("estadoven " + estadoven);
                
                v.setIdnrocta(idnrocta);
                v.setIdu(idu);
                v.setCodven(codigoven);
                v.setFecven(fechaop);
                v.setMonven(montoven);
                v.setEstven(estadoven);
                
                vdao.guardarVentas(v);
                
                JOptionPane.showMessageDialog(null, "Venta realizada exitosamente","Mensaje",1);
                
                genDetalleVen();
                desStock();
                descontarSaldoCta();
                
                //genera el pdf
                JOptionPane.showMessageDialog(null, "Abriendo Voucher de Venta","Mensaje",1);
                pdf(codigoven);
                
                //abre el pdf generado
                abrirPDF(codigoven);
                
                txtcli.setText("");
                txtcodpro.setText("");
                txtctacli.setText("");
                txtprepro.setText("");
                txtpro.setText("");
                txtsaldocli.setText("");
                txtstockpro.setText("");
                txttotalpago.setText("");
                spncanpro.setValue(0);
                
                //SE REINIA EL CON PARA LA TABLA QUE MUESTRA LOS PRODUCTOS
                cont = 0;
                
                limpiarTabla();
                
                generarSerie();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ventas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(ventas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    void genDetalleVen(){
        for(int i=0;i<tabla.getRowCount();i++){
            int idven = vdao.obtIdVentas();
            System.out.println("ingresar detalles de ventas");
            System.out.println("idven " + idven);
            int idpro = Integer.parseInt(tabla.getValueAt(i, 1).toString());
            System.out.println("idpro " + idpro);
            int cantidaddet = Integer.parseInt(tabla.getValueAt(i, 4).toString());
            System.out.println("cantidaddet " + cantidaddet);
            double precioventadet = Double.parseDouble(tabla.getValueAt(i, 6).toString());
            System.out.println("precioventadet " + precioventadet);

            d.setIdpro(idpro);
            d.setIdven(idven);
            d.setCantdet(cantidaddet);
            d.setPreciodet(precioventadet);
            
            vdao.guardarDetalleVentas(d);
        }
        JOptionPane.showMessageDialog(null, "Detalle de venta ingresados exitosamente");  
    }
    
    void descontarSaldoCta(){
        double saldo = n.getMontocta();
        System.out.println("\ndescontar saldo cta\nsaldo " + saldo);
        double monto = importeTotal;
        System.out.println("monto " + monto);
        String codigocta = n.getCodigocta();
        System.out.println("codigocta " +codigocta);
        
        vdao.descSaldo(codigocta, monto, saldo);
        
    }
    
    void limpiarTabla(){
        for (int i = 0; i < m.getRowCount(); i++) {
            m.removeRow(i);
            i -= 1;
        }
    }
    
    public void pdf(String codigo) throws FileNotFoundException, DocumentException{
        FileOutputStream archivo = new FileOutputStream(codigo+".pdf");
        Document documento = new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
                
        try {
            Image logo = Image.getInstance("src/img/logo (2).jpg");
        
            Font negrita = new Font(Font.FontFamily.HELVETICA,12,Font.BOLD,BaseColor.WHITE);
            Font negrita3 = new Font(Font.FontFamily.HELVETICA,12,Font.BOLD,BaseColor.BLACK);
            Font negrita2 = new Font(Font.FontFamily.HELVETICA,18,Font.BOLD,BaseColor.BLACK);
            Font negrita4 = new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.BLACK);

            Paragraph parrafo = new Paragraph("      Elelito S.A.C",negrita4);
            PdfPTable enca = new PdfPTable(4);
            enca.setWidthPercentage(100);
            enca.getDefaultCell().setBorder(0);
            float[] clumn = new float[]{20f, 30f, 70f, 40f};
            enca.setWidths(clumn);
            enca.setHorizontalAlignment(Element.ALIGN_LEFT);

            enca.addCell("");
            enca.addCell("");
            enca.addCell(parrafo);enca.addCell("");
            documento.add(enca);
            
            Date fechaActual = new Date();
                int anioactual = fechaActual.getYear()+1900;
                int mesactual = fechaActual.getMonth()+1;
                int diaactual = fechaActual.getDate();

                int hora = fechaActual.getHours();
                int minuto = fechaActual.getMinutes();
                int segundo = fechaActual.getSeconds();
                
                String horitas;String fechita;
                
                if(minuto<10 && hora<10){
                    horitas="0"+hora+":0"+minuto;
                }else if(hora<10){
                    horitas="0"+hora+":"+minuto;
                }else if(minuto<10){
                    horitas=hora+":0"+minuto;
                }else {
                    horitas=hora+":"+minuto;
                }
                
                if(mesactual<10 && diaactual<10){
                    fechita="0"+diaactual+"/0"+mesactual+"/"+anioactual;
                }else if(mesactual<10){
                    fechita=diaactual+"/0"+mesactual+"/"+anioactual;
                }else if(diaactual<10){
                    fechita="0"+diaactual+"/"+mesactual+"/"+anioactual;
                }else{
                    fechita=diaactual+"/"+mesactual+"/"+anioactual;
                }
     
            Paragraph fechaaa = new Paragraph();
            fechaaa.add("Codigo:    " + codigo + "\n Fecha:     " + fechita + "\n   Hora:     " + horitas);
            PdfPTable encabezado = new PdfPTable(4);
            encabezado.setWidthPercentage(100);
            encabezado.getDefaultCell().setBorder(0);
            float[] clumnasEncabezado = new float[]{20f, 30f, 70f, 40f};
            encabezado.setWidths(clumnasEncabezado);
            encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            encabezado.addCell(logo);
            encabezado.addCell("");
            
            String ruc = "207215509810";
            String tel = "(061) 263999";
            String dir = "Pucallpa";
            
            encabezado.addCell("   Ruc: "+ruc+"\nTelefono: "+tel+"\n   Dirección: "+dir);
            encabezado.addCell(fechaaa);
            documento.add(encabezado);
            
            Paragraph titulo = new Paragraph("Voucher de Compra",negrita2);
            titulo.setAlignment(1);
            documento.add(titulo);
            
            //datos del emp
            documento.add(new Paragraph("\n\nTrabajador:      " + login.name + " " + login.ape));
            documento.add(new Paragraph("Usuario:           " + login.username));
            
            //datos del cliente
            documento.add(new Paragraph("\nCliente:           " + c.getNombrecli() + " " + c.getApellidocli()));
            documento.add(new Paragraph("Dni:                 " + c.getDni()));
            documento.add(new Paragraph("Codigo CTA:   " + n.getCodigocta()));
            
            //peliculas
            documento.add(new Paragraph("\nPeliculas",negrita3));
            documento.add(new Paragraph("\n"));
            PdfPTable tablaP = new PdfPTable(6);
            tablaP.setWidthPercentage(100);
            tablaP.getDefaultCell().setBorder(0);
            float[] clumnasP = new float[]{10f, 15f, 50f, 15f, 15f, 15f};
            tablaP.setWidths(clumnasP);
            tablaP.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell p1 = new  PdfPCell(new Phrase("Nro",negrita));
            PdfPCell p2 = new  PdfPCell(new Phrase("Pelicula",negrita));
            PdfPCell p3 = new  PdfPCell(new Phrase("Cantidad",negrita));
            PdfPCell p4 = new  PdfPCell(new Phrase("Pre Uni",negrita));
            PdfPCell p5 = new  PdfPCell(new Phrase("Codigo",negrita));
            PdfPCell p6 = new  PdfPCell(new Phrase("Total",negrita));
            p1.setBorder(0);
            p2.setBorder(0);
            p3.setBorder(0);
            p4.setBorder(0);
            p5.setBorder(0);
            p6.setBorder(0);
            p1.setBackgroundColor(BaseColor.DARK_GRAY);
            p2.setBackgroundColor(BaseColor.DARK_GRAY);
            p3.setBackgroundColor(BaseColor.DARK_GRAY);
            p4.setBackgroundColor(BaseColor.DARK_GRAY);
            p5.setBackgroundColor(BaseColor.DARK_GRAY);
            p6.setBackgroundColor(BaseColor.DARK_GRAY);
            tablaP.addCell(p1);
            tablaP.addCell(p5);
            tablaP.addCell(p2);
            tablaP.addCell(p3);
            tablaP.addCell(p4);
            tablaP.addCell(p6);
            for(int i=0;i<tabla.getRowCount();i++){
                int idPeli=Integer.parseInt(tabla.getValueAt(i, 1).toString());
                String codigopelicula = tabla.getValueAt(i, 2).toString();
                String nombrePeli = tabla.getValueAt(i, 3).toString();
                String cantPeli = tabla.getValueAt(i, 4).toString();
                String precioPeli = tabla.getValueAt(i, 5).toString();
                String precioPeliT = tabla.getValueAt(i, 6).toString();
                int nroo= i+1;
                String nro = "" + nroo;
                
                tablaP.addCell(nro);
                tablaP.addCell(codigopelicula);
                tablaP.addCell(nombrePeli);
                tablaP.addCell(cantPeli);
                tablaP.addCell(precioPeli);
                tablaP.addCell(precioPeliT);
            }
            
            documento.add(tablaP);
            
            Paragraph preFinal = new Paragraph("\nImporte Total:     S/." + importeTotal, negrita3);
            preFinal.setAlignment(Element.ALIGN_RIGHT);
            documento.add(preFinal);
            
            Paragraph finall = new Paragraph("\n\n\n\nGracias por su Compra", negrita3);
            finall.setAlignment(Element.ALIGN_CENTER);
            documento.add(finall);
            
        } catch (BadElementException ex) {
            
        } catch (IOException ex) {
            
        }
        documento.close();
        
    }
    
    public void abrirPDF(String codigo){
        try {
            File path = new File(codigo + ".pdf");
            Desktop.getDesktop().open(path);
        } catch (Exception e) {
            System.out.println("Error al abrir el pdf " + e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcodventas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtfecventas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtempleado = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtctacli = new javax.swing.JTextField();
        btnbuscarcli = new javax.swing.JButton();
        txtcodpro = new javax.swing.JTextField();
        btnbuscarpro = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtcli = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtsaldocli = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtpro = new javax.swing.JTextField();
        txtprepro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtstockpro = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        spncanpro = new javax.swing.JSpinner();
        btnagregar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btngenvenntas = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        txttotalpago = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        jLabel12.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel12.setText("PRODUCTO:");

        jTextField9.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jTextField10.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel13.setText("PRECIO:");

        jMenuItem1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jMenuItem1.setText("Eliminar Producto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setClosable(true);
        setIconifiable(true);
        setTitle("Modulo de Ventas");

        jPanel1.setBackground(new java.awt.Color(204, 255, 102));

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PUNTO DE VENTA \"ELELITO S.A.C\"");

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Ventas de Peliculas");

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("RUP: 207215509810");

        txtcodventas.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel4.setText("CODIGO:");

        txtfecventas.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel5.setText("FECHA:");

        jLabel16.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel16.setText("VENDE:");

        txtempleado.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtempleado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcodventas, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfecventas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(txtfecventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtcodventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 102));

        jLabel6.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel6.setText("CTA CLIENTE:");

        txtctacli.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        btnbuscarcli.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnbuscarcli.setText("BUSCAR");
        btnbuscarcli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarcliActionPerformed(evt);
            }
        });

        txtcodpro.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        btnbuscarpro.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnbuscarpro.setText("BUSCAR");
        btnbuscarpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarproActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel7.setText("COD PRODUCTO:");

        jLabel8.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel8.setText("CLIENTE:");

        txtcli.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel9.setText("SALDO:");

        txtsaldocli.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel10.setText("PRODUCTO:");

        txtpro.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproActionPerformed(evt);
            }
        });

        txtprepro.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel11.setText("PRECIO:");

        jLabel14.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel14.setText("CANTIDAD:");

        txtstockpro.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel15.setText("STOCK:");

        spncanpro.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        btnagregar.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnagregar.setText("AGREGAR");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtctacli, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbuscarcli, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsaldocli, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcli))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(164, 164, 164))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel10))
                                .addGap(0, 3, Short.MAX_VALUE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtprepro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtpro)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtcodpro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spncanpro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtstockpro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnbuscarpro, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(btnagregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtctacli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscarcli)
                    .addComponent(txtcodpro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscarpro)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtcli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtsaldocli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtpro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtprepro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnagregar)
                            .addComponent(spncanpro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtstockpro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 102));

        tabla.setAutoCreateRowSorter(true);
        tabla.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NRO", "ID", "CODIGO", "PELICULA", "CANTIDAD", "PRE UNI", "TOTAL"
            }
        ));
        tabla.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(5);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(180);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 102));

        btngenvenntas.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btngenvenntas.setText("GENERAR VENTA");
        btngenvenntas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenvenntasActionPerformed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btncancelar.setText("CANCELAR");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        txttotalpago.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel17.setText("TOTAL A PAGAR:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttotalpago)
                    .addComponent(btngenvenntas, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotalpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btngenvenntas)
                    .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbuscarcliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarcliActionPerformed
        // TODO add your handling code here:
        buscarCli();
    }//GEN-LAST:event_btnbuscarcliActionPerformed

    private void txtproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtproActionPerformed

    private void btnbuscarproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarproActionPerformed
        // TODO add your handling code here:
        buscarPro();
    }//GEN-LAST:event_btnbuscarproActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        // TODO add your handling code here:
        agregar();
    }//GEN-LAST:event_btnagregarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        m = (DefaultTableModel) tabla.getModel();
        
        int fila = tabla.getSelectedRow();
        
        if(fila>=0){
            m.removeRow(fila);
            totalaPagar();
        }else{
            JOptionPane.showMessageDialog(null, "Selecciones una fila");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btngenvenntasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenvenntasActionPerformed
        // TODO add your handling code here:
        genventas();
    }//GEN-LAST:event_btngenvenntasActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        // TODO add your handling code here:
        limpiarTabla();
        cont = 0;
        txtcli.setText("");
        txtcodpro.setText("");
        txtctacli.setText("");
        txtprepro.setText("");
        txtpro.setText("");
        txtsaldocli.setText("");
        txtstockpro.setText("");
        txttotalpago.setText("");
        spncanpro.setValue(0);
    }//GEN-LAST:event_btncancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnbuscarcli;
    private javax.swing.JButton btnbuscarpro;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btngenvenntas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JSpinner spncanpro;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtcli;
    private javax.swing.JTextField txtcodpro;
    private javax.swing.JTextField txtcodventas;
    private javax.swing.JTextField txtctacli;
    private javax.swing.JTextField txtempleado;
    private javax.swing.JTextField txtfecventas;
    private javax.swing.JTextField txtprepro;
    private javax.swing.JTextField txtpro;
    private javax.swing.JTextField txtsaldocli;
    private javax.swing.JTextField txtstockpro;
    private javax.swing.JTextField txttotalpago;
    // End of variables declaration//GEN-END:variables
}

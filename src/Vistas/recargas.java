package Vistas;

import Modelo.ClienteDAO;
import java.awt.Color;
import javax.swing.JOptionPane;

public class recargas extends javax.swing.JInternalFrame {

    Modelo.ClienteDAO cdao = new ClienteDAO();
    int idcli, idcta;
    public static double montoI; double monto0=0,montoDB;
    
    public recargas() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);
        btn20.setEnabled(false);
        btn50.setEnabled(false);
        btn100.setEnabled(false);
        btnotromon.setEnabled(false);
        txtcli.setEnabled(false);
        txtmon.setEnabled(false);
        txtmoningresar.setEnabled(false);
        idcli = 0;
    }
    
    void activarBotonesRe(){
        btn20.setEnabled(true);
        btn50.setEnabled(true);
        btn100.setEnabled(true);
        btnotromon.setEnabled(true);
    }
    
    void consultarcts(String cta){
        String concta = cdao.consultarcta2(cta);
        if(cta.equals("")){
            JOptionPane.showMessageDialog(null, "Campos de texto vacio");
            txtcta.requestFocus();
        }else if(concta==null){
            JOptionPane.showMessageDialog(null, "CTA = "+ cta +" ingresado no existe");
            txtcta.requestFocus();
        }else{
            Object[] o = new Object[5];
            Object[] c= new Object[3];
            
            //cta
            o = cdao.consultarcta(cta);  

            idcta = (int) o[0];
            String cod = (String) o[1];
            montoDB = (double) o[2];
            idcli = (int) o[4];
            System.out.println("idcta " + idcta + "\tidcli " + idcli + "\tcod " + cod);
            
            //cli
            c = cdao.consultarcli(idcli);  

            idcli = (int) c[0];
            String nom = (String) c[1];
            String ape = (String) c[2];
            System.out.println("idcli " + idcli + "\tinom " + nom);
            
            
            //mensaje
            JOptionPane.showMessageDialog(null, "Cuenta CTA encontrada");
            
            //aactivamos los botones
            activarBotonesRe();
            
            //mostramos los datos en el formulario
            txtcli.setText(nom + " " + ape);
            txtmon.setText("S/. " + montoDB);
            montoI = monto0;
            txtmoningresar.setText("S/. " + montoI);
            
        }   
    }
    
    void ingMonto(double m){
        montoI = m;
        txtmoningresar.setText("S/. " + montoI);
    }

    void otromonto(){
        String a = JOptionPane.showInputDialog(null,"Otro Monto"); 
        while (!isNumber(a)) {
            a = JOptionPane.showInputDialog(null, "Accion invalidad, Ingrese monto");
        }
        montoI = Double.parseDouble(a);
        System.out.println("----" + montoI);
        txtmoningresar.setText("S/. " + montoI);
    }
    
    boolean isNumber(String n) {
        try {
                Integer.parseInt(n);
                return true;
        } catch (NumberFormatException nfe) {
                return false;
        }
    }
    
    void recargar(){
        double montoF;
        if(txtcta.getText().equals("") || txtmoningresar.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campos de textos vacios");
            txtcta.requestFocus();
        }else{
            int idIngresar = idcta;
            montoF = montoDB + montoI;

            Object[] ob = new Object[2];

            ob[0] = montoF;
            ob[1] = idIngresar;

            cdao.updatecta(ob);
    
            JOptionPane.showMessageDialog(null, "Recarga de S/." + montoI + " realizada exitosamente.\nMonto Total: S/." + montoF);
            
            //Operaciones segundarias 
            txtcta.setText("");
            txtcli.setText("");
            txtmon.setText("");
            txtmoningresar.setText("");
            
            btn20.setEnabled(false);
            btn50.setEnabled(false);
            btn100.setEnabled(false);
            btnotromon.setEnabled(false);
            txtcli.setEnabled(false);
            txtmon.setEnabled(false);
            txtmoningresar.setEnabled(false);
        
            idcli = 0;
            idcta = 0;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtcta = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtcli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtmon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtmoningresar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btn20 = new javax.swing.JButton();
        btn50 = new javax.swing.JButton();
        btn100 = new javax.swing.JButton();
        btnotromon = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnrecargar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Modilo de Recargas");

        jPanel1.setBackground(new java.awt.Color(255, 153, 102));

        jLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel1.setText("CTA:");

        txtcta.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        txtcta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtctaKeyTyped(evt);
            }
        });

        btnbuscar.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnbuscar.setText("BUSCAR");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel2.setText("CLIENTE:");

        txtcli.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel3.setText("MONTO:");

        txtmon.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel4.setText("MONTO A INGRESAR:");

        txtmoningresar.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtcli))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtmon, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtmoningresar, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtcta))
                .addGap(18, 18, 18)
                .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtcli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtmon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtmoningresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 102));

        btn20.setBackground(new java.awt.Color(204, 204, 204));
        btn20.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btn20.setText("s/. 20");
        btn20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn20ActionPerformed(evt);
            }
        });

        btn50.setBackground(new java.awt.Color(204, 204, 204));
        btn50.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btn50.setText("s/. 50");
        btn50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn50ActionPerformed(evt);
            }
        });

        btn100.setBackground(new java.awt.Color(204, 204, 204));
        btn100.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btn100.setText("s/. 100");
        btn100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn100ActionPerformed(evt);
            }
        });

        btnotromon.setBackground(new java.awt.Color(204, 204, 204));
        btnotromon.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnotromon.setText("OTRO MONTO");
        btnotromon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnotromonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        jLabel5.setText("ELIGE UN MONTO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(181, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn100, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn20, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(120, 120, 120)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn50, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnotromon, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(161, 161, 161))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn50, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn100, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnotromon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        btnrecargar.setBackground(new java.awt.Color(255, 255, 102));
        btnrecargar.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        btnrecargar.setText("RECARGAR");
        btnrecargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrecargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnrecargar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(btnrecargar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        String cta = txtcta.getText();
        consultarcts(cta);
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btn20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn20ActionPerformed
        // TODO add your handling code here:
        ingMonto(20);
        
    }//GEN-LAST:event_btn20ActionPerformed

    private void btn50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn50ActionPerformed
        // TODO add your handling code here:
        ingMonto(50);
    }//GEN-LAST:event_btn50ActionPerformed

    private void btn100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn100ActionPerformed
        // TODO add your handling code here:
        ingMonto(100);
    }//GEN-LAST:event_btn100ActionPerformed

    private void txtctaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtctaKeyTyped
        // TODO add your handling code here:
//        String cta = txtcta.getText();
//        consultarcts(cta);
    }//GEN-LAST:event_txtctaKeyTyped

    private void btnotromonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnotromonActionPerformed
        // TODO add your handling code here:
        otromonto();
    }//GEN-LAST:event_btnotromonActionPerformed

    private void btnrecargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrecargarActionPerformed
        // TODO add your handling code here:
        recargar();
    }//GEN-LAST:event_btnrecargarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn100;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn50;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnotromon;
    private javax.swing.JButton btnrecargar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtcli;
    private javax.swing.JTextField txtcta;
    private javax.swing.JTextField txtmon;
    private javax.swing.JTextField txtmoningresar;
    // End of variables declaration//GEN-END:variables
}

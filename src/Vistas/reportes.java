package Vistas;

import Modelo.Reportes.RepCliente;
import Modelo.Reportes.RepCta;
import Modelo.Reportes.RepEmpleado;
import Modelo.Reportes.RepInventario;
import Modelo.Reportes.RepMasVendidos;
import Modelo.Reportes.RepStockBajo;
import Modelo.Reportes.RepVentas;
import java.awt.Color;

public class reportes extends javax.swing.JInternalFrame {

    public reportes() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);
    }

    void reporteEmpleado(){
        Modelo.Reportes.RepEmpleado repemp = new RepEmpleado();
        
        repemp.reporteEmpleado();
    }
    
    void reporteCliente(){
        Modelo.Reportes.RepCliente repcli = new RepCliente();
        
        repcli.reporteCliente();
    }
    void reporteCta(){
        Modelo.Reportes.RepCta repcta = new RepCta();
        
        repcta.reporteCta();
    }
    
    void reporteInventario(){
        Modelo.Reportes.RepInventario repinv = new RepInventario();
        
        repinv.reporteInventario();
    }
    void reporteVentas(){
        Modelo.Reportes.RepVentas repven = new RepVentas();
        
        repven.reporteVentas();
    }
    
    void reporteMasVendidos(){
        Modelo.Reportes.RepMasVendidos repmasven = new RepMasVendidos();
        
        repmasven.reporteMasVendidos();
    }
    
    void reporteStockBajo(){
        Modelo.Reportes.RepStockBajo repstobaj = new RepStockBajo();
  
        repstobaj.reporteStockBajo();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnemp = new javax.swing.JButton();
        btncli = new javax.swing.JButton();
        btncta = new javax.swing.JButton();
        btninv = new javax.swing.JButton();
        btnven = new javax.swing.JButton();
        btnmasven = new javax.swing.JButton();
        btnstock = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Modulo Reportes");

        btnemp.setBackground(new java.awt.Color(255, 153, 153));
        btnemp.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnemp.setText("Empleados");
        btnemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnempActionPerformed(evt);
            }
        });

        btncli.setBackground(new java.awt.Color(255, 153, 153));
        btncli.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btncli.setText("Clientes");
        btncli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncliActionPerformed(evt);
            }
        });

        btncta.setBackground(new java.awt.Color(255, 153, 153));
        btncta.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btncta.setText("Cuentas Cta");
        btncta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnctaActionPerformed(evt);
            }
        });

        btninv.setBackground(new java.awt.Color(255, 153, 153));
        btninv.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btninv.setText("Inventario");
        btninv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninvActionPerformed(evt);
            }
        });

        btnven.setBackground(new java.awt.Color(255, 153, 153));
        btnven.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnven.setText("Ventas");
        btnven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvenActionPerformed(evt);
            }
        });

        btnmasven.setBackground(new java.awt.Color(255, 153, 153));
        btnmasven.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnmasven.setText("Mas Vendidos");
        btnmasven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmasvenActionPerformed(evt);
            }
        });

        btnstock.setBackground(new java.awt.Color(255, 153, 153));
        btnstock.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnstock.setText("Productos con Stock bajo");
        btnstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btncli, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btncta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnven, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btninv, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnemp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnmasven, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnstock, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)))
                .addGap(134, 134, 134))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncli, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnemp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btninv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnven, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnmasven, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(btnstock, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnempActionPerformed
        reporteEmpleado();
    }//GEN-LAST:event_btnempActionPerformed

    private void btncliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncliActionPerformed
        reporteCliente();
    }//GEN-LAST:event_btncliActionPerformed

    private void btnctaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnctaActionPerformed
        reporteCta();
    }//GEN-LAST:event_btnctaActionPerformed

    private void btninvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninvActionPerformed
        reporteInventario();
    }//GEN-LAST:event_btninvActionPerformed

    private void btnvenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvenActionPerformed
        reporteVentas();
    }//GEN-LAST:event_btnvenActionPerformed

    private void btnmasvenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmasvenActionPerformed
        reporteMasVendidos();
    }//GEN-LAST:event_btnmasvenActionPerformed

    private void btnstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstockActionPerformed
        reporteStockBajo();
    }//GEN-LAST:event_btnstockActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncli;
    private javax.swing.JButton btncta;
    private javax.swing.JButton btnemp;
    private javax.swing.JButton btninv;
    private javax.swing.JButton btnmasven;
    private javax.swing.JButton btnstock;
    private javax.swing.JButton btnven;
    // End of variables declaration//GEN-END:variables
}

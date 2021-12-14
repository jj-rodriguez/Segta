/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.formularios;

import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import segta.Conexion;
import segta.clases.Lote;
import segta.clases.Proveedor;
import segta.clases.Sector;

/**
 *
 * @author MODERNIZACION3
 */
public class JDialogListados extends javax.swing.JDialog {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SegTAPU");
    EntityManager em = emf.createEntityManager();

    public JDialogListados(javax.swing.JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SegTAPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("SegTAPU").createEntityManager();
        sectorQuery = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT s FROM Sector s");
        sectorList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : sectorQuery.getResultList();
        loteQuery = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT l FROM Lote l");
        loteList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : loteQuery.getResultList();
        apicultorQuery = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT p FROM Proveedor p ORDER BY p.razonSocial");
        apicultorList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : apicultorQuery.getResultList();
        acopiadorQuery = java.beans.Beans.isDesignTime() ? null :  SegTAPUEntityManager.createQuery("SELECT p FROM Proveedor p WHERE p.acopiador = 1 ORDER BY p.razonSocial");
        acopiadorList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : acopiadorQuery.getResultList();
        jPanel1 = new javax.swing.JPanel();
        jBStock = new javax.swing.JButton();
        jBTamborSector = new javax.swing.JButton();
        jBTamborSector1 = new javax.swing.JButton();
        jBTamborApicultor = new javax.swing.JButton();
        jBTamborAcopiador = new javax.swing.JButton();
        jBVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LISTADOS");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jBStock.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/report2.png"))); // NOI18N
        jBStock.setText("STOCK TAMBORES");
        jBStock.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBStockActionPerformed(evt);
            }
        });

        jBTamborSector.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBTamborSector.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/report2.png"))); // NOI18N
        jBTamborSector.setText("TAMBORES POR GRUPO");
        jBTamborSector.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBTamborSector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTamborSectorActionPerformed(evt);
            }
        });

        jBTamborSector1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBTamborSector1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/report2.png"))); // NOI18N
        jBTamborSector1.setText("TAMBORES POR LOTE");
        jBTamborSector1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBTamborSector1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTamborSector1ActionPerformed(evt);
            }
        });

        jBTamborApicultor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBTamborApicultor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/report2.png"))); // NOI18N
        jBTamborApicultor.setText("TAMBORES POR APICULTOR");
        jBTamborApicultor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBTamborApicultor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTamborApicultorActionPerformed(evt);
            }
        });

        jBTamborAcopiador.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBTamborAcopiador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/report2.png"))); // NOI18N
        jBTamborAcopiador.setText("TAMBORES POR ACOPIADOR");
        jBTamborAcopiador.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBTamborAcopiador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTamborAcopiadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBTamborSector, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(jBTamborSector1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(jBTamborApicultor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(jBTamborAcopiador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jBStock, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBTamborSector, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBTamborSector1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBTamborAcopiador, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBTamborApicultor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 260, 270));

        jBVolver.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jBVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/volver icono.png"))); // NOI18N
        jBVolver.setText("VOLVER");
        jBVolver.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVolverActionPerformed(evt);
            }
        });
        getContentPane().add(jBVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 140, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/fondo dialog.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBStockActionPerformed
        try {
            Conexion conn = new Conexion();
            conn.conectar();
            HashMap parameterMap = new HashMap();
            java.io.File file = new java.io.File("TestWindow.java");
            String path = file.getAbsolutePath();
            String only_path = path.substring(0, path.lastIndexOf('\\'));
            JasperPrint reporte = JasperFillManager.fillReport(only_path + "/reportStockTambor.jasper", null, conn.getConn());
            JasperViewer visor = new JasperViewer(reporte, false);
            visor.setVisible(true);
            visor.setDefaultCloseOperation(javax.swing.JDialog.HIDE_ON_CLOSE);
            conn.desconectar();
        } catch (Exception ex) {

            Logger.getLogger(jDialogDescargas.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jBStockActionPerformed

    private void jBTamborSectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTamborSectorActionPerformed

        JComboBox jComboBoxLote = new JComboBox((Vector) sectorList);
        int input = JOptionPane.showConfirmDialog(this, jComboBoxLote, "Seleccione Grupo", JOptionPane.DEFAULT_OPTION);

        if (input == JOptionPane.OK_OPTION) {

            Sector sectorSel = (Sector) jComboBoxLote.getSelectedItem();

            try {
                Conexion conn = new Conexion();
                conn.conectar();
                HashMap parameterMap = new HashMap();
                parameterMap.put("pSector", sectorSel.getIdSector());
                java.io.File file = new java.io.File("TestWindow.java");
                String path = file.getAbsolutePath();
                String only_path = path.substring(0, path.lastIndexOf('\\'));
                JasperPrint reporte = JasperFillManager.fillReport(only_path + "/reportLote2.jasper", parameterMap, conn.getConn());
                JasperViewer visor = new JasperViewer(reporte, false);
                visor.setVisible(true);
                visor.setDefaultCloseOperation(javax.swing.JDialog.HIDE_ON_CLOSE);
                conn.desconectar();
            } catch (Exception ex) {

                Logger.getLogger(jDialogDescargas.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jBTamborSectorActionPerformed

    private void jBVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVolverActionPerformed
        // TODO add your handling code here:
        this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_jBVolverActionPerformed

    private void jBTamborSector1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTamborSector1ActionPerformed

        JComboBox jComboBoxContenedor = new JComboBox((Vector) loteList);
        int input = JOptionPane.showConfirmDialog(this, jComboBoxContenedor, "Seleccione Lote", JOptionPane.DEFAULT_OPTION);

        if (input == JOptionPane.OK_OPTION) {

            Lote loteSel = (Lote) jComboBoxContenedor.getSelectedItem();

            try {
                Conexion conn = new Conexion();
                conn.conectar();
                HashMap parameterMap = new HashMap();
                parameterMap.put("pLote", loteSel.getIdLote());
                java.io.File file = new java.io.File("TestWindow.java");
                String path = file.getAbsolutePath();
                String only_path = path.substring(0, path.lastIndexOf('\\'));
                JasperPrint reporte = JasperFillManager.fillReport(only_path + "/reportContenedor.jasper", parameterMap, conn.getConn());
                JasperViewer visor = new JasperViewer(reporte, false);
                visor.setVisible(true);
                visor.setDefaultCloseOperation(javax.swing.JDialog.HIDE_ON_CLOSE);
                conn.desconectar();
            } catch (Exception ex) {

                Logger.getLogger(jDialogDescargas.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        /*
        try {
            Conexion conn = new Conexion();
            conn.conectar();
            java.io.File file = new java.io.File("TestWindow.java");
            String path = file.getAbsolutePath();
            String only_path = path.substring(0, path.lastIndexOf('\\'));
            JasperPrint reporte = JasperFillManager.fillReport(only_path + "/reportTamborContenedor.jasper", null, conn.getConn());
            JasperViewer visor = new JasperViewer(reporte, false);
            visor.setVisible(true);
            visor.setDefaultCloseOperation(javax.swing.JDialog.HIDE_ON_CLOSE);
            conn.desconectar();
        } catch (Exception ex) {

            Logger.getLogger(jDialogDescargas.class.getName()).log(Level.SEVERE, null, ex);
         */

    }//GEN-LAST:event_jBTamborSector1ActionPerformed

    private void jBTamborApicultorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTamborApicultorActionPerformed

        Proveedor provSel = new Proveedor();
        JComboBox jComboBoxLote = new JComboBox((Vector) apicultorList);
        JTextField descarga = new JTextField();

        int input = JOptionPane.showConfirmDialog(this, jComboBoxLote, "Seleccione Apicultor", JOptionPane.DEFAULT_OPTION);

        if (input == JOptionPane.OK_OPTION) {

            provSel = (Proveedor) jComboBoxLote.getSelectedItem();
//
//            dCCExportar.setVisible(true);
//            Date fecha = null;
//            String fechaS = "";

            int input2 = JOptionPane.showConfirmDialog(this, descarga, "Ingrese número de Descarga", JOptionPane.DEFAULT_OPTION);
            if (input2 == JOptionPane.OK_OPTION) {

//                fechaS = dCCExportar.getText();
                //TRAIGO LOS DATOS DE LOS POOL FILTRADO POR FECHA Y ARMO EL LIST
//                Query ql;
//                String queryTxt = "SELECT t FROM Tambor t JOIN Proveedor p ON t.IdProveedor = p.IdProveedor WHERE p.IdProveedor = :pProveedor ";
//                try {
//                    fecha = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(fechaS);
//                } catch (ParseException ex) {
//                    Logger.getLogger(jDialogDescargas.class.getName()).log(Level.SEVERE, null, ex);
//                    fecha = null;
//                }
//                queryTxt = queryTxt + " AND  p.fecha = :pFecha ";
//                ql = em.createQuery(queryTxt);
//                ql.setParameter("pFecha", fecha);
//                ql.setParameter("pProveedor", provSel);
                try {
                    Conexion conn = new Conexion();
                    conn.conectar();
                    HashMap parameterMap = new HashMap();
                    parameterMap.put("prov", provSel.getIdProveedor());
                    parameterMap.put("descarga", descarga.getText());
                    java.io.File file = new java.io.File("TestWindow.java");
                    String path = file.getAbsolutePath();
                    String only_path = path.substring(0, path.lastIndexOf('\\'));
                    JasperPrint reporte = JasperFillManager.fillReport(only_path + "/reportTamborApicultor.jasper", parameterMap, conn.getConn());
                    JasperViewer visor = new JasperViewer(reporte, false);
                    visor.setVisible(true);
                    visor.setDefaultCloseOperation(javax.swing.JDialog.HIDE_ON_CLOSE);
                    conn.desconectar();
                } catch (Exception ex) {

                    Logger.getLogger(jDialogDescargas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }// TODO add your handling code here:
    }//GEN-LAST:event_jBTamborApicultorActionPerformed

    private void jBTamborAcopiadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTamborAcopiadorActionPerformed
        
        Proveedor provSel = new Proveedor();
        JComboBox jComboBoxLote = new JComboBox((Vector) acopiadorList);
        JTextField descarga = new JTextField();

        int input = JOptionPane.showConfirmDialog(this, jComboBoxLote, "Seleccione Acopiador", JOptionPane.DEFAULT_OPTION);

        if (input == JOptionPane.OK_OPTION) {

            provSel = (Proveedor) jComboBoxLote.getSelectedItem();
            int input2 = JOptionPane.showConfirmDialog(this, descarga, "Ingrese número de Descarga", JOptionPane.DEFAULT_OPTION);
            if (input2 == JOptionPane.OK_OPTION) {
                try {
                    Conexion conn = new Conexion();
                    conn.conectar();
                    HashMap parameterMap = new HashMap();
                    parameterMap.put("prov", provSel.getIdProveedor());
                    parameterMap.put("descarga", descarga.getText());
                    java.io.File file = new java.io.File("TestWindow.java");
                    String path = file.getAbsolutePath();
                    String only_path = path.substring(0, path.lastIndexOf('\\'));
                    JasperPrint reporte = JasperFillManager.fillReport(only_path + "/reportTamborAcopiador.jasper", parameterMap, conn.getConn());
                    JasperViewer visor = new JasperViewer(reporte, false);
                    visor.setVisible(true);
                    visor.setDefaultCloseOperation(javax.swing.JDialog.HIDE_ON_CLOSE);
                    conn.desconectar();
                } catch (Exception ex) {

                    Logger.getLogger(jDialogDescargas.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }// TODO add your handling code here:
    }//GEN-LAST:event_jBTamborAcopiadorActionPerformed
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager SegTAPUEntityManager;
    private java.util.List acopiadorList;
    private javax.persistence.Query acopiadorQuery;
    private java.util.List apicultorList;
    private javax.persistence.Query apicultorQuery;
    private javax.swing.JButton jBStock;
    private javax.swing.JButton jBTamborAcopiador;
    private javax.swing.JButton jBTamborApicultor;
    private javax.swing.JButton jBTamborSector;
    private javax.swing.JButton jBTamborSector1;
    private javax.swing.JButton jBVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private java.util.List<segta.clases.Lote> loteList;
    private javax.persistence.Query loteQuery;
    private java.util.List<segta.clases.Sector> sectorList;
    private javax.persistence.Query sectorQuery;
    // End of variables declaration//GEN-END:variables
}

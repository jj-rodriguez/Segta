/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.formularios;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import segta.clases.Lote;
import segta.clases.Sector;
import segta.clases.Tambor;
import segta.controladores.ClienteJpaController;
import segta.controladores.TamborJpaController;
import static segta.formularios.inicio.cambioSector;

/**
 *
 * @author MODERNIZACION3
 */
public class jDialogBuscarTambor extends javax.swing.JDialog {

    /**
     * Creates new form JDialogLotes
     */
    /**
     * Centra Componente en la pantalla
     */
    public static void centerComponent(Component c) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = c.getSize();

        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }

        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        c.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
    }

//    CREO LA CONEXION Y LOS CONTROLADORES
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SegTAPU");
    EntityManager em = emf.createEntityManager();
    TamborJpaController controladorT = new TamborJpaController(emf);
    ClienteJpaController controladorC = new ClienteJpaController(emf);
  

    public jDialogBuscarTambor(javax.swing.JDialog parent, boolean modal) {
        
        super(parent, modal);
        //this.setLocationRelativeTo(parent);
        initComponents();
        tamborListClasificado.clear();
        centerComponent(this);

//CENTRA LOS DATOS DE LA TABLA
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);

        jTableTambor.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTableTambor.getColumnModel().getColumn(1).setCellRenderer(tcr);
       
        jTableTambor.getColumnModel().getColumn(3).setCellRenderer(tcr);
        jTableTambor.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTableTambor.getColumnModel().getColumn(5).setCellRenderer(tcr);
       
      

//COMPLETA EL PANEL CON LOS DATOS DEL LOTE
//        Cliente clienteLote = controladorC.findCliente(lote);
/*       jTFNumeroLote.setText(lote.getLote());
        jTFColor.setText(lote.getIdLoteContrato().getColor());
        this.jCheckBox.setSelected(lote.getIdLoteContrato().getIdContrato().getTamborNuevo());
        this.jCheckBoxHomo.setSelected(lote.getIdLoteContrato().getIdContrato().getHomogeneizada());
*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        SegTAPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("SegTAPU").createEntityManager();
        tamborQueryClasificado = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT t FROM Tambor t");
        tamborListClasificado = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() :org.jdesktop.observablecollections.ObservableCollections.observableList(tamborQueryClasificado.getResultList());
        jPanel1 = new javax.swing.JPanel();
        jBVolver = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableTambor = new javax.swing.JTable();
        jTamborBuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BUSCAR TAMBOR");
        setMinimumSize(new java.awt.Dimension(850, 350));
        setPreferredSize(new java.awt.Dimension(850, 350));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/volver icono.png"))); // NOI18N
        jBVolver.setText("VOLVER");
        jBVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVolverActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setPreferredSize(new java.awt.Dimension(765, 200));

        jTableTambor.setAutoCreateRowSorter(true);
        jTableTambor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tamborListClasificado, jTableTambor);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idDescarga.idDescargas}"));
        columnBinding.setColumnName("Descarga");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${numero}"));
        columnBinding.setColumnName("Numero");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idDescarga.fecha}"));
        columnBinding.setColumnName("Fecha");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idDescarga.camion}"));
        columnBinding.setColumnName("Camion");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${neto}"));
        columnBinding.setColumnName("Neto");
        columnBinding.setColumnClass(Float.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${estado}"));
        columnBinding.setColumnName("Estado");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jTableTambor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTamborMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableTambor);
        if (jTableTambor.getColumnModel().getColumnCount() > 0) {
            jTableTambor.getColumnModel().getColumn(0).setMinWidth(100);
            jTableTambor.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTableTambor.getColumnModel().getColumn(0).setMaxWidth(200);
            jTableTambor.getColumnModel().getColumn(1).setMinWidth(100);
            jTableTambor.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableTambor.getColumnModel().getColumn(1).setMaxWidth(200);
            jTableTambor.getColumnModel().getColumn(2).setMinWidth(100);
            jTableTambor.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableTambor.getColumnModel().getColumn(2).setMaxWidth(200);
            jTableTambor.getColumnModel().getColumn(3).setMinWidth(100);
            jTableTambor.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTableTambor.getColumnModel().getColumn(3).setMaxWidth(200);
            jTableTambor.getColumnModel().getColumn(4).setMinWidth(100);
            jTableTambor.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTableTambor.getColumnModel().getColumn(4).setMaxWidth(200);
            jTableTambor.getColumnModel().getColumn(5).setMinWidth(100);
            jTableTambor.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTableTambor.getColumnModel().getColumn(5).setMaxWidth(200);
        }

        jTamborBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTamborBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTamborBuscarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/buscar icono.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Número de Tambor");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTamborBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTamborBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBVolver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 800, 270));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/fondo dialog.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 720));

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableTamborMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTamborMouseClicked


    }//GEN-LAST:event_jTableTamborMouseClicked

    private void jBVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVolverActionPerformed
        this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_jBVolverActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Query ql;
        int numero = 0;
        boolean haynumero = false;
        String queryTxt = "SELECT t FROM Tambor t";
//        Sector secSel = sectorList.get(jComboBoxSec.getSelectedIndex());

        if (!jTamborBuscar.getText().isEmpty()) {
            try {
                numero = Integer.parseInt(jTamborBuscar.getText());
                haynumero = true;

            } catch (Exception e) {
                haynumero = false;
            }
        }

        if (haynumero) {
            queryTxt = queryTxt + " where t.numero like " + numero + "";
        }

        ql = em.createQuery(queryTxt);

      
        tamborQueryClasificado = java.beans.Beans.isDesignTime() ? null : ql;
        tamborListClasificado.clear();
        tamborListClasificado.addAll(tamborQueryClasificado.getResultList());


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTamborBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTamborBuscarActionPerformed
        this.jButton1ActionPerformed(evt);
    }//GEN-LAST:event_jTamborBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(jDialogSelecionarTambor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(jDialogSelecionarTambor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(jDialogSelecionarTambor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(jDialogSelecionarTambor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                jDialogSelecionarTambor dialog = new jDialogSelecionarTambor(new javax.swing.JDialog(),Lote lo, true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager SegTAPUEntityManager;
    private javax.swing.JButton jBVolver;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableTambor;
    private javax.swing.JTextField jTamborBuscar;
    private java.util.List<segta.clases.Tambor> tamborListClasificado;
    private javax.persistence.Query tamborQueryClasificado;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}

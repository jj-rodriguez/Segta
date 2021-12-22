/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.formularios;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import segta.clases.Descargas;
import segta.clases.Usuario;
import segta.clases.Usuarioperfil;

/**
 *
 * @author MODERNIZACION3
 */
public class inicio extends javax.swing.JFrame {

    /**
     * Creates new form inicio
     */
    public static Descargas de;
    public static int idDescargas;
    public Usuario user;
    public static boolean cambioSector=false;
    /**
     * Centra Componente en la pantalla
     */
    public inicio(Usuario u) {
        
        user=u;
        initComponents();
      
        centerComponent(this.jLabel1);
        centerComponent(this.jLabel2);
        setearPermisos();
//        this.setExtendedState(MAXIMIZED_BOTH);
        
//        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel1, "/segta/imagenes/fondo inicio.png");
    }

    public static void centerComponent(Component c) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = c.getSize();

//        if (frameSize.height > screenSize.height) {
//            frameSize.height = screenSize.height;
//        }
//
//        if (frameSize.width > screenSize.width) {
//            frameSize.width = screenSize.width;
//        }
c.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
       
    }
    
    public void setearPermisos(){
        
        if (user.tienePerfil("Administrador")){
            
            //Habilita botones del Panel
            this.jBContratos.setEnabled(true);
            this.jBDescargas.setEnabled(true);
            this.jBLaboratorio.setEnabled(true);
            this.jBListados.setEnabled(true);
            this.jBLotes.setEnabled(true);
            this.jBProveedores.setEnabled(true);
            this.jBTambores.setEnabled(true);
            this.jBclientes.setEnabled(true);
            
            //Habilita Botones del menú
            this.jMenuItemApicultores.setEnabled(true);
            this.jMenuItemClientes.setEnabled(true);
            this.jMenuItemContratos.setEnabled(true);
            this.jMenuItemDescargas.setEnabled(true);
            this.jMenuItemLab.setEnabled(true);
            this.jMenuItemList.setEnabled(true);
            this.jMenuItemLotes.setEnabled(true);
            this.jMenuItemTambores.setEnabled(true);
            this.jMenuConfig.setEnabled(true);
            this.jMenuItemUsuarios.setEnabled(true);
                        
        }else{
            if (user.tienePerfil("Descargas")){
                this.jBDescargas.setEnabled(true);
                this.jMenuItemDescargas.setEnabled(true);
            }
            if (user.tienePerfil("Laboratorio")){
                  this.jMenuItemLab.setEnabled(true);
                  this.jBLaboratorio.setEnabled(true);
            }
            
            if (user.tienePerfil("Contratos")){
                //Habilita botones del Panel
                this.jBContratos.setEnabled(true);
                this.jMenuItemContratos.setEnabled(true);
              
            }
            if (user.tienePerfil("Listados")){
                //Habilita botones del Panel
                this.jBListados.setEnabled(true);
                this.jMenuItemList.setEnabled(true);
              
            }
             if (user.tienePerfil("Tambores")){
                //Habilita botones del Panel
                this.jBTambores.setEnabled(true);
                this.jMenuItemTambores.setEnabled(true);
              
            }
            if (user.tienePerfil("Lotes")){
                //Habilita botones del Panel
                this.jBLotes.setEnabled(true);
                this.jMenuItemLotes.setEnabled(true);
              
            }
            if (user.tienePerfil("Configuración")){
                //Habilita botones del Panel
                this.jMenuConfig.setEnabled(true);
              
            }
            if (user.tienePerfil("Apicultores")){
                 //Habilita botones del Panel
                this.jBProveedores.setEnabled(true);
                this.jMenuItemApicultores.setEnabled(true);
              
            }
            if (user.tienePerfil("Clientes")){
                 //Habilita botones del Panel
                this.jBclientes.setEnabled(true);
                this.jMenuItemClientes.setEnabled(true);
              
            }
            
            
        }
     
        //"Administrador", "Descargas", "Laboratorio", "Contratos","Listados","Tambores","Lotes","Configuración","Apicultores","Clientes"
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jBContratos = new javax.swing.JButton();
        jBclientes = new javax.swing.JButton();
        jBLotes = new javax.swing.JButton();
        jBListados = new javax.swing.JButton();
        jBProveedores = new javax.swing.JButton();
        jBTambores = new javax.swing.JButton();
        jBDescargas = new javax.swing.JButton();
        jBLaboratorio = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemApicultores = new javax.swing.JMenuItem();
        jMenuItemClientes = new javax.swing.JMenuItem();
        jMenuItemContratos = new javax.swing.JMenuItem();
        jMenuItemDescargas = new javax.swing.JMenuItem();
        jMenuItemLab = new javax.swing.JMenuItem();
        jMenuItemList = new javax.swing.JMenuItem();
        jMenuItemLotes = new javax.swing.JMenuItem();
        jMenuItemTambores = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuConfig = new javax.swing.JMenu();
        jMenuItemColor = new javax.swing.JMenuItem();
        jMenuItemDet = new javax.swing.JMenuItem();
        jMenuItemSectores = new javax.swing.JMenuItem();
        jMenuItemUsuarios = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SegTa");
        setBackground(new java.awt.Color(0, 0, 0));
        setExtendedState(6);
        setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/logo gris.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 50, 170, 150));

        jBContratos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda contratos.png"))); // NOI18N
        jBContratos.setBorder(null);
        jBContratos.setBorderPainted(false);
        jBContratos.setContentAreaFilled(false);
        jBContratos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBContratos.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/botonEscondido.png"))); // NOI18N
        jBContratos.setEnabled(false);
        jBContratos.setFocusPainted(false);
        jBContratos.setFocusable(false);
        jBContratos.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda contratos 2.png"))); // NOI18N
        jBContratos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda contratos 2.png"))); // NOI18N
        jBContratos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBContratosActionPerformed(evt);
            }
        });
        getContentPane().add(jBContratos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 210, 210));

        jBclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda clientes.png"))); // NOI18N
        jBclientes.setBorderPainted(false);
        jBclientes.setContentAreaFilled(false);
        jBclientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBclientes.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/botonEscondido.png"))); // NOI18N
        jBclientes.setEnabled(false);
        jBclientes.setFocusable(false);
        jBclientes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda clientes 2.png"))); // NOI18N
        jBclientes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda clientes 3.png"))); // NOI18N
        jBclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBclientesActionPerformed(evt);
            }
        });
        getContentPane().add(jBclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 170, 210, 200));

        jBLotes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda lotes.png"))); // NOI18N
        jBLotes.setBorder(null);
        jBLotes.setBorderPainted(false);
        jBLotes.setContentAreaFilled(false);
        jBLotes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBLotes.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/botonEscondido.png"))); // NOI18N
        jBLotes.setEnabled(false);
        jBLotes.setFocusable(false);
        jBLotes.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda lotes 2.png"))); // NOI18N
        jBLotes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda lotes 3.png"))); // NOI18N
        jBLotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLotesActionPerformed(evt);
            }
        });
        getContentPane().add(jBLotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 180, 220, 220));

        jBListados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda listado.png"))); // NOI18N
        jBListados.setBorder(null);
        jBListados.setBorderPainted(false);
        jBListados.setContentAreaFilled(false);
        jBListados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBListados.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/botonEscondido.png"))); // NOI18N
        jBListados.setDoubleBuffered(true);
        jBListados.setEnabled(false);
        jBListados.setFocusPainted(false);
        jBListados.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda listado 2.png"))); // NOI18N
        jBListados.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda listado 3.png"))); // NOI18N
        jBListados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBListadosActionPerformed(evt);
            }
        });
        getContentPane().add(jBListados, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 430, 210, 200));

        jBProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda apicultor 2.png"))); // NOI18N
        jBProveedores.setBorder(null);
        jBProveedores.setBorderPainted(false);
        jBProveedores.setContentAreaFilled(false);
        jBProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBProveedores.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/botonEscondido.png"))); // NOI18N
        jBProveedores.setEnabled(false);
        jBProveedores.setFocusCycleRoot(true);
        jBProveedores.setFocusable(false);
        jBProveedores.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda apicultor 3.png"))); // NOI18N
        jBProveedores.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda apicultor 3.png"))); // NOI18N
        jBProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBProveedoresActionPerformed(evt);
            }
        });
        getContentPane().add(jBProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 420, 210, 200));

        jBTambores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda tambor.png"))); // NOI18N
        jBTambores.setBorderPainted(false);
        jBTambores.setContentAreaFilled(false);
        jBTambores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBTambores.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/botonEscondido.png"))); // NOI18N
        jBTambores.setDoubleBuffered(true);
        jBTambores.setEnabled(false);
        jBTambores.setFocusable(false);
        jBTambores.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda tambor 3.png"))); // NOI18N
        jBTambores.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda tambor 3.png"))); // NOI18N
        jBTambores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTamboresActionPerformed(evt);
            }
        });
        getContentPane().add(jBTambores, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, 230, 210));

        jBDescargas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda descargas.png"))); // NOI18N
        jBDescargas.setBorder(null);
        jBDescargas.setBorderPainted(false);
        jBDescargas.setContentAreaFilled(false);
        jBDescargas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBDescargas.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/botonEscondido.png"))); // NOI18N
        jBDescargas.setEnabled(false);
        jBDescargas.setFocusPainted(false);
        jBDescargas.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda descargas 2.png"))); // NOI18N
        jBDescargas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda descargas 3.png"))); // NOI18N
        jBDescargas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDescargasActionPerformed(evt);
            }
        });
        getContentPane().add(jBDescargas, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 200, 200));

        jBLaboratorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda laboratorio 2.png"))); // NOI18N
        jBLaboratorio.setBorder(null);
        jBLaboratorio.setBorderPainted(false);
        jBLaboratorio.setContentAreaFilled(false);
        jBLaboratorio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBLaboratorio.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/botonEscondido.png"))); // NOI18N
        jBLaboratorio.setDoubleBuffered(true);
        jBLaboratorio.setEnabled(false);
        jBLaboratorio.setFocusPainted(false);
        jBLaboratorio.setFocusable(false);
        jBLaboratorio.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda laboratorio 3.png"))); // NOI18N
        jBLaboratorio.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/celda laboratorio 3.png"))); // NOI18N
        jBLaboratorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLaboratorioActionPerformed(evt);
            }
        });
        getContentPane().add(jBLaboratorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, 230, 200));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/fondo inicio.png"))); // NOI18N
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1360, -1));

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar1MouseClicked(evt);
            }
        });

        jMenuArchivo.setText("Archivo");
        jMenuArchivo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuArchivo.setPreferredSize(new java.awt.Dimension(60, 20));
        jMenuArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuArchivoActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jSeparator1);

        jMenuItemApicultores.setText("Apicultores");
        jMenuItemApicultores.setEnabled(false);
        jMenuItemApicultores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemApicultoresActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemApicultores);

        jMenuItemClientes.setText("Clientes");
        jMenuItemClientes.setEnabled(false);
        jMenuItemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientesActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemClientes);

        jMenuItemContratos.setText("Contratos");
        jMenuItemContratos.setEnabled(false);
        jMenuItemContratos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContratosActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemContratos);

        jMenuItemDescargas.setText("Descargas");
        jMenuItemDescargas.setEnabled(false);
        jMenuItemDescargas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDescargasActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemDescargas);

        jMenuItemLab.setText("Laboratorios");
        jMenuItemLab.setEnabled(false);
        jMenuItemLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLabActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemLab);

        jMenuItemList.setText("Listados");
        jMenuItemList.setEnabled(false);
        jMenuItemList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemListActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemList);

        jMenuItemLotes.setText("Lotes");
        jMenuItemLotes.setEnabled(false);
        jMenuItemLotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLotesActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemLotes);

        jMenuItemTambores.setText("Tambores");
        jMenuItemTambores.setEnabled(false);
        jMenuItemTambores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTamboresActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemTambores);
        jMenuArchivo.add(jSeparator2);

        jMenuConfig.setText("Configuracion");
        jMenuConfig.setEnabled(false);
        jMenuConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuConfigActionPerformed(evt);
            }
        });

        jMenuItemColor.setText("Colores");
        jMenuItemColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemColorActionPerformed(evt);
            }
        });
        jMenuConfig.add(jMenuItemColor);

        jMenuItemDet.setText("Determinaciones");
        jMenuItemDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDetActionPerformed(evt);
            }
        });
        jMenuConfig.add(jMenuItemDet);

        jMenuItemSectores.setText("Grupos");
        jMenuItemSectores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSectoresActionPerformed(evt);
            }
        });
        jMenuConfig.add(jMenuItemSectores);

        jMenuArchivo.add(jMenuConfig);

        jMenuItemUsuarios.setText("Usuarios");
        jMenuItemUsuarios.setEnabled(false);
        jMenuItemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUsuariosActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemUsuarios);
        jMenuArchivo.add(jSeparator3);

        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemSalir);

        jMenuBar1.add(jMenuArchivo);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBDescargasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDescargasActionPerformed
        new jDialogDescargas(this, false).setVisible(true);
    }//GEN-LAST:event_jBDescargasActionPerformed

    private void jBTamboresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTamboresActionPerformed
        new jDialogTamboresGeneral(this, true).setVisible(true);
    }//GEN-LAST:event_jBTamboresActionPerformed

    private void jBLotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLotesActionPerformed
        new jDialogLotes(this, false).setVisible(true);
    }//GEN-LAST:event_jBLotesActionPerformed

    private void jBProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBProveedoresActionPerformed
        new jDialogProveedor(this, true).setVisible(true);
    }//GEN-LAST:event_jBProveedoresActionPerformed

    private void jBclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBclientesActionPerformed
        new jDialogCliente(this, true).setVisible(true);
    }//GEN-LAST:event_jBclientesActionPerformed

    private void jMenuBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar1MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jMenuBar1MouseClicked

    private void jBLaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLaboratorioActionPerformed
        new jDialogLaboratorio(this, true).setVisible(true);
    }//GEN-LAST:event_jBLaboratorioActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        
        System.exit(0);
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jBContratosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBContratosActionPerformed
        new JDialogContrato(this, false).setVisible(true);
    }//GEN-LAST:event_jBContratosActionPerformed

    private void jMenuItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUsuariosActionPerformed
        // TODO add your handling code here:
         new JDialogUsuario(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemUsuariosActionPerformed

    private void jMenuArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuArchivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuArchivoActionPerformed

    private void jMenuItemDescargasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDescargasActionPerformed
        // TODO add your handling code here:
        new jDialogDescargas(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemDescargasActionPerformed

    private void jMenuItemListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemListActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItemListActionPerformed

    private void jMenuItemApicultoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemApicultoresActionPerformed
        // TODO add your handling code here:
        new jDialogProveedor(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemApicultoresActionPerformed

    private void jMenuItemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientesActionPerformed
        // TODO add your handling code here:
        new jDialogCliente(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemClientesActionPerformed

    private void jMenuItemContratosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContratosActionPerformed
        // TODO add your handling code here:
        new JDialogContrato(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemContratosActionPerformed

    private void jMenuItemLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLabActionPerformed
        // TODO add your handling code here:
        new jDialogLaboratorio(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemLabActionPerformed

    private void jMenuItemLotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLotesActionPerformed
        // TODO add your handling code here:
        new jDialogLotes(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemLotesActionPerformed

    private void jMenuItemTamboresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTamboresActionPerformed
        // TODO add your handling code here:
        new jDialogLotes(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemTamboresActionPerformed

    private void jMenuConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuConfigActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jMenuConfigActionPerformed

    private void jMenuItemDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDetActionPerformed
        // TODO add your handling code here:
         new jDialogDeterminacion(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemDetActionPerformed

    private void jMenuItemColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemColorActionPerformed
        // TODO add your handling code here:
         new jDialogColor(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemColorActionPerformed

    private void jMenuItemSectoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSectoresActionPerformed
        // TODO add your handling code here:
         new jDialogSector(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemSectoresActionPerformed

    private void jBListadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBListadosActionPerformed
         new JDialogListados(this, false).setVisible(true);
    }//GEN-LAST:event_jBListadosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBContratos;
    private javax.swing.JButton jBDescargas;
    private javax.swing.JButton jBLaboratorio;
    private javax.swing.JButton jBListados;
    private javax.swing.JButton jBLotes;
    private javax.swing.JButton jBProveedores;
    private javax.swing.JButton jBTambores;
    private javax.swing.JButton jBclientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuConfig;
    private javax.swing.JMenuItem jMenuItemApicultores;
    private javax.swing.JMenuItem jMenuItemClientes;
    private javax.swing.JMenuItem jMenuItemColor;
    private javax.swing.JMenuItem jMenuItemContratos;
    private javax.swing.JMenuItem jMenuItemDescargas;
    private javax.swing.JMenuItem jMenuItemDet;
    private javax.swing.JMenuItem jMenuItemLab;
    private javax.swing.JMenuItem jMenuItemList;
    private javax.swing.JMenuItem jMenuItemLotes;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemSectores;
    private javax.swing.JMenuItem jMenuItemTambores;
    private javax.swing.JMenuItem jMenuItemUsuarios;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    // End of variables declaration//GEN-END:variables
}

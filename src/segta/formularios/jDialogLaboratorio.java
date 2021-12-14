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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import segta.controladores.PoolJpaController;
import segta.clases.Color;
import segta.clases.Exportar;
import segta.clases.Sector;
import segta.clases.Tambor;
import segta.clases.TimestampCellRenderer;
import segta.controladores.TamborJpaController;
import segta.clases.Pool;

/**
 *
 * @author MODERNIZACION3
 */
public class jDialogLaboratorio extends javax.swing.JDialog {

    /**
     * Creates new form jDialogLaboratorio
     */
    /**
     * Centra Componente en la pantalla
     */
    Exportar archivoExcel;

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
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SegTAPU");
    EntityManager em = emf.createEntityManager();
    TamborJpaController controladorT = new TamborJpaController(emf);
    PoolJpaController controladorP = new PoolJpaController(emf);

    int ultimop;

    public jDialogLaboratorio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dCCExportar.setVisible(false);
        this.jTFDextrina.enable(false);
        jRBNegativo.setSelected(true);
//        jCBColor.setRenderer(new DefaultListCellRenderer() {
//            @Override
//            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//                if (value instanceof Color) {
//                    Color tc = (Color) value;
//                    return super.getListCellRendererComponent(list, tc.getColor(), index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
//
//                }
//                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
//            }
//
//        });
        jCBSector.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Sector) {
                    Sector tc = (Sector) value;
                    return super.getListCellRendererComponent(list, tc.getNombre(), index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.

                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
            }

        });

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();

        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTableDescargado.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTableDescargado.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTableDescargado.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTableDescargado.getColumnModel().getColumn(3).setCellRenderer(tcr);
        jTableDescargado.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTableDescargado.getColumnModel().getColumn(5).setCellRenderer(tcr);
        jTableDescargado.getColumnModel().getColumn(6).setCellRenderer(tcr);
        jTableDescargado.getColumnModel().getColumn(7).setCellRenderer(tcr);
        jTableDescargado.getColumnModel().getColumn(8).setCellRenderer(tcr);
        jTableDescargado.getColumnModel().getColumn(9).setCellRenderer(tcr);
        jTableDescargado.getColumnModel().getColumn(10).setCellRenderer(new TimestampCellRenderer());

        jTableClasificado.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTableClasificado.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTableClasificado.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTableClasificado.getColumnModel().getColumn(3).setCellRenderer(tcr);
        jTableClasificado.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTableClasificado.getColumnModel().getColumn(5).setCellRenderer(tcr);
        jTableClasificado.getColumnModel().getColumn(6).setCellRenderer(tcr);
        jTableClasificado.getColumnModel().getColumn(7).setCellRenderer(tcr);
        jTableClasificado.getColumnModel().getColumn(8).setCellRenderer(tcr);
        jTableClasificado.getColumnModel().getColumn(9).setCellRenderer(tcr);

    }

    public void actualizarTabla() {
        em.clear();
        jTFHumedad.setText("");
        jTFDextrina.setText("");
        jRBNegativo.setSelected(true);
        Query ql;
        String queryTxt = "SELECT t FROM Tambor t WHERE t.estado = 'descargado' order by t.numero";
        ql = em.createQuery(queryTxt);
        Query nuevaQuery = java.beans.Beans.isDesignTime() ? null : ql;
        tamborListDescargado.clear();
        tamborListDescargado.addAll(nuevaQuery.getResultList());
        jTableDescargado.getColumnModel().getColumn(10).setCellRenderer(new TimestampCellRenderer());
        tamborListClasificado.clear();
        tamborListClasificado.addAll(tamborQueryClasificado.getResultList());
    }

    public void actualizaSector() {
        sectorList.clear();
        sectorList.addAll(sectorQuery.getResultList());
    }

    public void actualizaColor() {
        colorList.clear();
        colorList.addAll(colorQuery.getResultList());
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
        tamborQueryDescargado = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT t FROM Tambor t WHERE t.estado = 'descargado' order by t.numero asc");
        tamborListDescargado = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(tamborQueryDescargado.getResultList());
        tamborQueryClasificado = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT t FROM Tambor t WHERE t.estado = 'clasificado'");
        tamborListClasificado = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(tamborQueryClasificado.getResultList());
        colorQuery = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT c FROM Color c");
        colorList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : colorQuery.getResultList();
        sectorQuery = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT s FROM Sector s ORDER BY s.nombre ASC");
        sectorList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(sectorQuery.getResultList());
        poolQuery = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT p FROM Pool p");
        poolList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(poolQuery.getResultList());
        buttonGroup1 = new javax.swing.ButtonGroup();
        tamborExcelQuery = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT t FROM Tambor t WHERE t.estado = 'descargado' order by t.recepcion desc");
        tamboresExcelList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(tamborQueryDescargado.getResultList());
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDescargado = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jCBSector = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButtonDesclasificar = new javax.swing.JButton();
        jTFDextrina = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButtonClasificar1 = new javax.swing.JButton();
        jTFHumedad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jRBNegativo = new javax.swing.JRadioButton();
        jRBPositivo = new javax.swing.JRadioButton();
        jButtonAnalisis = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableClasificado = new javax.swing.JTable();
        jTFNumeroBusca = new javax.swing.JTextField();
        jTFNumeroBuscaC = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jBBuscarTamborC = new javax.swing.JButton();
        jBBuscarTamborD = new javax.swing.JButton();
        jBVolver = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jBRecepcion = new javax.swing.JButton();
        jTFNumeroPool = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        dCCBuscarFecha = new datechooser.beans.DateChooserCombo();
        jBExportar = new javax.swing.JButton();
        jButtonPool = new javax.swing.JButton();
        dCCExportar = new datechooser.beans.DateChooserCombo();
        jBCargarColor = new javax.swing.JButton();
        jBAsignarColor = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LABORATORIO");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTableDescargado.setAutoscrolls(false);
        jTableDescargado.setColumnSelectionAllowed(true);
        jTableDescargado.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tamborListDescargado, jTableDescargado);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${numero}"));
        columnBinding.setColumnName("Numero");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idProveedor.razonSocial}"));
        columnBinding.setColumnName("Apicultor");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${senasa}"));
        columnBinding.setColumnName("Senasa");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idColor.color}"));
        columnBinding.setColumnName("Color");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${humedad}"));
        columnBinding.setColumnName("Humedad");
        columnBinding.setColumnClass(Float.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${estadoDextrina}"));
        columnBinding.setColumnName("Dextrina");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idPool.numero}"));
        columnBinding.setColumnName("Pool");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bruto}"));
        columnBinding.setColumnName("Bruto");
        columnBinding.setColumnClass(Float.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tara}"));
        columnBinding.setColumnName("Tara");
        columnBinding.setColumnClass(Float.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${neto}"));
        columnBinding.setColumnName("Neto");
        columnBinding.setColumnClass(Float.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${recepcion}"));
        columnBinding.setColumnName("Recepción");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTableDescargado);
        jTableDescargado.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Grupo");

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, sectorList, jCBSector);
        bindingGroup.addBinding(jComboBoxBinding);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/add2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonDesclasificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonDesclasificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/desclasificar 2 icono.png"))); // NOI18N
        jButtonDesclasificar.setText("Desclasificar");
        jButtonDesclasificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonDesclasificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDesclasificarActionPerformed(evt);
            }
        });

        jTFDextrina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTFDextrina.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Dextrina");

        jButtonClasificar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonClasificar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/clasificar 2 icono.png"))); // NOI18N
        jButtonClasificar1.setText("Clasificar");
        jButtonClasificar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonClasificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClasificar1ActionPerformed(evt);
            }
        });

        jTFHumedad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Humedad");

        buttonGroup1.add(jRBNegativo);
        jRBNegativo.setSelected(true);
        jRBNegativo.setText("Negativo");
        jRBNegativo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBNegativoMouseClicked(evt);
            }
        });

        buttonGroup1.add(jRBPositivo);
        jRBPositivo.setText("Positivo");
        jRBPositivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRBPositivoMouseClicked(evt);
            }
        });

        jButtonAnalisis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/report2.png"))); // NOI18N
        jButtonAnalisis.setText("Registrar Análisis");
        jButtonAnalisis.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnalisisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFHumedad, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBNegativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRBPositivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFDextrina, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBSector, 0, 109, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonClasificar1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(jButtonDesclasificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13)
                    .addComponent(jTFHumedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRBNegativo)
                    .addComponent(jRBPositivo)
                    .addComponent(jTFDextrina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jCBSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonClasificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDesclasificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAnalisis))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jCBSector, jLabel10, jLabel13, jLabel8, jRBNegativo, jRBPositivo, jTFDextrina, jTFHumedad});

        jTableClasificado.setColumnSelectionAllowed(true);

        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tamborListClasificado, jTableClasificado);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${numero}"));
        columnBinding.setColumnName("Numero");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${senasa}"));
        columnBinding.setColumnName("Senasa");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bruto}"));
        columnBinding.setColumnName("Bruto");
        columnBinding.setColumnClass(Float.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tara}"));
        columnBinding.setColumnName("Tara");
        columnBinding.setColumnClass(Float.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${neto}"));
        columnBinding.setColumnName("Neto");
        columnBinding.setColumnClass(Float.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idColor.color}"));
        columnBinding.setColumnName("Color");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${humedad}"));
        columnBinding.setColumnName("Humedad");
        columnBinding.setColumnClass(Float.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dextrina}"));
        columnBinding.setColumnName("Dextrina");
        columnBinding.setColumnClass(Float.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idSector.nombre}"));
        columnBinding.setColumnName("Grupo");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idPool.numero}"));
        columnBinding.setColumnName("Pool");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idPool.fecha}"));
        columnBinding.setColumnName("Fecha Pool");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(jTableClasificado);
        jTableClasificado.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jTFNumeroBusca.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFNumeroBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNumeroBuscaActionPerformed(evt);
            }
        });

        jTFNumeroBuscaC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel11.setText("NUMERO DE POOL");

        jLabel12.setText("NUMERO DE TAMBOR");

        jBBuscarTamborC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/buscar icono.png"))); // NOI18N
        jBBuscarTamborC.setText("Buscar");
        jBBuscarTamborC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBBuscarTamborC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarTamborCActionPerformed(evt);
            }
        });

        jBBuscarTamborD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/buscar icono.png"))); // NOI18N
        jBBuscarTamborD.setText("Buscar");
        jBBuscarTamborD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBBuscarTamborD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarTamborDActionPerformed(evt);
            }
        });

        jBVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/volver icono.png"))); // NOI18N
        jBVolver.setText("VOLVER");
        jBVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVolverActionPerformed(evt);
            }
        });

        jLabel2.setText("Tambores Descargados");

        jLabel3.setText("Tambores Clasificados");

        jBRecepcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/aceptar.png"))); // NOI18N
        jBRecepcion.setText("Recepcion");
        jBRecepcion.setIconTextGap(12);
        jBRecepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRecepcionActionPerformed(evt);
            }
        });

        jTFNumeroPool.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFNumeroPool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNumeroPoolActionPerformed(evt);
            }
        });

        jLabel14.setText("NUMERO DE TAMBOR");

        dCCBuscarFecha.setFormat(2);
        dCCBuscarFecha.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
        try {
            dCCBuscarFecha.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
        } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
            e1.printStackTrace();
        }
        dCCBuscarFecha.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));

        jBExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/excel.png"))); // NOI18N
        jBExportar.setText("Exportar");
        jBExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExportarActionPerformed(evt);
            }
        });

        jButtonPool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/pool.png"))); // NOI18N
        jButtonPool.setText("Pool");
        jButtonPool.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonPool.setIconTextGap(10);
        jButtonPool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPoolActionPerformed(evt);
            }
        });

        jBCargarColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/add2.png"))); // NOI18N
        jBCargarColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCargarColorActionPerformed(evt);
            }
        });

        jBAsignarColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/add icono.png"))); // NOI18N
        jBAsignarColor.setText("Asignar Color");
        jBAsignarColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAsignarColorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNumeroBuscaC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNumeroPool, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(dCCBuscarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jBBuscarTamborC, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNumeroBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jBBuscarTamborD, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(dCCExportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addComponent(jBVolver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jBAsignarColor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBCargarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButtonPool, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jBExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dCCExportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBBuscarTamborD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTFNumeroBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBAsignarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBCargarColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPool, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTFNumeroBuscaC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTFNumeroPool, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBBuscarTamborC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dCCBuscarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jBVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 1220, 690));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/fondo dialog.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, 770));

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPoolActionPerformed
        new JDialogPool(null, true).setVisible(true);
        actualizarTabla();
    }//GEN-LAST:event_jButtonPoolActionPerformed

    private void jBBuscarTamborDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarTamborDActionPerformed
        int numero = 0;
        boolean haynumero = false;

        if (!jTFNumeroBusca.getText().isEmpty()) {
            try {
                numero = Integer.parseInt(jTFNumeroBusca.getText());
                haynumero = true;

            } catch (Exception e) {
                haynumero = false;
            }
        }
        if (haynumero) {
            tamborQueryDescargado = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT t FROM Tambor t WHERE t.estado = 'descargado' and t.numero = " + numero);
        } else {
            tamborQueryDescargado = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT t FROM Tambor t WHERE t.estado = 'descargado' ORDER BY t.numero");
        }
        tamborListDescargado.clear();
        tamborListDescargado.addAll(tamborQueryDescargado.getResultList());


    }//GEN-LAST:event_jBBuscarTamborDActionPerformed

    private void jBBuscarTamborCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarTamborCActionPerformed

        Date fecha = null;
        String f = dCCBuscarFecha.getText();
        System.out.println(f);
        Query ql;
        String queryTxt = "SELECT t FROM Tambor t WHERE 1=1 AND t.estado = 'clasificado'";

        if (!this.dCCBuscarFecha.getText().isEmpty()) {
            try {
                String fechaS = this.dCCBuscarFecha.getText();
                fecha = (Date) new SimpleDateFormat("dd/MM/yy").parse(fechaS);

            } catch (ParseException ex) {
                Logger.getLogger(jDialogDescargas.class.getName()).log(Level.SEVERE, null, ex);
                fecha = null;
            }

            if (fecha != null) {
                queryTxt = queryTxt + " AND  t.idPool.fecha = :pFecha ";
            }
        }

        if (!this.jTFNumeroBuscaC.getText().isEmpty()) {
            int numero = Integer.parseInt(this.jTFNumeroBuscaC.getText());
            queryTxt = queryTxt + " AND t.numero like " + numero + "";
        }
        if (!this.jTFNumeroPool.getText().isEmpty()) {
            int numeroPool = Integer.parseInt(this.jTFNumeroPool.getText());
            queryTxt = queryTxt + " AND t.idPool.numero like " + numeroPool + "";
        }

        ql = em.createQuery(queryTxt);
        System.out.println(queryTxt);
        if (fecha != null) {
            ql.setParameter("pFecha", fecha);
        }

        tamborQueryClasificado = java.beans.Beans.isDesignTime() ? null : ql;
        tamborListClasificado.clear();
        tamborListClasificado.addAll(tamborQueryClasificado.getResultList());

    }//GEN-LAST:event_jBBuscarTamborCActionPerformed

    private void jButtonDesclasificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDesclasificarActionPerformed

        if (this.jTableClasificado.getSelectedRowCount() != 1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tambor");
            jTableClasificado.requestFocus();

        } else {
            Tambor tamborSel = (Tambor) tamborListClasificado.get(jTableClasificado.getSelectedRow());
            tamborSel.setEstado("descargado");

            try {
                controladorT.edit(tamborSel);
                tamborListClasificado.remove(tamborSel);
                JOptionPane.showMessageDialog(null, "Tambor desclasificado");
                actualizarTabla();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al desclasificar el tambor");
            }
        }

    }//GEN-LAST:event_jButtonDesclasificarActionPerformed

    private void jBVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVolverActionPerformed
        this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));        // TODO add your handling code here:
    }//GEN-LAST:event_jBVolverActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new jDialogSector(null, true).setVisible(true);
        actualizaSector();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTFNumeroBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNumeroBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNumeroBuscaActionPerformed

    private void jBRecepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRecepcionActionPerformed
        new JDialogRecepcion(this, true).setVisible(true);
        tamborQueryDescargado.getResultList();
        actualizarTabla();


    }//GEN-LAST:event_jBRecepcionActionPerformed

    private void jButtonClasificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClasificar1ActionPerformed

        if (this.jTableDescargado.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un tambor");
            jTableClasificado.requestFocus();

        } else {

            boolean clasificados = false;
            boolean sinClasificar = false;

            Sector sectorSel = (Sector) jCBSector.getSelectedItem();
            for (int i = 0; i < jTableDescargado.getRowCount(); i++) {

                if (jTableDescargado.isRowSelected(i)) {

                    Tambor tamborSel = (Tambor) tamborListDescargado.get(i);
                    //si no tienen color, recepcion o análisis, no se clasifican
                    if (tamborSel.getIdColor() == null || tamborSel.getRecepcion() == null || tamborSel.getHumedad() == null) {
                        sinClasificar = true;
                    } else {

                        tamborSel.setIdSector(sectorSel);
//                        tamborSel.setIdLote(idLote);
                        tamborSel.setEstado("clasificado");

                        try {
                            controladorT.edit(tamborSel);
                            clasificados = true;
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error a clasificar un tambor");
                        }
                    }
                }
            }
            if (sinClasificar) {
                JOptionPane.showMessageDialog(null, "No se clasificaron tambores ya que falta recepcion y/o color y/o analisis");
            }
            if (clasificados) {
                JOptionPane.showMessageDialog(null, "Tambores clasificados");
            }
            actualizarTabla();
        }

    }//GEN-LAST:event_jButtonClasificar1ActionPerformed

    private void jBExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExportarActionPerformed
        dCCExportar.setVisible(true);
        Date fecha = null;
        String fechaS = "";
        int input = JOptionPane.showConfirmDialog(this, dCCExportar, "Seleccione fecha", JOptionPane.DEFAULT_OPTION);

        if (input == JOptionPane.OK_OPTION) {

            fechaS = dCCExportar.getText();

            //TRAIGO LOS DATOS DE LOS POOL FILTRADO POR FECHA Y ARMO EL LIST
            Query ql;
            String queryTxt = "SELECT p FROM Pool p WHERE 1=1 ";

            try {

                fecha = (Date) new SimpleDateFormat("dd/MM/yy").parse(fechaS);

            } catch (ParseException ex) {
                Logger.getLogger(jDialogDescargas.class
                        .getName()).log(Level.SEVERE, null, ex);
                fecha = null;
            }
            queryTxt = queryTxt + " AND  p.fecha = :pFecha ";
            ql = em.createQuery(queryTxt);
            ql.setParameter("pFecha", fecha);
//            System.out.println(fecha);
//            System.out.println(ql.toString());
            poolList.clear();
            poolQuery = java.beans.Beans.isDesignTime() ? null : ql;
            poolList.addAll(poolQuery.getResultList());

            //BUSCO LOS TAMBORES DENTRO DE LOS POOL EN ESA FECHA
            Query qlt;
            tamboresExcelList.clear();
            for (int i = 0; i < poolList.size(); i++) {
                Pool p = (Pool) poolList.get(i);
                qlt = em.createQuery("SELECT t FROM Tambor t WHERE t.idPool =:pPool ORDER BY t.numero");
                qlt.setParameter("pPool", p);
                tamborExcelQuery = java.beans.Beans.isDesignTime() ? null : qlt;
                tamboresExcelList.addAll(tamborExcelQuery.getResultList());
            }
           
           qlt = em.createQuery("SELECT t FROM Tambor t WHERE t.idPool is null AND CAST(t.recepcion AS date) = CAST(:pFecha AS date) ORDER BY t.numero");
           qlt.setParameter("pFecha", fecha);
           tamborExcelQuery = java.beans.Beans.isDesignTime() ? null : qlt;
           tamboresExcelList.addAll(tamborExcelQuery.getResultList());
          
                   
            try {
                archivoExcel = new Exportar();
                archivoExcel.ExportarExcel(poolList, tamboresExcelList, fechaS);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No se pudo exportar", "Intentelo de nuevo", JOptionPane.ERROR_MESSAGE);
                System.out.println("Error excel " + ex);
            }

        }

    }//GEN-LAST:event_jBExportarActionPerformed

    private void jBCargarColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCargarColorActionPerformed
        new jDialogColor(null, true).setVisible(true);
        this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        new jDialogLaboratorio(null, true).setVisible(true);
    }//GEN-LAST:event_jBCargarColorActionPerformed

    private void jBAsignarColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAsignarColorActionPerformed

        if (this.jTableDescargado.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un tambor");
            jTableDescargado.requestFocus();
        } else {
            String tamboresSinRecepcion = "";
            Boolean hayTamboresSinRecepcion = false;
            JComboBox jComboBoxAsignaColor = new JComboBox((Vector) colorList);
            int input = JOptionPane.showConfirmDialog(this, jComboBoxAsignaColor, "Seleccione Color", JOptionPane.DEFAULT_OPTION);

            if (input == JOptionPane.OK_OPTION) {

                Color colorSel = (Color) jComboBoxAsignaColor.getSelectedItem();

                for (int i = 0; i < jTableDescargado.getRowCount(); i++) {

                    if (jTableDescargado.isRowSelected(i)) {

                        Tambor tamborSel = (Tambor) tamborListDescargado.get(i);
                        if (tamborSel.getRecepcion() != null) {

                            tamborSel.setIdColor(colorSel);
                            try {
                                controladorT.edit(tamborSel);

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Error al modificar un tambor", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            hayTamboresSinRecepcion = true;
                            tamboresSinRecepcion = tamboresSinRecepcion + tamborSel.getNumero() + ", ";
                        }

                    }

                }
                actualizarTabla();
                if (hayTamboresSinRecepcion) {
                    JOptionPane.showMessageDialog(null, "Los siguientes tambores no se modificaron por no tener recepción: " + tamboresSinRecepcion, "Validación", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Color asignado con éxito", "Validación", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
    }//GEN-LAST:event_jBAsignarColorActionPerformed

    private void jRBPositivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBPositivoMouseClicked
        jTFDextrina.setEnabled(true);
    }//GEN-LAST:event_jRBPositivoMouseClicked

    private void jRBNegativoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRBNegativoMouseClicked
        jTFDextrina.setText("");
        jTFDextrina.setEnabled(false);
    }//GEN-LAST:event_jRBNegativoMouseClicked

    private void jTFNumeroPoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNumeroPoolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNumeroPoolActionPerformed

    private void jButtonAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnalisisActionPerformed
        if (this.jTableDescargado.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un tambor");
            jTableDescargado.requestFocus();
        } else {
            float humedad = 0;
            float dextrina = 0;

            String estadoDex = "Negativo";
            if (jRBPositivo.isSelected()) {
                estadoDex = "Positivo";
            }
            //convierto la humedad en Float
            try {
                humedad = Float.parseFloat(jTFHumedad.getText());
            } catch (Exception ex) {
                humedad = 100;
            }

            //convierto la dextrina en Float
            if (jRBPositivo.isSelected()) {
                try {
                    dextrina = Float.parseFloat(jTFDextrina.getText());
                } catch (Exception ex) {
                    dextrina = 100;
                }
            }

            if (jTFHumedad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Completar el campo Humedad");
                jTFHumedad.requestFocus();
            } else if (humedad > 99.9) {
                JOptionPane.showMessageDialog(null, "Dato Humedad incorrecto");
                jTFHumedad.requestFocus();
            } else if (jRBPositivo.isSelected() && (jTFDextrina.getText().isEmpty() || dextrina > 99.9)) {

                if (jTFDextrina.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Completar el campo Dextrina");
                    jTFDextrina.requestFocus();
                } else if (dextrina > 99.9) {
                    JOptionPane.showMessageDialog(null, "Dato Dextrina incorrecto");
                    jTFDextrina.requestFocus();
                }
            } else {

                boolean sinActualizar = false;
                boolean actualizados = false;

                if (jRBPositivo.isSelected()) {
                    estadoDex = "Positivo";
                }

                for (int i = 0; i < jTableDescargado.getRowCount(); i++) {

                    if (jTableDescargado.isRowSelected(i)) {
                        try {
                            Tambor tamborSel = (Tambor) tamborListDescargado.get(i);
                            //Si el tambor está asociado a un Pool o no tiene fecha de recepción, no se pueden cargar los datos
                            if (tamborSel.getIdPool() != null || tamborSel.getRecepcion() == null) {
                                sinActualizar = true;
                            } else {

                                humedad = Float.parseFloat(jTFHumedad.getText());
                                if (!this.jTFDextrina.getText().isEmpty()) {
                                    dextrina = Float.parseFloat(jTFDextrina.getText());
                                }
                                tamborSel.setHumedad(humedad);
                                tamborSel.setDextrina(dextrina);
                                tamborSel.setEstadoDextrina(estadoDex);

                                controladorT.edit(tamborSel);
                                actualizados = true;
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al actualizar el tambor");
                        }
                    }

                }
                if (sinActualizar) {
                    JOptionPane.showMessageDialog(null, "Algunos tambores no se actualizaron por no tener recepción y/o estar asociados a un Pool");
                }
                if (actualizados) {
                    JOptionPane.showMessageDialog(null, "Tambores actualizados");
                }
                actualizarTabla();

            }
        }
    }//GEN-LAST:event_jButtonAnalisisActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jDialogLaboratorio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jDialogLaboratorio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jDialogLaboratorio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jDialogLaboratorio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jDialogLaboratorio dialog = new jDialogLaboratorio(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager SegTAPUEntityManager;
    private javax.swing.ButtonGroup buttonGroup1;
    private java.util.List<segta.clases.Color> colorList;
    private javax.persistence.Query colorQuery;
    private datechooser.beans.DateChooserCombo dCCBuscarFecha;
    private datechooser.beans.DateChooserCombo dCCExportar;
    private javax.swing.JButton jBAsignarColor;
    private javax.swing.JButton jBBuscarTamborC;
    private javax.swing.JButton jBBuscarTamborD;
    private javax.swing.JButton jBCargarColor;
    private javax.swing.JButton jBExportar;
    private javax.swing.JButton jBRecepcion;
    private javax.swing.JButton jBVolver;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAnalisis;
    private javax.swing.JButton jButtonClasificar1;
    private javax.swing.JButton jButtonDesclasificar;
    private javax.swing.JButton jButtonPool;
    private javax.swing.JComboBox<String> jCBSector;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRBNegativo;
    private javax.swing.JRadioButton jRBPositivo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFDextrina;
    private javax.swing.JTextField jTFHumedad;
    private javax.swing.JTextField jTFNumeroBusca;
    private javax.swing.JTextField jTFNumeroBuscaC;
    private javax.swing.JTextField jTFNumeroPool;
    private javax.swing.JTable jTableClasificado;
    private javax.swing.JTable jTableDescargado;
    private java.util.List poolList;
    private javax.persistence.Query poolQuery;
    private java.util.List<segta.clases.Sector> sectorList;
    private javax.persistence.Query sectorQuery;
    private javax.persistence.Query tamborExcelQuery;
    private java.util.List<segta.clases.Tambor> tamborListClasificado;
    private java.util.List<segta.clases.Tambor> tamborListDescargado;
    private javax.persistence.Query tamborQueryClasificado;
    private javax.persistence.Query tamborQueryDescargado;
    private java.util.List<segta.clases.Tambor> tamboresExcelList;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.formularios;

import com.sun.glass.events.KeyEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import static java.lang.Float.parseFloat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import segta.clases.Proveedor;
import segta.clases.Tambor;
import segta.controladores.DescargasJpaController;
import segta.controladores.TamborJpaController;
import segta.controladores.exceptions.NonexistentEntityException;
import static segta.formularios.inicio.de;

/**
 *
 * @author MODERNIZACION3
 */
public class jDialogTambores extends javax.swing.JDialog {

    /**
     * Creates new form jDialogTambores
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
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SegTAPU");
    EntityManager em = emf.createEntityManager();
    TamborJpaController controladorT = new TamborJpaController(emf);
    DescargasJpaController   controladorD = new DescargasJpaController(emf);

    String senasa = null;
    String estadoTambor = null;
    Long ultimo;
    Tambor tamborSel;
    String imprimeNeto = null;
    String imprimeNro = null;

    public jDialogTambores(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jBCargarProveedor.setVisible(false);
        buscaNro();
        this.jComboBoxApicultor.requestFocus();
        jRBNuevo.setSelected(true);
//        COMPLETA LA LISTA DESPLEGABLE CON LA RAZON SOCIAL DEL PROVEEDOR
        jCBProveedor.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Proveedor) {
                    Proveedor tc = (Proveedor) value;
                    return super.getListCellRendererComponent(list, tc.getRazonSocial(), index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.

                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.
            }

        });

//        CENTRA LOS DATOS EN LAS COLUMNAS DE LAS TABLAS
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tcr);

//        COMPLETA EL PANEL CON LOS DATOS DE LA DESCARGA
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaDescarga = sdf.format(de.getFecha());
        jTFFechaDescarga.setText(fechaDescarga);
        jTFCamion.setText(de.getCamion());
        jTFDescarga.setText(de.getIdDescargas().toString());
//        jCBProveedor.setSelectedItem(de.getIdProveedor());

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
        tamborQuery = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT t FROM Tambor t WHERE t.idDescarga = :descargaSel").setParameter("descargaSel",de);
        tamborList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(tamborQuery.getResultList());
        EstadoTambor = new javax.swing.ButtonGroup();
        proveedorQuery = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT p FROM Proveedor p WHERE p.baja = 0 ORDER BY p.razonSocial");
        proveedorList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(proveedorQuery.getResultList());
        acopiadorQuery = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT p FROM Proveedor p WHERE p.acopiador = 1 AND p.baja = 0 ORDER BY p.razonSocial");
        acopiadorList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(acopiadorQuery.getResultList());
        tamborQuery1 = java.beans.Beans.isDesignTime() ? null :  SegTAPUEntityManager.createQuery("SELECT t FROM Tambor t WHERE t.idDescarga = :descargaSel").setParameter("descargaSel",de);
        tamborList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() :  org.jdesktop.observablecollections.ObservableCollections.observableList(tamborQuery.getResultList());
        jPanel3 = new javax.swing.JPanel();
        jPanelTambores = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBBorrar = new javax.swing.JButton();
        jBEditar = new javax.swing.JButton();
        jBAgregar = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jBApicultor = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTFNumero = new javax.swing.JTextField();
        jTFSenasa = new javax.swing.JTextField();
        jTFTara = new javax.swing.JTextField();
        jCBProveedor = new javax.swing.JComboBox<>();
        jLNeto = new javax.swing.JTextField();
        jRBNuevo = new javax.swing.JRadioButton();
        jRReacondicionado = new javax.swing.JRadioButton();
        jRGolpeado = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCheckBoxSenasa = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAObservaciones = new javax.swing.JTextArea();
        jTFBruto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jRCambio = new javax.swing.JRadioButton();
        jTFRemito = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jBCargarProveedor = new javax.swing.JButton();
        jTFIdentificador = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxApicultor = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTFCamion = new javax.swing.JTextField();
        jTFFechaDescarga = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTFDescarga = new javax.swing.JTextField();
        jBVolver = new javax.swing.JButton();
        jTextFieldPesoTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldCantidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        Proveedor p = new Proveedor();
        p.setRazonSocial("");
        proveedorList.add(0,p);

        Proveedor a = new Proveedor();
        a.setRazonSocial("");
        acopiadorList.add(0,a);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CARGAR TAMBORES");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setPreferredSize(new java.awt.Dimension(1330, 650));

        jPanelTambores.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TAMBORES DESCARGADOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable1.setOpaque(false);
        jTable1.setRequestFocusEnabled(false);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tamborList1, jTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${numero}"));
        columnBinding.setColumnName("Numero");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${senasa}"));
        columnBinding.setColumnName("Senasa");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${identificador}"));
        columnBinding.setColumnName("Identificador");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tara}"));
        columnBinding.setColumnName("Tara");
        columnBinding.setColumnClass(Float.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bruto}"));
        columnBinding.setColumnName("Bruto");
        columnBinding.setColumnClass(Float.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${neto}"));
        columnBinding.setColumnName("Neto");
        columnBinding.setColumnClass(Float.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${acopiador}"));
        columnBinding.setColumnName("Acopiador");
        columnBinding.setColumnClass(segta.clases.Proveedor.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idProveedor}"));
        columnBinding.setColumnName("Apicultor");
        columnBinding.setColumnClass(segta.clases.Proveedor.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${observaciones}"));
        columnBinding.setColumnName("Observaciones");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                /*
                ListSelectionModel sm = (ListSelectionModel) e.getSource();
                int sum = 0;
                for (int i = sm.getMinSelectionIndex(); i <= sm.getMaxSelectionIndex(); i++) {
                    sum += sm.isSelectedIndex(i) ? 1 : 0;
                }
                contadorT=Integer.toString(sum);
                */
                calculaSeleccion();
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(3).setMinWidth(60);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(4).setMinWidth(60);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(5).setMinWidth(60);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(60);
        }

        jBBorrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/borrar.png"))); // NOI18N
        jBBorrar.setText("BORRAR");
        jBBorrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBorrarActionPerformed(evt);
            }
        });

        jBEditar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/editar.png"))); // NOI18N
        jBEditar.setText("EDITAR");
        jBEditar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEditarActionPerformed(evt);
            }
        });

        jBAgregar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/aceptar.png"))); // NOI18N
        jBAgregar.setText("AGREGAR");
        jBAgregar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarActionPerformed(evt);
            }
        });
        jBAgregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBAgregarKeyPressed(evt);
            }
        });

        jButtonImprimir.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/imprimir icono.png"))); // NOI18N
        jButtonImprimir.setText("ETIQUETA");
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });

        jBApicultor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBApicultor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/cambiar icono.png"))); // NOI18N
        jBApicultor.setText("APIC.");
        jBApicultor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBApicultor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBApicultorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTamboresLayout = new javax.swing.GroupLayout(jPanelTambores);
        jPanelTambores.setLayout(jPanelTamboresLayout);
        jPanelTamboresLayout.setHorizontalGroup(
            jPanelTamboresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTamboresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTamboresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelTamboresLayout.createSequentialGroup()
                        .addComponent(jBAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBApicultor, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jButtonImprimir)))
                .addContainerGap())
        );
        jPanelTamboresLayout.setVerticalGroup(
            jPanelTamboresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTamboresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTamboresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBApicultor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DEL TAMBOR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jTFNumero.setEditable(false);
        jTFNumero.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jTFSenasa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFSenasa.setNextFocusableComponent(jTFTara);
        jTFSenasa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFSenasaFocusLost(evt);
            }
        });
        jTFSenasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFSenasaKeyPressed(evt);
            }
        });

        jTFTara.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFTara.setNextFocusableComponent(jTFBruto);
        jTFTara.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFTaraFocusLost(evt);
            }
        });
        jTFTara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFTaraKeyPressed(evt);
            }
        });

        jCBProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCBProveedor.setNextFocusableComponent(jTFNumero);

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, proveedorList, jCBProveedor);
        bindingGroup.addBinding(jComboBoxBinding);

        jCBProveedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBProveedorItemStateChanged(evt);
            }
        });
        jCBProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCBProveedorKeyPressed(evt);
            }
        });

        jLNeto.setEditable(false);
        jLNeto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        EstadoTambor.add(jRBNuevo);
        jRBNuevo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jRBNuevo.setText("NUEVO");

        EstadoTambor.add(jRReacondicionado);
        jRReacondicionado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jRReacondicionado.setText("REACONDICIONADO");

        EstadoTambor.add(jRGolpeado);
        jRGolpeado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jRGolpeado.setText("GOLPEADO");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("APICULTOR");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("NÚMERO");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("SENASA");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("BRUTO");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("TARA");

        jCheckBoxSenasa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jCheckBoxSenasa.setSelected(true);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("OBSERV.");

        jTAObservaciones.setColumns(20);
        jTAObservaciones.setRows(5);
        jScrollPane2.setViewportView(jTAObservaciones);

        jTFBruto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFBruto.setNextFocusableComponent(jLNeto);
        jTFBruto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFBrutoFocusLost(evt);
            }
        });
        jTFBruto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFBrutoActionPerformed(evt);
            }
        });
        jTFBruto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFBrutoKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("SIN ETIQUETA");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("NETO");

        EstadoTambor.add(jRCambio);
        jRCambio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jRCambio.setText("CAMBIO");

        jTFRemito.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTFRemito.setNextFocusableComponent(jTFTara);
        jTFRemito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFRemitoKeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("REMITO");

        jBCargarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/add2.png"))); // NOI18N
        jBCargarProveedor.setEnabled(false);
        jBCargarProveedor.setFocusable(false);
        jBCargarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCargarProveedorActionPerformed(evt);
            }
        });

        jTFIdentificador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFIdentificadorKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("IDENTIFICADOR");

        jComboBoxApicultor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, acopiadorList, jComboBoxApicultor);
        bindingGroup.addBinding(jComboBoxBinding);

        jComboBoxApicultor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBoxApicultorKeyPressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("ACOPIADOR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jRBNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRReacondicionado)
                .addGap(18, 18, 18)
                .addComponent(jRGolpeado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRCambio))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTFSenasa, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTFBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBoxSenasa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jTFIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(47, 47, 47)
                                                .addComponent(jBCargarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jTFNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jCBProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxApicultor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFTara, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(400, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxApicultor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCBProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFIdentificador, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jBCargarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFRemito, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxSenasa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTFSenasa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFTara, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBNuevo)
                    .addComponent(jRReacondicionado)
                    .addComponent(jRCambio)
                    .addComponent(jRGolpeado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DESCARGA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("CAMION");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("FECHA DESCARGA");

        jTFCamion.setEditable(false);
        jTFCamion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTFFechaDescarga.setEditable(false);
        jTFFechaDescarga.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("DESCARGA");

        jTFDescarga.setEditable(false);
        jTFDescarga.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFFechaDescarga, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFCamion, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFDescarga, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTFDescarga)
                                .addComponent(jLabel16))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTFCamion)
                                .addComponent(jLabel8))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTFFechaDescarga))))
                .addContainerGap())
        );

        jBVolver.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/volver icono.png"))); // NOI18N
        jBVolver.setText("VOLVER");
        jBVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBVolverActionPerformed(evt);
            }
        });

        jTextFieldPesoTotal.setEditable(false);
        jTextFieldPesoTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("CANTIDAD");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("NETO");

        jTextFieldCantidad.setEditable(false);
        jTextFieldCantidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPesoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jBVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanelTambores, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(126, 126, 126))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanelTambores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldPesoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jBVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segta/imagenes/fondo dialog.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void buscaNro() {

        Object resultado = em.createNativeQuery("select IFNULL(MAX(t.numero),(YEAR(now())-2000)*100000) from Tambor t  where t.numero> (YEAR(now())-2000)*100000").getSingleResult();
        ultimo = (Long) resultado;
        ultimo++;
        jTFNumero.setText(String.valueOf(ultimo));
    }

    public void actualizarTabla() {

        jTFSenasa.setText("");
        jTFBruto.setText("");
        jTFTara.setText("");
        jLNeto.setText("");
        jTFIdentificador.setText("");
        tamborList1.clear();
        tamborQuery1 = java.beans.Beans.isDesignTime() ? null : SegTAPUEntityManager.createQuery("SELECT t FROM Tambor t WHERE t.idDescarga =:descargaSel").setParameter("descargaSel", de);
        tamborList1.addAll(tamborQuery1.getResultList());
        this.jComboBoxApicultor.requestFocus();
        //jCBProveedor.setSelectedIndex(0);    
       
    }

    public boolean validarIngreso() {
        boolean valida = true;
        if (jTFNumero.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Numero incompleto");
            valida = false;
            jTFNumero.requestFocus();
        } else {
            for (Tambor tambor : controladorT.findTamborEntities()) {
                if (tambor.getNumero() == Integer.parseInt(jTFNumero.getText())) {
                    if (JOptionPane.showConfirmDialog(null, "El tambor ya existe, desea modificar ", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        actualizarTabla();
                        valida = false;
                        jTFNumero.requestFocus();
                    }
                }
            }
        }
        if (jCheckBoxSenasa.isSelected()) {

            senasa = null;

        } else {
            senasa = jTFSenasa.getText();
        }

        if (!validaBrutoTara()) {
            valida = false;
        };

        return valida;
    }
    private void jBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarActionPerformed

//        jTFNumero.requestFocus();

        if (jRBNuevo.isSelected()) {
            estadoTambor = "nuevo";
        } else if (jRReacondicionado.isSelected()) {
            estadoTambor = "reacondicionado";
        } else if (jRGolpeado.isSelected()) {
            estadoTambor = "golpeado";
        } else if (jRCambio.isSelected()) {
            estadoTambor = "cambio";
        }
        if (validarIngreso()) {

            Tambor nuevoTambor = new Tambor();
            float neto;
            int nro = Integer.parseInt(jTFNumero.getText());

            Proveedor provSel = (Proveedor) jCBProveedor.getSelectedItem();
            nuevoTambor.setIdDescarga(de);
            Proveedor AcoSel = (Proveedor) this.jComboBoxApicultor.getSelectedItem();
            if (AcoSel.getRazonSocial() == "") {
                AcoSel.setBaja(0);}
            
            nuevoTambor.setAcopiador(AcoSel);            
            if (provSel.getRazonSocial() == "") {
                nuevoTambor.setIdProveedor(AcoSel);
            }else{
                nuevoTambor.setIdProveedor(provSel);
                
                
            }
            nuevoTambor.setIdentificador(jTFIdentificador.getText());
            nuevoTambor.setNumero(nro);
            nuevoTambor.setRemito(jTFRemito.getText());
            nuevoTambor.setSenasa(senasa);
            float bruto = Float.parseFloat(jTFBruto.getText());
            nuevoTambor.setBruto(bruto);
            float tara = Float.parseFloat(jTFTara.getText());
            nuevoTambor.setTara(tara);
            neto = bruto - tara;
            nuevoTambor.setNeto(neto);
            nuevoTambor.setEstado("descargado");
            nuevoTambor.setEstadoTambor(estadoTambor);
            nuevoTambor.setTrazabilidad(false);
            nuevoTambor.setObservaciones(jTAObservaciones.getText());

            try {
                controladorT.create(nuevoTambor);
                //JOptionPane.showMessageDialog(null, "Tambor cargado");

                imprimeNeto = Float.toString(neto);
                imprimeNro = Integer.toString(nro);
                imprimir();
                jTAObservaciones.setText("");
                actualizarTabla();
                jTable1.requestFocus();
                jTable1.changeSelection(jTable1.getRowCount() - 1, 0, false, false);
                buscaNro();
//                this.jTFSenasa.requestFocus();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error al cargar tambor");
                Logger.getLogger(jDialogTambores.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
this.jComboBoxApicultor.requestFocus();
    }//GEN-LAST:event_jBAgregarActionPerformed

    private void jBEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEditarActionPerformed

        if (this.jTable1.getSelectedRowCount() == 1) {
            Tambor tamborSel = (Tambor) tamborList1.get(jTable1.getSelectedRow());
            if (tamborSel.getEstado().equals("descargado")) {
                new JDialogeditarTambor(this, true, tamborSel).setVisible(true);
            } else if (tamborSel.getEstado().equals("clasificado")) {
                new JDialogeditarTambor(this, true, tamborSel).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "El tambor se encuentra " + tamborSel.getEstado() + ". No se puede modificar", "Validación", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tambor", "Validación", JOptionPane.WARNING_MESSAGE);
        }
        actualizarTabla();
    }//GEN-LAST:event_jBEditarActionPerformed

    private void jBBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBorrarActionPerformed

        if (this.jTable1.getSelectedRowCount() == 1) {
            Tambor tamborSel = (Tambor) tamborList1.get(jTable1.getSelectedRow());
            if (tamborSel.getEstado().equals("descargado")) {
                if (JOptionPane.showConfirmDialog(null, "Desea Eliminar", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        controladorT.destroy(tamborSel.getIdTambor());
                        JOptionPane.showMessageDialog(null, "Tambor eliminado");
                        actualizarTabla();
                        buscaNro();

                    } catch (NonexistentEntityException ex) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el tambor", "Error", JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(jDialogDescargas.class.getName()).log(Level.SEVERE, null, ex);

                    };
                }
            } else {
                JOptionPane.showMessageDialog(null, "El tambor se encuentra clasificado. No se puede eliminar", "Validación", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tambor", "Validación", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jBBorrarActionPerformed

    private void jBVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBVolverActionPerformed
        this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));     }//GEN-LAST:event_jBVolverActionPerformed

    private void jTFTaraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFTaraFocusLost

    }//GEN-LAST:event_jTFTaraFocusLost

    private void jTFSenasaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFSenasaFocusLost
        if (jTFSenasa.getText().isEmpty()) {
            this.jCheckBoxSenasa.setSelected(true);
        } else {
            this.jCheckBoxSenasa.setSelected(false);
        }
    }//GEN-LAST:event_jTFSenasaFocusLost

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        if (this.jTable1.getSelectedRowCount() == 1) {
            Tambor tamborSel = (Tambor) tamborList1.get(jTable1.getSelectedRow());
            imprimeNeto = String.valueOf(tamborSel.getNeto());
            imprimeNro = String.valueOf(tamborSel.getNumero());
            imprimir();

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tambor", "Validación", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jTFBrutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFBrutoFocusLost
        boolean val = validaBrutoTara();
    }//GEN-LAST:event_jTFBrutoFocusLost

    public boolean validaBrutoTara() {
        float bruto = 0;
        float tara = 0;
        boolean valida = true;

        if (valida) {
            try {
                tara = parseFloat(jTFTara.getText());
                if (tara > 99) {
                    valida = false;
                    JOptionPane.showMessageDialog(null, "Tara incorrecta");
                    jTFTara.requestFocus();
                }
            } catch (Exception ex) {
                valida = false;
                JOptionPane.showMessageDialog(null, "Ingrese sólo números");
                jTFTara.requestFocus();
            }
        }

        if (valida) {
            try {
                bruto = parseFloat(jTFBruto.getText());
                if (bruto > 999) {
                    valida = false;
                    JOptionPane.showMessageDialog(null, "Bruto incorrecto");
                    jTFBruto.requestFocus();
                }
            } catch (Exception ex) {
                valida = false;
                JOptionPane.showMessageDialog(null, "Ingrese sólo números");
                jTFBruto.requestFocus();
            }

        }

        if (valida) {
            float neto = bruto - tara;
            if (neto < 0) {
                valida = false;
                JOptionPane.showMessageDialog(null, "El neto no puede ser negativo");
            } else {
                jLNeto.setText(neto + "");

            }
        }

        return valida;
    }

    private void jTFBrutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFBrutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFBrutoActionPerformed

    private void jBApicultorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBApicultorActionPerformed
        // TODO add your handling code here:
        if (this.jTable1.getSelectedRowCount() != 0) {

            boolean validacion = false;
//            JComboBox jComboBoxCambiaApicultor = new JComboBox((Vector) proveedorList);

            int input = JOptionPane.showConfirmDialog(this, jCBProveedor, "Seleccione Apicultor", JOptionPane.DEFAULT_OPTION);

            if (input == JOptionPane.OK_OPTION) {

                Proveedor apicultorSel = (Proveedor) jCBProveedor.getSelectedItem();

                for (int i = 0; i < jTable1.getRowCount(); i++) {

                    if (jTable1.isRowSelected(i)) {
                        tamborSel = (Tambor) tamborList1.get(i);
                        if (tamborSel.getEstado().equals("despachado") || tamborSel.getEstado().equals("loteado")) {
                            validacion = true;
                        } else {
                            tamborSel.setIdProveedor(apicultorSel);
                            try {
                                controladorT.edit(tamborSel);
                            } catch (Exception ex) {
                                validacion = true;
                                Logger.getLogger(jDialogTamboresGeneral.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    }

                }
                if (validacion) {
                    JOptionPane.showMessageDialog(null, "Hay tambores que no se pudieron cambiar ya que se encontarban despachados o en lote", "Validación", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los tambores fueron modificados con éxito", "Validación", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un Tambor", "Validación", JOptionPane.WARNING_MESSAGE);
        }

        tamborList1.clear();
        tamborList1.addAll(tamborQuery1.getResultList());
        this.jComboBoxApicultor.requestFocus();
    }//GEN-LAST:event_jBApicultorActionPerformed

    private void jBCargarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCargarProveedorActionPerformed
        jDialogProveedor dialog = new jDialogProveedor(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
        SegTAPUEntityManager.clear();
        this.proveedorList.clear();
        this.proveedorList.addAll(proveedorQuery.getResultList());

    }//GEN-LAST:event_jBCargarProveedorActionPerformed

    private void jCBProveedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBProveedorItemStateChanged
        this.jTFRemito.setText("");
    }//GEN-LAST:event_jCBProveedorItemStateChanged
// FUNCIONES PARA PASAR DE CAMPO EN CAMPO CON ENTRE 
    private void jComboBoxApicultorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxApicultorKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.jCBProveedor.requestFocus();}
    }//GEN-LAST:event_jComboBoxApicultorKeyPressed

    private void jCBProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCBProveedorKeyPressed
             if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.jTFIdentificador.requestFocus();}
    }//GEN-LAST:event_jCBProveedorKeyPressed

    private void jTFIdentificadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFIdentificadorKeyPressed
             if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.jTFRemito.requestFocus();}
    }//GEN-LAST:event_jTFIdentificadorKeyPressed

    private void jTFRemitoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFRemitoKeyPressed
             if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.jTFSenasa.requestFocus();}
    }//GEN-LAST:event_jTFRemitoKeyPressed

    private void jTFSenasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFSenasaKeyPressed
             if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.jTFTara.requestFocus();}
    }//GEN-LAST:event_jTFSenasaKeyPressed

    private void jTFTaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFTaraKeyPressed
             if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.jTFBruto.requestFocus();}
    }//GEN-LAST:event_jTFTaraKeyPressed

    private void jTFBrutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFBrutoKeyPressed
             if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.jBAgregar.requestFocus();}
    }//GEN-LAST:event_jTFBrutoKeyPressed

    private void jBAgregarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBAgregarKeyPressed
         
        if (jRBNuevo.isSelected()) {
            estadoTambor = "nuevo";
        } else if (jRReacondicionado.isSelected()) {
            estadoTambor = "reacondicionado";
        } else if (jRGolpeado.isSelected()) {
            estadoTambor = "golpeado";
        } else if (jRCambio.isSelected()) {
            estadoTambor = "cambio";
        }
        if (validarIngreso()) {

            Tambor nuevoTambor = new Tambor();
            float neto;
            int nro = Integer.parseInt(jTFNumero.getText());

            Proveedor provSel = (Proveedor) jCBProveedor.getSelectedItem();
            nuevoTambor.setIdDescarga(de);
            Proveedor AcoSel = (Proveedor) this.jComboBoxApicultor.getSelectedItem();
            if (AcoSel.getRazonSocial() == "") {
                AcoSel.setBaja(0);}
            
            nuevoTambor.setAcopiador(AcoSel);            
            if (provSel.getRazonSocial() == "") {
                nuevoTambor.setIdProveedor(AcoSel);
            }else{
                nuevoTambor.setIdProveedor(provSel);
                
                
            }
            nuevoTambor.setIdentificador(jTFIdentificador.getText());
            nuevoTambor.setNumero(nro);
            nuevoTambor.setRemito(jTFRemito.getText());
            nuevoTambor.setSenasa(senasa);
            float bruto = Float.parseFloat(jTFBruto.getText());
            nuevoTambor.setBruto(bruto);
            float tara = Float.parseFloat(jTFTara.getText());
            nuevoTambor.setTara(tara);
            neto = bruto - tara;
            nuevoTambor.setNeto(neto);
            nuevoTambor.setEstado("descargado");
            nuevoTambor.setEstadoTambor(estadoTambor);
            nuevoTambor.setTrazabilidad(false);
            nuevoTambor.setObservaciones(jTAObservaciones.getText());

            try {
                controladorT.create(nuevoTambor);
                //JOptionPane.showMessageDialog(null, "Tambor cargado");

                imprimeNeto = Float.toString(neto);
                imprimeNro = Integer.toString(nro);
                imprimir();
                jTAObservaciones.setText("");
                actualizarTabla();
                jTable1.requestFocus();
                jTable1.changeSelection(jTable1.getRowCount() - 1, 0, false, false);
                buscaNro();
//                this.jTFSenasa.requestFocus();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "error al cargar tambor");
                Logger.getLogger(jDialogTambores.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
this.jComboBoxApicultor.requestFocus();
    }//GEN-LAST:event_jBAgregarKeyPressed

    public PageFormat getPageFormat(PrinterJob pj) {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double width = convert_CM_To_PPI(10);      //printer know only point per inch.default value is 72ppi
        double height = convert_CM_To_PPI(8);
        paper.setSize(width, height);
        paper.setImageableArea(
                0,
                0,
                width,
                height
        );   //define boarder size    after that print area width is about 180 points

        pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
        pf.setPaper(paper);

        return pf;
    }

    protected static double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    public class BillPrintable implements Printable {

        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException {

            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;

                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                try {

                    //Crea el codigo de Barras//
                    Barcode barcode = null;
                    try {
                        barcode = BarcodeFactory.createCode128(imprimeNro);
                    } catch (Exception ex) {

                    }
                    //dibujo el texto tambien
                    barcode.setDrawingText(false);
                    barcode.setBarHeight(60);
                    barcode.setBarWidth(2);

                    Image im = BarcodeImageHandler.getImage(barcode);

                    //cambia letra
                    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 25);
                    g2d.setFont(font);

                    //IMPRIME TEXTO GRANDE
                    g2d.drawString("GASRRONI SRL", 75, 30);

                    //imprime codigo de barra grande
                    g2d.drawImage(im, 65, 40, 210, 40, null);

                    //imprime numero grande
                    font = new Font(Font.SANS_SERIF, Font.BOLD, 55);
                    g2d.setFont(font);
                    //g2d.drawString(imprimeNro, 110, 150);
                    g2d.drawString(imprimeNro, 65, 150);

                    //imprime neto
                    font = new Font(Font.SANS_SERIF, Font.BOLD, 30);
                    g2d.setFont(font);
                    g2d.drawString("Neto: " + imprimeNeto + "KG.", 72, 193);

                    //gira a vertical
                    g2d.rotate(Math.toRadians(90));

                    //imprime codigo de barra vertical arriba
                    g2d.drawImage(im, 7, -44, 105, 20, null);

                    //imprime codigo de barra vertical abajo
                    //g2d.drawImage(im,102, -44, 105, 20, null);
                    //g2d.drawImage(im, 110, -44, 105, 20, null);
                    font = new Font(Font.SANS_SERIF, Font.BOLD, 10);
                    g2d.setFont(font);
                    //imprime nombre vertical arriba
                    g2d.drawString("GASRRONI SRL", 21, -46);
                    //imprime nombre vertical abajo
                    // g2d.drawString("GASRRONI SRL", 118, -46);

                    font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
                    g2d.setFont(font);
                    //imprime numero vertical arriba
                    g2d.drawString(imprimeNro, 25, -7);
                    //imprime numero vertical abajo
                    // g2d.drawString(imprimeNro, 120, -7);

                } catch (Exception r) {
                    r.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    private void imprimir() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(), getPageFormat(pj));
        try {
            for (int i = 0; i < 2; i++) {
                pj.print();
            }

        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }

    public void calculaSeleccion() {
        Tambor t = new Tambor();
        int cantidad = 0;
        float totPeso = 0;
        float peso = 0;

        for (int i = 0; i < jTable1.getRowCount(); i++) {

            if (jTable1.isRowSelected(i)) {
                cantidad++;
                t = (Tambor) tamborList1.get(i);

                //Suma Pesos
                try {
                    peso = t.getNeto();
                } catch (Exception e) {
                    peso = 0;
                }
                totPeso = totPeso + peso;
            }
        }

        DecimalFormat formato1 = new DecimalFormat("#.00");

        this.jTextFieldCantidad.setText(Integer.toString(cantidad));
        this.jTextFieldPesoTotal.setText(formato1.format(totPeso));

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup EstadoTambor;
    private javax.persistence.EntityManager SegTAPUEntityManager;
    private java.util.List<segta.clases.Proveedor> acopiadorList;
    private javax.persistence.Query acopiadorQuery;
    private javax.swing.JButton jBAgregar;
    private javax.swing.JButton jBApicultor;
    private javax.swing.JButton jBBorrar;
    private javax.swing.JButton jBCargarProveedor;
    private javax.swing.JButton jBEditar;
    private javax.swing.JButton jBVolver;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JComboBox<String> jCBProveedor;
    private javax.swing.JCheckBox jCheckBoxSenasa;
    private javax.swing.JComboBox<String> jComboBoxApicultor;
    private javax.swing.JTextField jLNeto;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelTambores;
    private javax.swing.JRadioButton jRBNuevo;
    private javax.swing.JRadioButton jRCambio;
    private javax.swing.JRadioButton jRGolpeado;
    private javax.swing.JRadioButton jRReacondicionado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTAObservaciones;
    private javax.swing.JTextField jTFBruto;
    private javax.swing.JTextField jTFCamion;
    private javax.swing.JTextField jTFDescarga;
    private javax.swing.JTextField jTFFechaDescarga;
    private javax.swing.JTextField jTFIdentificador;
    private javax.swing.JTextField jTFNumero;
    private javax.swing.JTextField jTFRemito;
    private javax.swing.JTextField jTFSenasa;
    public javax.swing.JTextField jTFTara;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldCantidad;
    private javax.swing.JTextField jTextFieldPesoTotal;
    private java.util.List<segta.clases.Proveedor> proveedorList;
    private javax.persistence.Query proveedorQuery;
    private java.util.List<segta.clases.Tambor> tamborList;
    private java.util.List<segta.clases.Tambor> tamborList1;
    private javax.persistence.Query tamborQuery;
    private javax.persistence.Query tamborQuery1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}

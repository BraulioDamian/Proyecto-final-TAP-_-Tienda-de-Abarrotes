/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Configuraciones;
import Consultas.*;
import ConexionDB.*;
import DBObjetos.Area;
import INVENTARIO.*;
import DBObjetos.Usuario;
import DBObjetos.Usuario.Rol;
import Graficas.AvisosFrame;
import PanelUsuarios.UsuariosPanel;
import Principal.MenuPrincipal;
import Venta.Venta;
import com.sun.jdi.connect.spi.Connection;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import login.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.coobird.thumbnailator.Thumbnails;
import java.util.Date;
import com.itextpdf.text.DocumentException;  // Importación necesaria
import java.awt.Color;


/**
 *
 * @author braul
 */
public class Configuraciones extends javax.swing.JFrame {

    
    private Principal2_0 ventanaPrincipal;
    private static Configuraciones instance;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private boolean isCalendarOpen = false; // Controla si el calendario está abierto
    private AnimacionPanel animador;
    private boolean menuDesplegado = false;
     
     private Venta venta;

       // Se modificó el constructor para ser privado

    private  Configuraciones() {
        setUndecorated(true); // Hacer que el JFrame sea indecorado
        initComponents();
        setLocationRelativeTo(null); // Centramos la ventana en la pantalla

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarYVolver();
            }
        });
         
         cargarAreasEnComboBox();
         animador = new AnimacionPanel(); // Inicializa el animador
         
         

    }
    

    
        // Se agregó este método para obtener la instancia única de Configuraciones
    public static Configuraciones getInstance() {
        if (instance == null) {
            instance = new Configuraciones();
        }
        return instance;
    }

    public void setVentanaPrincipal(Principal2_0 principal) {
        this.ventanaPrincipal = principal;
    }

    private void cerrarYVolver() {
        if (ventanaPrincipal != null) {
            ventanaPrincipal.setVisible(true); // Hace visible la ventana principal nuevamente
        }
        dispose(); // Cierra esta ventana
    }
    
    // Método para reiniciar la instancia Singleton
    public static void resetInstance() {
        instance = null;
    }   
    
            

        private boolean fechaEsAdecuada(Date fechaInicio, Date fechaFinal) {
        return !fechaFinal.before(fechaInicio);
    }

    private void mostrarCalendario(JTextField textField, boolean esFechaInicio) {
        isCalendarOpen = true;
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setMaxSelectableDate(new Date());

        try {
            Date fechaActual = textField.getText().isEmpty() ? new Date() : dateFormat.parse(textField.getText());
            dateChooser.setDate(fechaActual);

            int result = JOptionPane.showConfirmDialog(null, dateChooser, "Seleccione la fecha", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                Date nuevaFecha = dateChooser.getDate();
                textField.setText(dateFormat.format(nuevaFecha));
                if (!esFechaInicio) {
                    Date fechaInicio = dateFormat.parse(txtFechaInicio.getText());
                    if (!fechaEsAdecuada(fechaInicio, nuevaFecha)) {
                        JOptionPane.showMessageDialog(this, "La fecha final no puede ser anterior a la fecha de inicio.", "Error de Fecha", JOptionPane.ERROR_MESSAGE);
                        textField.setText(dateFormat.format(fechaActual)); // Revertir a la fecha original
                    }
                }
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Error al parsear la fecha.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
        isCalendarOpen = false;
    }










    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        areaSeleccionada = new javax.swing.JComboBox<>();
        txtPorcentaje = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnPrecios = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnGenerarPDF = new javax.swing.JButton();
        txtFechaFinal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JTextField();
        MenuPlegable2 = new javax.swing.JPanel();
        Menu2 = new javax.swing.JLabel();
        Inicio2 = new javax.swing.JLabel();
        Usuarios2 = new javax.swing.JLabel();
        Configuracion2 = new javax.swing.JLabel();
        Ventas2 = new javax.swing.JLabel();
        Analisis2 = new javax.swing.JLabel();
        Inventario2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 0));

        jLabel1.setText("Selccione un Area");

        jLabel2.setText("Porcentaje (Solo numero)");

        btnPrecios.setText("Actualizar Precios");
        btnPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreciosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPorcentaje)
                    .addComponent(btnPrecios)
                    .addComponent(areaSeleccionada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(areaSeleccionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnPrecios)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 0));

        btnGenerarPDF.setText("Generar Reporte");
        btnGenerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPDFActionPerformed(evt);
            }
        });

        txtFechaFinal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFechaFinalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFechaFinalFocusLost(evt);
            }
        });
        txtFechaFinal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFechaFinalMouseClicked(evt);
            }
        });
        txtFechaFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaFinalActionPerformed(evt);
            }
        });

        jLabel4.setText("Fecha final");

        jLabel3.setText("Fecha de Inicio");

        txtFechaInicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFechaInicioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFechaInicioFocusLost(evt);
            }
        });
        txtFechaInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFechaInicioMouseClicked(evt);
            }
        });
        txtFechaInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarPDF)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addComponent(btnGenerarPDF)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Panel2Layout = new javax.swing.GroupLayout(Panel2);
        Panel2.setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(395, Short.MAX_VALUE))
        );
        Panel2Layout.setVerticalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        getContentPane().add(Panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 740, 610));

        MenuPlegable2.setBackground(new java.awt.Color(204, 204, 204));
        MenuPlegable2.setLayout(null);

        Menu2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Menu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Menu32px.png"))); // NOI18N
        Menu2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        Menu2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Menu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Menu2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Menu2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Menu2MouseExited(evt);
            }
        });
        MenuPlegable2.add(Menu2);
        Menu2.setBounds(0, 0, 170, 50);

        Inicio2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Inicio2.setForeground(new java.awt.Color(255, 255, 255));
        Inicio2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Inicio2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Inicio32px.png"))); // NOI18N
        Inicio2.setText("Inicio");
        Inicio2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        Inicio2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Inicio2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Inicio2.setIconTextGap(15);
        Inicio2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inicio2MouseClicked(evt);
            }
        });
        MenuPlegable2.add(Inicio2);
        Inicio2.setBounds(0, 70, 170, 50);

        Usuarios2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Usuarios2.setForeground(new java.awt.Color(255, 255, 255));
        Usuarios2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Usuarios2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Usuarios32px.png"))); // NOI18N
        Usuarios2.setText("Usuarios");
        Usuarios2.setToolTipText("");
        Usuarios2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        Usuarios2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Usuarios2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Usuarios2.setIconTextGap(15);
        Usuarios2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Usuarios2MouseClicked(evt);
            }
        });
        MenuPlegable2.add(Usuarios2);
        Usuarios2.setBounds(0, 440, 170, 50);

        Configuracion2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Configuracion2.setForeground(new java.awt.Color(255, 255, 255));
        Configuracion2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Configuracion2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Configuraciones32px.png"))); // NOI18N
        Configuracion2.setText("Configuracion");
        Configuracion2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        Configuracion2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Configuracion2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Configuracion2.setIconTextGap(15);
        Configuracion2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Configuracion2MouseClicked(evt);
            }
        });
        MenuPlegable2.add(Configuracion2);
        Configuracion2.setBounds(0, 530, 170, 50);

        Ventas2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Ventas2.setForeground(new java.awt.Color(255, 255, 255));
        Ventas2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Ventas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Venta32px.png"))); // NOI18N
        Ventas2.setText("Venta");
        Ventas2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        Ventas2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Ventas2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Ventas2.setIconTextGap(15);
        Ventas2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ventas2MouseClicked(evt);
            }
        });
        MenuPlegable2.add(Ventas2);
        Ventas2.setBounds(0, 160, 170, 50);

        Analisis2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Analisis2.setForeground(new java.awt.Color(255, 255, 255));
        Analisis2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Analisis2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Graficas32px.png"))); // NOI18N
        Analisis2.setText("Analisis");
        Analisis2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        Analisis2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Analisis2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Analisis2.setIconTextGap(15);
        Analisis2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Analisis2MouseClicked(evt);
            }
        });
        MenuPlegable2.add(Analisis2);
        Analisis2.setBounds(0, 350, 170, 50);

        Inventario2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Inventario2.setForeground(new java.awt.Color(255, 255, 255));
        Inventario2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Inventario2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Inventario32px.png"))); // NOI18N
        Inventario2.setText("Inventario");
        Inventario2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        Inventario2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Inventario2.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        Inventario2.setIconTextGap(15);
        Inventario2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inventario2MouseClicked(evt);
            }
        });
        MenuPlegable2.add(Inventario2);
        Inventario2.setBounds(0, 260, 170, 50);

        getContentPane().add(MenuPlegable2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-120, 0, 170, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents




private void cargarAreasEnComboBox() {
    try {
        CONSULTASDAO dao = new CONSULTASDAO(Conexion_DB.getConexion());
        List<Area> areas = dao.obtenerAreas();
        areaSeleccionada.removeAllItems();  // Limpiar el JComboBox antes de llenarlo
        for (Area area : areas) {
            areaSeleccionada.addItem(area.getNombreArea());  // Añade solo el nombre al JComboBox
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar las áreas: " + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
    }
}

// Método para obtener el ID del área seleccionada basado en el nombre
private int obtenerAreaID(String nombreAreaSeleccionada) throws SQLException, IllegalArgumentException {
    CONSULTASDAO dao = new CONSULTASDAO(Conexion_DB.getConexion());
    List<Area> areas = dao.obtenerAreas();
    for (Area area : areas) {
        if (area.getNombreArea().equals(nombreAreaSeleccionada)) {
            return area.getAreaID();
        }
    }
    throw new IllegalArgumentException("Área no encontrada.");
}

    private void btnPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreciosActionPerformed
   String nombreAreaSeleccionada = (String) areaSeleccionada.getSelectedItem();
    if (nombreAreaSeleccionada == null || txtPorcentaje.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un área y especifique un porcentaje.");
        return;
    }
    
    try {
        double porcentaje = Double.parseDouble(txtPorcentaje.getText().trim());
        int areaID = obtenerAreaID(nombreAreaSeleccionada);

        CONSULTASDAO dao = new CONSULTASDAO(Conexion_DB.getConexion());
        boolean actualizado = dao.actualizarPorcentajeGanancia(areaID, porcentaje);
        if (actualizado) {
            JOptionPane.showMessageDialog(this, "Porcentaje de ganancia actualizado con éxito para " + nombreAreaSeleccionada + ".");
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el porcentaje.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Por favor, introduzca un valor numérico válido para el porcentaje.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + e.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_btnPreciosActionPerformed

    private void txtFechaFinalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaFinalFocusGained

    }//GEN-LAST:event_txtFechaFinalFocusGained

    private void txtFechaFinalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaFinalFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFinalFocusLost

    private void txtFechaFinalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFechaFinalMouseClicked
        if (!isCalendarOpen) {
            mostrarCalendario(txtFechaFinal, false); // false indica que es la fecha final
        }
    }//GEN-LAST:event_txtFechaFinalMouseClicked

    private void txtFechaFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFinalActionPerformed

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed
        try {
            Date fechaInicio = dateFormat.parse(txtFechaInicio.getText());
            Date fechaFin = dateFormat.parse(txtFechaFinal.getText());

            // Primero, generar las gráficas
            ChartGenerator chartGenerator = new ChartGenerator();
            chartGenerator.initialize(fechaInicio, fechaFin);
            
            // Luego, generar el PDF usando las gráficas
            GenerarReportePDF generarReporte = new GenerarReportePDF();
            generarReporte.generarReporte(fechaInicio, fechaFin);

            JOptionPane.showMessageDialog(this, "Reporte generado exitosamente en la carpeta 'reportes'.");
        } catch (ParseException | DocumentException | IOException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    
    }//GEN-LAST:event_btnGenerarPDFActionPerformed

    private void txtFechaInicioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaInicioFocusGained

    }//GEN-LAST:event_txtFechaInicioFocusGained

    private void txtFechaInicioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaInicioFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaInicioFocusLost

    private void txtFechaInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFechaInicioMouseClicked
        if (!isCalendarOpen) {
            mostrarCalendario(txtFechaInicio, true); // true indica que es la fecha de inicio
        }
    }//GEN-LAST:event_txtFechaInicioMouseClicked

    private void txtFechaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaInicioActionPerformed

    private void Menu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu2MouseClicked
        // Mover dependiendo de la posición actual
        if (MenuPlegable2.getX() == 0) {
            // Mover ambos componentes hacia la izquierda
            animador.animar(MenuPlegable2, Panel2, -120, Panel2.getX() - 120, false);
            MenuPlegable2.setBackground(new Color(204, 204, 204)); // Cambiar al color cuando está plegado
            menuDesplegado = false;
        } else if (MenuPlegable2.getX() == -120) {
            // Mover ambos componentes hacia la derecha
            animador.animar(MenuPlegable2, Panel2, 0, Panel2.getX() + 120, true);
            MenuPlegable2.setBackground(new Color(51, 51, 51)); // Cambiar al color cuando está desplegado
            menuDesplegado = true;
        }
        HoverEffect.setMenuDesplegado(menuDesplegado); // Actualizar el estado en HoverEffect
    }//GEN-LAST:event_Menu2MouseClicked

    private void Menu2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu2MouseEntered

    }//GEN-LAST:event_Menu2MouseEntered

    private void Menu2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu2MouseExited

    }//GEN-LAST:event_Menu2MouseExited

    private void Inicio2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inicio2MouseClicked
        MenuPrincipal menuPrincipal = MenuPrincipal.getInstance();
        //principalWindow.setVentanaPrincipal(this);
        menuPrincipal.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_Inicio2MouseClicked

    private void Usuarios2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Usuarios2MouseClicked
        UsuariosPanel usuariosWindow = UsuariosPanel.getInstance();
        //usuariosWindow.setVentanaPrincipal(this);
        usuariosWindow.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_Usuarios2MouseClicked

    private void Configuracion2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Configuracion2MouseClicked
        Configuraciones configWindow = Configuraciones.getInstance();
        //configWindow.setVentanaPrincipal(this);
        configWindow.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_Configuracion2MouseClicked

    private void Ventas2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ventas2MouseClicked
        Venta ventaWindow = Venta.getInstance();
        //ventaWindow.setVentanaPrincipal(this);
        ventaWindow.initialize(SesionManager.getInstance().getUsuarioLogueado());

        ventaWindow.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_Ventas2MouseClicked

    private void Analisis2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Analisis2MouseClicked
        AvisosFrame avisosFrame = AvisosFrame.getInstance();
        avisosFrame.initialize(SesionManager.getInstance().getUsuarioLogueado());
        avisosFrame.setVisible(true);
        this.setVisible(false);
        AvisosFrame.getInstance().comparar();
    }//GEN-LAST:event_Analisis2MouseClicked

    private void Inventario2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inventario2MouseClicked
        Principal2_0 principalWindow = Principal2_0.getInstance();
        //principalWindow.setVentanaPrincipal(this);
        principalWindow.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_Inventario2MouseClicked
/*
    private void abrirFormularioCreacionUsuario(String rol) {
    FormularioNuevoUsuario formularioNuevoUsuario = new FormularioNuevoUsuario(rol);
    formularioNuevoUsuario.setVisible(true);
    this.setVisible(false); // Opcional: Oculta el formulario de login
}
*/


    public void mostrar() {
        this.setVisible(true);
    }
    
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
            java.util.logging.Logger.getLogger(Configuraciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuraciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuraciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuraciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Configuraciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Analisis2;
    private javax.swing.JLabel Configuracion2;
    private javax.swing.JLabel Inicio2;
    private javax.swing.JLabel Inventario2;
    private javax.swing.JLabel Menu2;
    private javax.swing.JPanel MenuPlegable2;
    private javax.swing.JPanel Panel2;
    private javax.swing.JLabel Usuarios2;
    private javax.swing.JLabel Ventas2;
    private javax.swing.JComboBox<String> areaSeleccionada;
    private javax.swing.JButton btnGenerarPDF;
    private javax.swing.JButton btnPrecios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtFechaFinal;
    private javax.swing.JTextField txtFechaInicio;
    private javax.swing.JTextField txtPorcentaje;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import conexionSQL.conexionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JulioD07
 */
public class sistema extends javax.swing.JFrame {

    conexionSQL cx = new conexionSQL();
    Connection con = cx.conexion();
    /**
     * Creates new form sistema
     */
    public sistema() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(getBackground());
        limpiarCampos();
        mostrarDatos();
    }

    
    public void mostrarDatos(){
        String[] titulos = {"ID","Nombre","Apellido","Materia","Calificacion","Estatus"};
        String[] registros = new String[7];
        
        DefaultTableModel modelo = new DefaultTableModel(null,titulos);
        
        String SQL = "SELECT * FROM alumnos";
        
        try {
            Statement st =  con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                
                registros[0] = rs.getString("idalumnos");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("apellidos");
                registros[3] = rs.getString("materia");
                registros[4] = rs.getString("calificacion");
                registros[5] = rs.getString("estatus");

                modelo.addRow(registros);
            }
            tablaAlumnos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al mostrar datos: " + e);
                    
        }
    }
    
    public void filtrarDatos(String valor){
        
          String[] titulos = {"ID","Nombre","Apellido","Materia","Calificacion","Estatus"};
        String[] registros = new String[7];
        
        DefaultTableModel modelo = new DefaultTableModel(null,titulos);
        
        String SQL = "select * from alumnos where nombre like '%"+valor+"%' ";
        
        try {
            Statement st =  con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                
                registros[0] = rs.getString("idalumnos");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("apellidos");
                registros[3] = rs.getString("materia");
                registros[4] = rs.getString("calificacion");
                registros[5] = rs.getString("estatus");

                modelo.addRow(registros);
            }
            tablaAlumnos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al mostrar datos: " + e);
                    
        }
        
    }
    public void limpiarCampos(){
        txt_nombre.setText("");
        txt_apellido.setText("");
        txt_calificacion.setText("");
        cbMateria.setSelectedItem(null);
        cbEstatus.setSelectedItem(null);
    }
    
    public void insertarDatos(){
        try {
            String  SQL = "INSERT into  alumnos (nombre,apellidos,materia,calificacion,estatus) values(?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1,txt_nombre.getText());
            pst.setString(2,txt_apellido.getText());
            
            int seleccionado = cbMateria.getSelectedIndex();
            pst.setString(3,cbMateria.getItemAt(seleccionado));
            
            pst.setDouble(4, Double.parseDouble(txt_calificacion.getText()));
            
            int seleccionado1 = cbEstatus.getSelectedIndex();
            pst.setString(5,cbEstatus.getItemAt(seleccionado1));
            
            //ejecutar consulta
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Registro Exitoso");
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registar: " + e.getMessage());
        }
    }
     public void actualizarDatos(){
        try {
            String  SQL = "update alumnos set nombre=?,apellidos=?,materia=?,calificacion=?,estatus=? where idalumnos=?";
            int filaseleccionada = tablaAlumnos.getSelectedRow();
            String dao = (String)tablaAlumnos.getValueAt(filaseleccionada, 0);
            
            
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1,txt_nombre.getText());
            pst.setString(2,txt_apellido.getText());
            
            int seleccionado = cbMateria.getSelectedIndex();
            pst.setString(3,cbMateria.getItemAt(seleccionado));
            
            pst.setDouble(4, Double.parseDouble(txt_calificacion.getText()));
            
            int seleccionado1 = cbEstatus.getSelectedIndex();
            pst.setString(5,cbEstatus.getItemAt(seleccionado1));
            
            pst.setString(6, dao);
            //ejecutar consulta
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Registro Editado Exitosamente");
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registar: " + e.getMessage());
        }
    }
    public void eliminarRegistro(){
        
        int filaSeleccionada = tablaAlumnos.getSelectedRow();
        
        try {
            String SQL = "delete from alumnos where idalumnos="+tablaAlumnos.getValueAt(filaSeleccionada, 0);
            Statement st = con.createStatement();
            int n = st.executeUpdate(SQL);
            if (n >= 0 ) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar registro: " +e.getMessage());

            
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_nombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_apellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbEstatus = new javax.swing.JComboBox<>();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        cbMateria = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txt_calificacion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        txt_buscar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Apellido");

        txt_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellidoActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Materia");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Calificacion");

        cbEstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bien", "Mal" }));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        cbMateria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Programacion", "Ingles", "Matematicas", "Religion" }));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Estatus");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_calificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(txt_apellido)
                                    .addComponent(cbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(cbEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActualizar)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_calificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(btnActualizar)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar))
                .addGap(14, 14, 14))
        );

        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAlumnosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAlumnos);

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });

        jLabel5.setText("Buscar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        insertarDatos();
        limpiarCampos();
        mostrarDatos();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tablaAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseClicked
        
        // Agarrar datos al hacer click y mandarlos al formulario
        int filaSeleccionada = tablaAlumnos.rowAtPoint(evt.getPoint());
        txt_nombre.setText(tablaAlumnos.getValueAt(filaSeleccionada, 1).toString());
        txt_apellido.setText(tablaAlumnos.getValueAt(filaSeleccionada, 2).toString());
        cbMateria.setSelectedItem(tablaAlumnos.getValueAt(filaSeleccionada, 3));
        txt_calificacion.setText(tablaAlumnos.getValueAt(filaSeleccionada, 4).toString());
        cbEstatus.setSelectedItem(tablaAlumnos.getValueAt(filaSeleccionada, 5));
    }//GEN-LAST:event_tablaAlumnosMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
       actualizarDatos();
       limpiarCampos();
       mostrarDatos();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarRegistro();
        limpiarCampos();
        mostrarDatos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        filtrarDatos(txt_buscar.getText());
    }//GEN-LAST:event_txt_buscarKeyReleased

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
            java.util.logging.Logger.getLogger(sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cbEstatus;
    private javax.swing.JComboBox<String> cbMateria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_calificacion;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}

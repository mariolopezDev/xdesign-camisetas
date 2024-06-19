/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package dev.mariolopez.xdesign.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import dev.mariolopez.xdesign.Logic.GestorPedidos;
import dev.mariolopez.xdesign.Logic.Pedido;

/**
 *
 * @author mariolopez
 */
public class UI_BusquedaEdicionPedidos extends javax.swing.JFrame {

    
    private JTextField searchField;
    private JButton searchButton;
    private JButton editButton;  
    private JTable resultsTable;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JScrollPane editPanel;
    private JTextField idField, tipoCamisetaField, tallaField, cantidadField, codigoDisenoField, tipoPedidoField, direccionField, formaPagoField, telefonoField, nombreClienteField;
    private JButton saveButton;
    private GestorPedidos gestorPedidos;
    
    
    /** Creates new form UI_BusquedaEdicionPedidos */
        public UI_BusquedaEdicionPedidos() {
        super("Buscar y Editar Pedidos");
        gestorPedidos = GestorPedidos.getInstance();
        initializeComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        searchField = new JTextField(20);
        searchButton = new JButton("Buscar");
        searchButton.addActionListener(this::performSearch);

        editButton = new JButton("Editar");
        editButton.addActionListener(this::openEditScreen);
        editButton.setEnabled(false);  // Desactivar hasta que se seleccione un pedido

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Tipo de Camiseta", "Talla", "Cantidad", "Código de Diseño", "Tipo de Pedido", "Dirección", "Forma de Pago", "Teléfono", "Nombre Cliente"});
        resultsTable = new JTable(model);
        scrollPane = new JScrollPane(resultsTable);
        resultsTable.getSelectionModel().addListSelectionListener(e -> loadSelectedPedido());

        layoutComponents();
    }

    private void layoutComponents() {
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Buscar por Teléfono:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);

        setLayout(new BorderLayout());
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void performSearch(ActionEvent e) {
        String phoneNumber = searchField.getText().trim();
        if (!phoneNumber.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, "Ingrese un número de teléfono válido de 8 dígitos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<Pedido> results = gestorPedidos.buscarPedidosPorTelefono(phoneNumber);
        model.setRowCount(0); // Clear previous results
        for (Pedido p : results) {
            model.addRow(new Object[]{p.getId(), p.getTipoCamiseta(), p.getTalla(), p.getCantidad(), p.getCodigoDiseno(), p.getTipoPedido(), p.getDireccionEntrega(), p.getFormaPago(), p.getNumeroTelefonicoCliente(), p.getNombreCompletoCliente()});
        }
        editButton.setEnabled(false);
    }

    private void loadSelectedPedido() {
        int selectedRow = resultsTable.getSelectedRow();
        if (selectedRow != -1) {
            editButton.setEnabled(true);  // Activar el botón de editar
        }
    }

    private void openEditScreen(ActionEvent e) {
        int selectedRow = resultsTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
            Pedido pedido = gestorPedidos.findPedidoById(id);
            if (pedido != null) {
                UI_RegistroPedidos registroPedidos = new UI_RegistroPedidos(pedido);
                registroPedidos.setVisible(true);
            }
        }
    }

    
    
    

    

   

    private void saveChanges(ActionEvent e) {
        try {
            // Aquí deberías agregar las validaciones necesarias antes de guardar
            int id = Integer.parseInt(idField.getText());
            String tipoCamiseta = tipoCamisetaField.getText();
            String talla = tallaField.getText();
            int cantidad = Integer.parseInt(cantidadField.getText());
            String codigoDiseno = codigoDisenoField.getText();
            String tipoPedido = tipoPedidoField.getText();
            String direccion = direccionField.getText();
            String formaPago = formaPagoField.getText();
            int telefono = Integer.parseInt(telefonoField.getText());
            String nombreCliente = nombreClienteField.getText();

            Pedido pedidoActualizado = new Pedido(id, tipoCamiseta, talla, cantidad, codigoDiseno, tipoPedido, direccion, formaPago, telefono, nombreCliente);
            gestorPedidos.editarPedido(id, pedidoActualizado);
            JOptionPane.showMessageDialog(this, "Pedido actualizado correctamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en la entrada de datos numéricos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar los cambios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(UI_BusquedaEdicionPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI_BusquedaEdicionPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI_BusquedaEdicionPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI_BusquedaEdicionPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new UI_BusquedaEdicionPedidos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}

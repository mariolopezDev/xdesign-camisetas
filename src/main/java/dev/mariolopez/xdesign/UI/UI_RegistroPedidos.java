/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dev.mariolopez.xdesign.UI;

import javax.swing.*;


import dev.mariolopez.xdesign.Logic.Pedido;
import dev.mariolopez.xdesign.Logic.GestorPedidos;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;

/**
 *
 * @author mariolopez
 */
public class UI_RegistroPedidos extends javax.swing.JFrame {

    private Pedido pedidoActual;
    private JTextField cantidadTextField, codigoDisenoTextField, direccionEntregaTextField, telefonoTextField, nombreClienteTextField, idPedidoTextField;
    private JComboBox<String> tipoCamisetaComboBox, tallaComboBox, tipoPedidoComboBox, formaPagoComboBox;
    private JButton guardarButton;
    private JLabel successLabel;  // Etiqueta para mensajes de éxito
    private final GestorPedidos gestorPedidos;

    
    /**
     * Constructor que puede ser usado para editar un pedido existente o crear uno nuevo.
     */
    public UI_RegistroPedidos(Pedido pedido) {
        super("Registro de Pedidos");
        initializeComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        gestorPedidos = GestorPedidos.getInstance();
        this.pedidoActual = pedido;

        // Si estamos editando un pedido existente, cargamos sus datos.
        if (pedido != null) {
            cargarDatosPedido(pedido);
        }
    }
    
    private void cargarDatosPedido(Pedido pedido) {
        // Cargar los datos del pedido en los campos
        idPedidoTextField.setText(String.valueOf(pedido.getId()));
        tipoCamisetaComboBox.setSelectedItem(pedido.getTipoCamiseta());
        tallaComboBox.setSelectedItem(pedido.getTalla());
        cantidadTextField.setText(String.valueOf(pedido.getCantidad()));
        codigoDisenoTextField.setText(pedido.getCodigoDiseno());
        tipoPedidoComboBox.setSelectedItem(pedido.getTipoPedido());
        direccionEntregaTextField.setText(pedido.getDireccionEntrega());
        formaPagoComboBox.setSelectedItem(pedido.getFormaPago());
        telefonoTextField.setText(String.valueOf(pedido.getNumeroTelefonicoCliente()));
        nombreClienteTextField.setText(pedido.getNombreCompletoCliente());
    }

    
    private void initializeComponents() {
                
        // Inicializar el successLabel
        successLabel = new JLabel("");
        successLabel.setForeground(new Color(0, 128, 0));  // Color verde para el mensaje de éxito
        successLabel.setVisible(false);  // Inicialmente no visible

        
        
        JLabel jLabel1 = new JLabel("ID del pedido");
        JLabel jLabel2 = new JLabel("Tipo de camiseta");
        JLabel jLabel3 = new JLabel("Talla");
        JLabel jLabel4 = new JLabel("Cantidad");
        JLabel jLabel5 = new JLabel("Código del diseño");
        JLabel jLabel6 = new JLabel("Tipo de pedido");
        JLabel jLabel7 = new JLabel("Dirección de entrega");
        JLabel jLabel8 = new JLabel("Forma de pago");
        JLabel jLabel11 = new JLabel("Número telefónico del cliente");
        JLabel jLabel12 = new JLabel("Nombre completo del cliente");

        idPedidoTextField = new JTextField(10);
        idPedidoTextField.setEditable(false);  // Hacer que el ID no sea editable

        cantidadTextField = new JTextField(10);
        codigoDisenoTextField = new JTextField(10);
        direccionEntregaTextField = new JTextField(10);
        telefonoTextField = new JTextField(10);
        nombreClienteTextField = new JTextField(10);
        
        // Definir restricciones de entrada para cantidad y teléfono
        cantidadTextField.setInputVerifier(new InputVerifier() {
            public boolean verify(JComponent input) {
                JTextField tf = (JTextField) input;
                return tf.getText().matches("\\d{1,2}");  // Solo números de 1 o 2 dígitos
            }
        });

        telefonoTextField.setInputVerifier(new InputVerifier() {
            public boolean verify(JComponent input) {
                JTextField tf = (JTextField) input;
                return tf.getText().matches("\\d{8}");  // Solo números de 8 dígitos
            }
        });
        
        // Guía tooltip para el usuario
        cantidadTextField.setToolTipText("Ingrese un número de 1 o 2 dígitos.");
        telefonoTextField.setToolTipText("Ingrese un número de teléfono de 8 dígitos sin guiones.");


        // Listas desplegables para campos seleccionables
        
        String[] camisetas = {"Lisa manga larga", "Polo manga corta", "Estampado manga corta", "Lisa manga corta", "Deportiva manga larga", "Deportiva manga corta", "Deportiva sin mangas"};
        tipoCamisetaComboBox = new JComboBox<>(camisetas);

        String[] tallas = {"S", "M", "L", "XL", "2XL"};
        tallaComboBox = new JComboBox<>(tallas);

        String[] tiposPedido = {"Recoger en tienda", "Envío a domicilio"};
        tipoPedidoComboBox = new JComboBox<>(tiposPedido);
        tipoPedidoComboBox.addActionListener(this::manejarTipoPedido);
        
        // Configura estado inicial de direccionEntregaTextField basado en la elección por defecto de tipoPedidoComboBox
        manejarTipoPedido(null);  // Pasa null inicialmente


        String[] formasPago = {"Efectivo", "Sinpe móvil", "Transferencia bancaria", "Tarjeta de débito/crédito"};
        formaPagoComboBox = new JComboBox<>(formasPago);

        guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(this::guardarButtonActionPerformed);

        createLayout(jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel11, jLabel12);
    }
    
    private void createLayout(JLabel... labels) {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(labels[0])
                    .addComponent(labels[1])
                    .addComponent(labels[2])
                    .addComponent(labels[3])
                    .addComponent(labels[4])
                    .addComponent(labels[5])
                    .addComponent(labels[6])
                    .addComponent(labels[7])
                    .addComponent(labels[8])
                    .addComponent(labels[9])
                    
                    .addComponent(guardarButton))
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(idPedidoTextField)
                    .addComponent(tipoCamisetaComboBox)
                    .addComponent(tallaComboBox)
                    .addComponent(cantidadTextField)
                    .addComponent(codigoDisenoTextField)
                    .addComponent(tipoPedidoComboBox)
                    .addComponent(direccionEntregaTextField)
                    .addComponent(formaPagoComboBox)
                    .addComponent(telefonoTextField)
                    .addComponent(nombreClienteTextField)
                    .addComponent(successLabel))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labels[0])
                    .addComponent(idPedidoTextField))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labels[1])
                    .addComponent(tipoCamisetaComboBox))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labels[2])
                    .addComponent(tallaComboBox))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labels[3])
                    .addComponent(cantidadTextField))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labels[4])
                    .addComponent(codigoDisenoTextField))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labels[5])
                    .addComponent(tipoPedidoComboBox))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labels[6])
                    .addComponent(direccionEntregaTextField))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labels[7])
                    .addComponent(formaPagoComboBox))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labels[8])
                    .addComponent(telefonoTextField))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(labels[9])
                    .addComponent(nombreClienteTextField))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(guardarButton)
                    .addComponent(successLabel))             
        );

        pack();
    }

    private boolean validarCampos() {
        if (!cantidadTextField.getText().matches("\\d{1,2}")) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número de 1 o 2 dígitos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!telefonoTextField.getText().matches("\\d{8}")) {
            JOptionPane.showMessageDialog(this, "El número de teléfono debe ser de 8 dígitos sin guiones.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!codigoDisenoTextField.getText().matches("\\w+")) {
            JOptionPane.showMessageDialog(this, "El código de diseño debe ser alfanumérico y no contener espacios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (direccionEntregaTextField.isEnabled() && direccionEntregaTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La dirección de entrega no puede estar vacía.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (nombreClienteTextField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre completo del cliente no puede estar vacío.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void guardarButtonActionPerformed(ActionEvent evt) {
        // Implementación de la lógica de guardado, asegurando que solo se guarde si las validaciones pasan
        if (!validarCampos()) {
            return;
        }
        try {
            String tipoCamiseta = tipoCamisetaComboBox.getSelectedItem().toString();
            String talla = tallaComboBox.getSelectedItem().toString();
            int cantidad = Integer.parseInt(cantidadTextField.getText());
            String codigoDiseno = codigoDisenoTextField.getText();
            String tipoPedido = tipoPedidoComboBox.getSelectedItem().toString();
            String direccion = direccionEntregaTextField.getText();
            String formaPago = formaPagoComboBox.getSelectedItem().toString();
            int telefono = Integer.parseInt(telefonoTextField.getText());
            String nombreCliente = nombreClienteTextField.getText();

            
            if (pedidoActual == null) {
                int id = 0;
                pedidoActual = new Pedido(id, tipoCamiseta, talla, cantidad, codigoDiseno, tipoPedido, direccion, formaPago, telefono, nombreCliente);
                gestorPedidos.agregarPedido(pedidoActual);
                //JOptionPane.showMessageDialog(this, "Pedido creado con éxito.");
            } else {
                pedidoActual.actualizarDatos(tipoCamiseta, talla, cantidad, codigoDiseno, tipoPedido, direccion, formaPago, telefono, nombreCliente);
                gestorPedidos.editarPedido(pedidoActual.getId(), pedidoActual);
                //JOptionPane.showMessageDialog(this, "Pedido actualizado con éxito.");
            }
            guardarButton.setEnabled(false);
            // Actualizar el successLabel en lugar de mostrar un JOptionPane
            //successLabel.setText("Pedido guardado con éxito. Cerrando en 3...");
            successLabel.setVisible(true);

            // Timer para la cuenta regresiva
            Timer timer = new Timer(1000, new ActionListener() {
                int count = 3; // Inicia el contador en 3 segundos

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (count > 0) {
                        successLabel.setText("Pedido ID " + pedidoActual.getId() + " guardado con éxito. Cerrando en " + count + "...");
                        count--;
                    } else {
                        ((Timer)e.getSource()).stop(); // Detener el timer
                        successLabel.setVisible(false);
                        dispose(); // Cierra la ventana
                    }
                }
            });
            timer.start();

            
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el pedido: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void manejarTipoPedido(ActionEvent e) {
        if ("Envío a domicilio".equals(tipoPedidoComboBox.getSelectedItem().toString())) {
            direccionEntregaTextField.setEnabled(true);
            direccionEntregaTextField.setText("");  // Clear the field for user input
        } else {
            direccionEntregaTextField.setEnabled(false);
            direccionEntregaTextField.setText("No Aplica");  // Default text when not "Envío a domicilio"
        }
    }
    
    private void limpiarCampos() {
        idPedidoTextField.setText("");
        cantidadTextField.setText("");
        codigoDisenoTextField.setText("");
        direccionEntregaTextField.setText("");
        telefonoTextField.setText("");
        nombreClienteTextField.setText("");
        tipoCamisetaComboBox.setSelectedIndex(0);
        tallaComboBox.setSelectedIndex(0);
        tipoPedidoComboBox.setSelectedIndex(0);
        formaPagoComboBox.setSelectedIndex(0);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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
            java.util.logging.Logger.getLogger(UI_RegistroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI_RegistroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI_RegistroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI_RegistroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new UI_RegistroPedidos(null).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

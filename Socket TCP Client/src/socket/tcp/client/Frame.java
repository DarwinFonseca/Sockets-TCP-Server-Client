/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.tcp.client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author DarwinFonck
 */
public final class Frame extends javax.swing.JFrame implements Runnable {

    public String Host;
    final int Puerto = 80;
    private Socket Cliente;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;
    private Clock ObjClock = new Clock();
    int intentos = 0;
    Frame ObjFrame;

    public Frame() {
        //Constructor Vacio
    }

    public Frame(String Host) {
        this.Host = Host;
        initComponents();
        this.setVisible(true);
        this.setTitle("Adivina");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        Conectar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        LabelTiempo = new javax.swing.JLabel();
        LabelTime = new javax.swing.JLabel();
        btnEnviar = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        LabelTitulo = new javax.swing.JLabel();
        LabelIntento = new javax.swing.JLabel();
        LabelTry = new javax.swing.JLabel();
        LabelTexto = new javax.swing.JLabel();
        jTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LabelTiempo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LabelTiempo.setText("Tiempo: ");

        LabelTime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnEnviar.setText("Envíar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        btnReiniciar.setText("Reiniciar");
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        LabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        LabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTitulo.setText("ADIVINA EL NÚMERO");

        LabelIntento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LabelIntento.setText("Intentos Realizados:");

        LabelTry.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        LabelTexto.setBackground(new java.awt.Color(153, 153, 153));
        LabelTexto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LabelTexto.setText("Adivina el número secreto, Digita un número entre 0 hasta el 100"); // NOI18N

        jTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();

                // Verificar si la tecla pulsada no es un digito
                if (((caracter < '0')
                        || (caracter > '9'))
                        && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
                    e.consume();  // ignorar el evento de teclado
                }
            }
        }
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(LabelIntento, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(LabelTry, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(53, 53, 53).addComponent(LabelTry, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(LabelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(LabelTime, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(LabelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEnviar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReiniciar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField)
                                .addComponent(LabelTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(LabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(LabelTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(LabelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(LabelTry, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(LabelIntento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(LabelTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviar)
                        .addComponent(btnReiniciar)
                        .addContainerGap())
        );

        pack();
    }// </editor-fold>                        


    /*Darwin Gonzalo Fonseca Abril
     Modificación del Código
     */
    private void Conectar() {
        btnReiniciar.hide();
        LabelTry.setText("" + intentos);
        try {
            ObjClock.start();
            this.Cliente = new Socket(this.Host, Puerto);
            LabelTime.setText("Iniciado");

            this.flujoEntrada = new DataInputStream(Cliente.getInputStream());
            this.flujoSalida = new DataOutputStream(Cliente.getOutputStream());
            //System.out.println(Cliente);
            EnviarDatos("OK");
        } catch (Exception e) {
            //System.out.println("ERROR EN EL CONSTRUCTOR");
            JOptionPane.showMessageDialog(null, "El Servidor no responde.", "No hay conexión", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == btnReiniciar) {
            btnEnviar.show();
            btnReiniciar.hide();
            this.dispose();
            ObjFrame = new Frame(this.Host);
            ObjFrame.run();

        }
    }

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == btnEnviar) {
            if (!jTextField.getText().isEmpty()) {
                try {
                    //System.out.println("Enviando: " + jTextField.getText());
                    EnviarDatos(jTextField.getText());
                    jTextField.setText("");
                    intentos++;
                    LabelTry.setText(String.valueOf(intentos));

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "El Servidor no responde\nNo se pueden enviar los datos.", "No hay conexión", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    RecibirDatos();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "El Servidor no responde\nNo se pueden recibir los datos.", "No hay conexión", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Digite un número entre 0 a 100.", "Ops...", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void RecibirDatos() {
        try {
            //Recibe los datos del Socket de parte del Servidor
            this.flujoEntrada = new DataInputStream(Cliente.getInputStream());
            //Lee el mensaje 
            String Entrada = this.flujoEntrada.readUTF();
            LabelTexto.setText(Entrada);
            if (Entrada.startsWith("<HTML>")) {
                jTextField.disable();
                jTextField.setText("Terminó el Juego");
                
                btnEnviar.hide();
                btnReiniciar.show();

                LabelTime.setText(ObjClock.Tiempo());
                ObjClock.stop();
                CerrarConexiones();
            }
        } catch (IOException ex) {
//            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "El Servidor no responde\nNo se pueden recibir los datos.", "No hay conexión", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void EnviarDatos(String Cadena) {
        try {
            //Recibe los datos al Servidor por medio del Socket
            this.flujoSalida = new DataOutputStream(Cliente.getOutputStream());
            this.flujoSalida.writeUTF(Cadena);
        } catch (IOException ex) {
//            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "El Servidor no responde\nNo se pueden enviar los datos.", "No hay conexión", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void CerrarConexiones() {
        try {
            //Cierra Puertas de enlace y el Socket
            this.flujoEntrada.close();
            this.flujoSalida.close();
            this.Cliente.close();
        } catch (IOException ex) {
            //  Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "El Servidor no responde.", "No hay conexión", JOptionPane.ERROR_MESSAGE);
        }
    }

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
            java.util.logging.Logger.getLogger(Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel LabelIntento;
    private javax.swing.JLabel LabelTexto;
    private javax.swing.JLabel LabelTiempo;
    private javax.swing.JLabel LabelTitulo;
    public javax.swing.JLabel LabelTime;
    public javax.swing.JLabel LabelTry;
    public javax.swing.JButton btnEnviar;
    public javax.swing.JButton btnReiniciar;
    public javax.swing.JTextField jTextField;
    // End of variables declaration                   

    @Override
    public void run() {
        RecibirDatos();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket.tcp.server;

/**
 *
 * @author DarwinFonck
 */
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class SocketTCPServer extends Thread {
    /*
     // ########################################################### //
     // ######### ACTIVIDAD NUMERO 3 DE INTERCONECTIVIDAD ######### //
     // ########################################################### //
     */

    private ServerSocket servidor;
    private final int numeroPuerto = 80;
    private DataOutputStream flujoSalida;
    private DataInputStream flujoEntrada;
    private Socket clienteConectado = null;
    private static Frame f;

    private static SocketTCPServer objServidor = new SocketTCPServer();
    private AdivinaELNumero ObjAdivinar = new AdivinaELNumero();
    static String thisIp = "";

    public static void main(String[] args) {

        try {
            //Reconoce la Direccion IP del HOST
            thisIp = InetAddress.getLocalHost().getHostAddress();
            JOptionPane.showMessageDialog(null, "Servidor Iniciado en " + thisIp + "\nEsperando conexión desde el Cliente");
        } catch (UnknownHostException ex) {
        }
        //Ejecuta la Clase Frame
        f = new Frame();
        //Conecta el servidor
        objServidor.Conectar();
        try {
            objServidor.RecibirDatos();
        } catch (Exception e) {
        }
    }

    void Conectar() {
        //Reestablece el contador a 0
        ObjAdivinar.intentos = 0;
        try {
            //Inicia el ServerSocket
            this.servidor = new ServerSocket(numeroPuerto);
            //Espera la conexión del Cliente
            this.clienteConectado = servidor.accept();
            //System.out.println("Conexión establecida desde: " + clienteConectado.getInetAddress() + " por el puerto " + clienteConectado.getPort());

            this.flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
            this.flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());

            Jugar();
        } catch (IOException ex) {
//            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("error en conectar");
        }
    }
  
    public void CerrarConexiones() {
        try {
            //flujoSalida.close();
            //flujoEntrada.close();
            //clienteConectado.close();
            servidor.close();
            //Llama a la función Conexión para volver a quedar en espera
            Conectar();
        } catch (IOException ex) {
            //System.out.println("Error en Cerrar");
        }
    }
    
    private void Jugar() { //Comunicación
        while (ObjAdivinar.intentos < 10) {
            //System.out.println("IP:" + clienteConectado.getInetAddress() + " Port: " + clienteConectado.getPort());
            RecibirDatos();
            ObjAdivinar.intentos++;
        }
        //No encontró el número secreto
        EnviarDatos(ObjAdivinar.Marcador());
        CerrarConexiones();
    }

    private void EnviarDatos(String cadena) {
        try {
            //Envía el mensaje al cliente a través del Socket
            this.flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());
            this.flujoSalida.writeUTF(cadena);
        } catch (IOException ex) {
            //Logger.getLogger(SocketTCPServer.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Error en Enviar Datos");
        }

    }

    private void RecibirDatos() {
        try {
            //Recibe los datos al Servidor por medio del Socket
            this.flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
            String recibido = flujoEntrada.readUTF();
            //System.out.println("Recibiendo del Cliente: \t" + recibido);
            //Recibe cualquier valor numérico
            if (recibido.equals("OK")) {
                //Empieza el Juego.
                EnviarDatos(ObjAdivinar.GenerarNumero());
                //System.out.println(ObjAdivinar.MostrarNumeroGenerado());
            } else {
                //Recibe cualquier valor numérico
                //System.out.println(ObjAdivinar.intentos);
                Validar(recibido);
            }
        } catch (IOException ex) {
            //System.out.println("error en recibir datos");
        }
    }

    private void Validar(String Recibido) {
        if (ObjAdivinar.numeroGenerado == (Integer.parseInt(Recibido))) {
            EnviarDatos(ObjAdivinar.Marcador());
            ObjAdivinar.intentos = 0;
            CerrarConexiones();
        } else {
            EnviarDatos(ObjAdivinar.UbicarNumero(Integer.parseInt(Recibido)));
        }
    }

    public void CerrarServidor() {
        try {
            flujoSalida.close();
            flujoEntrada.close();
            clienteConectado.close();
            servidor.close();
        } catch (IOException ex) {
            //System.out.println("Error en Cerrar");
        }
    }

    @Override
    public void run() {
        RecibirDatos();
    }
}

package socket.tcp.client;

import javax.swing.JOptionPane;

/**
 *
 * @author DarwinFonck
 */
public class SocketTCPClient {

    /*
     // ########################################################### //
     // ######### ACTIVIDAD NUMERO 3 DE INTERCONECTIVIDAD ######### //
     // ########################################################### //
     */
    public Frame ObjFrame = new Frame();
    public static SocketTCPClient Obj = new SocketTCPClient();
    public int valor;
    private String host = null;

    public static void main(String[] args) {
        Obj.Preguntar();
    }

    public void Preguntar() {

        JOptionPane.showMessageDialog(null, "Adivina el número");
        host = JOptionPane.showInputDialog(null,
                "¿ Con qué IP\n quiere establecer conexión ?",
                "127.0.0.1");

        if (host.isEmpty()) {
            Preguntar();
        } else {
            //System.out.println("Estableciendo Conexión...");
            ObjFrame = new Frame(host);
            ObjFrame.run();
        }

    }
}

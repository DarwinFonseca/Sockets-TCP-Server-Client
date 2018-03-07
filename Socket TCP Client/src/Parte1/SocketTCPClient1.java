
package Parte1;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DarwinFonck
 */
public class SocketTCPClient1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String msn = JOptionPane.showInputDialog("Digite el mensaje");
            // TODO code application logic here
            Socket socket = new Socket("127.0.0.1", 80);
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            salida.writeUTF(msn);
            salida.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketTCPClient1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

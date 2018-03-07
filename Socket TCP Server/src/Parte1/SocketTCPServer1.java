/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parte1;

import java.io.*;
import java.net.*;
import java.util.logging.*;

/**
 *
 * @author Darwin Gonzalo Fonseca Abril
 */
public class SocketTCPServer1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String nombre = "";
        String nrc = "";
        String asignatura = "";

        try {
            // TODO code application logic here
            ServerSocket miSocket = new ServerSocket(80);
            System.out.println("Socket TCP esperando conexión...");
            while (true) {

                Socket socket = miSocket.accept();
                
                DataInputStream entrada = new DataInputStream(socket.getInputStream());
//                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream());
                
                salida.println("HTTP/1.0 200 OK");
                salida.println("Content-Type: text/html; charset-utf-8");
                salida.println("Server: Mini server INTERCONEXIÓN");
                salida.println("");

//                System.out.println("<br>IP: " + socket.getInetAddress());
//                System.out.println("<br>Puerto: " + socket.getPort());
//                System.out.println("Mensaje: " + entrada.readUTF());
//                salida.println("<br>");
                salida.println("<br>Nombre: Darwin Gonzalo Fonseca Abril ");
                salida.println("<br>Codigo: 436354");
                salida.println("<br>NRC: 7996");
                salida.println("<br>Asignatura: Interconectividad");
                salida.println("<br>Docente: Ing. Johnn Eduar Criollo Salamanca");

                salida.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(SocketTCPServer1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

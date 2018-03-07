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
public class AdivinaELNumero {

    public int numeroGenerado = 0;
    public int intentos = 0;

    public String GenerarNumero() {
        numeroGenerado = (int) (Math.random() * 99) + 1;
        return "<html>Adivina el número secreto, Digita un número entre 0 hasta el 100<br>Número generado... Adivínalo !</html>";
    }

    public String MostrarNumeroGenerado() {
        return "Número generado: " + numeroGenerado;
    }

    public String UbicarNumero(int nPedido) {
        String Ubicacion = "";
        if (nPedido != numeroGenerado) {

            if (nPedido > numeroGenerado) {
                Ubicacion = ("El número secreto es Menor a " + nPedido);
            } else {
                Ubicacion = ("El número secreto es Mayor a " + nPedido);
            }
        }
        return Ubicacion;
    }

    public String Marcador() {
        String MarcadorFinal = "";
        if (intentos >= 8) {
            MarcadorFinal = ("<HTML>Eres un ingenuo, te he vencido, <br>El número secreto es " + numeroGenerado+"<HTML>");
        }
        if (intentos >= 6 && intentos <= 7) {
            MarcadorFinal = ("<HTML>Eres un Novato, has tardado mucho, <br>El número secreto adivinado es " + numeroGenerado+"<HTML>");
        }
        if (intentos >= 4 && intentos <= 5) {
            MarcadorFinal = ("<HTML>Eres un Aprendiz, podrías haberlo descubierto en menor número de intentos, <br>El número secreto adivinado es " + numeroGenerado+"<HTML>");
        }
        if (intentos > 1 && intentos <= 3) {
            MarcadorFinal = ("<HTML>Eres un Magister, has gastado muy pocos intentos, <br>El número secreto adivinado es  " + numeroGenerado+"<HTML>");
        }
        if (intentos == 1) {
            MarcadorFinal = ("<HTML>Me has vencido en el primer intento, realmente eres un Maestro, <br>El número secreto adivinado es " + numeroGenerado+"<HTML>");
        }
    return MarcadorFinal;
    }

}

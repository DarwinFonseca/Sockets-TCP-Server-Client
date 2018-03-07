package socket.tcp.client;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author DarwinFonck
 */
public class Clock extends Thread implements Runnable {

    private int Hora, Minutos, Segundos;

    @Override
    public void run() {
        while (true) {
            try {
                ++Segundos;
                if (Segundos == 60) {
                    Segundos = 0;
                    ++Minutos;
                }
                if (Minutos == 60) {
                    Minutos = 0;
                    ++Hora;
                }
                //System.out.println(Tiempo());
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public String Tiempo() {
        return ((Minutos <= 9 ? "0" : "") + Minutos + ":" + (Segundos <= 9 ? "0" : "") + Segundos);
    }
}

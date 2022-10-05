import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args){

        try{
            List<Integer> listaPaquetes = new Vector<>();

            Semaphore semaforoEnvio = new Semaphore(3);
            Semaphore semaforoRecibo = new Semaphore(3);
            semaforoRecibo.acquire(3);

            Thread hEnvio = new Thread(new EnviaPaqueteTask(semaforoEnvio, semaforoRecibo, listaPaquetes));
            Thread hRecibo = new Thread(new RecibePaqueteTask(semaforoEnvio, semaforoRecibo, listaPaquetes));

            hEnvio.start();
            hRecibo.start();
            hRecibo.join();

            System.out.println("Programa terminado");

        }catch(InterruptedException e){
            throw new RuntimeException();
        }
    }
}

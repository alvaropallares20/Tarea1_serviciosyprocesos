import java.util.List;
import java.util.concurrent.Semaphore;

public class EnviaPaqueteTask implements Runnable{

   private Semaphore sendPackageSemaphore;
   private Semaphore receivePackageSemaphore;
   private List<Integer> listaPaquetes;

    public EnviaPaqueteTask(Semaphore fullListSemaphore, Semaphore receivePackageSemaphore, List<Integer> listaPaquetes) {
        this.sendPackageSemaphore = fullListSemaphore;
        this.receivePackageSemaphore = receivePackageSemaphore;
        this.listaPaquetes = listaPaquetes;
    }

    @Override
   public synchronized void run() {

        for (int i = 0; i < 100; i++) {
                try{
                    sendPackageSemaphore.acquire();
                    System.out.println("Enviado paquete " + i);
                    listaPaquetes.add(i);
                    receivePackageSemaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}

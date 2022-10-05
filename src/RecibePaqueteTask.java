import java.util.List;
import java.util.concurrent.Semaphore;

public class RecibePaqueteTask implements Runnable{

    private Semaphore sendPackageSemaphore;
    private Semaphore receivePackageSemaphore;
    private List<Integer> listaPaquetes;

    public RecibePaqueteTask(Semaphore sendPackageSemaphore, Semaphore receivePackageSemaphore, List<Integer> listaPaquetes) {
        this.sendPackageSemaphore = sendPackageSemaphore;
        this.receivePackageSemaphore = receivePackageSemaphore;
        this.listaPaquetes = listaPaquetes;
    }

    @Override
    public synchronized void run() {

        for (int i = 0; i < 100; i++) {

                try{
                    receivePackageSemaphore.acquire();
                    int num = listaPaquetes.remove(0);
                    System.out.println("Recibido paquete " + num);
                    sendPackageSemaphore.release();
                    Thread.sleep(10);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}

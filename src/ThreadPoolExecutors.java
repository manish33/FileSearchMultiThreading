import java.io.File;
import java.util.concurrent.*;

public class ThreadPoolExecutors {
    String filename;
    String pattern;
    int numberofThreads;
     ConcurrentLinkedQueue<File> queue;
     ConcurrentLinkedQueue<String> resultqueue;
     ExecutorService pool;

    ThreadPoolExecutors(String filename, String pattern, int numberOfThreads){
        pool= Executors.newFixedThreadPool(numberOfThreads);
        this.pattern=pattern;
        this.filename=filename;
        this.numberofThreads=numberOfThreads;
        File current = new File(filename);
        queue = new ConcurrentLinkedQueue<File>();
        resultqueue = new ConcurrentLinkedQueue<String>();

        queue.add(current);

    }

    public void startSearch() throws InterruptedException {

        for(int i=0;i<numberofThreads;i++){
            pool.execute(new SerachAgent(queue,resultqueue,pattern));
            System.out.println("Thread #" + (i + 1) + " has been started");
            Thread.sleep(10);
        }
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.DAYS);

    }



}

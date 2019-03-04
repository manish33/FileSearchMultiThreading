import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SerachAgent implements  Runnable{

    ConcurrentLinkedQueue<File> queue;
    ConcurrentLinkedQueue<String> resultqueue;
    String pattern;

    public SerachAgent(ConcurrentLinkedQueue<File> queue, ConcurrentLinkedQueue<String> resultqueue, String pattern) {
        this.queue = queue;
        this.resultqueue = resultqueue;
        this.pattern = pattern;
    }

    @Override
    public void run() {
try{


        while(queue.isEmpty()==false){
            File current;
            synchronized (queue){
                current=queue.remove();
            }
            System.out.println(current.getAbsolutePath().toLowerCase());
            if(current.getAbsolutePath().toLowerCase().contains(pattern.toLowerCase())){
                System.out.println(current.getAbsolutePath().toLowerCase());
                synchronized (resultqueue){
                    resultqueue.add(current.getAbsolutePath().toLowerCase());
                }
            }

                if(current.isDirectory() || isRoot(current)){
                    File list[] = current.listFiles();
                    if(list!=null && list.length>0){


                    for(File f:list){
                        synchronized (queue){
                            queue.add(f);
                        }
                    }
                    }
                }

        }

}
catch (Exception e){
    e.printStackTrace();
}

    }

    public boolean isRoot(File f) {
        File roots[] = File.listRoots();
        for (File i : roots) {
            if (f.getAbsolutePath().equals(i.getAbsolutePath()))
                return true;
        }
        return false;
    }
}

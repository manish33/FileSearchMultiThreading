public class FileSearch {

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutors tp = new ThreadPoolExecutors("C:\\Users\\i342011\\Desktop","AutomationGroupMembers",1);
         tp.startSearch();



        for(String s: tp.resultqueue){
            System.out.println(s);
        }
    }
}

import java.lang.Thread;
 class ThreadA extends Thread{

    public void run(){
        for(int i=0;i<=10;i++){
            System.out.println("A thread: "+i);
        }
        System.out.print("Threadot a zavrsi");
    }
}
class ThreadB extends Thread{//kreiranje na klasa Thread
    public void run(){
        for(int i=-1;i>=-10;i--){
            System.out.println("B Thread: "+i);
        }
        System.out.print("Threadot B zavrsi");
    }
}

class CountThread extends Thread{
    private long result;

    public long getResult(){
        return result;
    }

    private long count(){
        long r=0;
        for(r=0;r<1000000;r++);
            return r;

    }

    public void run(){
        result=count();
    }

}


class BasicThreadTest {
    public static void main(String args[]) {
        ThreadA threadA = new ThreadA();//kreiranje na thread
        ThreadB threadB = new ThreadB();

        threadA.start();//so start avtomatski se startuva run() funkcijata
        threadB.start();//so start se aktivira threadot
        CountThread countThread = new CountThread();
        countThread.start();
        System.out.println("Countthread: ");

        try {
            countThread.join();// join naredbata go terminira odnosno mu kazuva na threadot da ceka dodeka drug
                               //thread ne se izvrsi
            System.out.println(countThread.getResult());
        }catch(InterruptedException e){
    e.printStackTrace();
        }
    }
}

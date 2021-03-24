import java.util.concurrent.Semaphore;

//acquire bara dozvola za da pristapi do spodeleniot medium. Dokolku counter e pogolem od 0 togas ima dozvola,
//vo sprotivno dozvolata e odbiena. Dokolku threadot pobara dozvola za da pristapi do spodeleniot resurs, togas
//counterot se dekrementira
//So release threadot ja pusta dozvolata za pristap bidejki zavrsil so pristapot do spodeleniot resurs

//Znaci Semaphore(int num) e isto so int counter. Odnosno broj na threadovi koi mozat da pristapat do
// spodeleniot resurs

class Shared{
    static int count =0;
}
class MyThread extends Thread{
    Semaphore semaphore;
    String ThreadName;
    public MyThread(Semaphore semaphore, String ThreadName){
        //super(ThreadName);
        this.semaphore=semaphore;
        this.ThreadName=ThreadName;
    }
    @Override
    public void run() {
        if (ThreadName.equals("A")) {
            System.out.println("Threadot bara dozvola: " + ThreadName);
            try {
                semaphore.acquire();
                System.out.println("Threadot dobi dozvola "+ThreadName);
                for (int i = 0; i < 5; i++) {
                    Shared.count++;
                }
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
        }
        else{
            System.out.println("Threadot bara dozvola "+ThreadName);
            try{
                System.out.println("Thread bara dozvola "+ThreadName);
                semaphore.acquire();
                System.out.println("Threadot dobi dozvola"+ThreadName);
                for(int i=0;i<5;i++){
                    Shared.count--;
                }
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
        }
    }

    public static void main(String args[])throws InterruptedException{
        Semaphore semaphore=new Semaphore(1);//samo edna dozvola
        MyThread t1=new MyThread(semaphore, "A");
        MyThread t2=new MyThread(semaphore,"B");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Counter: "+Shared.count);
    }
}

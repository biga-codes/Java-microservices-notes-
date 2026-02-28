class ThreadFromClass extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("Thread starting...");
            Thread.sleep(2000); //threadOne sleeping since its called within run method of this class 
            System.out.println("Thread finished after sleep.");
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted.");
        }
    }
}

class ThreadPiece implements Runnable{
  //must be public to override
  @Override
  public void run(){
    System.out.println("pgm");
  }
}

class Executor{

  public static void main(String[] args){
    ThreadFromClass threadOne = new ThreadFromClass();
    ThreadPiece tp = new ThreadPiece();
     Thread t = new Thread(tp);
    //threaded excecution:
    threadOne.start();
    t.start(); //OS calls run method within the thread 
    //Direct execution &No new thread created
        t.run();

    try {
            //Main/current thread will wait for threadOne to finish before continuing
            System.out.println("Main thread waiting for t1 to join...");
            threadOne.join(); 
            
            //print after executing threadonee
            System.out.println("Thread One has joined, Main thread resumed");
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread execution complete.");
    
    
  }


}

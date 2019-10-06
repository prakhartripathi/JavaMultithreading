class CallMe{
    void call(String msg){
        System.out.print("["+msg);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}
class Caller implements Runnable{
    String msg;
    CallMe target;
    Thread t;
    public Caller(CallMe targ, String m){
        target = targ;
        msg = m;
        t = new Thread(this);
    }
    public void run(){
        synchronized(target){
            target.call(msg);
        }   
    }
}
public class SyncDemo{
    public static void main(String args[]){
        CallMe target = new CallMe();
        Caller one = new Caller(target,"One");
        Caller two = new Caller(target,"two");
        Caller three = new Caller(target,"three");
        one.t.start();
        two.t.start();
        three.t.start();
        try {
            one.t.join();
            two.t.join();
            three.t.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}
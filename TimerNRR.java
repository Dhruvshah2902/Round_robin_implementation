import java.util.*;

public class TimerNRR {

    //Declare a timer for round robin
    Timer t = new Timer("timer");
    Timer emt = new Timer("EMT");
    int counter = 0;
    int qsel  = 0;
    Queue[] q;
    int n;
    int x;
    TimerNRR(Queue[] lanes, int totLanes)
    {
        n = totLanes;
        q = lanes;
    }
    TimerNRR(int i)
    {
        x = i;
    }

    //DRIVER LOGIC FOR ROUND ROBIN SCHEDULING
    void dequeuemet2(int secs){
        int arr[] = new int[n];
        qsel = qsel%(n*secs);
        int x = qsel/secs;
        if(q[x].checkIfEmpty()&&counter<100){
            if(counter==4){
                t.cancel();
            }
            qsel =(x+1)*secs;
            counter++;
            try{
                dequeuemet2(secs);
            }
            catch(Exception e){
                throw e;
            }
        }
        else{
            q[x].dequeue();
            System.out.println(Arrays.toString(q[x].getQueueArr(arr))+x);
            counter=0;
        }
    }
    void dequeuemet(int secs){
        int arr[] = new int[n];
        qsel = qsel%(n*secs);
        int x = qsel/secs;
        q[x].dequeue();
        System.out.println(Arrays.toString(q[x].getQueueArr(arr)));
    }

    //Do not touch
    void simple(int secs) {
        Timer t1 = new Timer("timer");
        t1.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                dequeuemet(secs);
                qsel++;
            }
        }, 0, 1000);
    }

    //DRIVER METHOD FOR ROUND ROBIN
    void roundrobin(int secs) {
        t.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                try{
                    dequeuemet2(secs);
                }
                catch(Exception e){
                    throw e;
                }
                qsel++;
            }
        }, 0, 1000);
    }
    void emtime(Queue queue){

        emt.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                emtTemp(queue);
                x--;
            }
        }, 0, 1000);
    }

    void emtTemp(Queue queue)
    {
        int arr[] = new int[queue.size];
        if( x>0){
            queue.dequeue();
            System.out.println(Arrays.toString(queue.getQueueArr(arr)) + "KQLY");
        }
        else
            emt.cancel();
    }
}

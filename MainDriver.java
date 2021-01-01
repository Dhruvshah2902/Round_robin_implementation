import java.util.Scanner;

public class MainDriver {

    //Driver function
    public static void main(String args[]){

        Scanner input = new Scanner(System.in);
        int capacity; //Total number of cars which each lane can carry
        int n;  //Total number of lanes

        System.out.println("Enter the total number of lanes : ");
        n = input.nextInt();

        System.out.println("Enter the size of each traffic lane : ");
        capacity = input.nextInt();

        Queue[] lane;
        lane = new Queue[n];

        boolean[] comp = new boolean[n]; //This will keep track of whether a lane has less values than the max specified

        //Initializing the queues (traffic lanes)
        for(int i=0;i<n;i++)
        {
            lane[i] = new Queue(capacity);
        }

        //Taking the input from the user

        for(int i=0;i<n;i++)
        {
            System.out.println("Procedurally input the vehicles entering the lane " + (i + 1) + " : ");
            for(int j=0;j<capacity;j++)
            {
                int inpt;
                inpt = input.nextInt();
                if(inpt != -1)
                {
                    lane[i].enqueue(inpt);   //Enqueue each car in respective queue
                    comp[i] = true;
                }
                else
                {
                    for(int k=i;k<n;k++)
                        lane[i].enqueue(-1);
                    comp[i] = false;
                    break;
                }

            }
        }

        //GENERATING THE TIMER AND ROUND ROBIN CLASS OBJECT
        TimerNRR timernrr = new TimerNRR(lane,n);

        //Now check the traffic flow

        //Checking emergency vehicles first

        Emergency emerCheck = new Emergency(n,capacity); //Generate the emergency object
        emerCheck.emerDriver(lane);

        //Threshold function //TO-DO CHECK IF ALL QUEUES ARE BELOW THRESHOLD
        Threshold threshold = new Threshold();
        threshold.performThresholdCheck(6,lane,n);
        threshold.checkBelowThreshold(6,lane,n);

        //ROUND ROBIN SCHEDULING
        timernrr.roundrobin(5);
    }
}

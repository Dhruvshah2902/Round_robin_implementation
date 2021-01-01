import java.util.Arrays;

public class Emergency {

    int emrA = 0, emrF = 0;
    int[] isFEmer;
    int[] isAEmer;
    int totalLanes;
    int laneCapacity;

    //Make a constructor which initializes both the arrays
    Emergency(int n,int capacity)
    {
        isAEmer = new int[n];
        isFEmer = new int[n];
        totalLanes = n;
        laneCapacity = capacity;
    }

    void emer(Queue queue,int[] isAEmer, int[] isFEmer, int laneIndex)
    {
        int in = 0;
        int i = -1;

        emrA = laneIndex;
        int[] queueArr = new int[laneCapacity];
        queueArr = queue.getQueueArr(queueArr);


        for(in = 0; in < queue.size; in++)
        {
            i++;

            if(queueArr[in] == 108)
            {
                emrA = laneIndex;
                isAEmer[emrA] = in;

                //System.out.println("Emergency found at : " + emrA);
                emrA++;

                break;
            }
            else if(queueArr[in] == 100)
            {
                emrF = laneIndex;
                isFEmer[emrF] = i;

                break;
            }
            else
            {
                isAEmer[emrA] = 1000;
            }
        }
    }

    void emergencyPop(int i, Queue queue)
    {
        TimerNRR timernrr = new TimerNRR(i);
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        timernrr.emtime(queue);
    }

    boolean checkAmbulance(int[] isAEmer, Queue queue)
    {
        int in;
        int[] queueArr = new int[laneCapacity];
        queueArr = queue.getQueueArr(queueArr);

        System.out.println(Arrays.toString(queueArr));
        for(in = 0; in<queue.size; in++)
        {
            if(queueArr[in] == 108)
            {
                //System.out.println("HELL-O");
                return true;
            }
        }

        return false;
    }

    boolean checkAmbulance(int[] isAEmer)
    {
        for(int i=0; i < isAEmer.length; i++)
        {
            if(isAEmer[i] != 1000)
                return true;
        }
        return false;
    }

    //
    //
    //DRIVER METHOD FOR THIS CLASS
    //
    //

    void emerDriver(Queue[] lanes)
    {

        do
        {
            emrA = 0;

            java.util.Arrays.fill(isAEmer,1000);

            for(int i=0; i < totalLanes; i++)
            {
                emer(lanes[i],isAEmer,isFEmer,i);
            }

            int min, j = 0;
            min = 1001;
            //System.out.println(Arrays.toString(isAEmer));

            int nearestAmb = 0;
            for(int i=0; i < isAEmer.length; i++)
            {
                //System.out.println("FRONT : " + lanes[i].front + " REAR : " + lanes[i].rear);
                if(isAEmer[i] < min)
                {
                    min = isAEmer[i];
                    nearestAmb = i;
                }

            }

            if(checkAmbulance(isAEmer))
            {
                System.out.println("Allowing vehicles to clear the way for emergency...");
                emergencyPop(min,lanes[nearestAmb]);
            }

            //System.out.println(("TOT LANES" + totalLanes));
            for(int i=0; i < totalLanes; i++)
            {
                //System.out.println(checkAmbulance(isAEmer,lanes[i]));
                if(checkAmbulance(isAEmer,lanes[i]))
                {
                    //System.out.println("CHECKER KAPOORR");

                    break;
                }
            }

        }while(checkAmbulance(isAEmer));



    }

}

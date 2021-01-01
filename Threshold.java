public class Threshold {

    void performThresholdCheck(int threshold, Queue[] lane,int n) {

        System.out.println("Checking for Max Threshold...");
        for(int i=0;i<n;i++)
        {
            if(lane[i].size > threshold)
            {
                int j = lane[i].size - threshold;
                TimerNRR timer = new TimerNRR(j);
                try {
                    Thread.sleep(j*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                timer.emtime(lane[i]);
            }
        }
    }

    void checkBelowThreshold(int threshold, Queue[] lane, int n){

        System.out.println("Checking for Min Threshold...");
        for(int i=0;i<n;i++)
        {
            if(lane[i].size < threshold)
            {
                int j = threshold - lane[i].size;
                TimerNRR timer = new TimerNRR(j);
                try {
                    Thread.sleep(j*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                timer.emtime(lane[i]);
            }
        }
    }

}

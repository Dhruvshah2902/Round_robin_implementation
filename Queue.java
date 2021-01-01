public class Queue {

    int size;
    int capacity;
    int front;
    int rear;
    int[] val;

    //The main constructor for the Queue
    //Syntax : Queue(int Queue Size)
    //Makes a queue of the given size
    public Queue(int capacity)
    {
        val = new int[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
        this.capacity = capacity;
        for(int i=0;i<capacity;i++)
        {
            this.val[i] = -1;
        }
    }

    //Method to enqueue an element from the queue
    public void enqueue(int element) {

        if(this.size < this.capacity)
        {
            this.val[(this.rear)%(this.capacity)] = element;
            this.rear = (this.rear + 1)%(this.capacity);
            this.size += 1;
        }
    }

    //Method to dequeue an element from the queue
    public void dequeue(){

        if(this.size != 0)
        {
            this.val[(this.front)%(this.capacity)] = 0;
            this.front = (this.front + 1)%(this.capacity);
            this.size -=1;
        }
    }

    //Method for accessing all the elements of the queue at once
    public int[] getQueueArr(int[] arr)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(this.front + i < this.capacity)
                arr[i] = this.val[this.front + i];
            else
                arr[i] = this.val[(this.front + i)%this.capacity];
        }

        return arr;
    }

    //Check if the queue is empty
    public boolean checkIfFull()
    {
        if(this.size == this.capacity)
            return true;
        else
            return false;
    }

    //Check if the queue is full
    public boolean checkIfEmpty()
    {
        if(this.size == 0)
            return true;
        else
            return false;
    }
}

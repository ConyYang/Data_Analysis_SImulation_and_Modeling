public class ProcessSimulator{
    public final int totalEventCount = 10000;
    public double clock;
    public BaseStation[] baseStations;
    public int no_call_total;
    public int no_dropped_call;
    public int no_blocked_call;


    public void init(FCA_Schemes scheme){
        // initialize the variables
        eventQueue =  new PriorityQueue<>();
        clock = 0.0;
        baseStations = new BaseStation[20];
        no_call_total = 0;
        no_dropped_call = 0;
        no_blocked_call = 0;

        for (int i=0; i<20; i++){
            // A Fixed Channel Allocation (FCA) scheme is used.
            baseStations[i].setNoFreeChannels(10);
        }
    }

    public void simulation(){
        // reading the input from Random Number Generator
        for (int i=0; i< totalEventCount; i++){
            double inter_arrival_t = Random(expo_distribution E);
            Direction direction = Random("LEFT", "RIGHT");
            base_station = RandomGenerator(prob. distribution X);
            speed = RandomGenerator(prob. distribution Y);
            duration = RandomGenerator(prob. distribution Z);
            position = UniformDistRandomNumber(0, baseStationMaxRadius=2000);

            CallInitiationEvent event = new CallInitiationEvent(
                i, clock+inter_arrival_t, base_station. speed, duration, position);

            clock = clock + inter_arrival_t;
            eventQueue.add(event);
        }

        while (!eventQueue.isEmpty()){
            handleEvent();
        }
    }

    public void handleEvent(){
        Event event = eventQueue.peek();
        eventQueue.remove(event);


    }




}

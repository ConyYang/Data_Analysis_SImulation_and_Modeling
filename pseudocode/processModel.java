public class ProcessSimulator{
    public final int totalEventCount = 10000;
    public double clock;
    public BaseStation[] baseStations;
    public int no_call_total;
    public int no_dropped_call;
    public int no_blocked_call;
    EventQueue eventQueue =  new PriorityQueue<>();

    public void init(FCA_Schemes scheme){
        // initialize the variables
        this.clock = 0.0;
        this.baseStations = new BaseStation[20];
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
                i, this.clock+inter_arrival_t, base_station. speed, duration, position);

            this.clock += inter_arrival_t;
            this.eventQueue.add(event);
        }

        while (!this.eventQueue.isEmpty()){
            handleEvent();
        }
    }

    public void handleEvent(){
        Event event = eventQueue.pop();
        this.clock = event.getTime();
        BaseStation curr_base_station = event.getBS();

        // call initiation
        if (event instanceof CallInitiationEvent){
            if (curr_base_station.hasFreeChannel()){
                curr_base_station.useChannel();
                no_call_total+=1;
                generateNextEvenet(event);
            }else{
                no_blocked_call+=1;
            }
        }
        // call handover
        else if (event instanceof CallHandoverEvent){
            if (event.direction == BS_1)
                BaseStation next_base_station = this.baseStations[event.get_BS_id()-1]
            else
                BaseStation next_base_station = this.baseStations[event.get_BS_id()+1]

            if (next_base_station.hasFreeChannel()){
                next_base_station.useChannel();
                generateNextEvenet(event);
            }else{
                no_dropped_call+=1;
            }
        }
        // Termination
        else if (event instanceof CallTerminationEvent){
            curr_base_station.releaseChannel();
        }
    }

    public void generateNextEvenet(Event event){
         double reachNextStation_time = 2000/(eventData.velocity*1000/3600);
         double callRemaining_time = Math.min(reachNextStation_time, event.callDuration);
         doube newNextEventDuration = event.event.callDuration() - callRemaining_time;

            if (eventData.callDuration > callRemaining_time &&
            eventData.direction==BS_20 &&
            eventData.baseStation_No!=20)
            {
                newNextEvent = new CallHandoverEvent(
                    startTime = simulationClock + reaching_time,
                    endTime = eventData.callDuration - reaching_time);
            }
            // Towards BS1: reach next BS before call ends
            else if (eventData.callDuration > reaching_time &&
                eventData.direction==BS_1 &&
                eventData.baseStation_No!=1))
            {
                newNextEvent = new CallHandoverEvent(
                    startTime = simulationClock + reaching_time,
                    endTime = eventData.callDuration - reaching_time);
            }
            else // call ends before reach next BS
            {
                newNextEvent = new CallTerminationEvent(
                    startTime=simulationClock + callDuration);
            }

            eventQueue.add(newNextEvent);

    }

    public void calculateResult()
    {
        System.out.println(no_dropped_call);
        System.out.println(no_blocked_call/totalEventCount);
        System.out.println(droppedCallCount);
        System.out.println(totalEventCount - warmUpPeriod);

    }





}

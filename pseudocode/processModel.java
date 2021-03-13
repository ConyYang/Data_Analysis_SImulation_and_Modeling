public class ProcessSimulator{
    public final int totalEventCount = 10000;
    public double clock = 0.0;
    public BaseStation[] baseStations = new BaseStation[20];
    public int no_call_total = 0;
    public int no_dropped_call = 0;
    public int no_blocked_call = 0 ;

    for (int i=0; i<20; i++){
        // A Fixed Channel Allocation (FCA) scheme is used.
        baseStations[i].setNoFreeChannels(10);
    }

    public void simulation(){

        // Generate data
        double inter_arrival_t = Random(expo_distribution E);
        Direction direction = Random("LEFT", "RIGHT");
        base_station = RandomGenerator(prob. distribution X);
        speed = RandomGenerator(prob. distribution Y);
        callDuration = RandomGenerator(prob. distribution Z);
        position = (base_station - 1) * 2 + UniformDistRandomNumber(0, baseStationMaxRadius=2);


        // if there is no available channel for the base station, the call is blocked and process ends here.
        if (baseStations[base_station].getNoFreeChannles()==0){
            no_blocked_call+=1;
            END();
        }

        // if there is an available channel for the base station, allocate the channel and initialize the call
        else {
            baseStations[base_station].useChannel();
            no_call_total +=1;

            callRemaining_time = min(callDuration, 1/speed);
            callDuration -= callRemaining_time;
            HOLD(callDuration);

            while (true)
            {
                reachNextStation_time = 0;

                // Calculate the time need to reach next station
                if (direction == 'BS_20'){
                    reachNextStation_time = (position - (BS_id-2)*2)/speed;
                    position = position - speed * reachNextStation_time;
                } else {
                    reachNextStation_time = (BS_id*2-position)/speed;
                    position = position + speed * reachNextStation_time;
                }

                // If the call ends before reach next station
                if (reachNextStation_time >= callDuration)
                {
                    HOLD(callDuration);
                    // End the call, release channel and end process
                    baseStations[base_station].releaseChannel();
                    END();
                }

                // If the car leave the highway before call ends
                next_base_station = (base_station + 1) if (direction == 'BS_20') else (base_station -1)
                if (next_base_station <=0  || next_base_station >= 20){
                    // End call, release channel and end process
                    HOLD(reachNextStation_time);
                    baseStations[base_station].releaseChannel();
                    END();
                }

                // Hold the channel and release before handover
                HOLD(reachNextStation_time);
                baseStations[base_station].releaseChannel();


                // If the new station has a free channel, allocate it to handle the call
                if (baseStations[next_base_station].hasFreeChannel()){
                   base_station[next_base_station].useChannel();
                   HOLD(min(callDuration, 2/speed));
                   callDuration -= min(callDuration, 2/speed);
                }else{
                    // If the new station hs no free channel
                    no_dropped_call += 1;
                    END();
                }
        }
    }

    public void calculateResult()
    {
        System.out.println(no_dropped_call);
        System.out.println(no_blocked_call/totalEventCount);
        System.out.println(droppedCallCount);
        System.out.println(totalEventCount - warmUpPeriod);

    }
}

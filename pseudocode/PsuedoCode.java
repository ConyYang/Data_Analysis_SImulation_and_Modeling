
CallInitiationEvent(eventData){
    double simulationClock = eventData.arrival_Time; //Sync clock to event time
    Schedule CallInitialEvent eventData.next.arrival_Time;
    int Total_Num_Calls += 1;

    // Check of this Base Station has any free channel
    if(BaseStation_List[eventData.base_Station_No].available_Channel>0)
    {
        // Has free channel: Use 1 channel for this new call
        BaseStation_List[eventData.baseStation_No]
    } else {
        // No free channel: Call is blocked
        int num_Blocked_Calls ++;
        CallInitialEvent End;
    }

    // time before reaching the next next Base station: assume start from center
    double reaching_time = 1000/(eventData.velocity*1000/3600);

    // Random create the direction
    if (Math.random() >= 0.5){
        eventData.direction = BS_1;
    }else{
        eventData.direction = BS_20;
    }

    // Towards BS20: reach next BS before call ends
    if (eventData.callDuration > reaching_time &&
        eventData.direction==BS_20 &&
        eventData.baseStation_No!=20)
    {
        Schedule CallHandoverEvent(startTime = simulationClock + reaching_time,
            endTime = eventData.callDuration - reaching_time);
    }
    // Towards BS1: reach next BS before call ends
    else if (eventData.callDuration > reaching_time &&
        eventData.direction==BS_1 &&
        eventData.baseStation_No!=1))
    {
        Schedule CallHandoverEvent(startTime = simulationClock + reaching_time,
            endTime = eventData.callDuration - reaching_time);
    }
    else // call ends before reach next BS
    {
        Schedule CallTerminationEvent(startTime=simulationClock + callDuration);
    }
}


CallTerminationEvent(eventData){
    double simulationClock = eventData.startTime;
    int num_Terminated_Calls ++;
    BaseStation[eventData.baseStation_No].releaseChannel();
}


CallHandoverEvent(eventData){
    simulationClock = eventData.startTime;

    // release the channel used in current Base station
    BaseStation_List[eventData.baseStation_No].releaseChannel();

    // Get id of next Base Station
    if (event.direction == BS_1){
        next_BaseStation = BaseStation_List[eventData.base_Station_No -1];
    }else{
        next_BaseStation = BaseStation_List[eventData.base_Station_No +1];
    }

    // check if next Base Station has any free channel in 9 not reserved ones
    if (next_BaseStation.num_Available_Channel_Each_Station > 0){
        next_BaseStation.useChannel();
        int num_Handovers_Calls ++;
    }
    // check if next Base Station's reserved channel is free
    else if (next_BaseStation.reserved_Channel_Free){
        next_BaseStation.useReservedChannel();
        int num_Handovers_Calls ++;
    }
    else // no more free channel: increase dropcalls
    {
        num_Dropped_Calls ++;
        CallHandoverEvent End;
    }

    // time before reaching the next next Base station: start from start
    double reaching_time = 2000/(eventData.velocity*1000/3600);

    // Towards BS20: reach next BS before call ends
    if (eventData.callDuration > reaching_time &&
        eventData.direction==BS_20 &&
        eventData.baseStation_No!=20)
    {
        Schedule CallHandoverEvent(startTime = simulationClock + reaching_time,
            endTime = eventData.callDuration - reaching_time);
    }
    // Towards BS1: reach next BS before call ends
    else if (eventData.callDuration > reaching_time &&
        eventData.direction==BS_1 &&
        eventData.baseStation_No!=1))
    {
        Schedule CallHandoverEvent(startTime = simulationClock + reaching_time,
            endTime = eventData.callDuration - reaching_time);
    }
    else // call ends before reach next BS
    {
        Schedule CallTerminationEvent(startTime=simulationClock + callDuration);
    }

}




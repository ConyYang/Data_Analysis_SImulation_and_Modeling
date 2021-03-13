
base_station_id = getFromDistribution(X) 
speed = getFromDistribution(Y)
duration = getFromDistribution(Z)
direction = Rand(0,1) #Rand(x,y) returns an integer from x to y inclusive, direction 0 means towards left, 1 towards right

TOT_CALLS += 1

if num_free_channels[base_station_id] == 0: #no free channel
    #call is blocked
    NUM_BLOCKED_CALLS += 1

else:
    #call is not blocked
    NUM_CALLS += 1
    num_free_channels[base_station_id] -= 1 #take up 1 free channel

    HOLD(min(duration,1/speed))
    duration -= min(duration,1/speed)

    while True:
        #the call is ended or go out from current cell
        num_free_channels[base_station_id] += 1 #release one channel
        if duration == 0: 
            #the call already ended, break the loop
            break

        else:
            #the call goes to the next cell
            base_station_id += (direction? 1 : -1) #calculate the next base_station_id by direction

            if base_station_id < 1 or base_station_id > 20: #base_station_id out of range
                #the car left the high way, break the loop
                break

            else if num_free_channels[base_station_id] == 0: #no free channel in new cell
                #the call is dropped, break the loop
                NUM_DROPPED_CALLS += 1
                break

            else:
                #handover happens
                NUM_HANDOVER += 1
                num_free_channels[base_station_id] -= 1 #take up 1 channel in new cell

                HOLD(min(duration,2/speed))
                duration -= min(duration,2/speed)

#terminated


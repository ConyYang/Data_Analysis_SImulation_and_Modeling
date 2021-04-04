
import numpy.random as random
import pandas as pd
import numpy as np


def baseStation():
    return int(random.uniform(1,21))


def interArrivalTime():
    return random.exponential(1.3697)


def callDuration():
    return 10+random.exponential(109.83590)


def carSpeed():
    return random.normal(loc=120.072098, scale=9.019058)


def direction():
    if random.uniform()<0.5:
        return 1
    else:
        return 0


def position():
    return random.uniform()*2000


if __name__ == '__main__':
    df = pd.DataFrame(columns=['BaseStation', 'interArrivalTime', 'callDuration', 'carSpeed', 'direction', 'position'])
    for i in range(1, 101):
        arr = [int(baseStation()), interArrivalTime(), callDuration(), carSpeed(), int(direction()), position()]
        # baseStation(), interArrivalTime(), callDuration(), carSpeed(), direction(), position()]
        df.loc[i] = arr
    df.to_csv('test.csv')



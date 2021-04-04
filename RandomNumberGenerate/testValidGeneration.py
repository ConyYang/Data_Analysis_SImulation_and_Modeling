import pandas as pd
import seaborn as sns
import numpy as np

df = pd.read_csv('test.csv')


def arrival_time():
    arrival_time_df = pd.DataFrame(df['interArrivalTime'])
    plt = sns.displot(arrival_time_df['interArrivalTime'], binwidth=0.12, kde=True)
    plt.savefig('result/interArrivalTimeDist.png')


def base_station():
    plt1 = sns.displot(df['BaseStation'], bins=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21], kde=True)
    plt1.savefig('result/BaseStationDist.png')


def call_duration():
    print("Minimum value of call duration: ", min(df['callDuration']))
    print("Maximum value of call duration: ", max(df['callDuration']))
    print("Mean value of call duration: ", np.mean(df['callDuration']))
    plt2 = sns.displot(df['callDuration'], binwidth=12.29, kde=True)
    plt2.savefig('result/CallDurationDist.png')


def velocity():
    print("Minimum value of velocity: ", min(df['carSpeed']))
    print("Maximum value of velocity: ", max(df['carSpeed']))
    print("Mean value of velocity: ", np.mean(df['carSpeed']))
    print('Square root of velocity: ', np.var(df['carSpeed']))

    plt3 = sns.displot(df['carSpeed'], binwidth=0.7, kde=True)
    plt3.savefig('result/carSpeedDist.png')


if __name__ == '__main__':
    arrival_time()

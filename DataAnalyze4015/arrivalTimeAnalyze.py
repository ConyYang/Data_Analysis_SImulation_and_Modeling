import pandas as pd
import seaborn as sns
import numpy as np

df = pd.read_excel('PCS_TEST_DETERMINSTIC.xls')


def arrival_time():
    arrival_time_df = pd.DataFrame(df['Arrival time (sec)'])

    compare_time = arrival_time_df['Arrival time (sec)'][0:len(arrival_time_df) - 1].to_list()
    compare_time.insert(0, 0)
    arrival_time_df['compare_time'] = compare_time

    arrival_time_df['Inter Arrival Time'] = arrival_time_df['Arrival time (sec)'] - arrival_time_df['compare_time']

    print('Minimum value of inter arrival time: ', min(arrival_time_df['Inter Arrival Time'][1:]))
    print('Maximum value of inter arrival time: ', max(arrival_time_df['Inter Arrival Time']))
    print('Mean value of inter arrival time: ', np.mean(arrival_time_df['Inter Arrival Time']))

    plt = sns.displot(arrival_time_df['Inter Arrival Time'], binwidth=0.12, kde=True)
    plt.savefig('result/ArrivalTimeDist.png')


def base_station():
    plt1 = sns.displot(df['Base station '], bins=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21], kde=True)
    plt1.savefig('result/BaseStationDist.png')


def call_duration():
    print("Minimum value of call duration: ", min(df['Call duration (sec)']))
    print("Maximum value of call duration: ", max(df['Call duration (sec)']))
    print("Mean value of call duration: ", np.mean(df['Call duration (sec)']))
    # plt2 = sns.displot(df['Call duration (sec)'], binwidth=12.29, kde=True)
    # plt2.savefig('result/CallDurationDist.png')


def velocity():
    print("Minimum value of velocity: ", min(df['velocity (km/h)']))
    print("Maximum value of velocity: ", max(df['velocity (km/h)']))
    print("Mean value of velocity: ", np.mean(df['velocity (km/h)']))
    print('Square root of velocity: ', np.var(df['velocity (km/h)']))

    plt3 = sns.displot(df['velocity (km/h)'], binwidth=0.7, kde=True)
    plt3.savefig('result/VelocityDist.png')


if __name__ == '__main__':
    velocity()

import pandas as pd


def process_file(filename, pngname):
    df = pd.read_csv(filename)
    df['DroppedCount'] = df['DroppedCount'].astype(float)/10000
    df['BlockedCount'] = df['BlockedCount'].astype(float)/10000
    lines = df.plot.line()

    fig = lines.get_figure()
    fig.savefig(pngname)


if __name__ == '__main__':
    for i in range(1, 11):
        # filename = "/Users/yangyubei/IdeaProjects/SimulationAndModeling/WarmUpAnalyze/Normal/SimulationAndModeling"+str(i)+".csv"
        # pngname = "result/" + "normalSimulationWarmUp" + str(i) + ".png"

        filename = "/Users/yangyubei/IdeaProjects/SimulationAndModeling/WarmUpAnalyze/Reserved/SimulationAndModeling" + str(i) + ".csv"
        pngname = "result/" + "reservedSimulationWarmUp" + str(i) + ".png"

        process_file(filename, pngname)
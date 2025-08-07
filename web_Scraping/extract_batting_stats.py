import pandas as pd

bat_df = pd.read_csv("ipl_most_runs.csv")

batting_raw = bat_df[[
    "StrikerName", "TeamCode", "TeamName", "Nationality",
    "Matches", "Innings", "TotalRuns", "HighestScore",
    "BattingAverage", "StrikeRate", "Centuries", "FiftyPlusRuns",
    "Fours", "Sixes"
]]

batting_raw.to_csv("batting_raw.csv", index=False)
print("✅ batting_raw.csv created")

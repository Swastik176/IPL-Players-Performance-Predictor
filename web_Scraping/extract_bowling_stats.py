import pandas as pd
bowl_df = pd.read_csv("ipl_most_wickets.csv")

bowling_raw = bowl_df[[
    "BowlerName", "TeamCode", "TeamName", "Nationality",
    "Matches", "Innings", "Wickets", "OversBowled", "EconomyRate",
    "Fours", "Sixes", "BowlingAverage", "BowlingSR",
    "FourWickets", "FiveWickets"
]]

bowling_raw.to_csv("bowling_raw.csv", index=False)
print("✅ bowling_raw.csv created")

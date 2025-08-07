import pandas as pd

# Step 1: Load the CSVs
players_df = pd.read_csv("players_table.csv")
bowl_df = pd.read_csv("bowling_raw.csv")

# Step 2: Standardize column names for merge
bowl_df = bowl_df.rename(columns={
    "BowlerName": "name",
    "TeamCode": "team_code",
    "TeamName": "team_name",
    "Nationality": "country"
})

# Step 3: Merge to bring in player_id
merged_df = pd.merge(bowl_df, players_df, on="name", how="inner")

# Step 4: Select and rename required columns
bowling_stats = merged_df[[
    "player_id", "Matches", "Innings", "Wickets", "OversBowled", "EconomyRate",
    "Fours", "Sixes", "BowlingAverage", "BowlingSR", "FourWickets", "FiveWickets"
]]

bowling_stats.columns = [
    "player_id", "matches", "innings", "wickets", "overs", "economy",
    "fours_conceded", "sixes_conceded", "bowling_average", "bowling_strike_rate",
    "four_wicket_hauls", "five_wicket_hauls"
]

# Step 5: Save the cleaned table
bowling_stats.to_csv("bowling_stats_table.csv", index=False)
print("✅ bowling_stats_table.csv created.")

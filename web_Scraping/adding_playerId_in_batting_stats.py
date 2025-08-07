import pandas as pd

# Load players and batting raw data
players_df = pd.read_csv("players_table.csv")
bat_df = pd.read_csv("batting_raw.csv")

# Standardize columns to match for merge
bat_df = bat_df.rename(columns={
    "StrikerName": "name",
    "TeamCode": "team_code",
    "TeamName": "team_name",
    "Nationality": "country"
})

# Merge to bring player_id
bat_merged = pd.merge(bat_df, players_df, on="name", how="inner")

# Extract final batting stats columns
batting_stats = bat_merged[[
    "player_id", "Matches", "Innings", "TotalRuns", "HighestScore",
    "BattingAverage", "StrikeRate", "Centuries", "FiftyPlusRuns",
    "Fours", "Sixes"
]]

# Rename to match schema
batting_stats.columns = [
    "player_id", "matches", "innings", "runs", "highest_score",
    "average", "strike_rate", "hundreds", "fifties", "fours", "sixes"
]

# Save
batting_stats.to_csv("batting_stats_table.csv", index=False)
print("✅ batting_stats_table.csv created.")

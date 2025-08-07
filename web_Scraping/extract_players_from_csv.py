import pandas as pd

bat_df = pd.read_csv("ipl_most_runs.csv")
bowl_df = pd.read_csv("ipl_most_wickets.csv")

# Rename batting CSV columns
bat_players = bat_df.rename(columns={
    "StrikerName": "name",
    "TeamCode": "team_code",
    "TeamName": "team_name",
    "Nationality": "country"
})[["name", "team_code", "team_name", "country"]]

# Rename bowling CSV columns
bowl_players = bowl_df.rename(columns={
    "BowlerName": "name",
    "TeamCode": "team_code",
    "TeamName": "team_name",
    "Nationality": "country"
})[["name", "team_code", "team_name", "country"]]


# Combine both sources
all_players = pd.concat([bat_players, bowl_players]).drop_duplicates(subset=["name", "team_code", "team_name", "country"])

# Assign unique player IDs
all_players["player_id"] = ["P" + str(i).zfill(4) for i in range(1, len(all_players)+1)]

# Save as master Player table
all_players.to_csv("players_table.csv", index=False)
print("✅ Normalized players_table.csv created.")

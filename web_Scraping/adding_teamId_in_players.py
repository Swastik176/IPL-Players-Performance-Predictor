import pandas as pd

# Load files
players_df = pd.read_csv("players_table.csv")
teams_df = pd.read_csv("ipl_teams.csv")

# Merge on team_code + team_name
merged_df = pd.merge(players_df, teams_df, on=["team_code", "team_name"], how="left")

# Drop unnecessary columns
cleaned_players_df = merged_df[["player_id", "name", "country", "team_id"]]

# Save updated players table
cleaned_players_df.to_csv("players_table1.csv", index=False)
print("✅ players_table.csv updated with team_id and cleaned.")

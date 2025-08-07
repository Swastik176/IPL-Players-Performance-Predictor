import pandas as pd

# Step 1: Load players table
players_df = pd.read_csv("players_table.csv")

# Step 2: Extract unique teams
teams_df = players_df[["team_code", "team_name"]].drop_duplicates().reset_index(drop=True)

# Step 3: Assign unique team IDs
teams_df["team_id"] = ["T" + str(i).zfill(3) for i in range(1, len(teams_df)+1)]

# Step 4: Add logo URLs
logo_map = {
    "CSK": "https://documents.iplt20.com/ipl/CSK/logos/Logooutline/CSKoutline.png",
    "MI": "https://documents.iplt20.com/ipl/MI/Logos/Logooutline/MIoutline.png",
    "RCB": "https://documents.iplt20.com/ipl/RCB/Logos/Logooutline/RCBoutline.png",
    "GT": "https://documents.iplt20.com/ipl/GT/Logos/Logooutline/GToutline.png",
    "DC": "https://documents.iplt20.com/ipl/DC/Logos/LogoOutline/DCoutline.png",
    "RR": "https://documents.iplt20.com/ipl/RR/Logos/Logooutline/RRoutline.png",
    "KKR": "https://documents.iplt20.com/ipl/KKR/Logos/Logooutline/KKRoutline.png",
    "SRH": "https://documents.iplt20.com/ipl/SRH/Logos/Logooutline/SRHoutline.png",
    "LSG": "https://documents.iplt20.com/ipl/LSG/Logos/Logooutline/LSGoutline.png",
    "PBKS": "https://documents.iplt20.com/ipl/PBKS/Logos/Logooutline/PBKSoutline.png"
}

teams_df["logo_url"] = teams_df["team_code"].map(logo_map)

# Step 5: Reorder columns
teams_df = teams_df[["team_id", "team_code", "team_name", "logo_url"]]

# Step 6: Save to CSV
teams_df.to_csv("ipl_teams.csv", index=False)
print("✅ ipl_teams.csv created with logos.")

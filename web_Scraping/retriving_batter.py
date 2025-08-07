import requests
import re
import json
import pandas as pd

# IPL official stats JSONP endpoint
url = "https://ipl-stats-sports-mechanic.s3.ap-south-1.amazonaws.com/ipl/feeds/stats/203-toprunsscorers.js?callback=ontoprunsscorers"

headers = {
    "User-Agent": "Mozilla/5.0"
}

# Fetch the raw response
response = requests.get(url, headers=headers)
raw_text = response.text

# Strip the JSONP wrapper
json_data = re.sub(r'^ontoprunsscorers\(|\);$', '', raw_text)

# Parse the JSON string into a Python dict
parsed = json.loads(json_data)

# Access player data
players = parsed["toprunsscorers"]

# Convert to DataFrame
df = pd.DataFrame(players)

# Preview and save
print(df.columns)  # See available fields
df.to_csv("ipl_most_runs.csv", index=False)
print("✅ Data saved to ipl_most_runs.csv")

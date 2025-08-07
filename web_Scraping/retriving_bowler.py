import requests
import re
import json
import pandas as pd

# Bowler stats endpoint (JSONP)
url = "https://ipl-stats-sports-mechanic.s3.ap-south-1.amazonaws.com/ipl/feeds/stats/203-mostwickets.js?callback=onmostwickets"

headers = {
    "User-Agent": "Mozilla/5.0"
}

# Fetch response
response = requests.get(url, headers=headers)
raw_text = response.text

# Strip the JSONP wrapper
json_data = re.sub(r'^onmostwickets\(|\);$', '', raw_text)

# Load JSON
parsed = json.loads(json_data)

# Access list of bowlers
bowlers = parsed["mostwickets"]  # This key name is now confirmed

# Convert to DataFrame
df = pd.DataFrame(bowlers)

# Preview fields
print(df.columns)

df.to_csv("ipl_most_wickets.csv", index=False)
print("Data extracted!!!")

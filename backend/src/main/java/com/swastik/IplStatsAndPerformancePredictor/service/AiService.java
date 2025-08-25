package com.swastik.IplStatsAndPerformancePredictor.service;

import com.swastik.IplStatsAndPerformancePredictor.dto.BattingStatsDTO;
import com.swastik.IplStatsAndPerformancePredictor.dto.BowlingStatsDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiService {

    @Value("${HF_API_KEY}")
    private String apiKey;

    @Value("${HF_MODEL}")
    private String model;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String callHuggingFace(String prompt) throws Exception {
        String url = "https://router.huggingface.co/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("model", model);
        body.put("stream", false);
        body.put("messages", new Object[]{Map.of("role", "user", "content", prompt)});

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        return jsonNode.get("choices").get(0).get("message").get("content").asText();
    }

    public String getBattingPrediction(BattingStatsDTO battingStatsDTO) throws Exception {
        String prompt = String.format("""
                You are a cricket performance prediction system. 
                Given the following batting statistics of a player, analyze their performance potential on there next match
                and return ONLY a single number between 0 and 100 representing the predicted performance percentage. 
                Do not return any explanation, text, or additional formatting. 
                Output must be only the numeric value.
            
                Player Batting Stats:
                - Player Name: %s
                - Team: %s (%s)
                - Country: %s
                - Matches: %d
                - Innings: %d
                - Runs: %d
                - Highest Score: %s
                - Batting Average: %s
                - Strike Rate: %.2f
                - 50s: %d
                - 100s: %d
                - 4s: %d
                - 6s: %d
                """,
                battingStatsDTO.getPlayerName(),
                battingStatsDTO.getTeamName(),
                battingStatsDTO.getTeamCode(),
                battingStatsDTO.getCountryName(),
                battingStatsDTO.getMatches(),
                battingStatsDTO.getInnings(),
                battingStatsDTO.getRuns(),
                battingStatsDTO.getHighestScore(),
                battingStatsDTO.getAverage(),
                battingStatsDTO.getStrikeRate(),
                battingStatsDTO.getFifties(),
                battingStatsDTO.getCenturies(),
                battingStatsDTO.getFours(),
                battingStatsDTO.getSixes()
        );

        return callHuggingFace(prompt);
    }

    public String getBowlingPrediction(BowlingStatsDTO bowlingStatsDTO) throws Exception {
        String prompt = String.format("""
                You are a cricket performance prediction system. 
                Given the following bowling statistics of a player, analyze their performance potential on there next match
                and return ONLY a single number between 0 and 100 representing the predicted performance percentage. 
                Do not return any explanation, text, or additional formatting. 
                Output must be only the numeric value.
            
                Player Bowling Stats:
                - Player Name: %s
                - Team: %s (%s)
                - Country: %s
                - Matches: %d
                - Innings: %d
                - Wickets: %d
                - Overs: %.1f
                - Economy: %.2f
                - 4s Conceded: %d
                - 6s Conceded: %d
                - Bowling Average: %.2f
                - Bowling Strike Rate: %.2f
                - 4-Wicket Hauls: %d
                - 5-Wicket Hauls: %d
                """,
                bowlingStatsDTO.getPlayerName(),
                bowlingStatsDTO.getTeamName(),
                bowlingStatsDTO.getTeamCode(),
                bowlingStatsDTO.getCountryName(),
                bowlingStatsDTO.getMatches(),
                bowlingStatsDTO.getInnings(),
                bowlingStatsDTO.getWickets(),
                bowlingStatsDTO.getOvers(),
                bowlingStatsDTO.getEconomy(),
                bowlingStatsDTO.getFoursConceded(),
                bowlingStatsDTO.getSixesConceded(),
                bowlingStatsDTO.getBowlingAvg(),
                bowlingStatsDTO.getBowlingStrikeRate(),
                bowlingStatsDTO.getFourWickets(),
                bowlingStatsDTO.getFiveWickets()
        );

        return callHuggingFace(prompt);
    }
}

package com.swastik.IplStatsAndPerformancePredictor.controller;

import com.swastik.IplStatsAndPerformancePredictor.dto.PlayersDTO;
import com.swastik.IplStatsAndPerformancePredictor.dto.PredictionRequestDTO;
import com.swastik.IplStatsAndPerformancePredictor.service.OllamaService;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/predict")
public class OllamaController {

    @Autowired
    private OllamaService ollamaService;

    @PostMapping("/")
    private ResponseEntity<String> getAnswer(@RequestBody PredictionRequestDTO predictionRequestDTO){

        if(predictionRequestDTO.getRole().equalsIgnoreCase("batting")){
            return ResponseEntity.ok(ollamaService.getBattingPrediction(predictionRequestDTO.getBattingStatsDTO()));
        }
        else if(predictionRequestDTO.getRole().equalsIgnoreCase("bowling")){
            return ResponseEntity.ok(ollamaService.getBowlingPrediction(predictionRequestDTO.getBowlingStatsDTO()));
        }
        return ResponseEntity.badRequest().body("Invalid Role");
    }
}

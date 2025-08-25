package com.swastik.IplStatsAndPerformancePredictor.controller;

import com.swastik.IplStatsAndPerformancePredictor.dto.PredictionRequestDTO;
import com.swastik.IplStatsAndPerformancePredictor.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/predict")
public class AiController {

    @Autowired
    private AiService aiService;

    @PostMapping("/")
    private ResponseEntity<String> getAnswer(@RequestBody PredictionRequestDTO predictionRequestDTO) throws Exception {

        if(predictionRequestDTO.getRole().equalsIgnoreCase("batting")){
            return ResponseEntity.ok(aiService.getBattingPrediction(predictionRequestDTO.getBattingStatsDTO()));
        }
        else if(predictionRequestDTO.getRole().equalsIgnoreCase("bowling")){
            return ResponseEntity.ok(aiService.getBowlingPrediction(predictionRequestDTO.getBowlingStatsDTO()));
        }
        return ResponseEntity.badRequest().body("Invalid Role");
    }
}

package com.swastik.IplStatsAndPerformancePredictor.repository;

import com.swastik.IplStatsAndPerformancePredictor.model.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayersRepo extends JpaRepository<Players, String> {

    @Query("SELECT p FROM Players p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Players> findByKeyword(String keyword);
}

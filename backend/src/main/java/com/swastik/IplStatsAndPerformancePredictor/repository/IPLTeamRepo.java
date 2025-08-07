package com.swastik.IplStatsAndPerformancePredictor.repository;

import com.swastik.IplStatsAndPerformancePredictor.model.IPLTeams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPLTeamRepo extends JpaRepository<IPLTeams, String> {
    @Query("SELECT t FROM IPLTeams t WHERE " +
            "LOWER(t.teamName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "+
            "LOWER(t.teamCode) LIKE LOWER(CONCAT('%', :keyword, '%'))"
    )
    List<IPLTeams> findByKeyword(String keyword);
}

package com.swastik.IplStatsAndPerformancePredictor.repository;

import com.swastik.IplStatsAndPerformancePredictor.dto.CountryDTO;
import com.swastik.IplStatsAndPerformancePredictor.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CountryRepo extends JpaRepository<Country, String>{
    @Query("SELECT c FROM Country c WHERE LOWER(c.countryName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Country> findByKeyword(String keyword);
}

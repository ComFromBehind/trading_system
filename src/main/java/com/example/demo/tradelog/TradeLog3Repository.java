package com.example.demo.tradelog;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface TradeLog3Repository extends JpaRepository<TradeLog3,Integer> {
    Optional<TradeLog3> findById(Long id);
    @Transactional
    void deleteById(Long id);
}

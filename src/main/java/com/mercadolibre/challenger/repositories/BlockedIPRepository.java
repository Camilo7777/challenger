package com.mercadolibre.challenger.repositories;

import com.mercadolibre.challenger.models.BlockedIp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedIPRepository extends JpaRepository<BlockedIp, Long> {
    boolean existsByIp(String ip);
}
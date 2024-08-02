package com.mercadolibre.challenger.repositories;

import com.mercadolibre.challenger.models.Ip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpRepository extends JpaRepository<Ip, Long> {
}

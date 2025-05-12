package org.lessons.java.games.gamer_hub.repository;

import java.util.List;

import org.lessons.java.games.gamer_hub.model.OnSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnSaleRepository extends JpaRepository<OnSale, Integer> {
    public List<OnSale> findByNameContainingIgnoreCase(String name);
}

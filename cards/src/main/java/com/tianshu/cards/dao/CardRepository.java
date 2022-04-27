package com.tianshu.cards.dao;

import com.tianshu.cards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    public List<Card> findByCustomerId(Long customerId);
}

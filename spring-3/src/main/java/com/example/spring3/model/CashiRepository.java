package com.example.spring3.model;

import com.example.spring3.model.entity.CASHI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CashiRepository extends JpaRepository<CASHI, CASHI.CASHIID> {
    @Transactional
    @Modifying
    void  deleteByMgniId(String id);
}
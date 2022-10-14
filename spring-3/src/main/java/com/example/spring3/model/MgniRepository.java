package com.example.spring3.model;

import com.example.spring3.model.entity.MGNI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MgniRepository extends JpaRepository<MGNI, String>, JpaSpecificationExecutor<MGNI> {

    @Modifying
    void deleteById(String id);
}

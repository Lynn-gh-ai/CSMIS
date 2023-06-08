package com.dat.csmis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dat.csmis.entity.MeatList;

public interface MeatListRepo extends JpaRepository<MeatList,Integer>{
    
}

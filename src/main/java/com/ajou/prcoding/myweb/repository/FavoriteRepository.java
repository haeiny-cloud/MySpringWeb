package com.ajou.prcoding.myweb.repository;

import com.ajou.prcoding.myweb.entity.FavoriteMusic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<FavoriteMusic, String> {
    List<FavoriteMusic> findAll();
    FavoriteMusic findByCollectionId(String id);
    void deleteByCollectionId(String id);
}

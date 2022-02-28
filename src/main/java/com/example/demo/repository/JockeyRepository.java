package com.example.demo.repository;

import com.example.demo.entity.Jockey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JockeyRepository {
    Jockey fetchJockey(int id);
    List<Jockey> fetchJockeys(List<String> names);
    void saveJockey(@Param("jockey") Jockey jockey);
}

package com.example.demo.repository;

import com.example.demo.entity.Jockey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JockeyRepository {
    Jockey fetchJoceky(int id);
    void saveJockey(String  name);
}

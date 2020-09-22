package com.example.demo.dao;

import com.example.demo.User;

import java.util.List;

public interface ProductDao {
    public String run(Integer id);
    public List<User> selectAllUser();
}

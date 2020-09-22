package com.example.demo.service;


import com.example.demo.User;
import com.example.demo.dao.ProductDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    private ProductDao productDao;

    @Override
    public String run(Integer id) {
        return productDao.run(id);
    }

    @Override
    public List<User> selectAllUser() {
        return productDao.selectAllUser();
    }
}

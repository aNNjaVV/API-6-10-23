package com.cibertec.QuickSale.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.QuickSale.model.Category;

public interface ICategoryRepo extends JpaRepository<Category,Integer>{
}

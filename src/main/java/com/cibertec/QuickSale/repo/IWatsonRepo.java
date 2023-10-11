package com.cibertec.QuickSale.repo;

import com.cibertec.QuickSale.model.Category;
import com.cibertec.QuickSale.model.Watson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWatsonRepo extends JpaRepository<Watson,Integer>{
}

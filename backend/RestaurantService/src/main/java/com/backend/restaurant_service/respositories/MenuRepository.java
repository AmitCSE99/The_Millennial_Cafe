package com.backend.restaurant_service.respositories;

import com.backend.restaurant_service.entities.Category;
import com.backend.restaurant_service.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,String> {

//    List<Menu> findByCategories(Category category);
}

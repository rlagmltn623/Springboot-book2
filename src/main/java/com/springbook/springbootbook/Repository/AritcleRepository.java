package com.springbook.springbootbook.Repository;

import com.springbook.springbootbook.enetity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AritcleRepository extends CrudRepository<Article, Long> {
    @Override
    List<Article> findAll();
}

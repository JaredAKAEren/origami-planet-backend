package com.shixuran.origami.dao;

import com.shixuran.origami.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDAO extends JpaRepository<Article, Integer> {
    Article findById(int id);

    Page<Article> findAllByArticleUserId(Pageable pageable, int articleUserId);

    Page<Article> findAllByArticleState(Pageable pageable, int state);
}

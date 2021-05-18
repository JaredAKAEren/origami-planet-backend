package com.shixuran.origami.service;

import com.shixuran.origami.dao.ArticleDAO;
import com.shixuran.origami.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ArticleService {
    @Autowired
    ArticleDAO articleDAO;

    public int addOrUpdate(Article article) {
        article.setArticleDate(new Date());
        Article articleAfterSave = articleDAO.save(article);
        return articleAfterSave.getId();
    }

    public Page<Article> list(int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return articleDAO.findAll(PageRequest.of(page, size, sort));
    }

    public Page<Article> listWithState(int page, int size, int state) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return articleDAO.findAllByArticleState(PageRequest.of(page, size, sort), state);
    }

    public Article findById(int id) {
        return articleDAO.findById(id);
    }

    public Page<Article> userArticleList(int page, int size, int id) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return articleDAO.findAllByArticleUserId(PageRequest.of(page - 1, size, sort), id);
    }

    public boolean delete(int id) {
        try {
            articleDAO.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeArticleState(Article article) {
        try {
            articleDAO.save(article);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

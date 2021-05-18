package com.shixuran.origami.dao;

import com.shixuran.origami.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TagDAO extends JpaRepository<Tag, Integer> {
    List<Tag> findAllByTagFolderId(int tagFolderId);

    List<Tag> findAllByTagDiagramId(int tagDiagramId);

    @Transactional
    void deleteAllByTagFolderId(int id);

    @Transactional
    void deleteAllByTagDiagramId(int id);
}

package com.shixuran.origami.dao;

import com.shixuran.origami.pojo.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ImageDAO extends JpaRepository<Image, Integer> {
    List<Image> findAllByImageFolderId(int imageFolderId);

    List<Image> findAllByImageDiagramId(int imageDiagramId);

    @Transactional
    void deleteAllByImageFolderId(int id);

    @Transactional
    void deleteAllByImageDiagramId(int id);
}

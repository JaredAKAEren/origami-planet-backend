package com.shixuran.origami.dao;

import com.shixuran.origami.pojo.Folder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderDAO extends JpaRepository<Folder, Integer> {
    Folder findById(int id);

    Page<Folder> findAllByFolderUserId(Pageable pageable, int folderUserId);

    Page<Folder> findAllByFolderState(Pageable pageable, int state);
}

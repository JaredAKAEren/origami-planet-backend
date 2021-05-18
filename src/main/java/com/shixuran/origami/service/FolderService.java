package com.shixuran.origami.service;

import com.shixuran.origami.dao.*;
import com.shixuran.origami.pojo.Folder;
import com.shixuran.origami.pojo.Image;
import com.shixuran.origami.pojo.Tag;
import com.shixuran.origami.response.FolderResponse;
import com.shixuran.origami.utils.FolderUtils;
import com.shixuran.origami.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FolderService {
    @Autowired
    FolderDAO folderDao;

    @Autowired
    TagDAO tagDAO;

    @Autowired
    ImageDAO imageDao;

    @Autowired
    ProfileDAO profileDAO;

    @Autowired
    UserDAO userDAO;

    public boolean addOrUpdate(FolderUtils folderUtils) {
        Folder folder = folderUtils.getFolder();
        folder.setFolderDate(new Date());
        Folder folderAfterSave = folderDao.save(folder);
        List<String> tagList = folderUtils.getTags();
        List<String> imageList = folderUtils.getImages();
        tagDAO.deleteAllByTagFolderId(folder.getId());
        imageDao.deleteAllByImageFolderId(folder.getId());
        for (String tagName : tagList) {
            Tag tag = new Tag(0, tagName, new Date(), folderAfterSave.getId(),0);
            tagDAO.save(tag);
        }
        for (String imageUrl : imageList) {
            Image image = new Image(0, imageUrl, new Date(), folderAfterSave.getId(),0);
            imageDao.save(image);
        }
        return true;
    }

    public MyPage getFolderList(int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");

        Page<Folder> folderPage = folderDao.findAll(PageRequest.of(page, size, sort));
        return getMyPage(folderPage);
    }

    public Page<Folder> userFolderList(int page, int size, int id) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return folderDao.findAllByFolderUserId(PageRequest.of(page - 1, size, sort), id);
    }

    private MyPage getMyPage(Page<Folder> folderPage) {
        List<Folder> folderList = folderPage.getContent();

        MyPage myPage = new MyPage();
        myPage.setFirst(folderPage.isFirst());
        myPage.setLast(folderPage.isFirst());
        myPage.setTotalPages(folderPage.getTotalPages());

        List<FolderResponse> folderResponseList = new ArrayList<>();
        for (Folder folder : folderList) {
            FolderResponse folderResponse = new FolderResponse();
            folderResponse.setProfile(profileDAO.findByProfileUserId(folder.getFolderUserId()));
            folderResponse.setFolder(folder);
            folderResponse.setTag(tagDAO.findAllByTagFolderId(folder.getId()));
            folderResponse.setImage(imageDao.findAllByImageFolderId(folder.getId()));
            folderResponse.setUsername(userDAO.findById(folder.getFolderUserId()).getUsername());
            folderResponseList.add(folderResponse);
        }

        myPage.setContent(folderResponseList);
        return myPage;
    }

    public MyPage getFolderListWithState(int page, int size, int state) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");

        Page<Folder> folderPage = folderDao.findAllByFolderState(PageRequest.of(page, size, sort), state);
        return getMyPage(folderPage);
    }

    public FolderResponse getOneFolder(int id) {
        FolderResponse folderResponse = new FolderResponse();
        folderResponse.setFolder(folderDao.findById(id));
        folderResponse.setProfile(profileDAO.findByProfileUserId(folderResponse.getFolder().getFolderUserId()));
        folderResponse.setTag(tagDAO.findAllByTagFolderId(id));
        folderResponse.setImage(imageDao.findAllByImageFolderId(id));
        return folderResponse;
    }

    public boolean changeFolderState(Folder folder) {
        try {
            folderDao.save(folder);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteFolder(int id) {
        try {
            imageDao.deleteAllByImageFolderId(id);
            tagDAO.deleteAllByTagFolderId(id);
            folderDao.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

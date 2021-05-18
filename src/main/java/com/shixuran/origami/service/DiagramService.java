package com.shixuran.origami.service;

import com.shixuran.origami.dao.*;
import com.shixuran.origami.pojo.Diagram;
import com.shixuran.origami.pojo.Image;
import com.shixuran.origami.pojo.Tag;
import com.shixuran.origami.response.DiagramResponse;
import com.shixuran.origami.utils.DiagramUtils;
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
public class DiagramService {
    @Autowired
    DiagramDAO diagramDAO;

    @Autowired
    TagDAO tagDAO;

    @Autowired
    ImageDAO imageDao;

    @Autowired
    ProfileDAO profileDAO;

    @Autowired
    UserDAO userDAO;

    public boolean addOrUpdate(DiagramUtils diagramUtils) {
        Diagram diagram = diagramUtils.getDiagram();
        diagram.setDiagramDate(new Date());
        Diagram diagramAfterSave = diagramDAO.save(diagram);
        List<String> tagList = diagramUtils.getTags();
        List<String> imageList = diagramUtils.getImages();
        for (String tagName : tagList) {
            Tag tag = new Tag(0, tagName, new Date(), 0, diagramAfterSave.getId());
            tagDAO.save(tag);
        }
        for (String imageUrl : imageList) {
            Image image = new Image(0, imageUrl, new Date(), 0, diagramAfterSave.getId());
            imageDao.save(image);
        }
        return true;
    }

    public MyPage getDiagramList(int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");

        Page<Diagram> diagramPage = diagramDAO.findAll(PageRequest.of(page, size, sort));
        return getMyPage(diagramPage);
    }

    private MyPage getMyPage(Page<Diagram> diagramPage) {
        List<Diagram> diagramList = diagramPage.getContent();

        MyPage myPage = new MyPage();
        myPage.setFirst(diagramPage.isFirst());
        myPage.setLast(diagramPage.isFirst());
        myPage.setTotalPages(diagramPage.getTotalPages());

        List<DiagramResponse> diagramResponseList = new ArrayList<>();
        for (Diagram diagram : diagramList) {
            DiagramResponse diagramResponse = new DiagramResponse();
            diagramResponse.setProfile(profileDAO.findByProfileUserId(diagram.getDiagramUserId()));
            diagramResponse.setDiagram(diagram);
            diagramResponse.setTag(tagDAO.findAllByTagDiagramId(diagram.getId()));
            diagramResponse.setImage(imageDao.findAllByImageDiagramId(diagram.getId()));
            diagramResponse.setUsername(userDAO.findById(diagram.getDiagramUserId()).getUsername());
            diagramResponseList.add(diagramResponse);
        }

        myPage.setContent(diagramResponseList);
        return myPage;
    }

    public MyPage getDiagramListWithState(int page, int size, int state) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");

        Page<Diagram> diagramPage = diagramDAO.findAllByDiagramState(PageRequest.of(page, size, sort), state);
        return getMyPage(diagramPage);
    }

    public DiagramResponse getOneDiagram(int id) {
        DiagramResponse diagramResponse = new DiagramResponse();
        diagramResponse.setDiagram(diagramDAO.findById(id));
        diagramResponse.setProfile(profileDAO.findByProfileUserId(diagramResponse.getDiagram().getDiagramUserId()));
        diagramResponse.setTag(tagDAO.findAllByTagDiagramId(id));
        diagramResponse.setImage(imageDao.findAllByImageDiagramId(id));
        diagramResponse.setUsername(userDAO.findById(diagramResponse.getDiagram().getDiagramUserId()).getUsername());
        return diagramResponse;
    }

    public boolean changeDiagramState(Diagram diagram) {
        try {
            diagramDAO.save(diagram);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteDiagram(int id) {
        try {
            imageDao.deleteAllByImageDiagramId(id);
            tagDAO.deleteAllByTagDiagramId(id);
            diagramDAO.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

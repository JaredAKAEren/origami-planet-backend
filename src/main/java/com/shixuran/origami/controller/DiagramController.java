package com.shixuran.origami.controller;

import com.shixuran.origami.pojo.Diagram;
import com.shixuran.origami.result.Result;
import com.shixuran.origami.result.ResultFactory;
import com.shixuran.origami.service.DiagramService;
import com.shixuran.origami.utils.DiagramUtils;
import com.shixuran.origami.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DiagramController {
    @Autowired
    DiagramService diagramService;

    @PostMapping("api/diagram")
    public Result saveDiagram(@RequestBody DiagramUtils diagramUtils) {
        boolean isSave = diagramService.addOrUpdate(diagramUtils);
        if (isSave) {
            return ResultFactory.buildSuccessResult("投稿成功");
        } else {
            return ResultFactory.buildFailResult("投稿失败");
        }
    }

    @GetMapping("api/diagram/{page}/{size}")
    public Result getDiagramList(@PathVariable("page") int page, @PathVariable("size") int size) {
        MyPage diagramPage = diagramService.getDiagramList(page - 1, size);
        return ResultFactory.buildSuccessResult(diagramPage);
    }

    @GetMapping("api/diagram/{page}/{size}/{state}")
    public Result getDiagramListWithState(@PathVariable("page") int page, @PathVariable("size") int size, @PathVariable("state") int state) {
        MyPage diagramPage = diagramService.getDiagramListWithState(page - 1, size, state);
        return ResultFactory.buildSuccessResult(diagramPage);
    }

    @GetMapping("api/diagram/{id}")
    public Result getOneDiagram(@PathVariable("id") int id) {
        return ResultFactory.buildSuccessResult(diagramService.getOneDiagram(id));
    }

    @PostMapping("api/diagram/state")
    public Result changeDiagramState(@RequestBody Diagram diagram) {
        return diagramService.changeDiagramState(diagram) ? ResultFactory.buildSuccessResult("更新状态成功") : ResultFactory.buildFailResult("更新状态失败");
    }

    @GetMapping("api/diagram/delete/{id}")
    public Result deleteDiagram(@PathVariable("id") int id) {
        return diagramService.deleteDiagram(id) ? ResultFactory.buildSuccessResult("删除动态成功") : ResultFactory.buildFailResult("删除动态失败");
    }
}

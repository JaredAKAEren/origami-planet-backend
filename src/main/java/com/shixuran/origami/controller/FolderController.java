package com.shixuran.origami.controller;

import com.shixuran.origami.pojo.Folder;
import com.shixuran.origami.result.Result;
import com.shixuran.origami.result.ResultFactory;
import com.shixuran.origami.service.FolderService;
import com.shixuran.origami.utils.FolderUtils;
import com.shixuran.origami.utils.MyPage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FolderController {
    @Autowired
    FolderService folderService;

    @PostMapping("api/folder")
    public Result saveFolder(@RequestBody FolderUtils folderUtils) {

        boolean isSave = folderService.addOrUpdate(folderUtils);
        if (isSave) {
            return ResultFactory.buildSuccessResult("投稿成功");
        } else {
            return ResultFactory.buildFailResult("投稿失败");
        }
    }

    @GetMapping("api/folder/{page}/{size}")
    public Result getFolderList(@PathVariable("page") int page, @PathVariable("size") int size) {
        MyPage folderPage = folderService.getFolderList(page - 1, size);
        return ResultFactory.buildSuccessResult(folderPage);
    }

    @GetMapping("api/folder/{page}/{size}/{state}")
    public Result getFolderListWithState(@PathVariable("page") int page, @PathVariable("size") int size, @PathVariable("state") int state) {
        MyPage folderPage = folderService.getFolderListWithState(page - 1, size, state);
        return ResultFactory.buildSuccessResult(folderPage);
    }

    @GetMapping("api/folder/{id}")
    public Result getOneFolder(@PathVariable("id") int id) {
        return ResultFactory.buildSuccessResult(folderService.getOneFolder(id));
    }

    @GetMapping("/api/user/folder/{page}/{size}/{userId}")
    @ApiOperation(value = "按用户id查文章")
    public Result userListArticles(@PathVariable("page") int page, @PathVariable("size") int size, @PathVariable("userId") int userId) {
        return ResultFactory.buildSuccessResult(folderService.userFolderList(page, size, userId));
    }

    @PostMapping("api/folder/state")
    public Result changeFolderState(@RequestBody Folder folder) {
        return folderService.changeFolderState(folder) ? ResultFactory.buildSuccessResult("更新状态成功") : ResultFactory.buildFailResult("更新状态失败");
    }

    @GetMapping("api/folder/delete/{id}")
    public Result deleteFolder(@PathVariable("id") int id) {
        return folderService.deleteFolder(id) ? ResultFactory.buildSuccessResult("删除动态成功") : ResultFactory.buildFailResult("删除动态失败");
    }
}

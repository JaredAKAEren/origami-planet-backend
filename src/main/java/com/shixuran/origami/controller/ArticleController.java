package com.shixuran.origami.controller;

import com.shixuran.origami.pojo.Article;
import com.shixuran.origami.result.Result;
import com.shixuran.origami.result.ResultFactory;
import com.shixuran.origami.service.ArticleService;
import com.shixuran.origami.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@RestController
@Api(value = "文章")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("api/article")
    public Result saveArticle(@RequestBody @Valid Article article) {
        int afterSaveId = articleService.addOrUpdate(article);
        return ResultFactory.buildSuccessResult(afterSaveId);
    }

    @PostMapping("api/covers")
    public Result coversUpload(@RequestParam("file") MultipartFile file) throws Exception {

        String folder = "D:/workspace/img";
        File imageFolder = new File(folder);
        File fileReader = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4));
        if (!fileReader.getParentFile().exists()) {
            if (!fileReader.getParentFile().mkdirs()) {
                return ResultFactory.buildFailResult("出错了");
            }
        }
        try {
            file.transferTo(fileReader);
            String imgURL = "http://localhost:8443/api/file/" + fileReader.getName();
            return ResultFactory.buildSuccessResult(imgURL);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("上传失败");
        }
    }

    @GetMapping("/api/article/{page}/{size}")
    public Result listArticles(@PathVariable("size") int size, @PathVariable("page") int page) {
        return ResultFactory.buildSuccessResult(articleService.list(page - 1, size));
    }

    @GetMapping("/api/article/{page}/{size}/{state}")
    public Result listArticlesWithState(@PathVariable("size") int size, @PathVariable("page") int page, @PathVariable("state") int state) {
        return ResultFactory.buildSuccessResult(articleService.listWithState(page - 1, size, state));
    }

    @GetMapping("/api/article/{id}")
    public Result getOneArticle(@PathVariable("id") int id) {
        return ResultFactory.buildSuccessResult(articleService.findById(id));
    }

    @GetMapping("/api/user/article/{page}/{size}/{userId}")
    @ApiOperation(value = "按用户id查文章")
    public Result userListArticles(@PathVariable("page") int page, @PathVariable("size") int size, @PathVariable("userId") int userId) {
        return ResultFactory.buildSuccessResult(articleService.userArticleList(page, size, userId));
    }

    @PostMapping("api/article/state")
    public Result changeFolderState(@RequestBody Article article) {
        return articleService.changeArticleState(article) ? ResultFactory.buildSuccessResult("更新状态成功") : ResultFactory.buildFailResult("更新状态失败");
    }

    @GetMapping("/api/article/delete/{id}")
    public Result deleteByArticleId(@PathVariable("id") int id) {
        boolean isSuccess = articleService.delete(id);
        if (isSuccess) {
            return ResultFactory.buildSuccessResult("删除成功");
        } else {
            return ResultFactory.buildFailResult("删除失败");
        }
    }
}

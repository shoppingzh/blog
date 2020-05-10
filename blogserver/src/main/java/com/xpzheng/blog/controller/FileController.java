/**
 * 
 */
package com.xpzheng.blog.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xpzheng.blog.constant.FileConsts;
import com.xpzheng.blog.controller.data.AjaxResult;
import com.xpzheng.blog.dto.FileDTO;
import com.xpzheng.blog.service.FileService;
import com.xpzheng.blog.util.FilePathUtils;

/**
 * @author xpzheng
 *
 */
@RestController
@RequestMapping("file")
public class FileController extends BaseController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public AjaxResult upload(@RequestParam(name = "file") MultipartFile file, HttpServletRequest req) {
        String path = FilePathUtils.joinPath(FileConsts.DEFAULT_LOCAL_FILE_PATH, FilePathUtils.getDatePath());
        File pathFile = new File(req.getServletContext().getRealPath(""), path);
        String randomFileName = UUID.randomUUID().toString() + "."
                + FilenameUtils.getExtension(file.getOriginalFilename());
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        try {
            File destFile = new File(pathFile, randomFileName);
            file.transferTo(destFile);
            if (!destFile.exists()) {
                return failed("文件保存失败");
            }
            FileDTO fileDTO = new FileDTO();
            fileDTO.setFilename(file.getOriginalFilename());
            fileDTO.setFilesize(file.getSize());
            fileDTO.setFrom(FileConsts.FILE_FROM_LOCAL);
            fileDTO.setFilepath(FilePathUtils.joinPath(path, randomFileName));
            fileDTO.setContentType(file.getContentType());
            String result = fileService.add(fileDTO);
            return result != null ? success(result) : failed();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return failed();
    }

    @GetMapping("{id}")
    public AjaxResult download(@PathVariable(name = "id") String id, HttpServletRequest req, HttpServletResponse resp) {
        FileDTO fileDTO = fileService.get(id);
        if (fileDTO == null) {
            return failed("文件不存在");
        }
        // FIXME 以后使用七牛云/又拍云存储时，将请求重定向到对应的访问地址
        if (fileDTO.getFrom() != FileConsts.FILE_FROM_LOCAL) {
            return failed("暂不支持下载");
        }
        File file = new File(req.getServletContext().getRealPath(""), fileDTO.getFilepath());
        if (!file.exists()) {
            return failed("文件不存在");
        }
//        resp.addHeader("Content-Disposition", "attachment;filename=" + fileDTO.getFilename());
        try {
            IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

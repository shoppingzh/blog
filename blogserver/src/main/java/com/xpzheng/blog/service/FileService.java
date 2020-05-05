/**
 * 
 */
package com.xpzheng.blog.service;

import com.xpzheng.blog.constant.FileConsts;
import com.xpzheng.blog.dto.FileDTO;
import com.xpzheng.blog.mapper.FileEntitiyMapper;
import com.xpzheng.blog.model.FileEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xpzheng
 *
 */
@Service
public class FileService {

    @Autowired
    private FileEntitiyMapper fileMapper;

    /**
     * 添加文件
     * @param fileDTO
     * @return
     */
    public String add(FileDTO fileDTO) {
        FileEntity file = new FileEntity();
        file.setFilefrom(fileDTO.getFrom());
        file.setFilename(fileDTO.getFilename());
        file.setFilepath(fileDTO.getFilepath());
        file.setFilesize(fileDTO.getFilesize());
        file.setContentType(fileDTO.getContentType());
        fileMapper.insert(file);
        return file.getId();
    }

    /**
     * 删除文件
     * @param id    文件id
     * @return
     */
    public boolean delete(String id) {
        return fileMapper.deleteById(id) > 0;
    }

    /**
     * 获取指定文件
     * @param id
     * @return
     */
    public FileDTO get(String id) {
        FileEntity file = fileMapper.selectById(id);
        return fileDTO(file);
    }

    private FileDTO fileDTO(FileEntity file) {
        if (file == null) {
            return null;
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setId(file.getId());
        fileDTO.setFilename(file.getFilename());
        fileDTO.setFilepath(file.getFilepath());
        fileDTO.setFilesize(file.getFilesize());
        fileDTO.setContentType(file.getContentType());
        switch (file.getFilefrom()) {
        case FileConsts.FILE_FROM_LOCAL:
            fileDTO.setRefPath(file.getFilepath());
            break;
        default:
            break;
        }
        if (file.getContentType() != null) {
            if (file.getContentType().matches("^image/.+$")) {
                fileDTO.setMediaType(FileConsts.MEDIA_TYPE_IMAGE);
            } else if (file.getContentType().matches("^video/.+$")) {
                fileDTO.setMediaType(FileConsts.MEDIA_TYPE_VIDEO);
            } else if (file.getContentType().matches("^audio/.+$")) {
                fileDTO.setMediaType(FileConsts.MEDIA_TYPE_AUDIO);
            }
        }
        return fileDTO;
    }

}

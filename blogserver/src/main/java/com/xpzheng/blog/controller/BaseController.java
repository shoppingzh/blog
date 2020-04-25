/**
 * 
 */
package com.xpzheng.blog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.xpzheng.blog.controller.util.AjaxResult;

/**
 * @author xpzheng
 *
 */
public abstract class BaseController {

    /**
     * Ajax success
     * @return
     */
    public AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * Ajax success with a view object
     * @param vo view object
     * @return
     */
    public AjaxResult success(Object vo) {
        return AjaxResult.success(vo);
    }

    /**
     * Ajax failed with a message
     * @param msg
     * @return
     */
    public AjaxResult failed(String msg) {
        return AjaxResult.failed(msg);
    }

}

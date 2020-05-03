/**
 * 
 */
package com.xpzheng.blog.controller;

import com.xpzheng.blog.controller.util.AjaxResult;

/**
 * @author xpzheng
 *
 */
public abstract class BaseController {
    
    protected static final String DEFAULT_PAGE = "1";
    protected static final String DEFAULT_PAGESIZE = "-1";

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
    
    public AjaxResult failed() {
        return AjaxResult.failed();
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

package com.sm.cn.common.http.entity;

import java.util.HashMap;

public class ResponseBean extends HashMap<String,Object> {

    /**
     * 定义全局变量，方便修改
     */
    public static final String STATUS = "status";
    public static final String MSG = "msg";
    public static final String DATA = "data";
    public static final String TOTAL = "total";

    public ResponseBean(){}

    /**
     * 有参的构造函数
     * @param myStatus
     */
    public ResponseBean(MyStatus myStatus){
        super.put(STATUS,myStatus.getStatus());
        super.put(MSG,myStatus.getMsg());
    }
    /**
     * 删除，添加，修改，操作成功时的返回函数
     * @return
     */
    public static ResponseBean success(){
        return new ResponseBean(MyStatus.OK);
    }
    /**
     * 方法的重载，当需要改变操作成功时的状态码时可以自定义
     * @param myStatus
     * @return
     */
    public static ResponseBean success(MyStatus myStatus){
        return new ResponseBean(myStatus);
    }
    /**
     * 删除，添加，修改，操作失败时的返回函数
     * @return
     */
    public static ResponseBean error(){
        return new ResponseBean(MyStatus.ERROR);
    }
    /**
     * 操作失败时，可以修改返回的转态码
     * @param myStatus
     * @return
     */
    public static ResponseBean error(MyStatus myStatus){
        return new ResponseBean(myStatus);
    }
    /**
     * 查询时需要返回数据和状态
     * @param data
     * @return
     */
    public static ResponseBean success(Object data){
        ResponseBean success = success();
        success.put(DATA,data);
        return success;
    }

    /**
     * 分页查询时返回数据、状态和数据库表的记录数
     * @param data
     * @param total
     * @return
     */
    public  static ResponseBean success(Object data,long total){
        ResponseBean success = success(data);
        success.put(TOTAL,total);
        return success;
    }

}

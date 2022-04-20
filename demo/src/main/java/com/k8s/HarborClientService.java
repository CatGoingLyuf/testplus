package com.k8s;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建日期：2021/12/24
 * 创建作者：hupenghao
 * 文件名称：
 * 版本：1.0
 * 功能：
 * 最后修改时间：
 * 修改记录:
 */
public interface HarborClientService {


    /**
     * get请求封装
     * @param request
     * @param harborIpAddr harbor ip端口
     * @return
     */
    String get(String harborIpAddr, HttpServletRequest request) throws  Exception;

    /**
     * post请求封装
     * @param request
     * @param harborIpAddr harbor ip端口
     * @return
     */
    String post(String harborIpAddr, HttpServletRequest request, String params) throws  Exception;

    /**
     * put请求封装
     * @param request
     * @param harborIpAddr harbor ip端口
     * @return
     */
    String put(String harborIpAddr, HttpServletRequest request, String params) throws  Exception;

    /**
     * delete请求封装
     * @param request
     * @param harborIpAddr harbor ip端口
     * @return
     */
    String delete(String harborIpAddr, HttpServletRequest request) throws  Exception;
}

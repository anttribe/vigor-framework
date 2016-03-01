/*
 * 文  件   名: IResourceService.java
 * 版         本 : vigor-defensor-service © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.service;

import java.util.List;

import org.anttribe.vigor.defensor.domain.Resource;
import org.anttribe.vigor.defensor.domain.User;
import org.anttribe.vigor.infra.common.service.IService;

/**
 * 资源服务层接口
 * 
 * @author zhaoyong
 * @version 2016年2月23日
 */
public interface IResourceService extends IService<Resource>
{
    /**
     * 根据用户和用户身份获取用户对应的权限
     * 
     * @param user
     *            User
     * @param identify
     *            String
     * @return List<Resource>
     */
    List<Resource> listUserResources(User user, String identify);
    
}
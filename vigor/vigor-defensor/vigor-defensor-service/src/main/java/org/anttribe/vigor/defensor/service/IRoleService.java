/*
 * 文  件   名: IRoleService.java
 * 版         本 : vigor-defensor-service © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.service;

import java.util.List;

import org.anttribe.vigor.defensor.domain.Role;
import org.anttribe.vigor.defensor.domain.User;
import org.anttribe.vigor.infra.common.service.IService;

/**
 * @author zhaoyong
 * @version 2016年2月23日
 */
public interface IRoleService extends IService<Role>
{
    /**
     * 列表用户角色
     * 
     * @param user
     * @param identity
     * @return List<Role>
     */
    List<Role> listUserRoles(User user, String identity);
}
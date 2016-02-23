/*
 * 文  件   名: UserServiceImpl.java
 * 版         本 : vigor-defensor-server © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年2月23日
 */
package org.anttribe.vigor.defensor.service.impl;

import org.anttribe.vigor.defensor.dao.IUserDao;
import org.anttribe.vigor.defensor.domain.User;
import org.anttribe.vigor.defensor.service.IUserService;
import org.anttribe.vigor.infra.common.service.AbstractServiceImpl;

/**
 * @author zhaoyong
 * @version 2016年2月23日
 */
public class UserServiceImpl extends AbstractServiceImpl<IUserDao, User>implements IUserService
{
}
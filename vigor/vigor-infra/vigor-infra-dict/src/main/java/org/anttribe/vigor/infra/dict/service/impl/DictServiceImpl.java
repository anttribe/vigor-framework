/*
 * 文  件   名: DictServiceImpl.java
 * 版         本 : vigor-infra-dict © 2016 Anttribe. All rights reserved.
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2016年4月9日
 */
package org.anttribe.vigor.infra.dict.service.impl;

import org.anttribe.vigor.infra.common.service.AbstractServiceImpl;
import org.anttribe.vigor.infra.dict.dao.IDictDao;
import org.anttribe.vigor.infra.dict.domain.Dict;
import org.anttribe.vigor.infra.dict.service.IDictService;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyong
 * @version 2016年4月9日
 */
@Service
public class DictServiceImpl extends AbstractServiceImpl<IDictDao, Dict> implements IDictService
{
}
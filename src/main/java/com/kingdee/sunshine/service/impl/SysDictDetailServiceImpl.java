package com.kingdee.sunshine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kingdee.sunshine.common.exception.BusinessException;
import com.kingdee.sunshine.entity.SysDictDetailEntity;
import com.kingdee.sunshine.entity.SysDictEntity;
import com.kingdee.sunshine.mapper.SysDictDetailMapper;
import com.kingdee.sunshine.mapper.SysDictMapper;
import com.kingdee.sunshine.service.SysDictDetailService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * 数据字典 服务类
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Service("sysDictDetailService")
public class SysDictDetailServiceImpl extends ServiceImpl<SysDictDetailMapper, SysDictDetailEntity> implements SysDictDetailService {
    @Resource
    private SysDictDetailMapper sysDictDetailMapper;
    @Resource
    private SysDictMapper sysDictMapper;


    @Override
    public IPage<SysDictDetailEntity> listByPage(Page<SysDictDetailEntity> page, String dictId) {

        SysDictEntity sysDictEntity = sysDictMapper.selectById(dictId);
        if (sysDictEntity == null) {
            throw new BusinessException("获取字典数据失败!");
        }

        LambdaQueryWrapper<SysDictDetailEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysDictDetailEntity::getDictId, dictId);
        wrapper.orderByAsc(SysDictDetailEntity::getSort);
        IPage<SysDictDetailEntity> result = sysDictDetailMapper.selectPage(page, wrapper);
        if (!CollectionUtils.isEmpty(result.getRecords())) {
            result.getRecords().parallelStream().forEach(entity -> entity.setDictName(sysDictEntity.getName()));
        }
        return result;
    }
}
package com.kingdee.sunshine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kingdee.sunshine.entity.SysLog;
import com.kingdee.sunshine.mapper.SysLogMapper;
import com.kingdee.sunshine.service.LogService;
import org.springframework.stereotype.Service;

/**
 * 系统日志
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
public class LogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements LogService {
}

package com.beiming.juc.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beiming.juc.mybatisplus.domain.entity.MqConsumerLog;
import com.beiming.juc.mybatisplus.mapper.MqConsumerLogMapper;
import com.beiming.juc.mybatisplus.service.IMqConsumerLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消费端消费日志表 服务实现类
 * </p>
 *
 * @author jinguomin
 * @since 2020-01-14
 */
@Service
public class MqConsumerLogServiceImpl extends
    ServiceImpl<MqConsumerLogMapper, MqConsumerLog> implements IMqConsumerLogService {

}

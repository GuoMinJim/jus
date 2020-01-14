package com.beiming.juc.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beiming.juc.mybatisplus.domain.entity.Car;
import com.beiming.juc.mybatisplus.mapper.CarMapper;
import com.beiming.juc.mybatisplus.service.ICarService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jinguomin
 * @since 2020-01-14
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

}

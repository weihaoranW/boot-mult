package com.wei.service.impl;

import com.wei.entity.Test;
import com.wei.mapper.TestMapper;
import com.wei.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weihaoran
 * @since 2019-05-31
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}

package com.wei.service.impl;

import com.wei.entity.Msg;
import com.wei.mapper.MsgMapper;
import com.wei.service.MsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weihaoran
 * @since 2019-06-08
 */
@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg> implements MsgService {

}

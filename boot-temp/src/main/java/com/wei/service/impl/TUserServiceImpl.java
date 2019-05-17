package com.wei.service.impl;

import com.wei.entity.TUser;
import com.wei.mapper.TUserMapper;
import com.wei.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author handsomeCcg
 * @since 2018-12-11
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}

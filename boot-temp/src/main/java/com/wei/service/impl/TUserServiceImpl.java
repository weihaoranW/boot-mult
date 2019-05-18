package com.wei.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.wei.entity.TUser;
import com.wei.mapper.TUserMapper;
import com.wei.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

@Override
public List<TUser> listTest(Map<String,Object > p) {
 return this.baseMapper.listTest(p);
}

@Override
public List<TUser> testWrapper(Wrapper<TUser> w) {
 return this.baseMapper.testWrapper(w);
}

}

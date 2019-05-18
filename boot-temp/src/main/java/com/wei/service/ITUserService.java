package com.wei.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.wei.entity.TUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author handsomeCcg
 * @since 2018-12-11
 */
public interface ITUserService extends IService<TUser> {
public List<TUser> listTest(Map<String,Object> p);
public List<TUser> testWrapper(Wrapper<TUser> w);
}

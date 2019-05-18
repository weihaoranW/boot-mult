package com.wei.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.wei.entity.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import sun.util.resources.cldr.mas.CalendarData_mas_KE;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author handsomeCcg
 * @since 2018-12-11
 */
public interface TUserMapper extends BaseMapper<TUser> {
public List<TUser> listTest(Map<String, Object> p);

List<TUser> testWrapper(@Param("ew") Wrapper<TUser> w);
}

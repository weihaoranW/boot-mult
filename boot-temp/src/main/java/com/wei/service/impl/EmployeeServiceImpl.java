package com.wei.service.impl;

import com.wei.entity.Employee;
import com.wei.mapper.EmployeeMapper;
import com.wei.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weihaoran
 * @since 2019-05-19
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}

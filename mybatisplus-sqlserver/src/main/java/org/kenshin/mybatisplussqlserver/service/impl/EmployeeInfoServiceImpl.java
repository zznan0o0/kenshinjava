package org.kenshin.mybatisplussqlserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kenshin.mybatisplussqlserver.domain.EmployeeInfo;
import org.kenshin.mybatisplussqlserver.service.EmployeeInfoService;
import org.kenshin.mybatisplussqlserver.mapper.EmployeeInfoMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class EmployeeInfoServiceImpl extends ServiceImpl<EmployeeInfoMapper, EmployeeInfo>
    implements EmployeeInfoService{

}





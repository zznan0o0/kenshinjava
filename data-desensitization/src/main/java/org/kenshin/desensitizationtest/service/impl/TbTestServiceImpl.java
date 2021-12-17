package org.kenshin.desensitizationtest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kenshin.desensitizationtest.domain.TbTest;
import org.kenshin.desensitizationtest.service.TbTestService;
import org.kenshin.desensitizationtest.mapper.TbTestMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TbTestServiceImpl extends ServiceImpl<TbTestMapper, TbTest>
    implements TbTestService{

}





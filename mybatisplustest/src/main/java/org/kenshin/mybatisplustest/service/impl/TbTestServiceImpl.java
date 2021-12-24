package org.kenshin.mybatisplustest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kenshin.mybatisplustest.domain.TbTest;
import org.kenshin.mybatisplustest.service.TbTestService;
import org.kenshin.mybatisplustest.mapper.TbTestMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TbTestServiceImpl extends ServiceImpl<TbTestMapper, TbTest>
    implements TbTestService{

}





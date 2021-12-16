package org.kenshin.mybatisplustest.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kenshin.mybatisplustest.domain.TbLabel;
import org.kenshin.mybatisplustest.service.TbLabelService;
import org.kenshin.mybatisplustest.mapper.TbLabelMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TbLabelServiceImpl extends ServiceImpl<TbLabelMapper, TbLabel>
    implements TbLabelService{
    @DS("slave")
    public TbLabel getLabel(){
        return this.getById(1);
    }
}





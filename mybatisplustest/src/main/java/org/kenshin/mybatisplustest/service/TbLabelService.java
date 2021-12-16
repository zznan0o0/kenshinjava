package org.kenshin.mybatisplustest.service;

import org.kenshin.mybatisplustest.domain.TbLabel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface TbLabelService extends IService<TbLabel> {
    TbLabel getLabel();
}

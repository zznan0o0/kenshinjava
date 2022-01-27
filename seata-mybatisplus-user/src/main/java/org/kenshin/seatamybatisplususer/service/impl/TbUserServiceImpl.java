package org.kenshin.seatamybatisplususer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kenshin.seatamybatisplususer.domain.TbUser;
import org.kenshin.seatamybatisplususer.service.TbUserService;
import org.kenshin.seatamybatisplususer.mapper.TbUserMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser>
    implements TbUserService{

}





package org.kenshin.oauyh2service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("UserDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //返回oauth2的用户
        return new org.springframework.security.core.userdetails.User(
                "root",
                "root",
                AuthorityUtils.createAuthorityList("root"));
    }
}

package com.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.mapper.TUserMapper;
import com.security.pojo.dao.TUser;
import com.security.service.TPermissionService;
import com.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
   * @description: 
   * @author recheard
   * @date 2021/4/2510:55
   */
@Service
public class UserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements UserService, UserDetailsService {

    @Autowired
    private TPermissionService permissionService;


    /**
     * 自定义UserDetailsService.loadUserByUsername实现
     * @param s 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TUser user = this.getById(Integer.valueOf(s));
        if(user == null) {
            throw new UsernameNotFoundException("用户ID" + s + "未找到");
        }
        //获取角色列表
        List<Map> list = permissionService.selectPermissionAndRoleById(user.getId());
        Set<String> permissionSet = new HashSet<>();
        list.forEach(l ->{
            //因为userDetail在注入角色和权限的时候authorities方法会覆盖roles方法中的值，所以统一使用authorities方法注入
            //permissionSet.add("ROLE_" + l.get("roleId").toString());
            permissionSet.add(l.get("code").toString());
            }
        );
        //这里将user转为json，将整体user存入userDetails
        user.setPassword(null);
        String principal = JSON.toJSONString(user);
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                //注入用户ID
                .withUsername(principal)
                //注入加密的密码
                .password(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()))
                //注入资源访问权限
                .authorities(permissionSet.toArray(new String[permissionSet.size()])).build();
        return userDetails;
    }

}

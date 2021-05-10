package com.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.security.mapper.TPermissionMapper;
import com.security.pojo.dao.TPermission;
import com.security.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
   * @description: 
   * @author recheard
   * @date 2021/4/2510:55
   */
@Service
public class TPermissionServiceImpl extends ServiceImpl<TPermissionMapper, TPermission> implements TPermissionService{

    @Autowired
    private TPermissionMapper permissionMapper;

    @Override
    public List<TPermission> selectPermissionByUserId(Long userId) {
        return permissionMapper.selectPermissionByUserId(userId);
    }

    @Override
    public List<Map> selectPermissionAndRoleById(Long userId) {
        return permissionMapper.selectPermissionAndRoleById(userId);
    }
}

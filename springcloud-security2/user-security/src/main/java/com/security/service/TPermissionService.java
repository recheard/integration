package com.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.security.pojo.dao.TPermission;

import java.util.List;
import java.util.Map;

/**
   * @description: 
   * @author recheard
   * @date 2021/4/2510:55
   */
public interface TPermissionService extends IService<TPermission>{


    /**
     * 查询用户权限列表
     * @param userId 用户ID
     * @return
     */
    List<TPermission> selectPermissionByUserId(Long userId);


    /**
     * 根据用户ID查询用户的角色和ID列表
     * @param userId
     * @return
     */
    List<Map> selectPermissionAndRoleById(Long userId);
}

package com.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.pojo.dao.TPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**  
   * @description: 
   * @author recheard
   * @date 2021/4/2510:55
   */
@Mapper
public interface TPermissionMapper extends BaseMapper<TPermission> {

    /**
     * 查询用户权限列表
     * @param userId 用户ID
     * @return
     */
    List<TPermission> selectPermissionByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询用户的角色和ID列表
     * @param userId
     * @return
     */
    List<Map> selectPermissionAndRoleById(@Param("userId") Long userId);


}
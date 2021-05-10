package com.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.security.pojo.dao.TUser;
import org.apache.ibatis.annotations.Mapper;

/**  
   * @description: 
   * @author recheard
   * @date 2021/4/2510:55
   */
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {
}
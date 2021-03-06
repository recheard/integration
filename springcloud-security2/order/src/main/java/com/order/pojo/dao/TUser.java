package com.order.pojo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**  
   * @description: 
   * @author recheard
   * @date 2021/4/2510:55
   */
@ApiModel(value="com-security-pojo-dao-TUser")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user")
public class TUser implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="用户id")
    private Long id;

    @TableField(value = "username")
    @ApiModelProperty(value="")
    private String username;

    @TableField(value = "password")
    @ApiModelProperty(value="")
    private String password;

    /**
     * 用户姓名
     */
    @TableField(value = "fullname")
    @ApiModelProperty(value="用户姓名")
    private String fullname;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    @ApiModelProperty(value="手机号")
    private String mobile;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_FULLNAME = "fullname";

    public static final String COL_MOBILE = "mobile";
}
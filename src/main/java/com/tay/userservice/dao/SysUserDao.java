package com.tay.userservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tay.userservice.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 *
 * @author huohuo ${email}
 * @since 1.0.0 2023-05-29
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	
}
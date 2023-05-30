package com.tay.userservice.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tay.userservice.dto.SysUserDTO;
import com.tay.userservice.entity.SysUserEntity;
import java.util.List;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author tay
 * @since 2022-05-15
 */
public interface SysUserService extends IService<SysUserEntity> {
    /**
     * 新增数据
     *
     * @param sysUserDTO 数据
     */
    void save(SysUserDTO sysUserDTO);

    /**
     * 根据id删除数据
     *
     * @param id 标识
     */
    void deleteById(Long id);

    /**
     * 根据id更新数据
     *
     * @param sysUserDTO 更新的数据
     */
    void updateById(SysUserDTO sysUserDTO);

    /**
     * 查询列表
     *
     * @param sysUserDTO 查询参数
     * @return 结果
     */
    List<SysUserDTO> list(SysUserDTO sysUserDTO);

    /**
     * 根据id获取数据
     *
     * @param id 标识
     * @return 结果
     */
    SysUserDTO getById(Long id);


    /**
     * 分页查询
     * @param sysUserDTO 查询参数
     * @return
     */
    IPage<SysUserDTO> page(SysUserDTO sysUserDTO);

}
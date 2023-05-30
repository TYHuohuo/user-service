package com.tay.userservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tay.userservice.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import com.tay.userservice.common.Result;
import com.tay.userservice.common.ResultUtil;
import com.tay.userservice.dto.SysUserDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author tay
 * @since 2022-05-15
 */
@Slf4j
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping
    public Result<Null> create(@RequestBody SysUserDTO sysUserDTO) {
        sysUserService.save(sysUserDTO);
        return ResultUtil.success("添加成功");
    }

    @PutMapping
    public Result<Null> updateById(@RequestBody SysUserDTO sysUserDTO) {
        sysUserService.updateById(sysUserDTO);
        return ResultUtil.success("更新成功");
    }

    @DeleteMapping("{id}")
    public Result<Null> removeById(@PathVariable Long id) {
        sysUserService.deleteById(id);
        return ResultUtil.success("删除成功");
    }

    @GetMapping("{id}")
    public Result<SysUserDTO> getById(@PathVariable Long id) {
        SysUserDTO res = sysUserService.getById(id);
        return ResultUtil.<SysUserDTO>success("查询成功").setData(res);
    }

    @PostMapping("/list")
    public Result<List<SysUserDTO>> getByCondition(@RequestBody SysUserDTO sysUserDTO) {
        List<SysUserDTO> list = sysUserService.list(sysUserDTO);
        return ResultUtil.<List<SysUserDTO>>success("查询成功").setData(list);
    }

    @PostMapping("/page")
    public Result<IPage<SysUserDTO>> page(@RequestBody SysUserDTO sysUserDTO) {
        IPage<SysUserDTO> page = sysUserService.page(sysUserDTO);
        return ResultUtil.<IPage<SysUserDTO>>success("查询成功").setData(page);
    }
}

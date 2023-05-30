package com.tay.userservice.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;


/**
 * 系统用户
 *
 * @author huohuo ${email}
 * @since 1.0.0 2023-05-29
 */
@Data
@Schema( name = "系统用户")
public class SysUserDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@Schema(description = "id", hidden = true)
	private Long id;

	@Schema(description = "用户名")
	private String username;

	@Schema(description = "密码")
	private String password;

	@Schema(description = "姓名")
	private String realName;

	@Schema(description = "头像")
	private String headUrl;

	@Schema(description = "性别   0：男   1：女    2：保密")
	private Integer gender;

	@Schema(description = "邮箱")
	private String email;

	@Schema(description = "手机号")
	private String mobile;

	@Schema(description = "部门ID")
	private Long deptId;

	@Schema(description = "超级管理员   0：否   1：是")
	private Integer superAdmin;

	@Schema(description = "状态  0：停用   1：正常")
	private Integer status;

	@Schema(description = "创建者")
	private Long creator;

	@Schema(description = "创建时间", hidden = true)
	private Date createDate;

	@Schema(description = "更新者", hidden = true)
	private Long updater;

	@Schema(description = "更新时间", hidden = true)
	private Date updateDate;

	@Schema(description = "地址")
	private String address;


}
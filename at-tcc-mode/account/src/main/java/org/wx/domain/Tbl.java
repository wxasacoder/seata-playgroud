package org.wx.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

@Data
@TableName("account_tbl")
public class Tbl {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 
	 */
	@TableField("user_id")
	private Long userId;

	/**
	 * 
	 */
	@TableField("money")
	private Integer money;

	@TableField("lock_money")
	private Integer lockMoney;


}
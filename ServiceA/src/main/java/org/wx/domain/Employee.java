package org.wx.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import java.math.BigDecimal;

@Data
@TableName("employee")
public class Employee {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 
	 */
	@TableField("name")
	private String name;

	/**
	 * 
	 */
	@TableField("depart")
	private Integer depart;

	/**
	 * 
	 */
	@TableField("sal")
	private BigDecimal sal;


}
package org.wx.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import java.math.BigDecimal;

@Data
@TableName("employee_sale_income")
public class SaleIncome {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 
	 */
	@TableField("employee_id")
	private Long employeeId;

	/**
	 * 
	 */
	@TableField("income")
	private BigDecimal income;


}
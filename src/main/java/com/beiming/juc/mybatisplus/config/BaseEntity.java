package com.beiming.juc.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: dengchunhai
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable {

  /**
   * id 数据库字段是：id  <br>
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "排序号 排序号")
  private Integer orderNum;

  @ApiModelProperty(value = "创建人")
  private String createBy;

  @ApiModelProperty(value = "创建时间")
  @TableField(fill = FieldFill.INSERT)
  private Date createTime;

  @ApiModelProperty(value = "更新人")
  private String updateBy;

  @ApiModelProperty(value = "更新时间")
  @TableField(fill = FieldFill.UPDATE)
  private Date updateTime;

  /**
   * 状态 0,正常；1，删除，2，停用 数据库字段是：status  <br>
   */
  @TableLogic(value = "0", delval = "1")
  private Integer status;

}

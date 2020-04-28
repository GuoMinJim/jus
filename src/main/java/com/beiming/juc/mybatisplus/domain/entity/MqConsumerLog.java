package com.beiming.juc.mybatisplus.domain.entity;

import com.beiming.juc.mybatisplus.config.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 消费端消费日志表
 * </p>
 *
 * @author jinguomin
 * @since 2020-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(description = "MqConsumerLog对象", value = "消费端消费日志表")
public class MqConsumerLog extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "消息id")
  private String msgId;

  @ApiModelProperty(value = "消息主题")
  private String msgBody;

  @ApiModelProperty(value = "消费次数")
  private Integer consumerCount;

  @ApiModelProperty(value = "消费异常原因")
  private String exception;

  @ApiModelProperty(value = "消息队列主题")
  private String topic;

  @ApiModelProperty(value = "消息队列分组")
  private String group;

  @ApiModelProperty(value = "详细消息")
  private String propertiesString;

  @ApiModelProperty(value = "消息事务id")
  private String transactionId;



}

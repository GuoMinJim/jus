package com.beiming.juc.mybatisplus.domain.entity;

import com.beiming.juc.mybatisplus.config.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author jinguomin
 * @since 2020-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(description = "Car对象", value = "")
public class Car extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private String name;

  private String brand;


}

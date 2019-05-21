package com.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author weihaoran
 * @since 2019-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Areaall implements Serializable {

private static final long serialVersionUID = 1L;

@TableId(value = "id", type = IdType.AUTO)
private Long id;

/**
 * 编码
 */
private String code;

/**
 * 编码你项，顶级为空或空串
 */
@TableField("pCode")
private String pCode;

/**
 * 名称
 */
private String name;

/**
 * 拼首首字
 */
private String pinyin;

/**
 * 首字线，只有一个长度
 */
@TableField("pinyinFirst")
private String pinyinFirst;

/**
 * 级别，省：1/市：2/区县：3
 */
private Integer level;

}

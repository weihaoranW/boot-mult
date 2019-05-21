package com.wei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

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
public class Msg implements Serializable {

private static final long serialVersionUID = 1L;

/**
 * id PK
 */
@TableId(value = "id", type = IdType.AUTO)
private Long id;

/**
 * 消 息标题
 */
private String title;

/**
 * 消息 内容
 */
private String detail;

/**
 * 消息发布机构insret时查询
 */
@TableField("idBranchSrc")
private Long idBranchSrc;

/**
 * 消息发布人idinsret时查询
 */
@TableField("idUser")
private Long idUser;

/**
 * 发布人姓名insret时查询
 */
@TableField("idUserTitle")
private String idUserTitle;

/**
 * 生insrt时取当前时间
 */
@TableField("createTime")
private LocalDateTime createTime;

/**
 * 状态1:已摿 0：未推送
 */
@TableField("iStatus")
private Integer iStatus;

/**
 * 推送时间insret时查询
 */
@TableField("pushTime")
private LocalDateTime pushTime;

/**
 * 推送量insert时不设置
 */
@TableField("pushCount")
private Integer pushCount;

/**
 * 阅读量insert时不设置
 */
@TableField("readCount")
private Integer readCount;

@TableField("idBranchSrcTitle")
private String idBranchSrcTitle;

}

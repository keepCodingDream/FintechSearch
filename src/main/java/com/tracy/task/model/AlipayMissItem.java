package com.tracy.task.model;

import com.cashbus.mybatis.GenericModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.mybatis.mapper.annotation.NameStyle;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lurenjie on 2016/12/27
 */
@Entity
@Table(name = "alipay_miss_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NameStyle
public class AlipayMissItem extends GenericModel {
    private Long mid;
}

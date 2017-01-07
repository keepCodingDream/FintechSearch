package com.tracy.task.model;

import com.cashbus.mybatis.GenericModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tk.mybatis.mapper.annotation.NameStyle;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "alipay_trade_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NameStyle
public class AlipayTradeItem extends GenericModel {
    private String alipay_order_no;
    private Date create_time;
    private long detailId;
    private String in_out_type;
    private String merchant_order_no;
    private Date modified_time;
    private String opposite_logon_id;
    private String opposite_name;
    private String opposite_user_id;
    private String order_from;
    private String order_status;
    private String order_title;
    private String order_type;
    private String owner_logon_id;
    private String owner_name;
    private String owner_user_id;
    private String partner_id;
    private double service_charge;
    private double total_amount;
    private String userId;
    private Long certificateId;
}

package com.sample.controller;

import ai.yue.library.base.view.Result;
import ai.yue.library.base.view.ResultInfo;
import ai.yue.library.pay.client.Pay;
import ai.yue.library.pay.ipo.PayOrderIPO;
import com.egzosn.pay.wx.bean.WxTransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/payExample/{listId}")
public class PayController {

    @Autowired
    Pay pay;

    /**
     * 公众号（小程序）支付
     *
     * @param listId 列表ID
     * @param order_id 订单ID
     * @param openid openid
     * @return 返回jsapi所需参数
     */
    @Transactional
    @PostMapping("/jsapi")
    public Result<?> jsapi(@PathVariable Integer listId, @RequestParam("order_id") Long order_id,
                           @RequestParam("openid") String openid) {
        String subject = "商品名称";
        String body = "商品描述";
        BigDecimal price = new BigDecimal(0.01);
        PayOrderIPO payOrderIPO = new PayOrderIPO(listId, subject, body, price, WxTransactionType.JSAPI);
        payOrderIPO.setOpenid(openid);

        Map<String, Object> orderInfo = pay.getOrderInfo(payOrderIPO);
        return ResultInfo.success(orderInfo);
    }

}

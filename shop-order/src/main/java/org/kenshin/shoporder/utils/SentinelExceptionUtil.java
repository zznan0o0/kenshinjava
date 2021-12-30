package org.kenshin.shoporder.utils;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.kenshin.shoporder.entity.Order;

public class SentinelExceptionUtil {
    //异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public static Order exceptionHandler(BlockException ex) {
        ex.printStackTrace();
        Order order  = new Order();
        order.orderId = "exceptionHandler" + ex.getMessage();
        return order;
    }

}

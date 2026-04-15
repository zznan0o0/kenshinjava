package org.kenshin.testjxls;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestJxls {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("I:\\project\\java\\kenshinjava\\test-jxls\\src\\main\\resources\\t1.xlsx");
        OutputStream out = new FileOutputStream("I:\\project\\java\\kenshinjava\\test-jxls\\src\\main\\resources\\r1.xlsx");
        Context context = new Context();
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> m = new HashMap<>();
        m.put("t1","r1");
        m.put("t2","r2");
        m.put("t3","r3");
        Map<String, String> lMap = new HashMap<>();
        lMap.put("lt1", "lr1");
        lMap.put("lt2", "lr2");
        lMap.put("lt3", "lr3");
        m.put("l", Stream.of(lMap, lMap, lMap).collect(Collectors.toList()));
        list.add(m);
        list.add(m);
        list.add(m);
//        context.putVar("list", list);
//        JxlsHelper.getInstance().processTemplate(in, out, context);
//        System.out.println("success");

        exportTest();
    }

    public static void exportTest() throws IOException {
        InputStream in = new FileInputStream("I:\\project\\java\\kenshinjava\\test-jxls\\src\\main\\resources\\t2.xlsx");
        OutputStream out = new FileOutputStream("I:\\project\\java\\kenshinjava\\test-jxls\\src\\main\\resources\\r2.xlsx");
        // 构建导出数据 - data 必须是数组，因为模板中使用 data[0]
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> dataItem = new HashMap<>();

        // basic 基础信息
        Map<String, Object> basic = new HashMap<>();
        basic.put("excelName", "生产计划标题");  // 修改为 excelName 与模板匹配
        basic.put("authorName", "制单人");
        dataItem.put("basic", basic);

        // details 详细信息列表（多个工序）
        List<Map<String, Object>> details = new ArrayList<>();

        // 第一个工序：切割
        Map<String, Object> detail1 = new HashMap<>();
        detail1.put("gz", "切割");
        detail1.put("gzsize", 6);
        List<Map<String, Object>> bzs1 = new ArrayList<>();

        // 切割-白班
        Map<String, Object> bz1_1 = new HashMap<>();
        bz1_1.put("bz", "白班");
        bz1_1.put("ry", "班组部门人员1");
        List<Map<String, Object>> scs1_1 = new ArrayList<>();

        Map<String, Object> sc1_1_1 = new HashMap<>();
        sc1_1_1.put("num", 1);
        sc1_1_1.put("jgdh", "加工单号1");
        sc1_1_1.put("gg", "规格1");
        sc1_1_1.put("bz", "班组部门1");
        sc1_1_1.put("ddsl", 1);
        sc1_1_1.put("sjwcsl", 1);
        sc1_1_1.put("kl", "");
        sc1_1_1.put("ddjq", "订单交期1");
        sc1_1_1.put("pcbz", "排产备注1");
        scs1_1.add(sc1_1_1);

        Map<String, Object> sc1_1_2 = new HashMap<>();
        sc1_1_2.put("num", 2);
        sc1_1_2.put("jgdh", "j加工单号2");
        sc1_1_2.put("gg", "规格2");
        sc1_1_2.put("bz", "班组部门2");
        sc1_1_2.put("ddsl", 2);
        sc1_1_2.put("sjwcsl", 2);
        sc1_1_2.put("kl", "");
        sc1_1_2.put("ddjq", "订单交期2");
        sc1_1_2.put("pcbz", "排产备注2");
        scs1_1.add(sc1_1_2);

        bz1_1.put("scs", scs1_1);
        bz1_1.put("boxNo", null);
        bz1_1.put("bzsSize", 3);
        bzs1.add(bz1_1);

        // 切割-夜班
        Map<String, Object> bz1_2 = new HashMap<>();
        bz1_2.put("bz", "夜班");
        bz1_2.put("ry", "班组部门人员1");
        List<Map<String, Object>> scs1_2 = new ArrayList<>();

        Map<String, Object> sc1_2_1 = new HashMap<>();
        sc1_2_1.put("num", 1);
        sc1_2_1.put("jgdh", "加工单号3");
        sc1_2_1.put("gg", "规格3");
        sc1_2_1.put("bz", "班组部门3");
        sc1_2_1.put("ddsl", 3);
        sc1_2_1.put("sjwcsl", 3);
        sc1_2_1.put("kl", "");
        sc1_2_1.put("ddjq", "订单交期3");
        sc1_2_1.put("pcbz", "排产备注3");
        scs1_2.add(sc1_2_1);

        Map<String, Object> sc1_2_2 = new HashMap<>();
        sc1_2_2.put("num", 2);
        sc1_2_2.put("jgdh", "加工单号4");
        sc1_2_2.put("gg", "规格4");
        sc1_2_2.put("bz", "班组部门4");
        sc1_2_2.put("ddsl", 4);
        sc1_2_2.put("sjwcsl", 4);
        sc1_2_2.put("kl", "");
        sc1_2_2.put("ddjq", "订单交期4");
        sc1_2_2.put("pcbz", "排产备注4");
        scs1_2.add(sc1_2_2);

        bz1_2.put("scs", scs1_2);
        bz1_2.put("boxNo", null);
        bz1_2.put("bzsSize", 3);
        bzs1.add(bz1_2);

        detail1.put("bzs", bzs1);
        detail1.put("detailSize", 6);
        details.add(detail1);

        // 第二个工序：磨边
        Map<String, Object> detail2 = new HashMap<>();
        detail2.put("gz", "磨边");
        List<Map<String, Object>> bzs2 = new ArrayList<>();

        // 磨边-白班
        Map<String, Object> bz2_1 = new HashMap<>();
        bz2_1.put("bz", "白班");
        List<Map<String, Object>> scs2_1 = new ArrayList<>();

        Map<String, Object> sc2_1_1 = new HashMap<>();
        sc2_1_1.put("num", 1);
        sc2_1_1.put("jgdh", "jgdh1");
        sc2_1_1.put("gg", "规格1");
        sc2_1_1.put("bz", "班组部门1");
        sc2_1_1.put("ddsl", 1);
        sc2_1_1.put("sjwcsl", 1);
        sc2_1_1.put("kl", "");
        sc2_1_1.put("ddjq", "订单交期1");
        sc2_1_1.put("pcbz", "排产备注1");
        scs2_1.add(sc2_1_1);

        Map<String, Object> sc2_1_2 = new HashMap<>();
        sc2_1_2.put("num", 2);
        sc2_1_2.put("jgdh", "jgdh2");
        sc2_1_2.put("gg", "规格2");
        sc2_1_2.put("bz", "班组部门2");
        sc2_1_2.put("ddsl", 2);
        sc2_1_2.put("sjwcsl", 2);
        sc2_1_2.put("kl", "");
        sc2_1_2.put("ddjq", "订单交期2");
        sc2_1_2.put("pcbz", "排产备注2");
        scs2_1.add(sc2_1_2);

        bz2_1.put("scs", scs2_1);
        bz2_1.put("boxNo", null);
        bzs2.add(bz2_1);

        // 磨边-夜班
        Map<String, Object> bz2_2 = new HashMap<>();
        bz2_2.put("bz", "夜班");
        List<Map<String, Object>> scs2_2 = new ArrayList<>();

        Map<String, Object> sc2_2_1 = new HashMap<>();
        sc2_2_1.put("num", 1);
        sc2_2_1.put("jgdh", "jgdh3");
        sc2_2_1.put("gg", "规格3");
        sc2_2_1.put("bz", "班组部门3");
        sc2_2_1.put("ddsl", 3);
        sc2_2_1.put("sjwcsl", 3);
        sc2_2_1.put("kl", "");
        sc2_2_1.put("ddjq", "订单交期3");
        sc2_2_1.put("pcbz", "排产备注3");
        scs2_2.add(sc2_2_1);

        Map<String, Object> sc2_2_2 = new HashMap<>();
        sc2_2_2.put("num", 2);
        sc2_2_2.put("jgdh", "jgdh4");
        sc2_2_2.put("gg", "规格4");
        sc2_2_2.put("bz", "班组部门4");
        sc2_2_2.put("ddsl", 4);
        sc2_2_2.put("sjwcsl", 4);
        sc2_2_2.put("kl", "");
        sc2_2_2.put("ddjq", "订单交期4");
        sc2_2_2.put("pcbz", "排产备注4");
        scs2_2.add(sc2_2_2);

        bz2_2.put("scs", scs2_2);
        bz2_2.put("boxNo", null);
        bzs2.add(bz2_2);

        detail2.put("bzs", bzs2);
        detail2.put("detailSize", 6);
        details.add(detail2);

        dataItem.put("details", details);
        dataList.add(dataItem);

        // 创建 Context 并放入数据 - data 是数组
        Context context = new Context();
        context.putVar("data", dataList);
        JxlsHelper.getInstance().processTemplate(in, out, context);
        System.out.println("success");
    }
}

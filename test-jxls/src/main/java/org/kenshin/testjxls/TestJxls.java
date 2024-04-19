package org.kenshin.testjxls;

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
        context.putVar("list", list);
        JxlsHelper.getInstance().processTemplate(in, out, context);
        System.out.println("success");
    }
}

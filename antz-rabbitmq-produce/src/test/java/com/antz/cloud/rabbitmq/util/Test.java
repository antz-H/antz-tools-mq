package com.antz.cloud.rabbitmq.util;

import com.antz.cloud.rabbitmq.bean.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Assert;

import javax.validation.constraints.AssertTrue;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: antz-cloud-mq
 * @description:
 * @author: huanghuang@rewin.com.cn
 * @Create: 2018-09-19 09:44
 **/

public class Test {

    private static SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
    private static Calendar calendar = Calendar.getInstance();

    private  SimpleDateFormat format1 = new SimpleDateFormat("HH-mm-ss");
    private  Calendar calendar1 = Calendar.getInstance();


    @org.junit.Test
    public void DemoTest() throws CloneNotSupportedException {
        AtomicInteger aInt1 = new AtomicInteger(0);
        AtomicInteger aInt2 = new AtomicInteger(0);

        System.out.println(aInt1.equals(aInt2));
        System.out.println(aInt1.get() == aInt2.get());

        System.out.println("============================");

        double d = 1.1;

        BigDecimal bigDecimal = new BigDecimal(d);
        System.out.println(bigDecimal);
        BigDecimal bigDecimal2 = new BigDecimal(1.1);
        System.out.println(bigDecimal2);

        System.out.println(BigDecimal.valueOf(d));
        System.out.println("============================");
        System.out.println(format.format(calendar.getTime()));
        System.out.println(format1.format(calendar1.getTime()));
        System.out.println("============================");

        Order order = Order.builder().build();
        Order order1 = (Order)order.clone() ;
        System.out.println(order1.toString());
        System.out.println("============================");
        System.out.println(Integer.MIN_VALUE);
        System.out.println("============================");
        int a = 100 ;
        long b =1000L ;
        System.out.println(Double.longBitsToDouble(100));
        System.out.println(Double.longBitsToDouble(b));
        System.out.println(Double.valueOf(String.valueOf(a)));

        System.out.println("============================");
        try{

        }catch (Exception e){

            e.printStackTrace();
        }
        Map map =new HashMap<>();
        List list = new ArrayList();

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.canSerialize(Order.class));
        Double aa = null ;
        this.dosomething(aa =100d);

        /*String str = "/File|Name.txt";
        System.out.println(str.replaceAll(File.separator,""));
        AtomicInteger integer = new AtomicInteger();*/
        double dd = 100d;

        for(int i = 0 ; i <100 ; i++)
            System.out.println(i);

        int i = 0 ;
        int j = 0 ;
        System.out.println(i = i ++);
        System.out.println(j = ++j);

        System.out.println("============================");

        System.out.println(String.valueOf(i));


    }

    public void dosomething(Double vatt){

    }

}

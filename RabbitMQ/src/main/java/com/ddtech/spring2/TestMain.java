package com.ddtech.spring2;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext2.xml"})
public class TestMain {
    @Autowired
    private PublishUtil publishUtil;

    private static String exChange = "DIRECT_EX";//交换机
    private static String queue = "CONFIRM_TEST";

    /**
     * EXCHANGE QUEUE都对, confirm 会执行, ack=true
     * @throws Exception
     */
    @Test
    public void test1()  throws Exception{
        String message = "当前时间是:" + System.currentTimeMillis();
        publishUtil.send(exChange, queue, message);
        Thread.sleep(2000);
    }
    /**
     * EXCHANGE错误 QUEUE对, confirm 会执行, ack=false
     * @throws Exception
     */
    @Test
    public void test2()  throws Exception{
        String message = "当前时间是:" + System.currentTimeMillis();
        publishUtil.send(exChange+"!", queue, message);
        Thread.sleep(2000);
    }

    /**
     * EXCHANGE对的 QUEUE错的,confirm 会执行 act=true, 失败会执行
     * @throws Exception
     */
    @Test
    public void test3()  throws Exception{
        String message = "当前时间是:" + System.currentTimeMillis();
        publishUtil.send(exChange, queue+"1", message);
        Thread.sleep(2000);
    }


    /**
     * EXCHANGE QUEUE都是错的 confirm 会执行,但是ack=false
     * @throws Exception
     */
    @Test
    public void test4()  throws Exception{
        String message = "当前时间是:" + System.currentTimeMillis();
        publishUtil.send(exChange+"`", queue+"1", message);
        Thread.sleep(2000);
    }
}

package com.gaoxiong.jvmjuc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gaoxiong
 * @ClassName SaleTicketDemo01
 * @Description TODO
 * @date 2019/10/3 0003 下午 9:42
 * 三个售票员  卖出 30张票
 * 如何编写 企业级 的多线程代码
 *  1.  在高内聚低耦合的前提下,  线程   操作    资源类
 */
public class SaleTicketDemo01 {
    public static void main ( String[] args ) {

        final Ticket ticket = new Ticket();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"t2");
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"t3");

//        t1.start();
//        t2.start();
//        t3.start();

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(t1);
        threadPool.submit(t2);
        threadPool.submit(t3);

    }
}

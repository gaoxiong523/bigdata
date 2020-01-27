package com.gaoxiong.jvmjuc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gaoxiong
 * @ClassName Ticket
 * @Description 资源类
 * @date 2019/10/3 0003 下午 9:52
 */
public class Ticket {

    private int number = 30;

    Lock lock = new ReentrantLock();

    public void sale () {
        lock.lock();
        try {
            if (number>0) {
                System.out.println(Thread.currentThread().getName()+"\t卖出第: "+(number--)+"\t还剩下:"+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

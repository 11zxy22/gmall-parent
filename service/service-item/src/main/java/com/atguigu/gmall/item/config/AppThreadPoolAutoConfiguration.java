package com.atguigu.gmall.item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author zhangxingyu
 * @description 配置线程池
 * @createDate 2022/9/8 20:54
 */
@Configuration
public class AppThreadPoolAutoConfiguration {

    @Bean
     public ThreadPoolExecutor coreExecutor(){
        /*
        最终需要配置可抽取
            int corePoolSize,  一般等于CPU核心数
            int maximumPoolSize,  最大线程数
            long keepAliveTime,  线程存活时间
            TimeUnit unit,  时间单位
            BlockingQueue<Runnable> workQueue,  阻塞队列由项目最终能占的最大内存决定 以及接口吞吐量
            ThreadFactory threadFactory,    线程工厂 自定义创建现成的方法 设置线程名
            RejectedExecutionHandler handler) 四大拒绝策略
                ①DiscardOldestPolicy  忽略队列中最早提交的任务
                ②AbortPolicy    线程池满后会抛出异常
                ③CallerRunsPolicy   直接调用run方法
                ④DiscardPolicy  无异常 任务也不执行

         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                4,
                8,
                5,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(2000),
                new ThreadFactory() {
                    int i = 0; //记录线程自增id
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("core-thread-" + i++);
                        return thread;
                    }},
                new ThreadPoolExecutor.CallerRunsPolicy());
        //new ArrayBlockingQueue  底层是一个数组
        //new LinkedBlockingQueue  底层是链表 （k可以利用碎片化空间）
        return executor;
     }
}

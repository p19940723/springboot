package com.seven.authority.config;


import com.seven.authority.common.utils.thread.VisiableThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author seven
 */
@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig {

    @Bean
    public Executor asyncServiceExecutor() {
        log.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        /**
         * corePoolSize 线程池维护线程的最少数量
         * maximumPoolSize 线程池维护线程的最大数量
         * keepAliveTime 线程池维护线程所允许的空闲时间
         * workQueue 任务队列，用来存放我们所定义的任务处理线程
         * threadFactory 线程创建工厂
         * handler 线程池对拒绝任务的处理策略
         */
        /**
         * 线程池中任务有三种排队策略：
         直接提交：SynchronousQueue
         直接提交策略表示线程池不对任务进行缓存。新进任务直接提交给线程池，当线程池中没有空闲线程时，创建一个新的线程处理此任务。
         这种策略需要线程池具有无限增长的可能性。实现为：SynchronousQueue

         有界队列：如ArrayBlockingQueue
         当线程池中线程达到corePoolSize时，新进任务被放在队列里排队等待处理。有界队列（如ArrayBlockingQueue）有助于防止资源耗尽，
         但是可能较难调整和控制。队列大小和最大池大小可能需要相互折衷：使用大型队列和小型池可以最大限度地降低 CPU 使用率、操作系
         统资源和上下文切换开销，但是可能导致人工降低吞吐量。如果任务频繁阻塞（例如，如果它们是 I/O 边界），则系统可能为超过您许
         可的更多线程安排时间。使用小型队列通常要求较大的池大小，CPU 使用率较高，但是可能遇到不可接受的调度开销，这样也会降低吞吐量。

         无界队列：LinkedBlockingQueue
         使用无界队列（例如，不具有预定义容量的 LinkedBlockingQueue）将导致在所有 corePoolSize 线程都忙时新任务在队列中等待。这
         样，创建的线程就不会超过 corePoolSize。（因此，maximumPoolSize 的值也就无效了。）当每个任务完全独立于其他任务，即任务执
         行互不影响时，适合于使用无界队列；例如，在 Web 页服务器中。这种排队可用于处理瞬态突发请求，当命令以超过队列所能处理的平
         均数连续到达时，此策略允许无界线程具有增长的可能性。

         spring ThreadPoolTaskExecutor 线程池判断设置队列大小是否超过0
         return (BlockingQueue)(queueCapacity > 0 ? new LinkedBlockingQueue(queueCapacity) : new SynchronousQueue());
         大于零  无界队列：LinkedBlockingQueue
         else  直接提交：SynchronousQueue
         */

        /**
         * 四种拒绝策略
         CallerRunsPolicy：线程调用运行该任务的 execute 本身。
         这个策略显然不想放弃执行任务。但是由于池中已经没有任何资源了，那么就直接使用调用该execute的线程本身来执行。很有可能造成当前线程也被阻塞。

         AbortPolicy：
         jdk默认策略，队列满并线程满时直接拒绝添加新任务，并抛出异常

         DiscardPolicy：不能执行的任务将被删除
         这种策略和AbortPolicy几乎一样，也是丢弃任务，只不过他不抛出异常。

         DiscardOldestPolicy：如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）
         该策略就稍微复杂一些，在pool没有关闭的前提下首先丢掉缓存在队列中的最早的任务，然后重新尝试运行该任务。这个策略需要适当小心。
         */


        //线程池维护线程的最少数量
        executor.setCorePoolSize(5);
        //线程池维护线程的最大数量
        executor.setMaxPoolSize(50);
        //配置队列大小
        executor.setQueueCapacity(1000);
        //线程池维护线程所允许的空闲时间
        executor.setKeepAliveSeconds(60);
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");
        //CallerRunsPolicy：池中已经没有任何资源，线程调用运行该任务的 execute 本身。很有可能造成当前线程也被阻塞。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //是否在所有线程任务完成后关闭线程池 默认false
        executor.setAllowCoreThreadTimeOut(false);
        //执行初始化
        executor.initialize();
        return executor;
    }
}
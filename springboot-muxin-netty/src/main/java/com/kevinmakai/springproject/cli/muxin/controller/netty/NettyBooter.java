package com.kevinmakai.springproject.cli.muxin.controller.netty;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Rainer
 * @Date 2020/5/15
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null){
            try {
                WSServer.getInstance().start();
            } catch (Exception e){
                System.out.println("122222222222222222");
                e.printStackTrace();
            }
        }
    }
}

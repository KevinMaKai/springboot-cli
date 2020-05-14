package com.kevinmakai.springproject.cli.muxin.controller.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author Rainer
 * @Date 2020/5/10
 */
@Component
public class WSServer {

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    private static class SingletonWSServer{
        static final WSServer instance = new WSServer();
    }

    public static WSServer getInstance(){
        return SingletonWSServer.instance;
    }

    public WSServer(){
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup,subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitialzer());

    }

    public void start(){
        this.future = server.bind(8089);
        System.err.println("netty websocket server 启动完毕...");
    }






//    public static void main(String[] args) throws Exception{
//
//        EventLoopGroup mainGroup = new NioEventLoopGroup();
//        EventLoopGroup subGroup = new NioEventLoopGroup();
//
//        try {
//            ServerBootstrap server = new ServerBootstrap();
//            server.group(mainGroup,subGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new WSServerInitialzer());
//            ChannelFuture future = server.bind(8090).sync();
//
//            future.channel().closeFuture().sync();
//        } finally {
//            mainGroup.shutdownGracefully();
//            subGroup.shutdownGracefully();
//        }
//
//
//    }
}

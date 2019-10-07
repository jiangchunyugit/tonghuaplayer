//package com.mysqldemo.demo;
//
//import com.mysqldemo.demo.videoservice.VlcjPlayerService;
//import org.apache.commons.lang3.concurrent.BasicThreadFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintStream;
//import java.net.Socket;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.ScheduledThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//@Component
//@Order(2)
//public class MyApplicationRunnerSV implements CommandLineRunner {
//
//    @Autowired
//    VlcjPlayerService vlcjPlayerService;
//
//    @Override
//    public void run (String... args) throws Exception
//    {
//        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
//                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                //do something
////                try {
////                    vlcjPlayerService.smainVD();
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//
//                System.out.println("Client Start...");
//                while (true) {
//                    Socket socket = null;
//                    try {
//                        //创建一个流套接字并将其连接到指定主机上的指定端口号
//                        socket=new Socket("169.254.200.136", 8081);
//
//                        //读取服务器端数据
//                        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                        //向服务器端发送数据
//                        PrintStream out = new PrintStream(socket.getOutputStream());
////                        System.out.print("请输入: \t");
//                        String str = String.valueOf(vlcjPlayerService.positionC());
////                                new BufferedReader(new InputStreamReader(" ")).readLine();
//                        out.println(str);
//
//                        String ret = input.readLine();
//                        System.out.println("服务器端返回过来的是: " + ret);
//                        // 如接收到 "OK" 则断开连接
//                        if ("OK".equals(ret)) {
//                            System.out.println("客户端将关闭连接");
//                            Thread.sleep(500);
//                            break;
//                        }
//                        Thread.sleep(1000);
//
//                        out.close();
//                        input.close();
//                    } catch (Exception e) {
//                        System.out.println("客户端异常:" + e.getMessage());
//                    } finally {
//                        if (socket != null) {
//                            try {
//                                socket.close();
//                            } catch (IOException e) {
//                                socket = null;
//                                System.out.println("客户端 finally 异常:" + e.getMessage());
//                            }
//                        }
//                    }
//                }
//            }
//        },10,TimeUnit.SECONDS);
//    }
//}

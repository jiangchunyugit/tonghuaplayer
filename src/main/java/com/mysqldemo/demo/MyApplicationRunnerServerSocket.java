package com.mysqldemo.demo;

import com.mysqldemo.demo.videoservice.VlcjPlayerService;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Order(2)
public class MyApplicationRunnerServerSocket implements CommandLineRunner {

    @Autowired
    VlcjPlayerService vlcjPlayerService;

    @Override
    public void run (String... args) throws Exception
    {
//        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
//                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
//        executorService.schdule(neew Runnable() {
//            @Override
//            public void run() {
//
//
//            }
//        },10,TimeUnit.SECONDS);
        java.net.ServerSocket serverSocket = null;
        try {
            serverSocket = new java.net.ServerSocket(8432);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            //从请求队列中取出一个连接
            Socket client = null;
            try {
                client = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 处理这次连接
            new HandlerThread(client);
        }
    }

    private class HandlerThread implements Runnable {
        private Socket socket;
        public HandlerThread(Socket client) {
            socket = client;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                // 读取客户端数据
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientInputStr = input.readLine();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException
                // 处理客户端数据
                System.out.println("客户端发过来的内容:" + clientInputStr);

                if ("video_start".equals(clientInputStr)) {

                    vlcjPlayerService.smainVD();
                }
                else if ("video_stop".equals(clientInputStr)) {
                    vlcjPlayerService.stopC();
                }

                else if ("video_paused".equals(clientInputStr)) {
                    vlcjPlayerService.pausedC();
                }

                else if ("video_play".equals(clientInputStr)) {
                    vlcjPlayerService.playC();
                }
                else if ("vup".equals(clientInputStr)) {
                    vlcjPlayerService.vup();
                }
                else if ("vdown".equals(clientInputStr)) {
                    vlcjPlayerService.vdown();
                }else if ("loop_video".equals(clientInputStr))
                {
                    vlcjPlayerService.smainVDWithNotRabit();
                }
                input.close();
            } catch (Exception e) {
                System.out.println("服务器 run 异常: " + e.getMessage());
            }
            finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("服务端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }


}

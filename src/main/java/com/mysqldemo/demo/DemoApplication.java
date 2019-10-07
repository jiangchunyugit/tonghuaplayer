package com.mysqldemo.demo;

import com.mysqldemo.demo.videoservice.VlcjPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SpringBootApplication
@RestController
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableAsync
public class DemoApplication {

	@Autowired
	VlcjPlayerService vlcjPlayerService;
	@PostMapping("/command")
	public RemoteResult command(@RequestParam(value = "command") String command) {

		return this.ExeCmd(command);
	}
	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
		builder.headless(false).run(args);

//		System.out.println("Server...\n");
//		Server server = new Server();
//		server.init();
	}

    public RemoteResult ExeCmd(String s) {
		RemoteResult remoteResult = new RemoteResult();
        try {
        	System.out.print("打印信息："+s);
            Runtime ec = Runtime.getRuntime();
			System.out.print("执行：runtime");
            ec.exec(s);
			System.out.print("执行完");
			remoteResult.setCode(1000);
            return remoteResult;
        } catch (IOException e) {
			remoteResult.setCode(999);
        	return remoteResult;
        }
    }



}

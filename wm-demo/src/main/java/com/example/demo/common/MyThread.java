package com.example.demo.common;

import java.io.IOException;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 自定义线程，测试webSocket
 * @author LiWenming
 * 2019年5月18日下午9:37:36
 */
public class MyThread implements Runnable {
	
	private final Logger log = LoggerFactory.getLogger(MyThread.class);
	
	private Session session;
	
	public MyThread(Session session) {
		super();
		this.session = session;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			log.info("线程执行，session={}",session);
			try {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (this.session.isOpen()) {
		    		log.info("----------------");
		    		this.session.getBasicRemote().sendText("session="+session);		
//		    		session.getBasicRemote().sendText("session="+session);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

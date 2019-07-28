package com.example.demo.controller;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.common.MyThread;
/**
 * MyWebSocket，测试可用，链接断开，线程thisthread停止
 * @author LiWenming
 * 2019年5月18日下午9:38:03
 */
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {
	
	private static final Logger log = LoggerFactory.getLogger(MyWebSocket.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

//     连接建立成功调用的方法
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        log.info("current this={},session={}",this,session);
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("@OnOpen执行，有新连接加入，当前在线人数={}",getOnlineCount());
        try {
        	webSocketSet.forEach(System.out::println);
            sendMessage("有新连接加入！当前在线人数为" + getOnlineCount());
        } catch (IOException e) {
            System.out.println("IO异常");
        }
        //启动线程
        Thread thisthread = new Thread(new MyThread(session));
        thisthread.start();
    }


//      连接关闭调用的方法
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为={}",getOnlineCount());
    }

    /**
     * 	收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
    	log.info("来自客户端session={},消息={}", session,message);
        //群发消息
        for (MyWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


//     发生错误时调用
    @OnError
    public void onError(Session session, Throwable error) {
    	log.info("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
    	log.info("sendMessage(String message),parameter={}",message);
    	if (this.session.isOpen()) {
    		log.info("----------------");
    		this.session.getBasicRemote().sendText(message);		
		}
        //this.session.getAsyncRemote().sendText(message);
    }



//     * 群发自定义消息
//     * */
    public static void sendInfo(String message) throws IOException {
    	log.info("sendInfo(String message),parameter={}",message);
        for (MyWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}
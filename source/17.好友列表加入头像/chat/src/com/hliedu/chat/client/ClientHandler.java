package com.hliedu.chat.client;

import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

import com.hliedu.chat.entity.ChatStatus;
import com.hliedu.chat.entity.FontStyle;
import com.hliedu.chat.entity.TransferInfo;
import com.hliedu.chat.entity.User;
import com.hliedu.chat.io.IOStream;
import com.hliedu.chat.ulist.ImageCellRenderer;
import com.hliedu.chat.ulist.ImageListModel;
import com.hliedu.chat.util.FontSupport;

/**
 * 客户端开辟的去消息的线程
 * 
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：107184365
 *
 */
public class ClientHandler extends Thread{

	Socket socket;
	
	//登录窗体
	LoginFrame loginFrame;
	
	//聊天主窗体
	ChatFrame chatFrame;
	
	public ClientHandler(Socket socket , LoginFrame loginFrame) {
		this.socket = socket;
		this.loginFrame = loginFrame;
	}
	
	@Override
	public void run() {
		//默认重复拿
		while(true) {
			try {
				//模拟一直拿消息，产生阻塞
				Object obj = IOStream.readMessage(socket);
				if(obj instanceof TransferInfo) {
					
					TransferInfo tfi = (TransferInfo)obj;
					if(tfi.getStatusEnum() == ChatStatus.LOGIN) {
						//这是登录
						loginResult(tfi);
					} else if(tfi.getStatusEnum() == ChatStatus.CHAT){
						//这是聊天消息
						chatResult(tfi);
					} else if(tfi.getStatusEnum() == ChatStatus.NOTICE){
						noticeResult(tfi);
						
					} else if(tfi.getStatusEnum() == ChatStatus.ULIST){
						//刷新当前在线用户列表
						onlineUsersResult(tfi);
					} else if(tfi.getStatusEnum() == ChatStatus.DD){
						//抖动相应处理
						DDResult(tfi);
					}
				} else {
					
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 接收从服务器发送过来的抖动信息
	 * @param tfi
	 */
	private void DDResult(TransferInfo tfi) {
		DouDong dd = new DouDong(chatFrame);
		dd.start();
	}
	
	/**
	 * 登录结果的处理
	 * @param tfi
	 */
	public void loginResult(TransferInfo tfi) {
		if(tfi.getLoginSucceessFlag()) {
			
			//根据实体类取出用户名
			String userName = tfi.getUserName();
			
			//登录成功，打开主界面
			chatFrame = new ChatFrame(userName , socket);
			loginFrame.dispose();//关闭登录窗体
		}else {
			//登录失败
			System.out.println("客户端接收到登录失败");
			
		}
	}
	
	/**
	 * 聊天消息处理
	 * @param tfi
	 */
	public void chatResult(TransferInfo tfi) {
		//发送人
		String sender = tfi.getSender();
		//接收人
		String reciver = tfi.getReciver();
		if(reciver != null && "All".equals(reciver)) {
			reciver = "所有人";//All转换成所有人
		}
		List<FontStyle> contents = tfi.getContent();
		//文字解析类进行解析处理
		FontSupport.fontDecode(chatFrame.acceptPane, contents, sender, reciver);
	}
	
	/**
	 * 系统消息提示
	 * @param tfi
	 */
	public void noticeResult(TransferInfo tfi) {
		//往公屏上面投射系统消息
		FontSupport.contentAppend(chatFrame.acceptPane, "\n" + tfi.getNotice());
	}
	
	/**
	 * 刷新当前界面的用户列表
	 * @param tfi
	 */
	public void onlineUsersResult(TransferInfo tfi) {
		String[] userOnlineArray = tfi.getUserOnlineArray();
		//你想展示什么，就添加什么
		ImageListModel model = new ImageListModel();
		for (String userName : userOnlineArray) {
			User user = new User();
			user.setUserName(userName);
			user.setNickName(userName + "昵称");
			user.setMotto("这是签名，都统一吧");
			user.setUiconPath("src/image/uicon/" + userName + ".png");
			model.addElement(user);
		}
		//JList的模型，给我们存放数据的
		chatFrame.lstUser.setModel(model);
		//提供给我们自定义想要的皮肤或者样式
		chatFrame.lstUser.setCellRenderer(new ImageCellRenderer());
				
				
//		chatFrame.lstUser.setListData(userOnlineArray);
	}
	
}

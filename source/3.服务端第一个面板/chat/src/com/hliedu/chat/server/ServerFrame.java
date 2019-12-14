package com.hliedu.chat.server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.hliedu.chat.server.ui.ServerInfoPanel;
/**
 * 服务器端主界面
 * 
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：107184365
 *
 */
public class ServerFrame extends JFrame {

	private static final long serialVersionUID = -8152378291726535742L;

	/**
	 * 服务器窗体宽度
	 */
	public static final Integer FRAME_WIDTH = 550;
	
	/**
	 * 服务器窗体高度
	 */
	public static final Integer FRAME_HEIGHT = 500;
		
	public ServerFrame() {

		this.setTitle("网络聊天室服务器");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		//窗体不可扩大
		setResizable(false);
		//获取屏幕
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		//屏幕居中处理
		setLocation((width-FRAME_WIDTH)/2, (height-FRAME_HEIGHT)/2);
		
		//设置窗体关闭，程序退出
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//选项卡
		JTabbedPane tpServer = new JTabbedPane(JTabbedPane.TOP);
		tpServer.setBackground(Color.WHITE);
		tpServer.setFont(new Font("宋体", 0, 18));
		
		
		ServerInfoPanel serverInfoPanel = new ServerInfoPanel();
		tpServer.add("服务器信息" , serverInfoPanel.getServerInfoPanel());
		
		tpServer.add("第二个选项卡" , new JLabel());
		add(tpServer);
		setVisible(true);
	}
	
	
	
	public static void main(String[] args) {

		new ServerFrame();
	}

}

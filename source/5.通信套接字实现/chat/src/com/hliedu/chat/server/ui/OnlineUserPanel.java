package com.hliedu.chat.server.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 服务端第二个选项卡：在线用户列表
 * 
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：107184365
 *
 */
public class OnlineUserPanel {

	public JLabel getUserPanel() {
		// 用户面板
		JPanel pnlUser = new JPanel();
		pnlUser.setLayout(null);
		pnlUser.setBackground(new Color(52, 130, 203));
		pnlUser.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(""),
				BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		pnlUser.setBounds(50, 5, 300, 400);
		pnlUser.setOpaque(false);//设置透明
		
		JLabel lblUser = new JLabel("[在线用户列表]");
		lblUser.setFont(new Font("宋体", 0, 16));
		lblUser.setBounds(50, 10, 200, 30);
		pnlUser.add(lblUser);
		
		//用户列表
		JList lstUser = new JList();
		lstUser.setFont(new Font("宋体", 0, 14));
		lstUser.setVisibleRowCount(17);
		lstUser.setFixedCellWidth(180);
		lstUser.setFixedCellHeight(18);
		lstUser.setOpaque(false);

		JScrollPane spUser = new JScrollPane(lstUser);
		spUser.setFont(new Font("宋体", 0, 14));
		spUser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spUser.setBounds(50, 35, 200, 360);
		spUser.setOpaque(false);
		pnlUser.add(spUser);
		
		//加载窗体的背景图片
		ImageIcon imageIcon = new ImageIcon("src\\image\\beijing.jpg");
		//创建一个标签并将图片添加进去
		JLabel lblBackground = new JLabel(imageIcon);
		//设置图片的位置和大小
		lblBackground.setBounds(0, 200, 300, 300);
		//添加到当前窗体中
		lblBackground.add(pnlUser);
		
		return lblBackground;
	}

}

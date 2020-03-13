package Demo5_29;
/*
 * 需求:
 * 界面随机生成字母
 * 在键盘上输入对应的字母，加20分，如果输入不对，减分50分
 * 每500分增加一个难度。
 * 思路:先创建一个窗体，设置属性。(位置,大小,标题,可见性,不可移动性);
 * 创建一个面板去继承Panel 
 * 实现 Runnable类,重写Run方法.
 * keyListenter.重写该类中的全部方法。
 * 	使用Graphics在窗体中添加该组件
 * 使用集合来存放字母，X轴 Y轴
 * 添加两个方法
 * 		----drawWords() 添加元素
 * 		----drawWords(index)设置元素
 * run方法
 * 		-----实现字母位置的改变，如果下落超出范围，有相应的加分操作。
 * keyPressed 方法
 * 		------添加事件处理机制，判断屏幕出现的字母和和键盘上的字母是否相同。相同加20分
 * 		------否则，减分50。
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Test {

	public static void main(String[] args) {
		JFrame Jframe = new JFrame();//创建一个窗体
		Jframe.setBounds(350,100, 800, 600);//设置在位置大小
		Jframe.setTitle("打字游戏");//设置标题
		Jframe.setResizable(false);//设置不可移动
		MyPanel myPanel = new MyPanel();//创建面板
		Thread t1 = new Thread(myPanel);//创建线程
		Jframe.add(myPanel);//添加到窗口上
		t1.start();//线程开启
		Jframe.addKeyListener(myPanel);//注册键盘监听器
		myPanel.addKeyListener(myPanel);//注册键盘监听器
		//注册动作监听器
		Jframe.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		Jframe.setVisible(true);//设置可见性
	}
}

package Demo5_29;
/*
 * ����:
 * �������������ĸ
 * �ڼ����������Ӧ����ĸ����20�֣�������벻�ԣ�����50��
 * ÿ500������һ���Ѷȡ�
 * ˼·:�ȴ���һ�����壬�������ԡ�(λ��,��С,����,�ɼ���,�����ƶ���);
 * ����һ�����ȥ�̳�Panel 
 * ʵ�� Runnable��,��дRun����.
 * keyListenter.��д�����е�ȫ��������
 * 	ʹ��Graphics�ڴ�������Ӹ����
 * ʹ�ü����������ĸ��X�� Y��
 * �����������
 * 		----drawWords() ���Ԫ��
 * 		----drawWords(index)����Ԫ��
 * run����
 * 		-----ʵ����ĸλ�õĸı䣬������䳬����Χ������Ӧ�ļӷֲ�����
 * keyPressed ����
 * 		------����¼�������ƣ��ж���Ļ���ֵ���ĸ�ͺͼ����ϵ���ĸ�Ƿ���ͬ����ͬ��20��
 * 		------���򣬼���50��
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Test {

	public static void main(String[] args) {
		JFrame Jframe = new JFrame();//����һ������
		Jframe.setBounds(350,100, 800, 600);//������λ�ô�С
		Jframe.setTitle("������Ϸ");//���ñ���
		Jframe.setResizable(false);//���ò����ƶ�
		MyPanel myPanel = new MyPanel();//�������
		Thread t1 = new Thread(myPanel);//�����߳�
		Jframe.add(myPanel);//��ӵ�������
		t1.start();//�߳̿���
		Jframe.addKeyListener(myPanel);//ע����̼�����
		myPanel.addKeyListener(myPanel);//ע����̼�����
		//ע�ᶯ��������
		Jframe.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		Jframe.setVisible(true);//���ÿɼ���
	}
}

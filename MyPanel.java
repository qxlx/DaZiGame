package Demo5_29;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends Panel implements Runnable,KeyListener{
	int speed = 20;//�����ٶ�
	int level = 1;//��Ϸ�ȼ�
	int score = 0;//����
	final  int  bgWidth = 500;//���
	final   int bgHeight = 580;//�߶�
	final int bgLeft = 150;//��߾�
	final int bgTop = 0;//�ϱ߾�
	List <Character>  charList = new ArrayList<Character>();//�������������ĸ����
	List<Integer> xList = new ArrayList<Integer>();//����
	List<Integer> yList = new ArrayList<Integer>();//����
	boolean gameStart = true;
	MyPanel(){//�����滭�����ĸ
		for(int i=0;i<5;i++){
			drawWords();
		}
	}
	public void paint(Graphics g){//����ͼ��
		//��ʼ����ͼ��
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.green);
		g.fillRoundRect(bgLeft, bgTop, bgWidth, bgHeight, 30, 30);
		g.setColor(Color.black);
		g.fillRoundRect(bgLeft+10,bgTop+10 , bgWidth-20, bgHeight-20, 30, 30);
		g.setColor(Color.orange);
		g.fillRoundRect(bgLeft+20,bgTop+20,bgWidth-40,bgHeight-40,30,30);
		g.setColor(Color.pink);
		g.fillRoundRect(bgLeft+30, bgTop+30, bgWidth-60, bgHeight-60, 30, 30);
		//Ϊͼ�������Ϸ��������Ϸ�ȼ��ͷ�����ʾ
		g.setFont(new Font("����",Font.BOLD,20));
		g.setColor(Color.cyan);
		g.drawString("Level:"+level, 40,60);
		g.setFont(new Font("����",Font.BOLD,20));
		g.setColor(Color.RED);
		g.drawString("score:"+score, 40, 100);
		g.setColor(Color.WHITE);
		for(int i=0;i<charList.size();i++){
			if(yList.get(i)>=40){//��ĸ������Χ
				g.drawString(""+charList.get(i),xList.get(i),yList.get(i));
			}
		}
		//��Ϸ����
		if(!gameStart){
			g.setFont(new Font("����",Font.BOLD,50));
			g.setColor(Color.red);
			g.drawString("Game Over!",250,180);
		}
	}
	//������ĸ
	public void drawWords(int index){
		charList.set(index, (char)(Math.random()*26+65));
		xList.set(index, (int)(Math.random()*(bgWidth-80)+(bgLeft+30)));
		yList.set(index,(int)(Math.random()*210-180));
	}
	//�����ĸ
	public void drawWords(){
		charList.add((char)(Math.random()*26+65));
		xList.add((int)(Math.random()*(bgWidth-80)+(bgLeft+30)));
		yList.add((int)(Math.random()*210-180));
	}
	//��дRunnable����run����
	public void run(){
		while(gameStart){
			for(int i=0;i<charList.size();i++){//������ÿ���ַ�
				yList.set(i,yList.get(i)+1);//�ı�����λ��
				if(yList.get(i)>bgHeight-40){//���ִ���
					yList.set(i,(int)(Math.random()*210-180));
					score-=50;
					if(score<0){//��Ϸ����
						score = 0;
						gameStart = false;
					}
				}
			}
			try {
				Thread.sleep(speed);//�߳�˯��
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();//��д�������
		}
		
	}
	//��дKeyListener�з���,����¼��������
	@Override
	public void keyPressed(KeyEvent e) {
		int bingo=-1000;//λ��
		int index=-1;
		//�ж��Ƿ���A��Z֮�����ĸ
		if(e.getKeyCode()>=e.VK_A&&e.getKeyCode()<=e.VK_Z){
			//�жϵ÷ֻ���
			//������ÿ��Ԫ�ؽ����ж�
			 for(int i=0;i<charList.size();i++){
				 //�����������
				   if(charList.get(i)==e.getKeyCode()){
					   if(bingo < yList.get(i)){
						   bingo = yList.get(i);
							index = i;
						}
				   }
			 }
			if(index!=-1){
				     yList.set(index,bingo);
				     drawWords(index);
					 score+=20;
					 if(score/500>level){//����ÿ500����һ��
						 level++;
						 speed=speed>=0?speed-=4:speed;
						 drawWords();
					 }
			}else{//���û�а��п۷�
				score-=50;
				  if(score<=0){
					  score=0;
					 gameStart=false;
				  }
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

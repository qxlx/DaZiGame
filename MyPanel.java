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
	int speed = 20;//下落速度
	int level = 1;//游戏等级
	int score = 0;//分数
	final  int  bgWidth = 500;//宽度
	final   int bgHeight = 580;//高度
	final int bgLeft = 150;//左边距
	final int bgTop = 0;//上边距
	List <Character>  charList = new ArrayList<Character>();//集合用来存放字母对象
	List<Integer> xList = new ArrayList<Integer>();//横轴
	List<Integer> yList = new ArrayList<Integer>();//纵轴
	boolean gameStart = true;
	MyPanel(){//开启绘画五个字母
		for(int i=0;i<5;i++){
			drawWords();
		}
	}
	public void paint(Graphics g){//设置图形
		//开始绘制图形
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
		//为图形添加游戏结束和游戏等级和分数显示
		g.setFont(new Font("楷体",Font.BOLD,20));
		g.setColor(Color.cyan);
		g.drawString("Level:"+level, 40,60);
		g.setFont(new Font("楷体",Font.BOLD,20));
		g.setColor(Color.RED);
		g.drawString("score:"+score, 40, 100);
		g.setColor(Color.WHITE);
		for(int i=0;i<charList.size();i++){
			if(yList.get(i)>=40){//字母掉出范围
				g.drawString(""+charList.get(i),xList.get(i),yList.get(i));
			}
		}
		//游戏结束
		if(!gameStart){
			g.setFont(new Font("楷体",Font.BOLD,50));
			g.setColor(Color.red);
			g.drawString("Game Over!",250,180);
		}
	}
	//设置字母
	public void drawWords(int index){
		charList.set(index, (char)(Math.random()*26+65));
		xList.set(index, (int)(Math.random()*(bgWidth-80)+(bgLeft+30)));
		yList.set(index,(int)(Math.random()*210-180));
	}
	//添加字母
	public void drawWords(){
		charList.add((char)(Math.random()*26+65));
		xList.add((int)(Math.random()*(bgWidth-80)+(bgLeft+30)));
		yList.add((int)(Math.random()*210-180));
	}
	//重写Runnable类中run方法
	public void run(){
		while(gameStart){
			for(int i=0;i<charList.size();i++){//遍历出每个字符
				yList.set(i,yList.get(i)+1);//改变下落位置
				if(yList.get(i)>bgHeight-40){//掉分处理
					yList.set(i,(int)(Math.random()*210-180));
					score-=50;
					if(score<0){//游戏结束
						score = 0;
						gameStart = false;
					}
				}
			}
			try {
				Thread.sleep(speed);//线程睡眠
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();//重写绘制面板
		}
		
	}
	//重写KeyListener中方法,添加事件处理机制
	@Override
	public void keyPressed(KeyEvent e) {
		int bingo=-1000;//位置
		int index=-1;
		//判断是否是A到Z之间的字母
		if(e.getKeyCode()>=e.VK_A&&e.getKeyCode()<=e.VK_Z){
			//判断得分机制
			//遍历出每个元素进行判断
			 for(int i=0;i<charList.size();i++){
				 //如果相等则进行
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
					 if(score/500>level){//分数每500升级一次
						 level++;
						 speed=speed>=0?speed-=4:speed;
						 drawWords();
					 }
			}else{//如果没有按中扣分
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

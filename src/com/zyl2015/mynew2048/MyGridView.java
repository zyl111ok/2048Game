package com.zyl2015.mynew2048;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.zyl2015.mynew2048.Cards;

public class MyGridView  extends GridLayout {

	private Cards[][] cardsMap=new Cards[4][4];
	private List<Point> emptyPoints = new ArrayList<Point>();
	public MyGridView(Context context) {
		super(context);
		initViews();
	}

	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initViews();
		
	}

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews();
	}
	/**
	 * ��ʼ����Ƭ����
	 */
	private void initViews(){
		setColumnCount(4);
		this.setBackgroundColor(0xffbbada0);
		//���������¼������߼�
		this.setOnTouchListener(new View.OnTouchListener(){
			private float startX,startY,offsetX,offsetY;
			@Override
			public boolean onTouch(View v, MotionEvent m) {
				// ��¼����
				switch(m.getAction()){
				case MotionEvent.ACTION_DOWN:
					startX=m.getX();
					startY=m.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetX=m.getX()-startX;
					offsetY=m.getY()-startY;
					
					if(Math.abs(offsetX)>Math.abs(offsetY)){
						if(offsetX<-5){
							swipeLeft();
							Log.i("zyl","left");
						}
						else if(offsetX>5){
							swipeRight();
							Log.i("zyl","right");
						}
					}
					else{
						if(offsetY<-5){
							swipeUp();
							Log.i("zyl","up");
						}
						else if(offsetY>5){
							swipeDown();
							Log.i("zyl","down");
						}
					}
					break;
					
				}
					
				return true;
			}
			
		});
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		int cardWidth=(Math.min(w, h)-10)/4;
		
		addCards(cardWidth,cardWidth);
		startGame();
	
	}
	/**
	 * ��ӿ�Ƭ��ͼ
	 * @param cardWidth
	 * @param cardHeight
	 */
	private void addCards(int cardWidth,int cardHeight){
		
		Cards c;
		for(int y=0;y<4;y++){
			for(int x=0;x<4;x++){
				c=new Cards(getContext());
				c.setNum(0);
				addView(c,cardWidth,cardHeight);
				cardsMap[x][y]=c;
			}
		}
	}
	
	private void swipeLeft(){
		boolean merge=false;
		
		for(int y=0;y<4;y++){
			for(int x=0;x<4;x++){
				
				for(int x1=x+1;x1<4;x1++){
					if(cardsMap[x1][y].getNum()>0){
						
						if(cardsMap[x][y].getNum()<=0){
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							x--;
							
							merge=true;
						}
						else if(cardsMap[x][y].equals(cardsMap[x1][y])){
								cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
								cardsMap[x1][y].setNum(0);
								MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
								merge=true;
							
						}
						break;
					}
					
				}
			}
		}
		if(merge){
			addRandom();
			checkComplete();
		}
		
	}
	
	private void swipeRight(){
		boolean merge=false;
		for(int y=0;y<=3;y++){
			for(int x=3;x>=0;x--){
				for(int x1=x-1;x1>=0;x1--){
					if(cardsMap[x1][y].getNum()>0){
						
						if(cardsMap[x][y].getNum()<=0){
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);
							x++;
							merge=true;
						}
						else if(cardsMap[x][y].equals(cardsMap[x1][y])){
								cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
								cardsMap[x1][y].setNum(0);
								MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
								merge=true;
							
						}
						break;
					}
					
				}
			}
		}
		if(merge){
			addRandom();
			checkComplete();
		}
	}
	private void swipeUp(){
		boolean merge=false;
		for(int x=0;x<4;x++){
			for(int y=0;y<4;y++){
				for(int y1=y+1;y1<4;y1++){
					if(cardsMap[x][y1].getNum()>0){
						
						if(cardsMap[x][y].getNum()<=0){
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);
							y--;
							merge=true;
						}
						else if(cardsMap[x][y].equals(cardsMap[x][y1])){
								cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
								cardsMap[x][y1].setNum(0);
								MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
								merge=true;
							
						}
						break;
					}
			
				}
			}
		}
		if(merge){
			addRandom();
			checkComplete();
		}
	}
	private void swipeDown(){
		boolean merge=false;
		for(int x=0;x<4;x++){
			for(int y=3;y>=0;y--){
				for(int y1=y-1;y1>=0;y1--){
					if(cardsMap[x][y1].getNum()>0){
						if(cardsMap[x][y].getNum()<=0){
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);
							y++;
							merge=true;
						}
						else if(cardsMap[x][y].equals(cardsMap[x][y1])){
								cardsMap[x][y].setNum(cardsMap[x][y].getNum()*2);
								cardsMap[x][y1].setNum(0);
								MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
								merge=true;
							
						}
						break;
					}
				
				}
			}
		}
		if(merge){
			addRandom();
			checkComplete();
		}
		
	}
	/**
	 * ������һ����Ƭ��ֵΪ2��4
	 */
	private void addRandom(){
		emptyPoints.clear();
		for (int x=0;x<4;x++){
			for(int y=0;y<4;y++){
				if(cardsMap[x][y].getNum()<=0){
					emptyPoints.add(new Point(x,y));
				}
			}
		}
		Point p=emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
		cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
		Log.i("zyl", cardsMap[p.x][p.y].getNum()+"");
	}
	/**
	 * ��ʼ��Ϸ�����������Ŀ
	 */
	private void startGame(){
		MainActivity.getMainActivity().clearScore();
		for(int x=0;x<4;x++){
			for(int y=0;y<4;y++){
				cardsMap[x][y].setNum(0);
			}
		}
		addRandom();
		addRandom();
	}
	/**
	 * ���ڵĿ����������һ��пգ���Ϸ��û����
	 */
	private void checkComplete(){
		boolean complete =true;
		ALL:
		for(int y=0;y<4;y++){
			for(int x=0;x<4;x++){
				if(cardsMap[x][y].getNum()==0||(x>0&&cardsMap[x][y].equals(cardsMap[x-1][y]))||
						(x<3&&cardsMap[x][y].equals(cardsMap[x+1][y]))||(y>0&&cardsMap[x][y].equals(cardsMap[x][y-1]))||
						(y<3&&cardsMap[x][y].equals(cardsMap[x][y+1]))){
					complete=false;
					break ALL;
				}
			}
		}
		if(complete){
		Builder builder=	new AlertDialog.Builder(getContext()).setTitle("��ʾ��Ϣ").setMessage("      ��GameOver�ˣ�");
		builder.setPositiveButton("����", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					startGame();
					
				}
			});
		builder.setNegativeButton("��һ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Toast.makeText(getContext(), "��", Toast.LENGTH_SHORT).show();
				
			}
		}); 
		builder.show();
			
		
		}
		
	}

}
package com.zyl2015.mynew2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;


public class Cards extends FrameLayout{
	
private TextView labels;
private int num=0;

public Cards(Context context) {
	super(context);
	labels=new TextView(getContext());
	labels.setTextSize(32);
	labels.setGravity(Gravity.CENTER);
	labels.setBackgroundColor(0x33ffffff);
	
	LayoutParams lp=new LayoutParams(-1, -1);
	lp.setMargins(10,10,0,0);
	this.addView(labels,lp);
	
	this.setNum(0);
}

public void setNum(int num){
	this.num=num;
	if(num<=0){
		labels.setText("");
	}
	else{
		labels.setText(num+"");
	}
}

public int getNum(){
	return this.num;
}

public boolean equals(Cards c){
	return this.getNum()==c.getNum();
}

}
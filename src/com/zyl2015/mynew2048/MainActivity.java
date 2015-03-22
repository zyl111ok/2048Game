package com.zyl2015.mynew2048;



import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {
	private int score = 0;
	private int tolScore=0;
	private TextView tvTotalScore;
	private static MainActivity mainActivity = null;
	public MainActivity(){
		mainActivity=this;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvTotalScore=(TextView)this.findViewById(R.id.tvTotalScore);
	
	}
	public void clearScore(){
		
		tolScore=0;
		showScore();
	}
	
	public void showScore(){
		tvTotalScore.setText(tolScore+"");
		
	}
	
	public void addScore(int s){
		tolScore+=s;
		showScore();
	}
	
	
	public static MainActivity getMainActivity() {
		return mainActivity;
	}
}

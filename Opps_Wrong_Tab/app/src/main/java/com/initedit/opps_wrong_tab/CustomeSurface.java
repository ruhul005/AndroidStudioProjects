package com.initedit.opps_wrong_tab;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class CustomeSurface extends SurfaceView implements Callback {
	
	static BackgroundThread bt;
	static ArrayList<Tile> tileHolder = new ArrayList<Tile>();
	public CustomeSurface(Context context,AttributeSet as) {
		super(context,as);
		getHolder().addCallback(this);
		bt = new BackgroundThread(this);
		setTextSize("Score 100");
	}
	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		surfaceCreated(this.getHolder());
	}

	@SuppressLint("WrongCall")
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
	@SuppressLint("WrongCall")
	@Override
	public void surfaceCreated(SurfaceHolder holder) {	
		if(holder!=null){
		VarHolder.IS_APPLICATION_PAUSE=false;
		VarHolder.IS_GAME_PAUSE=true;
		VarHolder.LAST_SCORE=0;	
		VarHolder.FIRST = true;
		Canvas c = holder.lockCanvas();
		onDraw(c);
		getHolder().unlockCanvasAndPost(c);
		if(bt!=null){
			if(bt.getState() == Thread.State.NEW){
				bt.start();
			}else{
				bt = new BackgroundThread(this);
				bt.start();
			}	
		}
		}
	}
	@SuppressLint("WrongCall")
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		onDraw(holder.lockCanvas());
	}
	long onDrawTime=0;
	Paint paint = new Paint();
	Tile tempTile;
	static int count=1;
	boolean isThunder=false;
	@Override
	protected void onDraw(Canvas canvas) {
		 super.onDraw(canvas); 
		 if(canvas==null){
			 return;
		 }
	     paint.setStyle(Paint.Style.FILL);
	     paint.setARGB(255, 0, 0, 0);
	     canvas.drawBitmap(ImageHolder.BACKGROUND, 0, 0, paint);
	     if(tileHolder.size()==0){ 	
		    	initPreTile(canvas); 
		 }
	     if((tileHolder.get(tileHolder.size()-1).y>=0 && tileHolder.size()>2) || tileHolder.get(tileHolder.size()-1).y>=100){
	    		 addTile(); 
	     }
	     boolean isEnd = false;
	     for(int i=0;i<tileHolder.size();i++){
	    	 tempTile = tileHolder.get(i);
	    	 for(int j=0;j<4;j++){
	    		 if(j==tempTile.col){
	    
	    			 paint.setARGB(tempTile.a, tempTile.r, tempTile.g, tempTile.b);
	    			 if(VarHolder.GAME_TYPE==2){
	    				 if(VarHolder.SCORE%5==4){
	    					 isThunder=true;
	    				 }
	    				 if(isThunder){
	    				 if(count%200!=0){
	    					 count++;
	    					 paint.setARGB(255, 255, 255, 255);
	    				 }else {
	    					 isThunder=false;
	    					 count=1;
	    				 }
	    				 }
	    			 }else if(VarHolder.GAME_TYPE==3){
	    				 
	    			 }
	    			 canvas.drawRect(VarHolder.SCREEN_WIDTH/4*j, tempTile.y, tempTile.x + tempTile.width, tempTile.y+tempTile.height, paint);
	    			 if(tempTile.isDanger){
	    				 canvas.drawBitmap(ImageHolder.DANGER, VarHolder.SCREEN_WIDTH/4*j, tempTile.y+(tempTile.height-ImageHolder.DANGER.getHeight())/2, paint);
	    			 }
	    		 }else{
	    			 paint.setARGB(255, 255, 255, 255);
	    			 canvas.drawRect(VarHolder.SCREEN_WIDTH/4*j, tempTile.y, tempTile.x + tempTile.width, tempTile.y+tempTile.height, paint);
	    		 }
	    		 paint.setARGB(255, 0, 0, 0);
		    	 paint.setStyle(Paint.Style.FILL); 
	    	 }
	    	 canvas.drawLine(0, tempTile.y, VarHolder.SCREEN_WIDTH, tempTile.y, paint);
	    	 if(tempTile.autoIncrement)
	    		 tempTile.y+=tempTile.speed; 
	    	 if(!isEnd){
	    		 if(tempTile.iscollidable){
	    			 if(tempTile.y + tempTile.height - tempTile.speed>VarHolder.SCREEN_HEIGHT ){
	    				 isEnd=true;
	    			 }
	    		 }else{
	    			 if(tempTile.y - tempTile.height - tempTile.speed*10>VarHolder.SCREEN_HEIGHT ){
	    				 tileHolder.remove(i);
	    			 }
	    		 }
	    	 }
	     }
	     if(isEnd){
	    	 Reset();
    		 stopGame(canvas);
	     }
	     if(tileHolder.size()<5){
    		 float y = tileHolder.get(0).y + tileHolder.get(0).height-tileHolder.get(0).speed;
    		 if(VarHolder.GAME_TYPE==4 || VarHolder.GAME_TYPE==5){
    			 y+=tileHolder.get(0).speed;
    		 }
    		 canvas.drawLine(0, y, VarHolder.SCREEN_WIDTH, y, paint);
    	 }
	     for(int i=0;i<4;i++){
	    	 tempTile = tileHolder.get(0);
	    	 float y = tempTile.y+tempTile.height-tempTile.speed;
	    	 if(VarHolder.GAME_TYPE==4 || VarHolder.GAME_TYPE==5){
    			 y+=tempTile.speed;
    		 }
	    	 canvas.drawLine(VarHolder.SCREEN_WIDTH/4*i, 0, VarHolder.SCREEN_WIDTH/4*i, y, paint);
	     }
	     tempTile=null; 
	     if(VarHolder.IS_TOUCHED){
	     boolean isCorrect = false;
	     VarHolder.IS_TOUCHED=false;
	     for(int i=0;i<tileHolder.size();i++){
	    	     tempTile = tileHolder.get(i);    		 
	    		 if (VarHolder.Touch.X > tempTile.x && VarHolder.Touch.X < tempTile.x + tempTile.width)
	             {
	                 if (VarHolder.Touch.Y > tempTile.y && VarHolder.Touch.Y < tempTile.y+ tempTile.height)
	                 {
	                	 if(tempTile.iscollidable){
	                         tempTile.setARGB(220, 200, 200, 200);
	                         tempTile.iscollidable = false;
	                         isCorrect = true;
	                         VarHolder.SCORE++;
	                         if(VarHolder.SCORE%5==0){
	                        	 VarHolder.TIME_THRUSHHOLD--;
	                        	 if(VarHolder.TIME_THRUSHHOLD<10){
	                        		 VarHolder.TIME_THRUSHHOLD=10;
	                        	 }
	                         }
	                         break;
	                     } else {
	                         isCorrect = false;
	                     }
	                 }
	             }
	     }
	     if(isCorrect){
	    	 CustomeSurface.addTile();
	    	 for(int i=0;i<tileHolder.size();i++){
	    	     tempTile = tileHolder.get(i);
	    	     tempTile.y+=tempTile.speed;
	    	 }
	     }
	     
	     if(!isCorrect){
	    	 int col=0;
	    	 if (VarHolder.Touch.X > 0 && VarHolder.Touch.X  < VarHolder.SCREEN_WIDTH / 4) {
	                col = 0;
	            }
	            if (VarHolder.Touch.X  > VarHolder.SCREEN_WIDTH / 4 && VarHolder.Touch.X  < 2 * VarHolder.SCREEN_WIDTH / 4) {
	                col = 1;
	            }
	            if (VarHolder.Touch.X  > 2 * VarHolder.SCREEN_WIDTH / 4 && VarHolder.Touch.X  < 3 * VarHolder.SCREEN_WIDTH / 4) {
	                col = 2;
	            }
	            if (VarHolder.Touch.X  > 3 * VarHolder.SCREEN_WIDTH / 4 && VarHolder.Touch.X  < 4 * VarHolder.SCREEN_WIDTH / 4) {
	                col = 3;
	            }
	            for(int i=0;i<tileHolder.size();i++){
		    	     tempTile = tileHolder.get(i);
	                if (VarHolder.Touch.Y > tempTile.y && VarHolder.Touch.Y < tempTile.y + tempTile.height) {
	                	paint.setStyle(Paint.Style.FILL);
	                    paint.setARGB(255, 255, 0, 0);
	                    int x = col * (VarHolder.SCREEN_WIDTH / 4);
	                    int y = (int) (tempTile.y - tempTile.speed);
	                    if(VarHolder.GAME_TYPE==4 || VarHolder.GAME_TYPE==5){
	                    	y+=tempTile.speed;
	                    }
	                    canvas.drawRect(x,y, x + tempTile.width, y + tempTile.height,paint);
	                    Reset();
	                    stopGame(canvas);
	                    return;
	                }
	            }
	          Reset();
    		 stopGame(canvas);
    		 return;
    	 }
	     } 
	     paint.setARGB(255, 0, 0, 0);
	     canvas.drawText("" + VarHolder.SCORE, VarHolder.SCREEN_WIDTH/2 - 20, (float) (VarHolder.SCREEN_HEIGHT*0.1), paint);
	     if(VarHolder.GAME_TYPE==4)
	    	 zenControl(canvas);
	     else  if(VarHolder.GAME_TYPE==5)
	    	 rushControl(canvas);
	     
	}
	public void rushControl(Canvas canvas){
		if(startTime==0){
			startTime = System.currentTimeMillis();
		}
		if(System.currentTimeMillis()-startTime>=15000){
			Reset();
			stopGame(canvas);
			startTime=0;
		}
	}
	public void setTextSize(String text){
	float testTextSize = 80f;
    paint.setTextSize(testTextSize);
    Rect bounds = new Rect();
    paint.getTextBounds(text, 0, text.length(), bounds);

    // Calculate the desired size as a proportion of our testTextSize.
    float desiredTextSize = (float) (testTextSize * (VarHolder.SCREEN_WIDTH*0.2) / bounds.width());

    // Set the paint for that size.
    paint.setTextSize(desiredTextSize);	
}
	long startTime = 0;
	public void zenControl(Canvas canvas){
		if(startTime==0){
			startTime = System.currentTimeMillis();
		}
		if(VarHolder.SCORE==50){
			Reset();
			stopGame(canvas);
		}
		float width = (float) (VarHolder.SCREEN_WIDTH *((double)VarHolder.SCORE/50));
		Log.e("Custome",""+width);
		paint.setARGB(230, 255, 120, 0);
		canvas.drawRect(0, 0, width, 5, paint);
		paint.setARGB(255, 0, 0, 0);
	}
	private void initPreTile(Canvas canvas) {
		addTile();addTile();
		for(int i=0;i<2;i++){
			 tempTile = tileHolder.get(i);
			 tempTile.y=tempTile.height*(1-i);
			 Log.e("Custome","init");
	    	 for(int j=0;j<4;j++){
	    		 if(j==tempTile.col){
	    			 paint.setARGB(tempTile.a, tempTile.r, tempTile.g, tempTile.b);
	    			 canvas.drawRect(VarHolder.SCREEN_WIDTH/4*j, tempTile.y, tempTile.x + tempTile.width, tempTile.y+tempTile.height, paint);
	    		 }else{
	    			 paint.setARGB(255, 255, 255, 255);
	    			 canvas.drawRect(VarHolder.SCREEN_WIDTH/4*j, tempTile.y, tempTile.x + tempTile.width, tempTile.y+tempTile.height, paint);
	    		 }
	    		 paint.setARGB(255, 0, 0, 0);
		    	 paint.setStyle(Paint.Style.FILL); 
	    	 }
	    	 canvas.drawLine(0, tempTile.y, VarHolder.SCREEN_WIDTH, tempTile.y, paint);	 
	    	 for(int k=0;k<4;k++){
		    	 tempTile = tileHolder.get(0);
		    	 canvas.drawLine(VarHolder.SCREEN_WIDTH/4*k, 0, VarHolder.SCREEN_WIDTH/4*k, tempTile.y+tempTile.height, paint);
		     }
		}
		if(tileHolder.size()<5){
   		 float y = tileHolder.get(0).y+tileHolder.get(0).height;
   		 canvas.drawLine(0, y, VarHolder.SCREEN_WIDTH, y, paint);
   	    }
		 
	}
	public void stopGame(Canvas canvas){
	 paint.setARGB(255, 0, 0, 0);
	
	 canvas.drawText("Game Over", VarHolder.SCREEN_WIDTH/2 - VarHolder.SCREEN_WIDTH/10, (float) (VarHolder.SCREEN_HEIGHT*0.47), paint);
	 if(VarHolder.GAME_TYPE==4){
		 float time =  (System.currentTimeMillis()-startTime)/1000;
		
		 canvas.drawText("Time " + time+"s", VarHolder.SCREEN_WIDTH/2 - VarHolder.SCREEN_WIDTH/10, (float) (VarHolder.SCREEN_HEIGHT*0.53), paint);
		 PermanentScoreHolder.storeScore(VarHolder.GAME_TYPE+"", time);
	 }else{
		 
		 canvas.drawText("Score " + VarHolder.LAST_SCORE, VarHolder.SCREEN_WIDTH/2 - VarHolder.SCREEN_WIDTH/10, (float) (VarHolder.SCREEN_HEIGHT*0.53), paint);
		 PermanentScoreHolder.storeScore(VarHolder.GAME_TYPE+"", VarHolder.LAST_SCORE);
	 }
	 canvas.drawBitmap(ImageHolder.RESTART_BUTTON, (float)(VarHolder.SCREEN_WIDTH*0.4),(float) (VarHolder.SCREEN_HEIGHT*0.6), paint);	
	}
	public static void Reset()
	{
		VarHolder.LAST_SCORE=VarHolder.SCORE;
		VarHolder.SCORE=0;
		VarHolder.FIRST_TOUCH=false;
		VarHolder.IS_GAME_PAUSE=true;
		VarHolder.TIME_THRUSHHOLD=50;
		
	}
	static int countDanger=0;;
	public static void addTile(){
		int r,g,b,a;
		a= 230;
		r = g = b = 0;
        int col = (int) (Math.floor(Math.random() * 4));
        int width = VarHolder.SCREEN_WIDTH / 4;
        int height = (int) (VarHolder.SCREEN_HEIGHT*0.25);
        int xPosition = (int) Math.floor( width* col);
        int yPosition = -1*height;
        if(CustomeSurface.tileHolder.size()>0){
        	yPosition = (int) CustomeSurface.tileHolder.get(tileHolder.size()-1).y - height;
        }
        int speed = (int) (VarHolder.SCREEN_HEIGHT*0.03);
		Tile tile = new Tile(xPosition,yPosition,width,height,speed,col,a,r,g,b);
		if(VarHolder.GAME_TYPE==3){
			if(countDanger%5==4 && Math.floor((Math.random()*3))%3==1){
				tile.iscollidable=false;
				tile.isDanger=true;
				countDanger=0;
			}
			countDanger++;
		}else if(VarHolder.GAME_TYPE==4 || VarHolder.GAME_TYPE==5){
			tile.autoIncrement=false;
			tile.speed = tile.height;
		}
		CustomeSurface.tileHolder.add(tile);
	}
}

package com.scxh.android.ui.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BasicSurfaceViewActivity extends Activity {
	public int tabTextSize;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));

		DisplayMetrics dm = getResources().getDisplayMetrics();
		tabTextSize = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, 20, dm);
	}

	// 视图内部类
	class MyView extends SurfaceView implements SurfaceHolder.Callback {
		private SurfaceHolder holder;
		private MyThread myThread;

		public MyView(Context context) {
			super(context);
			holder = this.getHolder();
			holder.addCallback(this);
			myThread = new MyThread(holder);// 创建一个绘图线程
		}

		/**
		 * 在surface的大小发生改变时激发
		 */
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {

		}

		/**
		 * 销毁时激发，一般在这里将画图的线程停止、释放。
		 */
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			myThread.isRun = true;
			myThread.start();
		}

		/**
		 * 销毁时激发，一般在这里将画图的线程停止、释放。
		 * 
		 */
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			myThread.isRun = false;
		}

	}

	// 线程内部类
	class MyThread extends Thread {
		private SurfaceHolder holder;
		public boolean isRun;

		public MyThread(SurfaceHolder holder) {
			this.holder = holder;
			isRun = true;
		}

		@Override
		public void run() {
			int count = 0;
			while (isRun) {
				Canvas c = null;
				try {
					synchronized (holder) {
						c = holder.lockCanvas();// 锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
						c.drawColor(Color.BLACK);// 设置画布背景颜色

						Paint p = new Paint(); // 创建画笔
						p.setColor(Color.WHITE);
						p.setTextSize(tabTextSize);
						
						Rect r = new Rect(100, 50, 300, 250);
						c.drawRect(r, p);
						
						c.drawText("这是第" + (count++) + "秒", 100, 310, p);
						
						Thread.sleep(1000);// 睡眠时间为1秒
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (c != null) {
						holder.unlockCanvasAndPost(c);// 结束锁定画图，并提交改变。

					}
				}
			}
		}
	}
}

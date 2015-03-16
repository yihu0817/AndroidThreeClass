package com.scxh.android.animation.gift;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class MyAnimation extends Animation{
	private final float mFromDegrees;
    private final float mToDegrees;
    private final float mCenterX;
    private final float mCenterY;
    private final float mDepthZ;
    private final boolean mReverse;
    private final boolean rotate;
    private Camera mCamera;

    public MyAnimation(float fromDegrees, float toDegrees,
            float centerX, float centerY, float depthZ, boolean reverse, boolean rotate) {
    	this.mFromDegrees = fromDegrees;
    	this.mToDegrees = toDegrees;
    	this.mCenterX = centerX;
    	this.mCenterY = centerY;
    	this.mDepthZ = depthZ;
    	this.mReverse = reverse;
        this.rotate = rotate;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float fromDegrees = mFromDegrees;
        //����м�Ƕ�
        float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);

        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;

        final Matrix matrix = t.getMatrix();

        camera.save();
//        if (mReverse) {
//            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
//        } else {
//            camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
//        }
//        //��ת
//        if(horizontal)
//        	camera.rotateY(degrees);
//        else
//        	camera.rotateX(degrees);
        //�������ת������ת
        if(rotate){
        	if (mReverse) {
        		camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
        	} else {
        		camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
        	}
        	//��ת
        	camera.rotateX(degrees);
        }
        //�����λ��
        else{
	        if(mReverse) {
	        	camera.translate(mDepthZ * 4 * interpolatedTime, -mDepthZ * 16 * interpolatedTime, mDepthZ * 20 * interpolatedTime);
	        } else {
	        	camera.translate(0.0f, mDepthZ * (1.0f - interpolatedTime), mDepthZ * (1.0f - interpolatedTime));
	        }
	    }
        //ȡ�ñ任��ľ���
        camera.getMatrix(matrix);
        camera.restore();

        //����ǰ���λ��
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }
}
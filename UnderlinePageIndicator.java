package com.example.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug.FlagToString;

/**
 * underline page 
 * @author hewei05
 *
 */
public class UnderlinePageIndicator extends View {

	private final static String TAG = UnderlinePageIndicator.class.getSimpleName();
	
	private int mTotalSize;
	private int mCurrentItem;
	
	private Drawable indicatorDrawable;
	public UnderlinePageIndicator(Context context) {
		super(context);
	}
	
	public UnderlinePageIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public UnderlinePageIndicator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// get background and other attrs
		
	}
	
	
	public void setUnderLineDrawable(Drawable underDrawable) {
		indicatorDrawable = underDrawable;
	}
	
	
	public void setTotalUnderLinePage(int totalSize) {
		mTotalSize = totalSize;
	}
	
	public void setCurrentUnderLinePage(int currentUnderLinePage) {
		if (currentUnderLinePage > mTotalSize) {
			currentUnderLinePage = mTotalSize;
		}
		
		Log.d(TAG, "setCurrentUnderLinePage : " + currentUnderLinePage);
		mCurrentItem = currentUnderLinePage;
		invalidate();
	}
	
    @Override
    protected void onDraw(Canvas canvas) {
    	super.onDraw(canvas);
        Log.d(TAG, "onDraw Begin");

    	final int count = mTotalSize;
    	if (count == 0) {
    		Log.d(TAG, "onDraw Error count == 0");
    		return;
    	}
    	
    	// set current page
        if (mCurrentItem >= count) {
        	mCurrentItem = 0;
        	Log.d(TAG, "onDraw Error mCurrentItem >= count");
        	setCurrentUnderLinePage(mCurrentItem);
            return;
        }

        
        final int paddingLeft = getPaddingLeft();
        final float pageWidth = (getWidth() - paddingLeft - getPaddingRight()) / (1f * count);
        final float left = paddingLeft + pageWidth * (mCurrentItem);
        final float right = left + pageWidth;
        final float top = getPaddingTop() + 1;
        final float bottom = getHeight() - getPaddingBottom() - 2;
        
        if (indicatorDrawable != null) {
        	indicatorDrawable.setBounds((int)left, (int)top, (int)right, (int)bottom);
        	indicatorDrawable.draw(canvas);
        	Log.d(TAG, "onDraw End");
        }
    }
	/**
	 * change current page interface
	 * @author hewei05
	 *
	 */
	interface OnChangeUnderLinePageIndicator {
		public void OnChangeUnderLinePage(int currentItem);
	}
	
}

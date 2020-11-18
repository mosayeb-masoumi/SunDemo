package com.example.sundemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class sunDemo extends View {
    private final Paint paint = new Paint();
    private Canvas canvas;
    private int radiusDynamic = 180;
    private int x;
    private int y;
    private PointF pointF = new PointF();
    private HashMap _$_findViewCache;

    protected void onDraw(@NonNull Canvas canvas) {
        //Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        super.onDraw(canvas);
        this.canvas = canvas;
        this.x = this.getWidth();
        this.y = this.getHeight();
        int d = this.x / 2 - this.x / 4;
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(5.0F);
        //background all

        this.paint.setColor(Color.rgb(255, 255, 255));
        this.paint.setAlpha(0);

        canvas.drawPaint(this.paint);
        this.paint.setStyle(Paint.Style.STROKE);
        //inner
        this.paint.setColor(Color.rgb(0, 204, 193));
        //  this.paint.setAlpha(50);
        Path path = new Path();
        path.moveTo((float)(this.x / 2 - (this.x / 2 - this.x / 4)), (float)(this.y ));
        int i = 180;

        for(int var5 = this.radiusDynamic + 1; i < var5; ++i) {
            this.pointF = this.getXYofAngle((double)i, (float)d);
            path.lineTo(this.pointF.x, this.pointF.y);
            path.lineTo(this.pointF.x, (float)this.y);
        }

        canvas.drawPath(path, this.paint);
        //line
        this.paint.setColor(Color.rgb(0, 165, 165));

        RectF rectMain = new RectF((float)(this.x / 2 - (this.x / 2 - this.x / 4)), (float)(this.y  - (this.x / 2 - this.x / 4)), (float)(this.x / 2 + (this.x / 2 - this.x / 4)), (float)(this.y  + (this.x / 2 - this.x / 4)));
        canvas.drawArc(rectMain, 180.0F, 180.0F, true, this.paint);
        this.paint.setStyle(Paint.Style.FILL);
        //sun
        this.paint.setColor(Color.rgb(255, 196, 0));

        canvas.drawCircle(this.pointF.x, this.pointF.y, 50.0F, this.paint);
        //   canvas.save();
        //   drawQibla(canvas);
    }
//    Bitmap kaaba = BitmapFactory.decodeResource(getResources(), R.drawable.yellow_sun);
    Paint qiblaPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public void drawQibla(Canvas canvas) {

        // canvas.rotate((float) qiblaInfo.getHeading() - 360, px, py);
        qiblaPaint.reset();
        qiblaPaint.setColor(Color.GREEN);
        qiblaPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //   qiblaPaint.setPathEffect(dashPath);
        qiblaPaint.setStrokeWidth(5.5f);


        //   qiblaPaint.setPathEffect(null);
        //  canvas.drawBitmap(kaaba,);
      /*  canvas.drawBitmap(kaaba, this.pointF.x, this.pointF.y, 50.0F, this.paint
            );*/
        canvas.restore();

    }
    private final PointF getXYofAngle(double angle, float r) {
        PointF coordinate = new PointF();
        double radians = Math.toRadians(angle);
        boolean var9 = false;
        double cosAngle = Math.cos(radians);
        boolean var11 = false;
        double sinAngle = Math.sin(radians);
        float pointX = (float)((double)r * cosAngle + (double)(this.x / 2));
        float pointY = (float)((double)r * sinAngle + (double)(this.y));
        coordinate.x = pointX;
        coordinate.y = pointY;
        return coordinate;
    }

    public final void illuminate(int progress) {
        Log.d("MainActivity", "illuminate: " + progress);
        if (progress > 0) {
            this.radiusDynamic = 180 + 180 * progress / 100;
            this.invalidate();
        }

    }

    public boolean onTouchEvent(@NonNull MotionEvent event) {
        // Intrinsics.checkParameterIsNotNull(event, "event");
        float x = event.getX();
        float y = event.getY();
        float var4;

        switch(event.getAction()) {
            case 0:
                if (this.isInCircle((float)this.getWidth() / (float)2, (float)this.getHeight() / (float)2, event, (float)(this.getWidth() / 2 - this.getWidth() / 4))) {
                    Log.d("Tap", "Inside the  Circle");
                }
                break;
            case 1:
                if (this.isInCircle((float)this.getWidth() / (float)2, (float)this.getHeight() / (float)2, event, (float)(this.getWidth() / 2 - this.getWidth() / 4))) {
                    Log.d("Tap", "Inside the  Circle");
                    //    Toast.makeText(this.getContext(), (CharSequence)"Click on the Circle", Toast.LENGTH_LONG).show();
                }

                var4 = x + (float)32 + y;

                System.out.println(var4);
            case 2:
            default:
                break;
            case 3:
                var4 = x + (float)32 + y;

                System.out.println(var4);
        }

        return true;
    }

    private final boolean isInCircle(float cx, float cy, MotionEvent tapPoint, float radius) {
        double var6 = (double)(tapPoint.getX() - cx);
        double var8 = 2.0D;
        boolean var10 = false;
        double var10000 = Math.pow(var6, var8);
        var6 = (double)(tapPoint.getY() - cy);
        var8 = 2.0D;
        double var11 = var10000;

        double var13 = Math.pow(var6, var8);
        var6 = var11 + var13;
        boolean var15 = false;
        float hype = (float)Math.sqrt(var6);
        return radius >= hype;
    }

    public sunDemo(@Nullable Context context) {
        super(context);
    }

    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }

        View var2 = (View)this._$_findViewCache.get(var1);
        if (var2 == null) {
            var2 = this.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }

    }
}

/*@Metadata(
        mv = {1, 1, 16},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
        d2 = {"Lcom/msm/half/circle/progress/MainActivity$Companion;", "", "()V", "TAG", "", "app_debug"}
)*/
/*
public static final class Companion {
    private Companion() {
    }

    // $FF: synthetic method
    public Companion(DefaultConstructorMarker $constructor_marker) {
        this();
    }
}*/

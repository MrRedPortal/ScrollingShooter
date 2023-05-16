package com.becroft.scrollingshooter.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;

import com.becroft.scrollingshooter.specs.ObjectSpec;

public class stdGraphicsComponent implements GraphicsComponent{

    private Bitmap bitmap;
    private Bitmap reverseBitmap;

    @Override
    public void initialise(Context c, ObjectSpec s, PointF objectSize) {

        // Make a resource ID out of the string of the file name
        int resID = c.getResources().getIdentifier(s.getBitmapName(),"drawable",c.getPackageName());

        // Load the bitmap using the ID
        bitmap = BitmapFactory.decodeResource(c.getResources(), resID);

        // Resize bitmap
        bitmap = Bitmap.createScaledBitmap(bitmap,(int)objectSize.x,(int)objectSize.y,false);

        // Create mirror image of the new bitmap (if needed)

        Matrix matrix = new Matrix();
        matrix.setScale(-1,1);
        reverseBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

    }

    @Override
    public void draw(Canvas canvas, Paint paint, Transform t) {
        if(t.getFacingRight()){
            canvas.drawBitmap(bitmap,t.getLocation().x, t.getLocation().y,paint);
        } else {
            canvas.drawBitmap(reverseBitmap,t.getLocation().x,t.getLocation().y,paint);
        }
    }
}

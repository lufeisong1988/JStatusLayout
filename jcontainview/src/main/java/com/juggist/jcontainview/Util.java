package com.juggist.jcontainview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import java.io.InputStream;

/**
 * Created by lufeisong on 2017/4/7.
 */

public class Util {
    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param ctx
     * @param resID
     * @return
     */
    public static BitmapDrawable bitmapDrawable(Context ctx, int resID) {
        BitmapFactory.Options opt = new BitmapFactory.Options();

        opt.inPreferredConfig = Bitmap.Config.RGB_565;

        opt.inPurgeable = true;

        opt.inInputShareable = true;

        InputStream is = ctx.getResources().openRawResource(

                resID);

        Bitmap bm = BitmapFactory.decodeStream(is, null, opt);

        BitmapDrawable bd = new BitmapDrawable(ctx.getResources(), bm);

        return bd;
    }
}

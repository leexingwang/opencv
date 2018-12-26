package com.example.lixingwang.opencv;

import android.graphics.Bitmap;
import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixingwang on 2018/12/26.
 * com.example.lixingwang.opencv
 */
public class ImageProcessUtils {

    private static String TAG = ImageProcessUtils.class.getSimpleName();
    private static long time;
    private static long startTime;


    public static final String ImageProcessType_convert2Gray = "图像灰度";
    public static final String ImageProcessType_invert = "单个像素取反操作";
    public static final String ImageProcessType_fastInvert = "快速取反操作";
    public static final String ImageProcessType_openCVInvert = "直接调用OpenCV取反操作";
    public static final String ImageProcessType_subtractImage = "两张图片相减操作";
    public static final String ImageProcessType_addImage = "两张图片相加操作";
    public static final String ImageProcessType_multiplyImage = "提高图片对比度操作";


    public static List<String> getCommendList() {
        List<String> commendList = new ArrayList<>();
        commendList.add(ImageProcessType_convert2Gray);
        commendList.add(ImageProcessType_invert);
        commendList.add(ImageProcessType_fastInvert);
        commendList.add(ImageProcessType_openCVInvert);
        commendList.add(ImageProcessType_subtractImage);
        commendList.add(ImageProcessType_addImage);
        commendList.add(ImageProcessType_multiplyImage);
//        Log.i(TAG, ImageProcessUtils.class.getDeclaredMethods()[3].getDeclaredAnnotations().toString() + "");
        return commendList;
    }


    public static Bitmap processByType(String commend, Bitmap bitmap) {
        startTime = System.currentTimeMillis();
        if (ImageProcessType_convert2Gray.equals(commend)) {
            return convert2Gray(bitmap);
        } else if (ImageProcessType_invert.equals(commend)) {
            return invert(bitmap);
        } else if (ImageProcessType_fastInvert.equals(commend)) {
            return fastInvert(bitmap);
        } else if (ImageProcessType_openCVInvert.equals(commend)) {
            return openCVInvert(bitmap);
        } else if (ImageProcessType_subtractImage.equals(commend)) {
            return subtractImage(bitmap);
        } else if (ImageProcessType_addImage.equals(commend)) {
            return addImage(bitmap);
        } else if (ImageProcessType_multiplyImage.equals(commend)) {
            return multiplyImage(bitmap);
        } else {
            return bitmap;
        }

    }


    /**
     * 转为灰度图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap convert2Gray(Bitmap bitmap) {
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGRA2GRAY);
        Utils.matToBitmap(dst, bitmap);
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;
    }


    /**
     * 取反
     * <p>
     * 效率超级慢
     * 不要使用
     *
     * @param bitmap
     * @return
     */
    public static Bitmap invert(Bitmap bitmap) {


        Mat src = new Mat();
        Utils.bitmapToMat(bitmap, src);
        int row = src.rows();
        int cols = src.cols();
        int channels = src.channels();
        byte[] bytes = new byte[channels];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cols; j++) {
                src.get(i, j, bytes);
                for (int k = 0; k < channels; k++) {
                    bytes[k] = (byte) (255 - bytes[k] & 0xff);
                }
                src.put(i, j, bytes);
            }
        }
        Utils.matToBitmap(src, bitmap);
        src.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;
    }

    /**
     * 快速取反操作
     *
     * @param bitmap
     * @return
     */
    public static Bitmap fastInvert(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] pixels = new int[width * height];
        //获取缓存数据
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        int index = 0;
        int a, r, g, b;
        for (int i = 0; i < height; i++) {
            index = i * width;
            for (int j = 0; j < width; j++) {
                int pixel = pixels[index];
                a = (pixel >> 24) & 0xff;
                r = (pixel >> 16) & 0xff;
                g = (pixel >> 8) & 0xff;
                b = pixel & 0xff;
                //取反
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;
                //重新赋值
                pixel = ((a & 0xff) << 24) | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
                pixels[index] = pixel;
                index++;
            }
        }
        //缓存数据重新写入bitmap
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;
    }

    /**
     * 直接调用OpenCV方法取反
     *
     * @param bitmap
     * @return
     */
    public static Bitmap openCVInvert(Bitmap bitmap) {
        Mat src = new Mat();
        Utils.bitmapToMat(bitmap, src);
        Core.bitwise_not(src, src);
        Utils.matToBitmap(src, bitmap);
        src.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;

    }

    /**
     * 图像相减
     *
     * @param bitmap
     * @return
     */
    public static Bitmap subtractImage(Bitmap bitmap) {
        Mat src = new Mat();
        Utils.bitmapToMat(bitmap, src);
        Mat dst = new Mat(src.size(), src.type(), Scalar.all(255));
        Core.subtract(dst, src, src);
        Utils.matToBitmap(src, bitmap);
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;
    }

    /**
     * 图像相加
     *
     * @param bitmap
     * @return
     */
    public static Bitmap addImage(Bitmap bitmap) {
        Mat src = new Mat();
        Utils.bitmapToMat(bitmap, src);
        Mat dst = new Mat(src.size(), src.type(), Scalar.all(255));
        Core.addWeighted(dst, 0.5, src, 0.5, 0.0, src);
        Utils.matToBitmap(src, bitmap);
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;
    }


    /**
     * 提高对比度
     *
     * @param bitmap
     * @return
     */
    public static Bitmap multiplyImage(Bitmap bitmap) {
        Mat src = new Mat();
        Utils.bitmapToMat(bitmap, src);
        src.convertTo(src, CvType.CV_32F);
        Mat dst = new Mat(src.size(), src.type(), Scalar.all(2.1));
        Core.multiply(dst, src, src);
        src.convertTo(src, CvType.CV_8U);
        Utils.matToBitmap(src, bitmap);
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;
    }
}

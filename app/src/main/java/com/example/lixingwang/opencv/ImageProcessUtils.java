package com.example.lixingwang.opencv;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
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
    public static final String ImageProcessType_cutImage = "图像截取操作";
    public static final String ImageProcessType_blurImage = "均值模糊处理图片";
    public static final String ImageProcessType_gaussianBlurImage = "高斯模糊处理图片";
    public static final String ImageProcessType_bilateralFilterImage = "双边模糊处理图片";
    public static final String ImageProcessType_customFilterImage1 = "自定义算子处理图片--模糊";
    public static final String ImageProcessType_customFilterImage2 = "自定义算子处理图片--拉普拉斯边缘";
    public static final String ImageProcessType_customFilterImage3 = "自定义算子处理图片--锐化";
    public static final String ImageProcessType_erodeFilterImage = "腐蚀/最小值滤波处理图片";
    public static final String ImageProcessType_dilateFilterImage = "膨胀/最大值滤波处理图片";
    public static final String ImageProcessType_openImage = "开操作处理图片";
    public static final String ImageProcessType_closeImage = "闭操作处理图片";
    public static final String ImageProcessType_morphLineFindImage = "开操作直线检测";
    public static final String ImageProcessType_THRESH_BINARY = "阈值二值化处理图片";
    public static final String ImageProcessType_THRESH_BINARY_INV = "阈值反二值化处理图片";
    public static final String ImageProcessType_THRESH_TOZERO = "阈值取零二值化处理图片";
    public static final String ImageProcessType_THRESH_TOZERO_INV = "阈值反取零二值化处理图片";
    public static final String ImageProcessType_THRESH_TRUNC = "截断二值化处理图片";


    public static List<String> getCommendList() {
        List<String> commendList = new ArrayList<>();
        commendList.add(ImageProcessType_convert2Gray);
        commendList.add(ImageProcessType_invert);
        commendList.add(ImageProcessType_fastInvert);
        commendList.add(ImageProcessType_openCVInvert);
        commendList.add(ImageProcessType_subtractImage);
        commendList.add(ImageProcessType_addImage);
        commendList.add(ImageProcessType_multiplyImage);
        commendList.add(ImageProcessType_cutImage);
        commendList.add(ImageProcessType_blurImage);
        commendList.add(ImageProcessType_gaussianBlurImage);
        commendList.add(ImageProcessType_bilateralFilterImage);
        commendList.add(ImageProcessType_customFilterImage1);
        commendList.add(ImageProcessType_customFilterImage2);
        commendList.add(ImageProcessType_customFilterImage3);
        commendList.add(ImageProcessType_erodeFilterImage);
        commendList.add(ImageProcessType_dilateFilterImage);
        commendList.add(ImageProcessType_openImage);
        commendList.add(ImageProcessType_closeImage);
        commendList.add(ImageProcessType_morphLineFindImage);
        commendList.add(ImageProcessType_THRESH_BINARY);
        commendList.add(ImageProcessType_THRESH_BINARY_INV);
        commendList.add(ImageProcessType_THRESH_TOZERO);
        commendList.add(ImageProcessType_THRESH_TOZERO_INV);
        commendList.add(ImageProcessType_THRESH_TRUNC);
//        Log.i(TAG, ImageProcessUtils.class.getDeclaredMethods()[3].getDeclaredAnnotations().toString() + "");
        return commendList;
    }


    public static Bitmap processByType(String commend, Bitmap bitmap, Context context) {
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
        } else if (ImageProcessType_cutImage.equals(commend)) {
            return cutImage(bitmap);
        } else if (ImageProcessType_blurImage.equals(commend)) {
            return blurImage(bitmap);
        } else if (ImageProcessType_gaussianBlurImage.equals(commend)) {
            return gaussianBlurImage(bitmap);
        } else if (ImageProcessType_bilateralFilterImage.equals(commend)) {
            return bilateralFilterImage(bitmap);
        } else if (ImageProcessType_customFilterImage1.equals(commend)) {
            return customFilterImage(1, bitmap);
        } else if (ImageProcessType_customFilterImage2.equals(commend)) {
            return customFilterImage(2, bitmap);
        } else if (ImageProcessType_customFilterImage3.equals(commend)) {
            return customFilterImage(3, bitmap);
        } else if (ImageProcessType_erodeFilterImage.equals(commend)) {
            return erodeFilterImage(bitmap);
        } else if (ImageProcessType_dilateFilterImage.equals(commend)) {
            return dilateFilterImage(bitmap);
        } else if (ImageProcessType_openImage.equals(commend)) {
            return openImage(bitmap);
        } else if (ImageProcessType_closeImage.equals(commend)) {
            return closeImage(bitmap);
        } else if (ImageProcessType_morphLineFindImage.equals(commend)) {
            return morphLineFindImage(bitmap);
        } else if (ImageProcessType_THRESH_BINARY.equals(commend)) {
            return thresholdImage(1, bitmap);
        } else if (ImageProcessType_THRESH_BINARY_INV.equals(commend)) {
            return thresholdImage(2, bitmap);
        } else if (ImageProcessType_THRESH_TOZERO.equals(commend)) {
            return thresholdImage(3, bitmap);
        } else if (ImageProcessType_THRESH_TOZERO_INV.equals(commend)) {
            return thresholdImage(4, bitmap);
        } else if (ImageProcessType_THRESH_TRUNC.equals(commend)) {
            return thresholdImage(5, bitmap);
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


    /**
     * 提高对比度
     *
     * @param bitmap
     * @return
     */
    public static Bitmap cutImage(Bitmap bitmap) {
        Rect rect = new Rect(0, 0, 200, 200);

        Bitmap rectBitmap = Bitmap.createBitmap(rect.width, rect.height, Bitmap.Config.ARGB_8888);
        Mat src = new Mat();
        Utils.bitmapToMat(bitmap, src);
        Mat subMat = src.submat(rect);
//        Core.circle(subMat, new Point(150, 150), 10, Scalar.all(0), 10);
        Utils.matToBitmap(subMat, rectBitmap);
        src.release();
        subMat.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return rectBitmap;
    }


    /**
     * 均值模糊处理图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap blurImage(Bitmap bitmap) {
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        Imgproc.blur(src, dst, new Size(59, 59), new Point(-1, -1), Imgproc.BORDER_DEFAULT);
        Utils.matToBitmap(dst, bitmap);
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;

    }


    /**
     * 高斯模糊处理图片
     * 3*3 降噪处理
     *
     * @param bitmap
     * @return
     */
    public static Bitmap gaussianBlurImage(Bitmap bitmap) {
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        Imgproc.GaussianBlur(src, dst, new Size(19, 19), 0, 0, Imgproc.BORDER_DEFAULT);
        Utils.matToBitmap(dst, bitmap);
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;

    }


    /**
     * 双边模糊处理图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap bilateralFilterImage(Bitmap bitmap) {
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2BGR);
        Imgproc.bilateralFilter(src, dst, 3, 150, 0, Imgproc.BORDER_DEFAULT);
        Utils.matToBitmap(dst, bitmap);
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;

    }


    /**
     * 自定义算子处理图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap customFilterImage(int commend, Bitmap bitmap) {
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        Mat kernel = getCustomKernel(commend);
        Imgproc.filter2D(src, dst, -1, kernel, new Point(-1, -1), 8, Imgproc.BORDER_DEFAULT);
        if (commend == 2) {
            Imgproc.cvtColor(dst, dst, Imgproc.COLOR_BGRA2GRAY);
        }
        Utils.matToBitmap(dst, bitmap);
        kernel.release();
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;

    }

    /**
     * 返回不同的算子
     *
     * @param commend
     * @return
     */
    private static Mat getCustomKernel(int commend) {
        Mat kernel = new Mat(3, 3, CvType.CV_32FC1);
        switch (commend) {
            //模糊算子
            case 1:
                kernel.put(0, 0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0, 1.0 / 9.0);
                break;
            //拉普拉斯算子
            case 2:
                kernel.put(0, 0, -1, -1, -1, -1, 8, -1, -1, -1, -1);
                break;
            //边缘模糊算子
            case 3:
                kernel.put(0, 0, -1, -1, -1, -1, 9, -1, -1, -1, -1);
                break;
        }
        return kernel;
    }


    /**
     * 腐蚀/最小值滤波处理图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap erodeFilterImage(Bitmap bitmap) {
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        //获取结构元素
        Mat structuringElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3), new Point(-1, -1));
        //腐蚀
        Imgproc.erode(src, dst, structuringElement, new Point(-1, -1), 5);
        Utils.matToBitmap(dst, bitmap);
        structuringElement.release();
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;

    }


    /**
     * 腐蚀/最小值滤波处理图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap dilateFilterImage(Bitmap bitmap) {
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        //获取结构元素
        Mat structuringElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3), new Point(-1, -1));
        //腐蚀
        Imgproc.dilate(src, dst, structuringElement, new Point(-1, -1), 5);
        Utils.matToBitmap(dst, bitmap);
        structuringElement.release();
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;

    }


    /**
     * 开操作处理图片
     * 先腐蚀后膨胀
     *
     * @param bitmap
     * @return
     */
    public static Bitmap openImage(Bitmap bitmap) {
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        //转为灰度图片
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2GRAY);
        //二值化
        Imgproc.threshold(src, src, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
        //获取结构元素
        Mat structuringElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3), new Point(-1, -1));
        //开操作
        Imgproc.morphologyEx(src, dst, Imgproc.MORPH_OPEN, structuringElement);
        Utils.matToBitmap(dst, bitmap);
        structuringElement.release();
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;

    }


    /**
     * 闭操作处理图片
     * 先膨胀后腐蚀
     *
     * @param bitmap
     * @return
     */
    public static Bitmap closeImage(Bitmap bitmap) {
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        //转为灰度图片
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2GRAY);
        //二值化
        Imgproc.threshold(src, src, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
        //获取结构元素
        Mat structuringElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3), new Point(-1, -1));
        //开操作
        Imgproc.morphologyEx(src, dst, Imgproc.MORPH_CLOSE, structuringElement);
        Utils.matToBitmap(dst, bitmap);
        structuringElement.release();
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;

    }


    /**
     * 开操作直线检测处理图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap morphLineFindImage(Bitmap bitmap) {
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        //转为灰度图片
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2GRAY);
        //二值化
        Imgproc.threshold(src, src, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
        //获取结构元素
        Mat structuringElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(200, 2), new Point(-1, -1));
        //开操作
        Imgproc.morphologyEx(src, dst, Imgproc.MORPH_OPEN, structuringElement);
        Utils.matToBitmap(dst, bitmap);
        structuringElement.release();
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;

    }


    /**
     * 二值化处理图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap thresholdImage(int commend, Bitmap bitmap) {
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        //转为灰度图片
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2GRAY);
        //二值化
        Imgproc.threshold(src, dst, 0, 255, getTypess(commend));

        Utils.matToBitmap(dst, bitmap);
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;

    }

    private static int getTypess(int commend) {
        int kernel = Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU;
        switch (commend) {
            //阈值二值化(threshold binary)
            case 1:
                kernel = Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU;
                break;
            //阈值反二值化
            case 2:
                kernel = Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU;
                break;
            //阈值取零
            case 3:
                kernel = Imgproc.THRESH_TOZERO | Imgproc.THRESH_OTSU;
                break;
            //阈值反取零
            case 4:
                kernel = Imgproc.THRESH_TOZERO_INV | Imgproc.THRESH_OTSU;
                break;
            //截断
            case 5:
                kernel = Imgproc.THRESH_TRUNC | Imgproc.THRESH_OTSU;
                break;
        }
        return kernel;
    }

}

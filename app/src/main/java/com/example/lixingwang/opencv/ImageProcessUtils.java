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
    public static final String ImageProcessType_THRESH_BINARY = "阈值二值化处理图片(可以拖动SeekBar)";
    public static final String ImageProcessType_THRESH_BINARY_INV = "阈值反二值化处理图片(可以拖动SeekBar)";
    public static final String ImageProcessType_THRESH_TOZERO = "阈值取零二值化处理图片(可以拖动SeekBar)";
    public static final String ImageProcessType_THRESH_TOZERO_INV = "阈值反取零二值化处理图片(可以拖动SeekBar)";
    public static final String ImageProcessType_THRESH_TRUNC = "截断二值化处理图片(可以拖动SeekBar)";
    public static final String ImageProcessType_adaptiveGAUSSIANThresholdImage = "自适应高斯阈值处理图片(可以拖动SeekBar)";
    public static final String ImageProcessType_adaptiveMEAN_CThresholdImage = "自适应均值-c阈值处理图片(可以拖动SeekBar)";
    public static final String ImageProcessType_equalizeHistImage = "直方图均值化处理图片";
    public static final String ImageProcessType_sobelImage_x = "Sobel算子X处理图片";
    public static final String ImageProcessType_sobelImage_y = "Sobel算子Y处理图片";
    public static final String ImageProcessType_sobelImage_xy = "Sobel算子XY处理图片";
    public static final String ImageProcessType_cannyImage = "图片轮廓检测(可以拖动SeekBar)";
    public static final String ImageProcessType_houghLinesImage = "图片霍夫直线检测(可以拖动SeekBar)";
    public static final String ImageProcessType_houghCirclesImage = "图片霍夫圆检测(可以拖动SeekBar)";


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
        commendList.add(ImageProcessType_adaptiveGAUSSIANThresholdImage);
        commendList.add(ImageProcessType_adaptiveMEAN_CThresholdImage);
        commendList.add(ImageProcessType_equalizeHistImage);
        commendList.add(ImageProcessType_sobelImage_x);
        commendList.add(ImageProcessType_sobelImage_y);
        commendList.add(ImageProcessType_sobelImage_xy);
        commendList.add(ImageProcessType_cannyImage);
        commendList.add(ImageProcessType_houghLinesImage);
        commendList.add(ImageProcessType_houghCirclesImage);
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
        } else if (ImageProcessType_adaptiveGAUSSIANThresholdImage.equals(commend)) {
            return adaptiveGAUSSIANThresholdImage(5, bitmap, false);
        } else if (ImageProcessType_adaptiveMEAN_CThresholdImage.equals(commend)) {
            return adaptiveMEAN_CThresholdImage(5, bitmap, false);
        } else if (ImageProcessType_equalizeHistImage.equals(commend)) {
            return equalizeHistImage(bitmap);
        } else if (ImageProcessType_sobelImage_x.equals(commend)) {
            return sobelImage(bitmap, 1);
        } else if (ImageProcessType_sobelImage_y.equals(commend)) {
            return sobelImage(bitmap, 2);
        } else if (ImageProcessType_sobelImage_xy.equals(commend)) {
            return sobelImage(bitmap, 3);
        } else if (ImageProcessType_cannyImage.equals(commend)) {
            return cannyImage(25, bitmap);
        } else if (ImageProcessType_houghLinesImage.equals(commend)) {
            return houghLinesImage(69, bitmap);
        } else if (ImageProcessType_houghCirclesImage.equals(commend)) {
            return houghCirclesImage(69, bitmap);
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


    /**
     * 二值化处理图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap thresholdImageSeekBar(int seek, int commend, Bitmap bitmap) {
        startTime = System.currentTimeMillis();
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        //转为灰度图片
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2GRAY);
        //二值化
        Imgproc.threshold(src, dst, seek, 255, getTypes(commend));
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

    private static int getTypes(int commend) {
        int kernel = Imgproc.THRESH_BINARY;
        switch (commend) {
            //阈值二值化(threshold binary)
            case 1:
                kernel = Imgproc.THRESH_BINARY;
                break;
            //阈值反二值化
            case 2:
                kernel = Imgproc.THRESH_BINARY_INV;
                break;
            //阈值取零
            case 3:
                kernel = Imgproc.THRESH_TOZERO;
                break;
            //阈值反取零
            case 4:
                kernel = Imgproc.THRESH_TOZERO_INV;
                break;
            //截断
            case 5:
                kernel = Imgproc.THRESH_TRUNC;
                break;
        }
        return kernel;
    }


    public static Bitmap adaptiveThresholdImage(int seek, int commend, Bitmap bitmap) {
        seek = (seek % 2 == 0 ? (seek - 1 < 3 ? 3 : seek - 1) : (seek < 3 ? 3 : seek));
        if (commend == 1) {
            return adaptiveGAUSSIANThresholdImage(seek, bitmap, true);
        } else {
            return adaptiveMEAN_CThresholdImage(seek, bitmap, true);
        }
    }


    /**
     * 自适应高斯阈值处理图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap adaptiveGAUSSIANThresholdImage(int seek, Bitmap bitmap, boolean isSeek) {
        startTime = System.currentTimeMillis();
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        //转为灰度图片
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2GRAY);
        //二值化
        if (!isSeek) {
            Imgproc.adaptiveThreshold(src, dst, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 59, 0.0);
        } else {
            Imgproc.adaptiveThreshold(src, dst, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, seek, 0.0);
        }

        Utils.matToBitmap(dst, bitmap);
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;

    }


    /**
     * 自适应均值-c阈值处理图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap adaptiveMEAN_CThresholdImage(int seek, Bitmap bitmap, boolean isSeek) {
        startTime = System.currentTimeMillis();
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        //转为灰度图片
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2GRAY);
        //二值化
        if (!isSeek) {
            Imgproc.adaptiveThreshold(src, dst, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 59, 0.0);
        } else {
            Imgproc.adaptiveThreshold(src, dst, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, seek, 0.0);
        }
        Utils.matToBitmap(dst, bitmap);
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;

    }


    /**
     * 直方图均衡化处理图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap equalizeHistImage(Bitmap bitmap) {
        startTime = System.currentTimeMillis();
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        //转为灰度图片
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2GRAY);
        //直方图均衡化输入图像一定要单通道的
        Imgproc.equalizeHist(src, dst);
        Utils.matToBitmap(dst, bitmap);
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;
    }


    /**
     * 索贝阿梯度处理图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap sobelImage(Bitmap bitmap, int commend) {
        startTime = System.currentTimeMillis();
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        //转为灰度图片
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2GRAY);
        if (commend == 1) {
            Imgproc.Sobel(src, dst, CvType.CV_16S, 1, 0);
            Core.convertScaleAbs(dst, dst);
        } else if (commend == 2) {
            Imgproc.Sobel(src, dst, CvType.CV_16S, 0, 1);
            Core.convertScaleAbs(dst, dst);
        } else {
            Mat xSobel = new Mat();
            Mat ySobel = new Mat();
            Imgproc.Sobel(src, xSobel, CvType.CV_16S, 1, 0);
            Imgproc.Sobel(src, ySobel, CvType.CV_16S, 0, 1);
            Core.convertScaleAbs(xSobel, xSobel);
            Core.convertScaleAbs(ySobel, ySobel);
            Core.addWeighted(xSobel, 0.5, ySobel, 0.5, 0.0, dst);
            Core.convertScaleAbs(dst, dst);
            xSobel.release();
            ySobel.release();
        }
        Utils.matToBitmap(dst, bitmap);
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;
    }


    /**
     * 轮廓检测
     * <p>
     * 步骤
     * <p>
     * 高斯模糊
     * 梯度计算
     * 非最大信号压制
     * 高低阈值链接
     * 显示
     *
     * @param bitmap
     * @return
     */
    public static Bitmap cannyImage(int progress, Bitmap bitmap) {
        startTime = System.currentTimeMillis();
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(gaussianBlurImage(bitmap), src);
        //转为灰度图片
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2GRAY);
        //轮廓检测
        Imgproc.Canny(src, dst, progress, 2 * progress, 3, true);
        Utils.matToBitmap(dst, bitmap);
        src.release();
        dst.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;
    }


    /**
     * 霍夫直线检测
     *
     * @param bitmap
     * @return
     */
    public static Bitmap houghLinesImage(int progress, Bitmap bitmap) {
        startTime = System.currentTimeMillis();
        Mat src = new Mat();
        Mat lines = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        Imgproc.GaussianBlur(src, src, new Size(3, 3), 0, 0, Imgproc.BORDER_DEFAULT);
        Mat drawImage = new Mat(src.size(), src.type());
        //转为灰度图片
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2GRAY);
        //轮廓检测
        Imgproc.Canny(src, dst, progress, 2 * progress, 3, false);
        //直线检测
//        Imgproc.HoughLines(dst, lines, 1, Math.PI / 180.0, progress);
//        double[] linesp = new double[2];
//        for (int i = 0; i < lines.cols(); i++) {
//            linesp = lines.get(0, i);
//            double rho = linesp[0];
//            double thete = linesp[1];
//            double a = Math.cos(thete);
//            double b = Math.sin(thete);
//            double x0 = a * rho;
//            double y0 = b * rho;
//            Point point = new Point(x0 + 1000 * (-b), y0 + 1000 * a);
//            Point point1 = new Point(x0 - 1000 * (-b), y0 - 1000 * a);
//            Core.line(drawImage, point, point1, new Scalar(255, 0, 0, 0), 2, 8, 0);
//        }

        Imgproc.HoughLinesP(dst, lines, 1, Math.PI / 180.0, progress > 0 ? progress : 3, 75, 3);
        double[] pts = new double[4];
        for (int i = 0; i < lines.cols(); i++) {
            pts = lines.get(0, i);
            Point point = new Point(pts[0], pts[1]);
            Point point1 = new Point(pts[2], pts[3]);
            Core.line(drawImage, point, point1, new Scalar(255, 0, 0, 0), 2, 8, 0);
        }
        Mat finalMat = new Mat();
        Mat src1 = new Mat();
        Utils.bitmapToMat(bitmap, src1);
        Core.addWeighted(drawImage, 0.6, src1, 0.7, 0.0, finalMat);
        Utils.matToBitmap(finalMat, bitmap);
        finalMat.release();
        src1.release();
        src.release();
        lines.release();
        drawImage.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;
    }


    /**
     * 霍夫圆检测
     *
     * @param bitmap
     * @return
     */
    public static Bitmap houghCirclesImage(int progress, Bitmap bitmap) {
        startTime = System.currentTimeMillis();
        Mat src = new Mat();
        Mat lines = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        Mat drawImage = new Mat(src.size(), src.type());
        //转为灰度图片
        Imgproc.cvtColor(src, src, Imgproc.COLOR_BGRA2GRAY);
        //轮廓检测
        Imgproc.Canny(src, dst, progress, 2 * progress, 3, false);
        //圆检测
        Imgproc.HoughCircles(dst, dst, Imgproc.CV_HOUGH_GRADIENT, 1, 5, 2 * (progress > 0 ? progress : 3), 30, 25, 500);
        double[] circles = new double[3];
        for (int i = 0; i < dst.cols(); i++) {
            Point point = new Point(circles[0], circles[1]);
            Core.circle(drawImage, point, (int) circles[2], new Scalar(255, 0, 0, 0), 2, 8, 0);
        }
        Mat finalMat = new Mat();
        Mat src1 = new Mat();
        Utils.bitmapToMat(bitmap, src1);
        Core.addWeighted(drawImage, 0.6, src1, 0.7, 0.0, finalMat);
        Utils.matToBitmap(finalMat, bitmap);
        finalMat.release();
        src1.release();
        src.release();
        lines.release();
        drawImage.release();
        String method = new Throwable().getStackTrace()[0].getMethodName();
        time = System.currentTimeMillis() - startTime;
        Log.i(TAG, method + ": " + time + "");
        return bitmap;
    }
}

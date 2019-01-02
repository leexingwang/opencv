# Android 端opencv对于图片的基本操作
<video id="video" controls="" preload="none" poster="https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-095825.png"><source id="mp4" src="https://github.com/leexingwang/opencv/blob/master/video/device-2019-01-02-144655.mp4" type="video/mp4"></video>

ImageProcessType_convert2Gray = "图像灰度";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-095825.png)
ImageProcessType_invert = "单个像素取反操作";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-095849.png)
ImageProcessType_fastInvert = "快速取反操作";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-095849.png)
ImageProcessType_openCVInvert = "直接调用OpenCV取反操作";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-095849.png)
ImageProcessType_subtractImage = "两张图片相减操作";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-095908.png)
ImageProcessType_addImage = "两张图片相加操作";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-095920.png)
ImageProcessType_multiplyImage = "提高图片对比度操作";
-------
ImageProcessType_cutImage = "图像截取操作";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-095849.png)
ImageProcessType_blurImage = "均值模糊处理图片";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-095955.png)
ImageProcessType_gaussianBlurImage = "高斯模糊处理图片";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100014.png)
ImageProcessType_bilateralFilterImage = "双边模糊处理图片";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100031.png)
ImageProcessType_customFilterImage1 = "自定义算子处理图片--模糊";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100051.png)
ImageProcessType_customFilterImage2 = "自定义算子处理图片--拉普拉斯边缘";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100103.png)
ImageProcessType_customFilterImage3 = "自定义算子处理图片--锐化";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100120.png)
ImageProcessType_erodeFilterImage = "腐蚀/最小值滤波处理图片";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100133.png)
ImageProcessType_dilateFilterImage = "膨胀/最大值滤波处理图片";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100145.png)
ImageProcessType_openImage = "开操作处理图片";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100206.png)
ImageProcessType_closeImage = "闭操作处理图片";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100215.png)
ImageProcessType_morphLineFindImage = "开操作直线检测";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100246.png)
ImageProcessType_THRESH_BINARY = "阈值二值化处理图片(可以拖动SeekBar)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100310.png)
ImageProcessType_THRESH_BINARY_INV = "阈值反二值化处理图片(可以拖动SeekBar)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100324.png)
ImageProcessType_THRESH_TOZERO = "阈值取零二值化处理图片(可以拖动SeekBar)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100338.png)
ImageProcessType_THRESH_TOZERO_INV = "阈值反取零二值化处理图片(可以拖动SeekBar)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100400.png)
ImageProcessType_THRESH_TRUNC = "截断二值化处理图片(可以拖动SeekBar)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100418.png)
ImageProcessType_adaptiveGAUSSIANThresholdImage = "自适应高斯阈值处理图片(可以拖动SeekBar)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100430.png)
ImageProcessType_adaptiveMEAN_CThresholdImage = "自适应均值-c阈值处理图片(可以拖动SeekBar)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100447.png)
ImageProcessType_equalizeHistImage = "直方图均值化处理图片";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100500.png)
ImageProcessType_sobelImage_x = "Sobel算子X处理图片";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100515.png)
ImageProcessType_sobelImage_y = "Sobel算子Y处理图片";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100526.png)
ImageProcessType_sobelImage_xy = "Sobel算子XY处理图片";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100539.png)
ImageProcessType_cannyImage = "图片边缘检测(可以拖动SeekBar)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100606.png)
ImageProcessType_houghLinesImage = "图片霍夫直线检测(可以拖动SeekBar)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100629.png)
ImageProcessType_houghCirclesImage = "图片霍夫圆检测(可以拖动SeekBar)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100650.png)
ImageProcessType_matchTemplateImage = "模式匹配图像检测";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100712.png)
ImageProcessType_findContoursImage = "图片轮廓检测(可以拖动SeekBar)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100745.png)
ImageProcessType_momentsImage = "轮廓周长面积计算(可以拖动SeekBar)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100826.png)
ImageProcessType_findFaceImage = "人脸检测";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100851.png)
ImageProcessType_smileFaceImage = "人脸笑容检测(不准)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100903.png)
ImageProcessType_eyeFaceImage = "人脸眼睛检测(不准)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100914.png)
ImageProcessType_mouthFaceImage = "人脸嘴检测(不准)";
-------
![](https://github.com/leexingwang/opencv/blob/master/pic/device-2019-01-02-100927.png)
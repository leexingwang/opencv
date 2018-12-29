package com.example.lixingwang.opencv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.opencv.android.OpenCVLoader;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private List<String> data_list;
    private Button button;
    private ImageView imageView;
    private SeekBar seekBar;
    private TextView textView;
    private ArrayAdapter<String> arr_adapter;
    private String commend = "";
    private Bitmap bitmap;
    private Bitmap bitmap1;
    private BitmapFactory.Options options;
    private CascadeClassifier cascadeClassifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOpenCV();
        spinner = findViewById(R.id.spinner);
        data_list = ImageProcessUtils.getCommendList();
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textview);
        button = findViewById(R.id.button2);
        imageView = findViewById(R.id.imageView);

        seekBar.setMax(255);
        seekBar.setProgress(0);
        textView.setText("当前值为：0");
        options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.neina, options);
        bitmap1 = bitmap.copy(options.inPreferredConfig, true);
        imageView.setImageBitmap(bitmap1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (ImageProcessUtils.ImageProcessType_THRESH_BINARY.equals(commend) ||
                        ImageProcessUtils.ImageProcessType_THRESH_BINARY_INV.equals(commend) ||
                        ImageProcessUtils.ImageProcessType_THRESH_TOZERO.equals(commend) ||
                        ImageProcessUtils.ImageProcessType_THRESH_TOZERO_INV.equals(commend) ||
                        ImageProcessUtils.ImageProcessType_THRESH_TRUNC.equals(commend)) {
                    int commendInt = 1;
                    if (ImageProcessUtils.ImageProcessType_THRESH_BINARY.equals(commend)) {
                        commendInt = 1;
                    } else if (ImageProcessUtils.ImageProcessType_THRESH_BINARY_INV.equals(commend)) {
                        commendInt = 2;
                    } else if (ImageProcessUtils.ImageProcessType_THRESH_TOZERO.equals(commend)) {
                        commendInt = 3;
                    } else if (ImageProcessUtils.ImageProcessType_THRESH_TOZERO_INV.equals(commend)) {
                        commendInt = 4;
                    } else if (ImageProcessUtils.ImageProcessType_THRESH_TRUNC.equals(commend)) {
                        commendInt = 5;
                    }
                    textView.setText("当前值为：" + progress);
                    bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(ImageProcessUtils.thresholdImageSeekBar(progress, commendInt, bitmap1));
                }


                if (ImageProcessUtils.ImageProcessType_adaptiveGAUSSIANThresholdImage.equals(commend) ||
                        ImageProcessUtils.ImageProcessType_adaptiveMEAN_CThresholdImage.equals(commend)) {
                    int commendInt = 1;
                    if (ImageProcessUtils.ImageProcessType_adaptiveGAUSSIANThresholdImage.equals(commend)) {
                        commendInt = 1;
                    } else if (ImageProcessUtils.ImageProcessType_adaptiveMEAN_CThresholdImage.equals(commend)) {
                        commendInt = 2;
                    }
                    textView.setText("当前值为：" + progress);
                    bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(ImageProcessUtils.adaptiveThresholdImage(progress, commendInt, bitmap1));
                }
                if (ImageProcessUtils.ImageProcessType_cannyImage.equals(commend)) {
                    textView.setText("当前值为：" + progress);
                    bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(ImageProcessUtils.cannyImage(progress, bitmap1));
                }
                if (ImageProcessUtils.ImageProcessType_houghLinesImage.equals(commend)) {
                    textView.setText("当前值为：" + progress);
                    bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(ImageProcessUtils.houghLinesImage(progress, bitmap1));
                }

                if (ImageProcessUtils.ImageProcessType_houghCirclesImage.equals(commend)) {
                    textView.setText("当前值为：" + progress);
                    bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(ImageProcessUtils.houghCirclesImage(progress, bitmap1));
                }
                if (ImageProcessUtils.ImageProcessType_findContoursImage.equals(commend)) {
                    textView.setText("当前值为：" + progress);
                    bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(ImageProcessUtils.findContoursImage(progress, bitmap1));
                }
                if (ImageProcessUtils.ImageProcessType_momentsImage.equals(commend)) {
                    textView.setText("当前值为：" + progress);
                    bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(ImageProcessUtils.momentsImage(progress, bitmap1));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr_adapter);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                commend = data_list.get(arg2);
                if (ImageProcessUtils.ImageProcessType_morphLineFindImage.equals(commend)) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.findline, options);
                    Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(bitmap1);
                } else if (ImageProcessUtils.ImageProcessType_openImage.equals(commend) || ImageProcessUtils.ImageProcessType_closeImage.equals(commend)) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.text, options);
                    Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(bitmap1);
                } else if (ImageProcessUtils.ImageProcessType_houghLinesImage.equals(commend)) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.hough, options);
                    Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(bitmap1);
                } else if (ImageProcessUtils.ImageProcessType_houghCirclesImage.equals(commend)) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.houghc, options);
                    Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(bitmap1);
                } else if (ImageProcessUtils.ImageProcessType_matchTemplateImage.equals(commend)) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.tpsrc, options);
                    Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(bitmap1);
                } else if (ImageProcessUtils.ImageProcessType_findContoursImage.equals(commend)) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.houghc, options);
                    Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(bitmap1);
                } else if (ImageProcessUtils.ImageProcessType_momentsImage.equals(commend)) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.lunkuo, options);
                    Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(bitmap1);
                } else if (ImageProcessUtils.ImageProcessType_findFaceImage.equals(commend)||
                        ImageProcessUtils.ImageProcessType_smileFaceImage.equals(commend)||
                        ImageProcessUtils.ImageProcessType_eyeFaceImage.equals(commend)||
                        ImageProcessUtils.ImageProcessType_mouthFaceImage.equals(commend)) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.face, options);
                    Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(bitmap1);
                } else {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.neina, options);
                    Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(bitmap1);
                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(commend)) {
                    return;
                } else {
                    if (ImageProcessUtils.ImageProcessType_morphLineFindImage.equals(commend)) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.findline, options);
                        Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                        imageView.setImageBitmap(ImageProcessUtils.processByType(commend, bitmap1, MainActivity.this));
                    } else if (ImageProcessUtils.ImageProcessType_openImage.equals(commend) || ImageProcessUtils.ImageProcessType_closeImage.equals(commend)) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.text, options);
                        Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                        imageView.setImageBitmap(ImageProcessUtils.processByType(commend, bitmap1, MainActivity.this));
                    } else if (ImageProcessUtils.ImageProcessType_houghLinesImage.equals(commend)) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.hough, options);
                        Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                        imageView.setImageBitmap(ImageProcessUtils.processByType(commend, bitmap1, MainActivity.this));
                    } else if (ImageProcessUtils.ImageProcessType_houghCirclesImage.equals(commend)) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.houghc, options);
                        Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                        imageView.setImageBitmap(ImageProcessUtils.processByType(commend, bitmap1, MainActivity.this));
                    } else if (ImageProcessUtils.ImageProcessType_matchTemplateImage.equals(commend)) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.tpsrc, options);
                        Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                        imageView.setImageBitmap(ImageProcessUtils.processByType(commend, bitmap1, MainActivity.this));
                    } else if (ImageProcessUtils.ImageProcessType_findContoursImage.equals(commend)) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.houghc, options);
                        Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                        imageView.setImageBitmap(ImageProcessUtils.processByType(commend, bitmap1, MainActivity.this));
                    } else if (ImageProcessUtils.ImageProcessType_momentsImage.equals(commend)) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.lunkuo, options);
                        Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                        imageView.setImageBitmap(ImageProcessUtils.processByType(commend, bitmap1, MainActivity.this));
                    } else if (ImageProcessUtils.ImageProcessType_findFaceImage.equals(commend)) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.face, options);
                        Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                        imageView.setImageBitmap(ImageProcessUtils.findFaceImage(bitmap1, getFaceCascadeClassifier(1)));
                    } else if (ImageProcessUtils.ImageProcessType_smileFaceImage.equals(commend)) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.face, options);
                        Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                        imageView.setImageBitmap(ImageProcessUtils.findFaceImage(bitmap1, getFaceCascadeClassifier(2)));
                    } else if (ImageProcessUtils.ImageProcessType_eyeFaceImage.equals(commend)) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.face, options);
                        Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                        imageView.setImageBitmap(ImageProcessUtils.findFaceImage(bitmap1, getFaceCascadeClassifier(3)));
                    } else if (ImageProcessUtils.ImageProcessType_mouthFaceImage.equals(commend)) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.face, options);
                        Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                        imageView.setImageBitmap(ImageProcessUtils.findFaceImage(bitmap1, getFaceCascadeClassifier(4)));
                    } else {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.neina, options);
                        Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                        imageView.setImageBitmap(ImageProcessUtils.processByType(commend, bitmap1, MainActivity.this));
                    }

                }
            }
        });

    }

    private CascadeClassifier getFaceCascadeClassifier(int type) {
        InputStream inputStream = getResources().openRawResource(R.raw.haarcascade_frontalface_default);
        switch (type) {
            case 1:
                inputStream = getResources().openRawResource(R.raw.haarcascade_frontalface_default);
                break;
            case 2:
                inputStream = getResources().openRawResource(R.raw.haarcascade_smile);
                break;
            case 3:
                inputStream = getResources().openRawResource(R.raw.haarcascade_eye);
                break;
            case 4:
                inputStream = getResources().openRawResource(R.raw.haarcascade_mcs_mouth);
                break;
            default:
                inputStream = getResources().openRawResource(R.raw.haarcascade_frontalface_default);
                break;
        }
        try {
            File cascadeClassifierFile = this.getDir("CascadeClassifier", Context.MODE_PRIVATE);
            File file = new File(cascadeClassifierFile.getAbsolutePath(), "lbpcascade_frontalface.xml");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buff = new byte[1024];
            int lengh = 0;
            while ((lengh = inputStream.read(buff)) != -1) {
                fileOutputStream.write(buff, 0, lengh);
            }
            inputStream.close();
            fileOutputStream.close();
            cascadeClassifier = new CascadeClassifier(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cascadeClassifier;

    }

    private void initOpenCV() {
        OpenCVLoader.initDebug();
    }
}

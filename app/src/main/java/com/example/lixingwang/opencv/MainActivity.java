package com.example.lixingwang.opencv;

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
                    int commend = 1;
                    if (ImageProcessUtils.ImageProcessType_THRESH_BINARY.equals(commend)) {
                        commend = 1;
                    } else if (ImageProcessUtils.ImageProcessType_THRESH_BINARY_INV.equals(commend)) {
                        commend = 2;
                    } else if (ImageProcessUtils.ImageProcessType_THRESH_TOZERO.equals(commend)) {
                        commend = 3;
                    } else if (ImageProcessUtils.ImageProcessType_THRESH_TOZERO_INV.equals(commend)) {
                        commend = 4;
                    } else if (ImageProcessUtils.ImageProcessType_THRESH_TRUNC.equals(commend)) {
                        commend = 5;
                    }
                    textView.setText("当前值为：" + progress);
                    bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(ImageProcessUtils.thresholdImageSeekBar(progress, commend, bitmap1));
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
                } else {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    bitmap = BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.neina, options);
                    Bitmap bitmap1 = bitmap.copy(options.inPreferredConfig, true);
                    imageView.setImageBitmap(bitmap1);
                }

                seekBar.setProgress(0);
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

    private void initOpenCV() {
        OpenCVLoader.initDebug();
    }
}

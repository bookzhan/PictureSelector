package com.luck.pictureselector;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.config.SelectModeConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;

import java.util.ArrayList;

public class SimpleActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "bz_SimpleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Button btn_activity = findViewById(R.id.btn_activity);
        Button btn_inject_fragment = findViewById(R.id.btn_inject_fragment);
        Button btn_only_query_data = findViewById(R.id.btn_only_query_data);
        btn_activity.setOnClickListener(this);
        btn_inject_fragment.setOnClickListener(this);
        btn_only_query_data.setOnClickListener(this);
        findViewById(R.id.btn_audio).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_activity) {
            startActivity(new Intent(SimpleActivity.this, MainActivity.class));
        } else if (v.getId() == R.id.btn_inject_fragment) {
            startActivity(new Intent(SimpleActivity.this, InjectFragmentActivity.class));
        } else if (v.getId() == R.id.btn_only_query_data) {
            startActivity(new Intent(SimpleActivity.this, OnlyQueryDataActivity.class));
        } else if (v.getId() == R.id.btn_audio) {
            PictureSelector.create(this)
                    .openGallery(SelectMimeType.ofAudio())
                    .setImageEngine(GlideEngine.createGlideEngine())
                    .isDisplayCamera(false)
                    .setSelectionMode(SelectModeConfig.SINGLE)
                    .isDirectReturnSingle(true)
                    .isPreviewVideo(false)
                    .isPreviewImage(false)
                    .setImageSpanCount(3)
                    .forResult(new OnResultCallbackListener<LocalMedia>() {
                        @Override
                        public void onResult(ArrayList<LocalMedia> result) {
                            if (null == result || result.isEmpty()) {
                                return;
                            }
                            LocalMedia localMedia = result.get(0);
                            String path = localMedia.getPath();
                            if (TextUtils.isEmpty(path) || TextUtils.isEmpty(localMedia.getMimeType())) {
                                return;
                            }
                            Log.d(TAG, "OriginalPath=" + localMedia.getOriginalPath());
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
        }
    }
}

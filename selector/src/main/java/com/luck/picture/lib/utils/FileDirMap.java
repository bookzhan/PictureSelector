package com.luck.picture.lib.utils;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;

import com.luck.picture.lib.config.SelectMimeType;

import java.io.File;
import java.util.HashMap;

/**
 * @author：luck
 * @date：2022/9/20 7:57 下午
 * @describe：FileDirMap
 */
public final class FileDirMap {

    public static String getFileDirPath(Context context, int type) {
        if (type == SelectMimeType.TYPE_IMAGE) {
            return getFileDir(context, Environment.DIRECTORY_PICTURES).getAbsolutePath();
        } else if (type == SelectMimeType.TYPE_VIDEO) {
            return getFileDir(context, Environment.DIRECTORY_MOVIES).getAbsolutePath();
        } else if (type == SelectMimeType.TYPE_AUDIO) {
            return getFileDir(context, Environment.DIRECTORY_MUSIC).getAbsolutePath();
        }
        return getFileDir(context, Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
    }

    @NonNull
    private static File getFileDir(Context context, String type) {
        File filesDir;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            filesDir = context.getExternalFilesDir(type);
            if (null == filesDir) {
                filesDir = context.getFilesDir();
            }
        } else {
            filesDir = context.getFilesDir();
        }
        return filesDir;
    }
}

package com.luck.picture.lib.interfaces;

import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by bookzhan on 2023−07-14 22:17.
 * description:
 */
public interface OnSelectedChangedListener {
    void onSelectedChanged(List<LocalMedia> selectedMediaList);
}

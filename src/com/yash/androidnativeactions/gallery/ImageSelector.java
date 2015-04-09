package com.yash.androidnativeactions.gallery;

import com.yash.androidnativeactions.hook;

import android.content.Intent;

public class ImageSelector implements Runnable{

	@Override
	public void run() {
		Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        hook.activity.startActivityForResult(Intent.createChooser(intent,"Select Picture"), hook.Select_Image);
	}

}

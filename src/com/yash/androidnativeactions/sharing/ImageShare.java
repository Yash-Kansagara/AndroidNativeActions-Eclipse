package com.yash.androidnativeactions.sharing;

import java.io.File;

import android.content.Intent;
import android.net.Uri;

import com.yash.androidnativeactions.hook;

public class ImageShare implements Runnable{

	String imagePath;
	public ImageShare(String iPath) {
		imagePath = iPath;
	}
	@Override
	public void run() {
		
		try {
			if(imagePath == "" || imagePath == null){
				return;
			}
			File imageFile = new File(imagePath);
			if( !imageFile.exists() ){
				return;
			}
			
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			//sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
			
			//byte[] imageData = new byte[(int) imageFile.length()];
			//FileInputStream fis = new FileInputStream(imageFile);
			//fis.read(imageData);
			//fis.close();
			sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imageFile));
			sendIntent.setType("image/*");
			hook.activity.startActivity(Intent.createChooser(sendIntent, "Share With..."));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}

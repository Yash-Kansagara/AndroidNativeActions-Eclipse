package com.yash.androidnativeactions.router;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.unity3d.player.UnityPlayer;

public class Router extends Activity {
	
	public static final int GalleryRequest = 101;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		int request = this.getIntent().getIntExtra("toStart", -1);
		
		Intent intent = new Intent();
		switch (request) {
		case GalleryRequest:
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(intent, GalleryRequest);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Activity.RESULT_OK){
			if(requestCode == GalleryRequest){
				String imagePath = data.getExtras().getString("imagePath");
				UnityPlayer.UnitySendMessage("AndroidNativeEventsReceiver", "OnImageSelected", imagePath);
			}
		}else{
			System.out.print("Result code in router:"+resultCode);
		}
		
		setResult(resultCode);
		finish();
	}
	
	
}

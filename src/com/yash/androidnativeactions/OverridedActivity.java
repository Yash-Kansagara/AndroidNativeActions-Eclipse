package com.yash.androidnativeactions;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerNativeActivity;

public class OverridedActivity extends UnityPlayerNativeActivity {

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		System.out.println("******************"+resultCode+" should be "+Activity.RESULT_OK);
	    if (resultCode == Activity.RESULT_OK) {
        	 
        	if (requestCode == hook.Select_Image) {
                Uri selectedImageUri = data.getData();
                String selectedImagePath = selectedImageUri.getPath();
                UnityPlayer.UnitySendMessage(hook.sendActivityResultOn, "imageSelected", selectedImagePath);
            }
        }
	    
	}

}

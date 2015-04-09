package com.yash.androidnativeactions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.unity3d.player.UnityPlayer;
import com.yash.androidnativeactions.ads.BannerLoader;
import com.yash.androidnativeactions.ads.InterstitialLoader;
import com.yash.androidnativeactions.gallery.ImageSelector;
import com.yash.androidnativeactions.router.Router;
import com.yash.androidnativeactions.sharing.ImageShare;

public class hook {

		public static Activity activity;
		public static AdView bannerAd;
		public static InterstitialAd interstitialAd;
		public static boolean isTestAd;
		
		public static int Select_Image = 1;
		
		public hook(Activity a) {
			activity = a;
		}
		

		public static void LoadBannerAd(String bannerID,String position){
			BannerLoader loader = new BannerLoader(bannerID, position);
			activity.runOnUiThread(loader);
		}
		
		public static void LoadInterstitial(String id){
			InterstitialLoader loader = new InterstitialLoader(id);
			activity.runOnUiThread(loader);
		}
		
		public static void HideBannerAd() {
			if(bannerAd != null){
				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						bannerAd.setVisibility(View.INVISIBLE);
					}
				});
				
			}
		}
		
		public static void ShowBannerAd() {
			if(bannerAd != null && bannerAd.getVisibility() == View.INVISIBLE){
				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						bannerAd.setVisibility(View.VISIBLE);
					}
				});
				
			}
		}
		
		public static void DestroyBannerAd(){
			if(bannerAd != null){
				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						
						bannerAd.destroy();
					}
				});
			}
		}
		
		public static void ShowInterstitial(){
			if(interstitialAd != null ){
				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if(interstitialAd.isLoaded()){
							interstitialAd.show();
						}
					}
				});
			}
		}
		
		public static String GetTestDeviceID(){
			String android_id = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
	        String deviceId = md5(android_id).toUpperCase(Locale.ENGLISH);
			return deviceId;
			
		}


		public static String sendActivityResultOn;
		
		public static void SelectImage(String objectName){
//			sendActivityResultOn = objectName;
//			ImageSelector is = new ImageSelector();
//			activity.runOnUiThread(is);
			
			Intent intent = new Intent();
			intent.setAction("androidnativeactions.Router");
			activity.startActivityForResult(intent, Router.GalleryRequest);
		}
		
		public static void ShareImage(String path){
			ImageShare is = new ImageShare(path);
			activity.runOnUiThread(is);
		}
		
		public static void ShareText(String text){
			final String temptext = text;
			activity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Intent sendIntent = new Intent();
					sendIntent.setAction(Intent.ACTION_SEND);
					sendIntent.putExtra(Intent.EXTRA_TEXT, temptext);
					sendIntent.setType("text/*");
					activity.startActivity(Intent.createChooser(sendIntent, "Share With..."));
				}
			});
		}
		
		
		
		
		
		
		private static final String md5(final String s) {
		    try {
		        // Create MD5 Hash
		        MessageDigest digest = java.security.MessageDigest
		                .getInstance("MD5");
		        digest.update(s.getBytes());
		        byte messageDigest[] = digest.digest();

		        // Create Hex String
		        StringBuffer hexString = new StringBuffer();
		        for (int i = 0; i < messageDigest.length; i++) {
		            String h = Integer.toHexString(0xFF & messageDigest[i]);
		            while (h.length() < 2)
		                h = "0" + h;
		            hexString.append(h);
		        }
		        return hexString.toString();

		    } catch (NoSuchAlgorithmException e) {
		       
		    }
		    return "";
		}
}

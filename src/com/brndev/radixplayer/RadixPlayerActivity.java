package com.brndev.radixplayer;

import com.appbrain.AppBrain;
import com.appbrain.AppBrainBanner;
import com.mobfox.video.sdk.MobFoxAdManager;
import com.tapfortap.AdView;
import com.tapfortap.TapForTap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RadixPlayerActivity extends Activity {
	
	
    private Button button;
    private EditText et;
    private EditText et2;
    private AutoCompleteTextView actv;
    
    private String number;
    private String radix;
    private String answer;
    
    private LinearLayout layout;
    private AppBrainBanner banner;
    private AdView adView;
    
	private static String APPLICATION_ID ="";
	  private MobFoxAdManager manager;
	  
	  
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        AppBrain.init(this);
        
        //RevMobAds.startSession(this, APPLICATION_ID);
        
        TapForTap.setDefaultAppId("2004bb10-cecf-012f-fc23-4040d804a637");
        TapForTap.checkIn(this);
        
        

        
        // Starting RevMob session
        //RevMobAds.startSession(this, APPLICATION_ID);
        
        // Create the banner
        //Banner banner = new Banner(APPLICATION_ID, this);

        
       adView = (AdView) findViewById(R.id.ad_view);
 
        
        adView.loadAds();
        
        button = (Button) findViewById(R.id.button1);
        et = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

        
        button.setOnClickListener(
                new OnClickListener() {
            public void onClick(View v) {
            	
            	number = et.getText().toString();
            	radix = et2.getText().toString();
            	

            	try {
            		
            		if(Integer.decode(radix)<=36) {
            			answer = Integer.toString(Integer.decode(number),Integer.decode(radix));
            		}
            		else {
            			answer = "Radix values cannot exceed 36!";
            		}
            		
            	}
            	catch(Exception e) {
            		Toast.makeText(getBaseContext(), "Error: "+e, Toast.LENGTH_SHORT).show();  
            	}
            	
            	
            	actv.setText(answer);
            }
        });
    }
    
    
    public void onResume() {
    	super.onResume();
    	
    	adView.loadAds();
    }
    
    
    public void onBackPressed() {
    	super.onBackPressed();
    	
    	AppBrain.getAds().showInterstitial(this);
    }
}
package com.newhollandpress.apicture;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;

@TargetApi(5)
public class ShowPicture extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deliver);
        
       /* final Button upload = (Button) this.findViewById(R.id.button2);
        upload.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Upload picture
				
                CharSequence error = "Upload";
                int duration = Toast.LENGTH_SHORT;
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, error, duration);
                toast.show();
			}
        	
        });*/
        final Button send = (Button) this.findViewById(R.id.button1);
        send.setOnClickListener(new View.OnClickListener() {

  


        	private void getStream(InputStream in,String line) {
        	  BufferedReader reader = null;
        	  try {
        	    reader = new BufferedReader(new InputStreamReader(in));
        	    //String line = "";
        	    int i=0;
        	    while ((line += reader.readLine()) != null) {
        	      i+=1;
        	    }
        	  } catch (IOException e) {
        	    e.printStackTrace();
        	  } finally {
        	    if (reader != null) {
        	      try {
        	        reader.close();
        	      } catch (IOException e) {
        	        e.printStackTrace();
        	        }
        	    }
        	  }
        	} 
        	
        	
        	@TargetApi(8)
            public void onClick(View v) {
                // TODO Auto-generated method stub
/*
                try {   
                	final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);                    
                    emailIntent.setType("plain/text");
                    // how to fetch home email address
                    // stackoverflow.com/questions/2112965/how-to-get-the-android-devices-primary-e-mail-address
                    Pattern emailPattern = Patterns.EMAIL_ADDRESS; //
                    Account[] accounts = AccountManager.get(getBaseContext()).getAccounts();
                    boolean foundEmail = false;
                    for (Account account: accounts) {
                    	if (emailPattern.matcher(account.name).matches()) {
                    		String ourEmail = account.name;
                            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, ourEmail);             
                    	    foundEmail = true;
                    	}
                    }
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, R.id.editText1);             
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, R.id.TextView1);
                    String mIntentString;   
                    //Context context = getApplicationContext();

                    //int duration = Toast.LENGTH_SHORT;
                    
                    Bundle extras = getIntent().getExtras();
                    mIntentString = extras.getString("picture");
                    emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+mIntentString));
                    
                    if (foundEmail) {
                    	ShowPicture.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    }
            } catch (Exception e) {   
                    Log.e("SendMail", e.getMessage(), e);
                    CharSequence error = e.getMessage();
                    int duration = Toast.LENGTH_SHORT;
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, error, duration);
                    toast.show();
            } */

                    Bundle extras = getIntent().getExtras();
                    String mIntentString = extras.getString("picture");
        			//FileInputStream fis;
                    Context context = getApplicationContext();
					try {
						//File storageDir = Environment.getExternalStorageDirectory();
						//File outFileDirectory = new File(storageDir.toString()+"/data/com.newhollandpress.apicture/");
						//String fileName = outFileDirectory.toString() + "/1390185079536.jpg";
						//FileInputStream fis = new FileInputStream(mIntentString);
	        			HttpGetServletActivity htfu = new HttpGetServletActivity();
	        			htfu.doStart(mIntentString);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			      	// in a thread which is not the user interface
		        	// thread

		        	/*try {
		        	  URL url = new URL("http://69.142.93.192:8080/android/android");
		        	  HttpURLConnection con = (HttpURLConnection) url
		        	    .openConnection();
		        	  String line = "1234 ";
		        	  getStream(con.getInputStream(),line);
						
					  //CharSequence text = "Uploading File! "+mIntentString;
	                  int duration = Toast.LENGTH_SHORT;

	                  Toast toast = Toast.makeText(context, line, duration);
	                  toast.show();
		        	  } catch (Exception e) {
		        	  e.printStackTrace();
		        	}
*/
					
				//	CharSequence text = "Uploading File! "+mIntentString;
                  //  int duration = Toast.LENGTH_SHORT;

                    //Toast toast = Toast.makeText(context, text, duration);
                    //toast.show();

//        			Read more: http://getablogger.blogspot.com/2008/01/android-how-to-post-file-to-php-server.html#ixzz2iHbLN9h5

 
				//Intent returnToSnap = new Intent(ShowPicture.this, CameraDemo.class);
		        //startActivityForResult(returnToSnap,0);

            }
        });

        
        
        String mIntentString;   
        //Context context = getApplicationContext();

        //int duration = Toast.LENGTH_SHORT;
        
        Bundle extras = getIntent().getExtras();
        mIntentString = extras.getString("picture");
   		//LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        		      //      View cellView = this.findViewById(R.layout.deliver);


        		         TextView dateText = (TextView)findViewById(R.id.TextView1);
        		             dateText.setText(mIntentString);
        		             //File defName = new File(mIntentString);
        			            //outFileName = defName.toString();
        						FileInputStream inStream = null;
								try {
									inStream = new FileInputStream(mIntentString);
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
        						//outStream.write(data);
        						//outStream.close();
        						Resources res = getResources();
        						Drawable bitImage = new BitmapDrawable(res,inStream);
        							
        ImageView imgV = (ImageView)findViewById(R.id.imageView1);
        imgV.setBackgroundColor(44);
        imgV.setImageDrawable(bitImage);
     
        //EditText tv = new EditText(this);
        //tv.setId(R.id.editText1);
        //tv.append(mIntentString);
        
        
        
        //LayoutInflater ll = this.getLayoutInflater();
        //R.id.editText1 = mIntentString;
        //String editText2 = new String(savedInstanceState.getString("picture"));
        //Toast toast = Toast.makeText(context,mIntentString,duration);
        //toast.show();
       //TextView theText = (TextView)ll.inflate(R.id.editText1,null);
        //theText.setText(mIntentString);
        //String[] from = new String[] {"col_1", "col_2"};
        //int[] to = new int[] { R.id.editText1, R.id.editText1 };
        /* Fill Fill Maps
         * 
         */
       // List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
      //  HashMap<String, String> map = new HashMap<String, String>();

        //map.put("col_1", editText2); 
        //fillMaps.add(map);
        /* Fill Fill Maps
         * 
         */
        //SimpleAdapter adapter = new SimpleAdapter(ShowPicture.this, fillMaps, R.layout.deliver, from, to);
    }
}

package com.newhollandpress.apicture;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.mime.*;
import org.apache.http.entity.mime.content.*;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.InputStream;
import java.io.InputStreamReader;


import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.os.Environment;
import android.util.Log;
import android.util.Patterns;

import android.widget.ImageView;

import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
 
public class HttpGetServletActivity extends Activity implements OnClickListener{
    Button button;
    TextView outputText;
 
    public static final String URL = "http://newhollandpress.com/upload/index/";
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
 
        //findViewsById();
 
       // button.setOnClickListener(this);
    }
 
    public void doStart(String filepath) {
    	//getOutputFromUrl(fis,URL);
    	putPost(filepath,URL);
    }

    private String putPost(String filepath,String url) {
        String output = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                //HttpGet httpGet = new HttpGet(url);
                MultipartEntity mpEntity = new MultipartEntity();
                
                File file = new File(filepath);
                FileBody cbFile = new FileBody(file,"image/jpg");
                cbFile.getMediaType();
                mpEntity.addPart("file",cbFile);
                
                StringBody cbString = new StringBody("Hello World!");
                mpEntity.addPart("description",cbString);
                
                
                httpPost.setEntity(mpEntity);
                
                

               /* httpPost.addHeader("Content-Type","image/jpeg;");
                File file = new File(filepath); 
                FileEntity fileentity; 
                

                fileentity = new FileEntity(file,"image/jpeg;"); 
                fileentity.setChunked(true); 
                httpPost.setEntity(fileentity);
                 
                */
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                output = EntityUtils.toString(httpEntity);

                //Toast.makeText(getApplicationContext(), output, 1000);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            //Toast.makeText(getApplicationContext(),output,1000);
            return output;
     }
 
    private String getOutputFromUrl(FileInputStream fis,String url) {
        String output = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
                //HttpPost httpPost = new HttpPost(url);
                HttpGet httpGet = new HttpGet(url);
                
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                output = EntityUtils.toString(httpEntity);
                //Toast.makeText(getApplicationContext(), output, 1000);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            //Toast.makeText(getApplicationContext(),output,1000);
            return output;
     }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
 }

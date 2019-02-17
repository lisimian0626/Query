package com.example.taxt.asynctask;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.example.taxt.webservice.SoapService;

import java.util.HashMap;
import java.util.Map;

/*
 * 
 */
public class GetDataTask extends AsyncTask<String, Integer, String>{
	private Context context;
	private Handler handler;
	ProgressDialog pd=null;
	private int currentversion;
	char mark;
	SharedPreferences preferences;
	private Map<String, Object> params_map = new HashMap<String, Object>();
	private String method_name;
    private String  f_name;
    private boolean win=false;
    private String landing=null;
	public GetDataTask(Handler handler, Context context, ProgressDialog pd, Map<String,Object> map, String m_name, String json) {
    this.context = context;
    this.pd=pd;
	this.params_map=map;
	this.method_name=m_name;
    this. f_name=json;
    this.handler=handler;
	}

	@Override
	protected String doInBackground(String... params) {
		SoapService e = new SoapService(handler,context,pd, params_map, method_name);
		String s = e.weConnect();
	
		if (s != null) {
			return insert(s);
		} else
			return null;
	}

	@Override
	protected void onPostExecute(String result) {
		pd.dismiss();
		if(result!=null){

		}
		
		

		super.onPostExecute(result);
	}

	private String insert(String json) {
		if(f_name.equals("getStationList")){
			Log.i("test","data:"+json);
		}
		return json;
		
	
	}
	

	
}

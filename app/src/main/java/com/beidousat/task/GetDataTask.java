package com.beidousat.task;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;


import com.beidousat.querydata.common.Constant;
import com.beidousat.querydata.http.webservice.SoapService;

import java.util.HashMap;
import java.util.Map;

/*
 * 
 */
public class GetDataTask extends AsyncTask<String, Integer, String>{
	private Map<String, Object> params_map ;
	public GetDataTask(Map<String,Object> map) {
	this.params_map=map;
	}

	@Override
	protected String doInBackground(String... params) {
		return SoapService.callWS(Constant.NAMESPACE,Constant.GETSTATIONLIST,Constant.WEBSERVICE_URL,params_map);
	}

	@Override
	protected void onPostExecute(String result) {
		if(result!=null){
          insert(result);
		}
		
		

		super.onPostExecute(result);
	}

	private String insert(String json) {
	    Log.i("test",json);
		return json;
		
	
	}
	

	
}

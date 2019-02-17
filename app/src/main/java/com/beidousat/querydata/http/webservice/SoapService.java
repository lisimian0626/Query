package com.example.taxt.webservice;

import java.io.IOException;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.ServiceConnection;
import org.ksoap2.transport.ServiceConnectionSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.widget.Toast;

import com.example.taxt.util.Gloable;
import com.example.taxt.util.NewToast;

public class SoapService {
private static Map<String, Object> map=null;
private static String f;
private static Context context;
private static final int timeOut=20*1000;
private ProgressDialog pd=null;
private Handler mhandler;
//private Handler handler;
public SoapService(Handler handler,Context context1 ,ProgressDialog pd,Map<String, Object> map1,String fa){
		map=map1;
		mhandler=handler;
		f=fa;
		context=context1;
		this.pd=pd;
}

public  String weConnect(){
	
	 SoapObject soap=new SoapObject(Gloable.NAMESPACE, f);
//	System.out.println(preferences.getString("nk", "")+"!!@!@");
//	SoapObject soap=new SoapObject(preferences.getString("nk", ""), f);
	 for(String key:map.keySet()){
		 
		 soap.addProperty(key, map.get(key));
		 
	 }
	 
	  SoapSerializationEnvelope sse = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	                            sse.bodyOut=soap;
                                sse.dotNet=true;  
                                
//      HttpTransportSE  hse = new   HttpTransportSE(context.getResources().getString(R.string.nw));
                                HttpTransportSE  hse = new   MyTransportSE(Gloable.WEBSERVICE_URL,timeOut);
      try {
    	    
    		
//    	    hse.call(context.getResources().getString(R.string.nasmx), sse); 
    	  hse.call(Gloable.WEBSERVICE_URL, sse);  	  
    	    System.out.println( sse.bodyIn.toString());
    	    
			SoapObject soap1=(SoapObject) sse.bodyIn;  
			String sss=soap1.getProperty(0).toString();
			  return sss;
		}
      catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			  mhandler.post(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Toast.makeText(context, "网络不给力，检查一下吧", 0).show();
					}
				});
			if(pd!=null){
			pd.dismiss();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            System.out.println("网络有问题，稍后重试");
            mhandler.post(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(context, "网络不给力，检查一下吧", 0).show();
				}
			});
            if(pd!=null){
    			pd.dismiss();
    			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			  mhandler.post(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Toast.makeText(context, "网络不给力，检查一下吧", 0).show();
					}
				});
			if(pd!=null){
				pd.dismiss();
				}
		}
		 return null;
}

private static class MyTransportSE extends HttpTransportSE{
	private String url="";
		private int timeOut=0;

	public MyTransportSE(String url,int timeOut) {
		super(url);
		this.url=url;
		this.timeOut=timeOut;
	}

	@Override
	protected ServiceConnection getServiceConnection() throws IOException {
		ServiceConnectionSE se=new ServiceConnectionSE(url, timeOut);
		return se;
	}
	
}

}

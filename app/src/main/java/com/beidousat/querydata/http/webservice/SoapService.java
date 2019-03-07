package com.beidousat.querydata.http.webservice;

import java.io.IOException;
import java.util.Iterator;
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
import android.util.Log;
import android.widget.Toast;

import com.beidousat.querydata.common.Constant;

public class SoapService {
//    private static Map<String, Object> map = null;
//    private static Context context;
//    private static final int timeOut = 20 * 1000;
//
//    //private Handler mhandler;
////private Handler handler;
//    public SoapService(Context context1, Map<String, Object> map1) {
//        map = map1;
//        context = context1;
//    }

//    public String weConnect() {
//
//        SoapObject soap = new SoapObject(Constant.NAMESPACE, Constant.GETSTATIONLIST);
////	System.out.println(preferences.getString("nk", "")+"!!@!@");
////	SoapObject soap=new SoapObject(preferences.getString("nk", ""), f);
//        for (String key : map.keySet()) {
//
//            soap.addProperty(key, map.get(key));
//
//        }
//
//        SoapSerializationEnvelope sse = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//        sse.bodyOut = soap;
//        sse.dotNet = true;
//
////      HttpTransportSE  hse = new   HttpTransportSE(context.getResources().getString(R.string.nw));
//        HttpTransportSE hse = new MyTransportSE(Constant.WEBSERVICE_URL, timeOut);
//        try {
//
//
////    	    hse.call(context.getResources().getString(R.string.nasmx), sse);
//            hse.call(Constant.WEBSERVICE_URL, sse);
//            System.out.println(sse.bodyIn.toString());
//
//            SoapObject soap1 = (SoapObject) sse.bodyIn;
//            String sss = soap1.getProperty(0).toString();
//            return sss;
//        } catch (NotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            System.out.println("网络有问题，稍后重试");
////			  mhandler.post(new Runnable() {
////					@Override
////					public void run() {
////						// TODO Auto-generated method stub
////						Toast.makeText(context, "网络不给力，检查一下吧", Toast.LENGTH_SHORT).show();
////					}
////				});
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
////            mhandler.post(new Runnable() {
////				@Override
////				public void run() {
////					// TODO Auto-generated method stub
////					Toast.makeText(context, "网络不给力，检查一下吧", Toast.LENGTH_SHORT).show();
////				}
////			});
//        } catch (XmlPullParserException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
////			  mhandler.post(new Runnable() {
////					@Override
////					public void run() {
////						// TODO Auto-generated method stub
////						Toast.makeText(context, "网络不给力，检查一下吧", Toast.LENGTH_SHORT).show();
////					}
////				});
//        }
//        return null;
//    }

//    private static class MyTransportSE extends HttpTransportSE {
//        private String url = "";
//        private int timeOut = 0;
//
//        public MyTransportSE(String url, int timeOut) {
//            super(url);
//            this.url = url;
//            this.timeOut = timeOut;
//        }
//
//        @Override
//        protected ServiceConnection getServiceConnection() throws IOException {
//            ServiceConnectionSE se = new ServiceConnectionSE(url, timeOut);
//            return se;
//        }
//
//    }
    /**
     * @param nameSpace  WS的命名空间
     * @param methodName WS的方法名
     * @param wsdl       WS的wsdl的完整路径名
     * @param params     WS的方法所需要的参数
     * @return           SoapObject对象
     */
    public static  String callWS(String nameSpace, String methodName,
                                    String wsdl, Map<String, Object> params) {
        final String SOAP_ACTION = nameSpace + methodName;
        SoapObject soapObject = new SoapObject(nameSpace, methodName);

        if ((params != null) && (!params.isEmpty())) {
            Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> e = (Map.Entry<String, Object>) it
                        .next();
                soapObject.addProperty(e.getKey(), e.getValue());
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;

        // 兼容.NET开发的Web Service
        envelope.dotNet = false;

        HttpTransportSE ht = new HttpTransportSE(wsdl);
        try {
            ht.call(SOAP_ACTION, envelope);
            if (envelope.getResponse() != null) {
                SoapObject soap = (SoapObject) envelope.bodyIn;
                String result = soap.getProperty(0).toString();
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
        return null;
    }

}

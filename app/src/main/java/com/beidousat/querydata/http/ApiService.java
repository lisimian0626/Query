package com.beidousat.querydata.http;

import com.beidousat.querydata.model.Banci;
import com.beidousat.querydata.model.Cash;
import com.beidousat.querydata.model.Gas;
import com.beidousat.querydata.model.ReCharge;
import com.beidousat.querydata.model.Station;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


public interface ApiService {
    @POST("/wxjfeng/gasappPort")
    Observable<Station> login(@HeaderMap Map<String,String> headerMap, @Body String body);

    @POST("/wxjfeng/gasappPort")
    Observable<Station> getStations(@HeaderMap Map<String,String> headerMap, @Body String body);

    @POST("/wxjfeng/gasappPort")
    Observable<ReCharge> getRechargeList(@HeaderMap Map<String,String> headerMap, @Body String body);

    @POST("/wxjfeng/gasappPort")
    Observable<Banci> getChangeDutyList(@HeaderMap Map<String,String> headerMap, @Body String body);

    @POST("/wxjfeng/gasappPort")
    Observable<ReCharge> getChangeDutyDetailList(@HeaderMap Map<String,String> headerMap, @Body String body);

    @POST("/wxjfeng/gasappPort")
    Observable<Cash> getCashList(@HeaderMap Map<String,String> headerMap, @Body String body);

    @POST("/wxjfeng/gasappPort")
    Observable<Gas> getGasList(@HeaderMap Map<String,String> headerMap, @Body String body);

    @POST("/wxjfeng/gasappPort")
    Observable<ReCharge> getEquipmentList(@HeaderMap Map<String,String> headerMap, @Body String body);
}

package com.beidousat.querydata.model;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Store {

    /**
     * root : {"data":[{"stationName":"北站站","checkTime":"2019-02-22 10:00:01.0","L1":"1123","P1":"987","T1":"25","L2":"1222","P2":"889","T2":"10","NN":"16","W3":"13","uploadTime":"2019-04-02 10:00:01.0"},{"stationName":"北站站","checkTime":"2019-03-22 10:00:01.0","L1":"1123","P1":"987","T1":"26","L2":"1222","P2":"889","T2":"28","NN":"15","W3":"13","uploadTime":"2019-04-02 10:00:01.0"},{"stationName":"北站站","checkTime":"2019-03-12 10:00:01.0","L1":"1123","P1":"987","T1":"27","L2":"1222","P2":"889","T2":"30","NN":"20","W3":"13","uploadTime":"2019-04-02 10:00:01.0"},{"stationName":"北站站","checkTime":"2019-02-02 10:00:01.0","L1":"1123","P1":"987","T1":"26","L2":"1222","P2":"889","T2":"28","NN":"15","W3":"13","uploadTime":"2019-04-02 10:00:01.0"},{"stationName":"北站站","checkTime":"2019-03-02 10:00:01.0","L1":"1123","P1":"987","T1":"27","L2":"1222","P2":"889","T2":"30","NN":"20","W3":"13","uploadTime":"2019-04-02 10:00:01.0"}],"response":{"errcode":"0"}}
     */

    private RootBean root;

    public static List<Store> arrayStoreFromData(String str) {

        Type listType = new TypeToken<ArrayList<Store>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public RootBean getRoot() {
        return root;
    }

    public void setRoot(RootBean root) {
        this.root = root;
    }

    public static class RootBean {
        /**
         * data : [{"stationName":"北站站","checkTime":"2019-02-22 10:00:01.0","L1":"1123","P1":"987","T1":"25","L2":"1222","P2":"889","T2":"10","NN":"16","W3":"13","uploadTime":"2019-04-02 10:00:01.0"},{"stationName":"北站站","checkTime":"2019-03-22 10:00:01.0","L1":"1123","P1":"987","T1":"26","L2":"1222","P2":"889","T2":"28","NN":"15","W3":"13","uploadTime":"2019-04-02 10:00:01.0"},{"stationName":"北站站","checkTime":"2019-03-12 10:00:01.0","L1":"1123","P1":"987","T1":"27","L2":"1222","P2":"889","T2":"30","NN":"20","W3":"13","uploadTime":"2019-04-02 10:00:01.0"},{"stationName":"北站站","checkTime":"2019-02-02 10:00:01.0","L1":"1123","P1":"987","T1":"26","L2":"1222","P2":"889","T2":"28","NN":"15","W3":"13","uploadTime":"2019-04-02 10:00:01.0"},{"stationName":"北站站","checkTime":"2019-03-02 10:00:01.0","L1":"1123","P1":"987","T1":"27","L2":"1222","P2":"889","T2":"30","NN":"20","W3":"13","uploadTime":"2019-04-02 10:00:01.0"}]
         * response : {"errcode":"0"}
         */
        private String total;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        private ResponseBean response;
        private List<DataBean> data;

        public static List<RootBean> arrayRootBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<RootBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public ResponseBean getResponse() {
            return response;
        }

        public void setResponse(ResponseBean response) {
            this.response = response;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class ResponseBean {
            /**
             * errcode : 0
             */

            private String errcode;

            public static List<ResponseBean> arrayResponseBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<ResponseBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getErrcode() {
                return errcode;
            }

            public void setErrcode(String errcode) {
                this.errcode = errcode;
            }
        }

        public static class DataBean {
            /**
             * stationName : 北站站
             * checkTime : 2019-02-22 10:00:01.0
             * L1 : 1123
             * P1 : 987
             * T1 : 25
             * L2 : 1222
             * P2 : 889
             * T2 : 10
             * NN : 16
             * W3 : 13
             * uploadTime : 2019-04-02 10:00:01.0
             */

            private String stationName;
            private String checkTime;
            private String L1;
            private String P1;
            private String T1;
            private String L2;
            private String P2;
            private String T2;
            private String NN;
            private String W3;
            private String uploadTime;

            public DataBean() {
            }

            public DataBean(String stationName, String checkTime, String l1, String p1, String t1, String l2, String p2, String t2, String NN, String w3, String uploadTime) {
                this.stationName = stationName;
                this.checkTime = checkTime;
                L1 = l1;
                P1 = p1;
                T1 = t1;
                L2 = l2;
                P2 = p2;
                T2 = t2;
                this.NN = NN;
                W3 = w3;
                this.uploadTime = uploadTime;
            }

            public static List<DataBean> arrayDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getStationName() {
                return stationName;
            }

            public void setStationName(String stationName) {
                this.stationName = stationName;
            }

            public String getCheckTime() {
                if(!TextUtils.isEmpty(checkTime)){
                    String str;
                    try {
                        str=checkTime.substring(0,checkTime.lastIndexOf(":"));
                    }catch (Exception e){
                        e.printStackTrace();
                        return checkTime;
                    }
                    return str;
                }
                return checkTime;
            }

            public void setCheckTime(String checkTime) {
                this.checkTime = checkTime;
            }

            public String getL1() {
                return L1;
            }

            public void setL1(String L1) {
                this.L1 = L1;
            }

            public String getP1() {
                return P1;
            }

            public void setP1(String P1) {
                this.P1 = P1;
            }

            public String getT1() {
                return T1;
            }

            public void setT1(String T1) {
                this.T1 = T1;
            }

            public String getL2() {
                return L2;
            }

            public void setL2(String L2) {
                this.L2 = L2;
            }

            public String getP2() {
                return P2;
            }

            public void setP2(String P2) {
                this.P2 = P2;
            }

            public String getT2() {
                return T2;
            }

            public void setT2(String T2) {
                this.T2 = T2;
            }

            public String getNN() {
                return NN;
            }

            public void setNN(String NN) {
                this.NN = NN;
            }

            public String getW3() {
                return W3;
            }

            public void setW3(String W3) {
                this.W3 = W3;
            }

            public String getUploadTime() {
                return uploadTime;
            }

            public void setUploadTime(String uploadTime) {
                this.uploadTime = uploadTime;
            }
        }
    }
}

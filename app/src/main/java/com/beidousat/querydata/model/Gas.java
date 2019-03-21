package com.beidousat.querydata.model;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Gas {

    /**
     * root : {"data":[{"dutyID":"3","startTime":"2019-02-20 17:59:00.0","sellCash":"1699.92","sellMoney":"15.22","chargeMoney1":"1000.00","refundMoney":"-684.70","reissueMoney":"0.00","reissueCount":"0","cardCount":"1","userName":"娃娃"},{"dutyID":"2","startTime":"2019-02-20 11:49:00.0","sellCash":"1000.00","sellMoney":"0.00","chargeMoney1":"1000.00","refundMoney":"0.00","reissueMoney":"0.00","reissueCount":"0","cardCount":"1","userName":"12"},{"dutyID":"1","startTime":"2019-02-19 10:41:00.0","sellCash":"440.00","sellMoney":"0.00","chargeMoney1":"440.00","refundMoney":"0.00","reissueMoney":"0.00","reissueCount":"0","cardCount":"5","userName":"123"}],"sum":{"sumsellCash":"3139.92","sumsellMoney":"15.22","sumchargeMoney1":"2440.00","sumrefundMoney":"-684.70","sumreissueMoney":"0.00","sumreissueCount":"0","sumcardCount":"7"},"total":"1","response":{"errcode":"0"}}
     */

    private RootBean root;

    public static List<Gas> arrayCashFromData(String str) {

        Type listType = new TypeToken<ArrayList<Gas>>() {
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
         * data : [{"dutyID":"3","startTime":"2019-02-20 17:59:00.0","sellCash":"1699.92","sellMoney":"15.22","chargeMoney1":"1000.00","refundMoney":"-684.70","reissueMoney":"0.00","reissueCount":"0","cardCount":"1","userName":"娃娃"},{"dutyID":"2","startTime":"2019-02-20 11:49:00.0","sellCash":"1000.00","sellMoney":"0.00","chargeMoney1":"1000.00","refundMoney":"0.00","reissueMoney":"0.00","reissueCount":"0","cardCount":"1","userName":"12"},{"dutyID":"1","startTime":"2019-02-19 10:41:00.0","sellCash":"440.00","sellMoney":"0.00","chargeMoney1":"440.00","refundMoney":"0.00","reissueMoney":"0.00","reissueCount":"0","cardCount":"5","userName":"123"}]
         * sum : {"sumsellCash":"3139.92","sumsellMoney":"15.22","sumchargeMoney1":"2440.00","sumrefundMoney":"-684.70","sumreissueMoney":"0.00","sumreissueCount":"0","sumcardCount":"7"}
         * total : 1
         * response : {"errcode":"0"}
         */

        private SumBean sum;
        private String total;
        private ResponseBean response;
        private List<DataBean> data;

        public static List<RootBean> arrayRootBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<RootBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public SumBean getSum() {
            return sum;
        }

        public void setSum(SumBean sum) {
            this.sum = sum;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
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

        public static class SumBean {
            /**
             * sumsellCash : 3139.92
             * sumsellMoney : 15.22
             * sumchargeMoney1 : 2440.00
             * sumrefundMoney : -684.70
             * sumreissueMoney : 0.00
             * sumreissueCount : 0
             * sumcardCount : 7
             */

            private String sumquantity;
            private String summoney;


            public static List<SumBean> arraySumBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<SumBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getSumquantity() {
                return sumquantity;
            }

            public void setSumquantity(String sumquantity) {
                this.sumquantity = sumquantity;
            }

            public String getSummoney() {
                return summoney;
            }

            public void setSummoney(String summoney) {
                this.summoney = summoney;
            }

            public Gas.RootBean.DataBean toDataBean(){
                Gas.RootBean.DataBean dataBean=new Gas.RootBean.DataBean();
                dataBean.setQuantity(getSumquantity());
                dataBean.setMoney(getSummoney());
                return dataBean;
            }
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
             * dutyID : 3
             * startTime : 2019-02-20 17:59:00.0
             * sellCash : 1699.92
             * sellMoney : 15.22
             * chargeMoney1 : 1000.00
             * refundMoney : -684.70
             * reissueMoney : 0.00
             * reissueCount : 0
             * cardCount : 1
             * userName : 娃娃
             */

            private String dutyID;
            private String startTime;
            private String carNo;
            private String gasNo;
            private String quantity;
            private String price;
            private String money;
            private String cardType;
            private String workerName;
            private String uploadTime;

            public DataBean() {
            }

            public DataBean(String dutyID, String startTime, String carNo, String gasNo, String quantity, String price, String money, String cardType, String workerName, String uploadTime) {
                this.dutyID = dutyID;
                this.startTime = startTime;
                this.carNo = carNo;
                this.gasNo = gasNo;
                this.quantity = quantity;
                this.price = price;
                this.money = money;
                this.cardType = cardType;
                this.workerName = workerName;
                this.uploadTime = uploadTime;
            }

            public static List<DataBean> arrayDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getDutyID() {
                return dutyID;
            }

            public void setDutyID(String dutyID) {
                this.dutyID = dutyID;
            }

            public String getStartTime() {
                if(!TextUtils.isEmpty(startTime)){
                    String str;
                    try {
                        str=startTime.substring(0,startTime.lastIndexOf(":"));
                    }catch (Exception e){
                        e.printStackTrace();
                        return startTime;
                    }
                    return str;
                }
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getCarNo() {
                return carNo;
            }

            public void setCarNo(String carNo) {
                this.carNo = carNo;
            }

            public String getGasNo() {
                return gasNo;
            }

            public void setGasNo(String gasNo) {
                this.gasNo = gasNo;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getCardType() {
                return cardType;
            }

            public void setCardType(String cardType) {
                this.cardType = cardType;
            }

            public String getWorkerName() {
                return workerName;
            }

            public void setWorkerName(String workerName) {
                this.workerName = workerName;
            }

            public String getUploadTime() {
                if(!TextUtils.isEmpty(uploadTime)){
                    String str;
                    try {
                        str=uploadTime.substring(0,uploadTime.lastIndexOf(":"));
                    }catch (Exception e){
                        e.printStackTrace();
                        return uploadTime;
                    }
                    return str;
                }
                return uploadTime;
            }

            public void setUploadTime(String uploadTime) {
                this.uploadTime = uploadTime;
            }
        }
    }
}

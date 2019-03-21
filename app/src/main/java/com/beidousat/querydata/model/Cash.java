package com.beidousat.querydata.model;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Cash {

    /**
     * root : {"data":[{"dutyID":"3","startTime":"2019-02-20 17:59:00.0","sellCash":"1699.92","sellMoney":"15.22","chargeMoney1":"1000.00","refundMoney":"-684.70","reissueMoney":"0.00","reissueCount":"0","cardCount":"1","userName":"娃娃"},{"dutyID":"2","startTime":"2019-02-20 11:49:00.0","sellCash":"1000.00","sellMoney":"0.00","chargeMoney1":"1000.00","refundMoney":"0.00","reissueMoney":"0.00","reissueCount":"0","cardCount":"1","userName":"12"},{"dutyID":"1","startTime":"2019-02-19 10:41:00.0","sellCash":"440.00","sellMoney":"0.00","chargeMoney1":"440.00","refundMoney":"0.00","reissueMoney":"0.00","reissueCount":"0","cardCount":"5","userName":"123"}],"sum":{"sumsellCash":"3139.92","sumsellMoney":"15.22","sumchargeMoney1":"2440.00","sumrefundMoney":"-684.70","sumreissueMoney":"0.00","sumreissueCount":"0","sumcardCount":"7"},"total":"1","response":{"errcode":"0"}}
     */

    private RootBean root;

    public static List<Cash> arrayCashFromData(String str) {

        Type listType = new TypeToken<ArrayList<Cash>>() {
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

            private String sumsellCash;
            private String sumsellMoney;
            private String sumchargeMoney1;
            private String sumrefundMoney;
            private String sumreissueMoney;
            private String sumreissueCount;
            private String sumcardCount;

            public static List<SumBean> arraySumBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<SumBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getSumsellCash() {
                return sumsellCash;
            }

            public void setSumsellCash(String sumsellCash) {
                this.sumsellCash = sumsellCash;
            }

            public String getSumsellMoney() {
                return sumsellMoney;
            }

            public void setSumsellMoney(String sumsellMoney) {
                this.sumsellMoney = sumsellMoney;
            }

            public String getSumchargeMoney1() {
                return sumchargeMoney1;
            }

            public void setSumchargeMoney1(String sumchargeMoney1) {
                this.sumchargeMoney1 = sumchargeMoney1;
            }

            public String getSumrefundMoney() {
                return sumrefundMoney;
            }

            public void setSumrefundMoney(String sumrefundMoney) {
                this.sumrefundMoney = sumrefundMoney;
            }

            public String getSumreissueMoney() {
                return sumreissueMoney;
            }

            public void setSumreissueMoney(String sumreissueMoney) {
                this.sumreissueMoney = sumreissueMoney;
            }

            public String getSumreissueCount() {
                return sumreissueCount;
            }

            public void setSumreissueCount(String sumreissueCount) {
                this.sumreissueCount = sumreissueCount;
            }

            public String getSumcardCount() {
                return sumcardCount;
            }

            public void setSumcardCount(String sumcardCount) {
                this.sumcardCount = sumcardCount;
            }
            public Cash.RootBean.DataBean toDataBean(){
                Cash.RootBean.DataBean dataBean=new Cash.RootBean.DataBean();
                dataBean.setSellCash(getSumsellCash());
                dataBean.setSellMoney(getSumsellMoney());
                dataBean.setChargeMoney1(getSumchargeMoney1());
                dataBean.setRefundMoney(getSumrefundMoney());
                dataBean.setReissueMoney(getSumreissueMoney());
                dataBean.setReissueCount(getSumreissueCount());
                dataBean.setCardCount(getSumcardCount());
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
            private String sellCash;
            private String sellMoney;
            private String chargeMoney1;
            private String refundMoney;
            private String reissueMoney;
            private String reissueCount;
            private String cardCount;
            private String userName;

            public DataBean() {
            }

            public DataBean(String dutyID, String startTime, String sellCash, String sellMoney, String chargeMoney1, String refundMoney, String reissueMoney, String reissueCount, String cardCount, String userName) {
                this.dutyID = dutyID;
                this.startTime = startTime;
                this.sellCash = sellCash;
                this.sellMoney = sellMoney;
                this.chargeMoney1 = chargeMoney1;
                this.refundMoney = refundMoney;
                this.reissueMoney = reissueMoney;
                this.reissueCount = reissueCount;
                this.cardCount = cardCount;
                this.userName = userName;
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

            public String getSellCash() {
                return sellCash;
            }

            public void setSellCash(String sellCash) {
                this.sellCash = sellCash;
            }

            public String getSellMoney() {
                return sellMoney;
            }

            public void setSellMoney(String sellMoney) {
                this.sellMoney = sellMoney;
            }

            public String getChargeMoney1() {
                return chargeMoney1;
            }

            public void setChargeMoney1(String chargeMoney1) {
                this.chargeMoney1 = chargeMoney1;
            }

            public String getRefundMoney() {
                return refundMoney;
            }

            public void setRefundMoney(String refundMoney) {
                this.refundMoney = refundMoney;
            }

            public String getReissueMoney() {
                return reissueMoney;
            }

            public void setReissueMoney(String reissueMoney) {
                this.reissueMoney = reissueMoney;
            }

            public String getReissueCount() {
                return reissueCount;
            }

            public void setReissueCount(String reissueCount) {
                this.reissueCount = reissueCount;
            }

            public String getCardCount() {
                return cardCount;
            }

            public void setCardCount(String cardCount) {
                this.cardCount = cardCount;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}

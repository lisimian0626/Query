package com.beidousat.querydata.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Banci {

    /**
     * root : {"data":[{"status":"0","banci":"3","startTime":"2019-02-20 17:59:00.0","endTime":"2019-03-08 09:42:00.0","theoryQuantity":"54.98","sumQuantity":"54.98","cardQuantity":"50.07","accountQuantity":"0.00","otherQuantity":"0.00","diffQuantity":"0.00","dutyName":"大大","userName":"娃娃"},{"status":"0","banci":"2","startTime":"2019-02-20 11:49:00.0","endTime":"2019-02-20 17:59:00.0","theoryQuantity":"120.98","sumQuantity":"120.98","cardQuantity":"87.50","accountQuantity":"0.00","otherQuantity":"33.48","diffQuantity":"0.00","dutyName":"娃娃","userName":"12"},{"status":"0","banci":"1","startTime":"2019-02-19 10:41:00.0","endTime":"2019-02-20 11:48:00.0","theoryQuantity":"20048.15","sumQuantity":"28.57","cardQuantity":"23.43","accountQuantity":"0.00","otherQuantity":"5.14","diffQuantity":"-20019.58","dutyName":"娃娃","userName":"123"}],"sum":{"sumtheoryQuantity":"20224.11","sumsumQuantity":"204.53","sumsellQuantity":"4.91","sumcardQuantity":"161.00","sumaccountQuantity":"0.00","sumotherQuantity":"38.62","sumdiffQuantity":"-20019.58"},"total":"1","response":{"errcode":"0"}}
     */

    private RootBean root;

    public static List<Banci> arrayBanciFromData(String str) {

        Type listType = new TypeToken<ArrayList<Banci>>() {
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
         * data : [{"status":"0","banci":"3","startTime":"2019-02-20 17:59:00.0","endTime":"2019-03-08 09:42:00.0","theoryQuantity":"54.98","sumQuantity":"54.98","cardQuantity":"50.07","accountQuantity":"0.00","otherQuantity":"0.00","diffQuantity":"0.00","dutyName":"大大","userName":"娃娃"},{"status":"0","banci":"2","startTime":"2019-02-20 11:49:00.0","endTime":"2019-02-20 17:59:00.0","theoryQuantity":"120.98","sumQuantity":"120.98","cardQuantity":"87.50","accountQuantity":"0.00","otherQuantity":"33.48","diffQuantity":"0.00","dutyName":"娃娃","userName":"12"},{"status":"0","banci":"1","startTime":"2019-02-19 10:41:00.0","endTime":"2019-02-20 11:48:00.0","theoryQuantity":"20048.15","sumQuantity":"28.57","cardQuantity":"23.43","accountQuantity":"0.00","otherQuantity":"5.14","diffQuantity":"-20019.58","dutyName":"娃娃","userName":"123"}]
         * sum : {"sumtheoryQuantity":"20224.11","sumsumQuantity":"204.53","sumsellQuantity":"4.91","sumcardQuantity":"161.00","sumaccountQuantity":"0.00","sumotherQuantity":"38.62","sumdiffQuantity":"-20019.58"}
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
             * sumtheoryQuantity : 20224.11
             * sumsumQuantity : 204.53
             * sumsellQuantity : 4.91
             * sumcardQuantity : 161.00
             * sumaccountQuantity : 0.00
             * sumotherQuantity : 38.62
             * sumdiffQuantity : -20019.58
             */

            private String sumtheoryQuantity;
            private String sumsumQuantity;
            private String sumsellQuantity;
            private String sumcardQuantity;
            private String sumaccountQuantity;
            private String sumotherQuantity;
            private String sumdiffQuantity;

            public Banci.RootBean.DataBean toDataBean(){
                Banci.RootBean.DataBean dataBean=new Banci.RootBean.DataBean();
                dataBean.setTheoryQuantity(getSumtheoryQuantity());
                dataBean.setSumQuantity(getSumsumQuantity());
                dataBean.setSellQuantity(getSumsellQuantity());
                dataBean.setCardQuantity(getSumcardQuantity());
                dataBean.setAccountQuantity(getSumaccountQuantity());
                dataBean.setOtherQuantity(getSumotherQuantity());
                dataBean.setDiffQuantity(getSumdiffQuantity());
                return dataBean;
            }

            public static List<SumBean> arraySumBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<SumBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getSumtheoryQuantity() {
                return sumtheoryQuantity;
            }

            public void setSumtheoryQuantity(String sumtheoryQuantity) {
                this.sumtheoryQuantity = sumtheoryQuantity;
            }

            public String getSumsumQuantity() {
                return sumsumQuantity;
            }

            public void setSumsumQuantity(String sumsumQuantity) {
                this.sumsumQuantity = sumsumQuantity;
            }

            public String getSumsellQuantity() {
                return sumsellQuantity;
            }

            public void setSumsellQuantity(String sumsellQuantity) {
                this.sumsellQuantity = sumsellQuantity;
            }

            public String getSumcardQuantity() {
                return sumcardQuantity;
            }

            public void setSumcardQuantity(String sumcardQuantity) {
                this.sumcardQuantity = sumcardQuantity;
            }

            public String getSumaccountQuantity() {
                return sumaccountQuantity;
            }

            public void setSumaccountQuantity(String sumaccountQuantity) {
                this.sumaccountQuantity = sumaccountQuantity;
            }

            public String getSumotherQuantity() {
                return sumotherQuantity;
            }

            public void setSumotherQuantity(String sumotherQuantity) {
                this.sumotherQuantity = sumotherQuantity;
            }

            public String getSumdiffQuantity() {
                return sumdiffQuantity;
            }

            public void setSumdiffQuantity(String sumdiffQuantity) {
                this.sumdiffQuantity = sumdiffQuantity;
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
             * status : 0
             * banci : 3
             * startTime : 2019-02-20 17:59:00.0
             * endTime : 2019-03-08 09:42:00.0
             * theoryQuantity : 54.98
             * sumQuantity : 54.98
             * cardQuantity : 50.07
             * accountQuantity : 0.00
             * otherQuantity : 0.00
             * diffQuantity : 0.00
             * dutyName : 大大
             * userName : 娃娃
             */

            private String status;
            private String banci;
            private String startTime;
            private String endTime;
            private String theoryQuantity;
            private String sumQuantity;
            private String sellQuantity;
            private String cardQuantity;
            private String accountQuantity;
            private String otherQuantity;
            private String diffQuantity;
            private String dutyName;
            private String userName;

            public DataBean() {
            }

            public DataBean(String status, String banci, String startTime, String endTime, String theoryQuantity, String sumQuantity, String sellQuantity, String cardQuantity, String accountQuantity, String otherQuantity, String diffQuantity, String dutyName, String userName) {
                this.status = status;
                this.banci = banci;
                this.startTime = startTime;
                this.endTime = endTime;
                this.theoryQuantity = theoryQuantity;
                this.sumQuantity = sumQuantity;
                this.sellQuantity = sellQuantity;
                this.cardQuantity = cardQuantity;
                this.accountQuantity = accountQuantity;
                this.otherQuantity = otherQuantity;
                this.diffQuantity = diffQuantity;
                this.dutyName = dutyName;
                this.userName = userName;
            }

            public static List<DataBean> arrayDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getBanci() {
                return banci;
            }

            public void setBanci(String banci) {
                this.banci = banci;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getTheoryQuantity() {
                return theoryQuantity;
            }

            public void setTheoryQuantity(String theoryQuantity) {
                this.theoryQuantity = theoryQuantity;
            }

            public String getSumQuantity() {
                return sumQuantity;
            }

            public void setSumQuantity(String sumQuantity) {
                this.sumQuantity = sumQuantity;
            }

            public String getCardQuantity() {
                return cardQuantity;
            }

            public void setCardQuantity(String cardQuantity) {
                this.cardQuantity = cardQuantity;
            }

            public String getAccountQuantity() {
                return accountQuantity;
            }

            public void setAccountQuantity(String accountQuantity) {
                this.accountQuantity = accountQuantity;
            }

            public String getOtherQuantity() {
                return otherQuantity;
            }

            public void setOtherQuantity(String otherQuantity) {
                this.otherQuantity = otherQuantity;
            }

            public String getDiffQuantity() {
                return diffQuantity;
            }

            public void setDiffQuantity(String diffQuantity) {
                this.diffQuantity = diffQuantity;
            }

            public String getDutyName() {
                return dutyName;
            }

            public void setDutyName(String dutyName) {
                this.dutyName = dutyName;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getSellQuantity() {
                return sellQuantity;
            }

            public void setSellQuantity(String sellQuantity) {
                this.sellQuantity = sellQuantity;
            }
        }
    }
}

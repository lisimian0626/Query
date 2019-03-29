package com.beidousat.querydata.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DutyDetail {

    /**
     * root : {"data":[{"gasNo":"01","theoryQuantity":"0.00","sumQuantity":"0.00","sellQuantity":"0.00","cardQuantity":"0.00","accountQuantity":"0.00","otherQuantity":"0.00","diffQuantity":"0.00"},{"gasNo":"02","theoryQuantity":"54.98","sumQuantity":"54.98","sellQuantity":"4.91","cardQuantity":"50.07","accountQuantity":"0.00","otherQuantity":"0.00","diffQuantity":"0.00"},{"gasNo":"合计","theoryQuantity":"54.98","sumQuantity":"54.98","sellQuantity":"4.91","cardQuantity":"50.07","accountQuantity":"0.00","otherQuantity":"0.00","diffQuantity":"0.00"}],"sum":{"sumtheoryQuantity":"109.96","sumsumQuantity":"109.96","sumsellQuantity":"9.82","sumcardQuantity":"100.14","sumaccountQuantity":"0.00","sumotherQuantity":"0.00","sumdiffQuantity":"0.00"},"total":"3","response":{"errcode":"0"}}
     */

    private RootBean root;

    public static List<DutyDetail> arrayDutyDetailFromData(String str) {

        Type listType = new TypeToken<ArrayList<DutyDetail>>() {
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
         * data : [{"gasNo":"01","theoryQuantity":"0.00","sumQuantity":"0.00","sellQuantity":"0.00","cardQuantity":"0.00","accountQuantity":"0.00","otherQuantity":"0.00","diffQuantity":"0.00"},{"gasNo":"02","theoryQuantity":"54.98","sumQuantity":"54.98","sellQuantity":"4.91","cardQuantity":"50.07","accountQuantity":"0.00","otherQuantity":"0.00","diffQuantity":"0.00"},{"gasNo":"合计","theoryQuantity":"54.98","sumQuantity":"54.98","sellQuantity":"4.91","cardQuantity":"50.07","accountQuantity":"0.00","otherQuantity":"0.00","diffQuantity":"0.00"}]
         * sum : {"sumtheoryQuantity":"109.96","sumsumQuantity":"109.96","sumsellQuantity":"9.82","sumcardQuantity":"100.14","sumaccountQuantity":"0.00","sumotherQuantity":"0.00","sumdiffQuantity":"0.00"}
         * total : 3
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
             * sumtheoryQuantity : 109.96
             * sumsumQuantity : 109.96
             * sumsellQuantity : 9.82
             * sumcardQuantity : 100.14
             * sumaccountQuantity : 0.00
             * sumotherQuantity : 0.00
             * sumdiffQuantity : 0.00
             */

            private String sumtheoryQuantity;
            private String sumsumQuantity;
            private String sumsellQuantity;
            private String sumcardQuantity;
            private String sumaccountQuantity;
            private String sumotherQuantity;
            private String sumdiffQuantity;

            public DutyDetail.RootBean.DataBean toDataBean(){
                DutyDetail.RootBean.DataBean dataBean=new DutyDetail.RootBean.DataBean();
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
             * gasNo : 01
             * theoryQuantity : 0.00
             * sumQuantity : 0.00
             * sellQuantity : 0.00
             * cardQuantity : 0.00
             * accountQuantity : 0.00
             * otherQuantity : 0.00
             * diffQuantity : 0.00
             */

            private String gasNo;
            private String theoryQuantity;
            private String sumQuantity;
            private String sellQuantity;
            private String cardQuantity;
            private String accountQuantity;
            private String otherQuantity;
            private String diffQuantity;


            public static List<DataBean> arrayDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public DataBean() {
            }

            public DataBean(String gasNo, String theoryQuantity, String sumQuantity, String sellQuantity, String cardQuantity, String accountQuantity, String otherQuantity, String diffQuantity) {
                this.gasNo = gasNo;
                this.theoryQuantity = theoryQuantity;
                this.sumQuantity = sumQuantity;
                this.sellQuantity = sellQuantity;
                this.cardQuantity = cardQuantity;
                this.accountQuantity = accountQuantity;
                this.otherQuantity = otherQuantity;
                this.diffQuantity = diffQuantity;
            }

            public String getGasNo() {
                return gasNo;
            }

            public void setGasNo(String gasNo) {
                this.gasNo = gasNo;
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

            public String getSellQuantity() {
                return sellQuantity;
            }

            public void setSellQuantity(String sellQuantity) {
                this.sellQuantity = sellQuantity;
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
        }
    }
}

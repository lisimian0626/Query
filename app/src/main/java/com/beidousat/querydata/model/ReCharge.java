package com.beidousat.querydata.model;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReCharge {

    /**
     * root : {"data":[{"platenumber":"ATR123","owner":"王达","company":"邯郸站","addmoney":"5.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-29 15:56:53.647"},{"platenumber":"ATR123","owner":"王达","company":"邯郸站","addmoney":"5.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-29 15:57:47.7"},{"platenumber":"ATR123","owner":"王达","company":"邯郸站","addmoney":"5.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 16:21:59.0"},{"platenumber":"ATR123","owner":"王达","company":"邯郸站","addmoney":"5.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 16:25:20.53"},{"platenumber":"ATR123","owner":"王达","company":"邯郸站","addmoney":"5.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 16:40:03.473"},{"platenumber":"ATR123","owner":"王达","company":"邯郸站","addmoney":"10.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 16:40:31.43"},{"platenumber":"HTM521","owner":"加气","company":"北站站","addmoney":"5.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 17:15:13.197"},{"platenumber":"HTM852","owner":"问问","company":"北站站","addmoney":"200.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 17:22:43.217"},{"platenumber":"THR656","owner":"娃娃","company":"邯郸站","addmoney":"200.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 17:33:34.527"},{"platenumber":"ATR123","owner":"王飞","company":"北站站","addmoney":"1000.00","zengmoney":"0.00","staff":"super","addtime":"2019-02-19 12:00:17.41"}],"sum":{"addsum":"1440.00","zengnum":"0.00"},"total":"1","response":{"errcode":"0"}}
     */

    private RootBean root;

    public static List<ReCharge> arrayReChargeFromData(String str) {

        Type listType = new TypeToken<ArrayList<ReCharge>>() {
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
         * data : [{"platenumber":"ATR123","owner":"王达","company":"邯郸站","addmoney":"5.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-29 15:56:53.647"},{"platenumber":"ATR123","owner":"王达","company":"邯郸站","addmoney":"5.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-29 15:57:47.7"},{"platenumber":"ATR123","owner":"王达","company":"邯郸站","addmoney":"5.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 16:21:59.0"},{"platenumber":"ATR123","owner":"王达","company":"邯郸站","addmoney":"5.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 16:25:20.53"},{"platenumber":"ATR123","owner":"王达","company":"邯郸站","addmoney":"5.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 16:40:03.473"},{"platenumber":"ATR123","owner":"王达","company":"邯郸站","addmoney":"10.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 16:40:31.43"},{"platenumber":"HTM521","owner":"加气","company":"北站站","addmoney":"5.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 17:15:13.197"},{"platenumber":"HTM852","owner":"问问","company":"北站站","addmoney":"200.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 17:22:43.217"},{"platenumber":"THR656","owner":"娃娃","company":"邯郸站","addmoney":"200.00","zengmoney":"0.00","staff":"super","addtime":"2019-01-30 17:33:34.527"},{"platenumber":"ATR123","owner":"王飞","company":"北站站","addmoney":"1000.00","zengmoney":"0.00","staff":"super","addtime":"2019-02-19 12:00:17.41"}]
         * sum : {"addsum":"1440.00","zengnum":"0.00"}
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
             * addsum : 1440.00
             * zengnum : 0.00
             */

            private String addsum;
            private String zengnum;

            public static List<SumBean> arraySumBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<SumBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getAddsum() {
                return addsum;
            }

            public void setAddsum(String addsum) {
                this.addsum = addsum;
            }

            public String getZengnum() {
                return zengnum;
            }

            public void setZengnum(String zengnum) {
                this.zengnum = zengnum;
            }

            public DataBean toDataBean(){
                DataBean dataBean=new DataBean();
                dataBean.setAddmoney(getAddsum());
                dataBean.setZengmoney(getZengnum());
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
             * platenumber : ATR123
             * owner : 王达
             * company : 邯郸站
             * addmoney : 5.00
             * zengmoney : 0.00
             * staff : super
             * addtime : 2019-01-29 15:56:53.647
             */

            private String platenumber;
            private String owner;
            private String company;
            private String addmoney;
            private String zengmoney;
            private String staff;
            private String addtime;

            public DataBean() {
            }

            public DataBean(String platenumber, String owner, String company, String addmoney, String zengmoney, String staff, String addtime) {
                this.platenumber = platenumber;
                this.owner = owner;
                this.company = company;
                this.addmoney = addmoney;
                this.zengmoney = zengmoney;
                this.staff = staff;
                this.addtime = addtime;
            }

            public static List<DataBean> arrayDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getPlatenumber() {
                return platenumber;
            }

            public void setPlatenumber(String platenumber) {
                this.platenumber = platenumber;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getAddmoney() {
                return addmoney;
            }

            public void setAddmoney(String addmoney) {
                this.addmoney = addmoney;
            }

            public String getZengmoney() {
                return zengmoney;
            }

            public void setZengmoney(String zengmoney) {
                this.zengmoney = zengmoney;
            }

            public String getStaff() {
                return staff;
            }

            public void setStaff(String staff) {
                this.staff = staff;
            }

            public String getAddtime() {
                if(!TextUtils.isEmpty(addtime)){
                    String str;
                    try {
                        str=addtime.substring(0,addtime.lastIndexOf(":"));
                    }catch (Exception e){
                        e.printStackTrace();
                        return addtime;
                    }
                    return str;
                }
              return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }
        }
    }
}

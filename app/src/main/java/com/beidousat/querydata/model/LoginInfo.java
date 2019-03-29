package com.beidousat.querydata.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LoginInfo {

    /**
     * root : {"data":[{"idresult":"0"}],"response":{"errcode":"0"}}
     */

    private RootBean root;

    public static List<LoginInfo> arrayLoginInfoFromData(String str) {

        Type listType = new TypeToken<ArrayList<LoginInfo>>() {
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
         * data : [{"idresult":"0"}]
         * response : {"errcode":"0"}
         */

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
             * idresult : 0
             */

            private String idresult;

            public static List<DataBean> arrayDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public String getIdresult() {
                return idresult;
            }

            public void setIdresult(String idresult) {
                this.idresult = idresult;
            }
        }
    }
}

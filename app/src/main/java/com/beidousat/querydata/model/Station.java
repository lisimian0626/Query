package com.beidousat.querydata.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Station {

    /**
     * root : {"data":[{"row":[{"stationName":["东圃站"]}]}],"response":[{"errcode":["0"]}]}
     */

    private RootBean root;

    public static List<Station> arrayStationFromData(String str) {

        Type listType = new TypeToken<ArrayList<Station>>() {
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
        private List<DataBean> data;
        private List<ResponseBean> response;

        public static List<RootBean> arrayRootBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<RootBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public List<ResponseBean> getResponse() {
            return response;
        }

        public void setResponse(List<ResponseBean> response) {
            this.response = response;
        }

        public static class DataBean {
            private List<RowBean> row;

            public static List<DataBean> arrayDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public List<RowBean> getRow() {
                return row;
            }

            public void setRow(List<RowBean> row) {
                this.row = row;
            }

            public static class RowBean {
                private List<String> stationName;

                public static List<RowBean> arrayRowBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<RowBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public List<String> getStationName() {
                    return stationName;
                }

                public void setStationName(List<String> stationName) {
                    this.stationName = stationName;
                }
            }
        }

        public static class ResponseBean {
            private List<String> errcode;

            public static List<ResponseBean> arrayResponseBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<ResponseBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public List<String> getErrcode() {
                return errcode;
            }

            public void setErrcode(List<String> errcode) {
                this.errcode = errcode;
            }
        }
    }
}

package com.beidousat.querydata.common;

import com.beidousat.querydata.model.SelectConfig;

public class GlobalDataUtil {
    private static GlobalDataUtil globalDataUtil;
    private SelectConfig selectConfig;
    public static GlobalDataUtil getInstance() {
        if (globalDataUtil == null)
            globalDataUtil = new GlobalDataUtil();
        return globalDataUtil;
    }

    public SelectConfig getSelectConfig() {
        return selectConfig;
    }

    public void setSelectConfig(SelectConfig selectConfig) {
        this.selectConfig = selectConfig;
    }
}

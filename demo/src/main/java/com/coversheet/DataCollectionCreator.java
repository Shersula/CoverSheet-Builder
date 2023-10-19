package com.coversheet;

import java.util.ArrayList;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DataCollectionCreator {
    private ArrayList<DataBean> dataBeanList = new ArrayList<DataBean>();

    public void add(DataBean obj)
    {
        dataBeanList.add(obj);
    }

    public void clear()
    {
        dataBeanList.clear();
    }

    public JRBeanCollectionDataSource getCollection()
    {
        return new JRBeanCollectionDataSource(dataBeanList);
    }
}

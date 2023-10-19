package com.coversheet;

public class DataBeanCoverSheet implements DataBean
{
    private int number;

    private int removedOperationCode;
    private int removedCount;
    private String removedDefects;

    private int deliverOperationCode;
    private int deliverCount;
    private int deliverOriginalBatch;
    private int deliverOTK;

    private String controlDesignation;
    private String controlStamp;

    DataBeanCoverSheet(int number, int removedOperationCode, int removedCount, String removedDefects, int deliverOperationCode, int deliverCount, int deliverOriginalBatch, int deliverOTK, String controlDesignation, String controlStamp)
    {
        this.number = number;
        this.removedOperationCode = removedOperationCode;
        this.removedCount = removedCount;
        this.removedDefects = removedDefects;
        this.deliverOperationCode = deliverOperationCode;
        this.deliverCount = deliverCount;
        this.deliverOriginalBatch = deliverOriginalBatch;
        this.deliverOTK = deliverOTK;
        this.controlDesignation = controlDesignation;
        this.controlStamp = controlStamp;
    }

    //////////////////////////////Общее поле////////////////////////////////
    public int getNumber()
    {
        return this.number;
    }
    public void setNumber(int number)
    {
        this.number = number;
    }
    ////////////////////////////////////Изъятые//////////////////////////////
    public int getRemovedOperationCode()
    {
        return this.removedOperationCode;
    }
    public void setRemovedOperationCode(int removedOperationCode)
    {
        this.removedOperationCode = removedOperationCode;
    }

    public int getRemovedCount()
    {
        return this.removedCount;
    }
    public void setRemovedCount(int removedCount)
    {
        this.removedCount = removedCount;
    }

    public String getRemovedDefects()
    {
        return this.removedDefects;
    }
    public void setRemovedDefects(String removedDefects)
    {
        this.removedDefects = removedDefects; 
    }
    ////////////////////Доставленные///////////////////////////////////
    public int getDeliverOperationCode()
    {
        return this.deliverOperationCode;
    }
    public void setDeliverOperationCode(int deliverOperationCode)
    {
        this.deliverOperationCode = deliverOperationCode;
    }

    public int getDeliverCount()
    {
        return this.deliverCount;
    }
    public void setDeliverCount(int deliverCount)
    {
        this.deliverCount = deliverCount;
    }

    public int getDeliverOriginalBatch()
    {
        return this.deliverOriginalBatch;
    }
    public void setDeliverOriginalBatch(int deliverOriginalBatch)
    {
        this.deliverOriginalBatch = deliverOriginalBatch;
    }

    public int getDeliverOTK()
    {
        return this.deliverOTK;
    }
    public void setDeliverOTK(int deliverOTK)
    {
        this.deliverOTK = deliverOTK;
    }
    ////////////////////Контрольные/////////////////////////
    public String getControlDesignation()
    {
        return this.controlDesignation;
    }
    public void setControlDesignation(String controlDesignation)
    {
        this.controlDesignation = controlDesignation;
    }

    public String getControlStamp()
    {
        return this.controlStamp;
    }
    public void setControlStamp(String controlStamp)
    {
        this.controlStamp = controlStamp;
    }
}

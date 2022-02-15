package cn.savory.stockman;

import java.math.BigDecimal;

public class CalcRequest {

    /**
     * 买入单价
     */
    private BigDecimal buyMoney;

    /**
     * 买入股数
     */
    private Integer buyCount;

    /**
     * 卖出单价
     */
    private BigDecimal sellMoney;

    /**
     * 卖出股数
     */
    private Integer sellCount;

    /**
     * 是否是ETF
     */
    private Boolean etf;

    public BigDecimal getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(BigDecimal buyMoney) {
        this.buyMoney = buyMoney;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public BigDecimal getSellMoney() {
        return sellMoney;
    }

    public void setSellMoney(BigDecimal sellMoney) {
        this.sellMoney = sellMoney;
    }

    public Integer getSellCount() {
        return sellCount;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }

    public Boolean getEtf() {
        return etf;
    }

    public void setEtf(Boolean etf) {
        this.etf = etf;
    }
}

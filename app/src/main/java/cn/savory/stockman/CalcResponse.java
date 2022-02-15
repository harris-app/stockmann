package cn.savory.stockman;

import java.math.BigDecimal;

public class CalcResponse {

    /**
     * 买入手续费
     */
    private BigDecimal buyServiceCharge;

    /**
     * 买入过户费
     */
    private BigDecimal buyTransferFee;

    /**
     * 卖出手续费
     */
    private BigDecimal sellServiceCharge;

    /**
     * 卖出过户费
     */
    private BigDecimal sellTransferFee;

    /**
     * 卖出印花税
     */
    private BigDecimal sellStampTax;

    /**
     * 额外成本
     */
    private BigDecimal totalFee;

    /**
     * 做T赚的差价
     */
    private BigDecimal totalWin;

    /**
     * | 买入单价- 基础单价 | / 基础单价
     */
    private BigDecimal buyDelta;

    /**
     * | 买入单价- 基础单价 | / 基础单价
     */
    private BigDecimal sellDelta;

    public BigDecimal getBuyServiceCharge() {
        return buyServiceCharge;
    }

    public void setBuyServiceCharge(BigDecimal buyServiceCharge) {
        this.buyServiceCharge = buyServiceCharge;
    }

    public BigDecimal getBuyTransferFee() {
        return buyTransferFee;
    }

    public void setBuyTransferFee(BigDecimal buyTransferFee) {
        this.buyTransferFee = buyTransferFee;
    }

    public BigDecimal getSellServiceCharge() {
        return sellServiceCharge;
    }

    public void setSellServiceCharge(BigDecimal sellServiceCharge) {
        this.sellServiceCharge = sellServiceCharge;
    }

    public BigDecimal getSellTransferFee() {
        return sellTransferFee;
    }

    public void setSellTransferFee(BigDecimal sellTransferFee) {
        this.sellTransferFee = sellTransferFee;
    }

    public BigDecimal getSellStampTax() {
        return sellStampTax;
    }

    public void setSellStampTax(BigDecimal sellStampTax) {
        this.sellStampTax = sellStampTax;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(BigDecimal totalWin) {
        this.totalWin = totalWin;
    }

    public BigDecimal getBuyDelta() {
        return buyDelta;
    }

    public void setBuyDelta(BigDecimal buyDelta) {
        this.buyDelta = buyDelta;
    }

    public BigDecimal getSellDelta() {
        return sellDelta;
    }

    public void setSellDelta(BigDecimal sellDelta) {
        this.sellDelta = sellDelta;
    }
}

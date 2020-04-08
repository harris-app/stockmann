package cn.savory.stockman;

import java.math.BigDecimal;

public class Calculator {

    /**
     * 买入时手续费费率
     */
    private final static BigDecimal serviceChargeRate = BigDecimal.valueOf(0.00025d);

    /**
     * 买入时最低手续费
     */
    private final static BigDecimal minServiceChargeRate = BigDecimal.valueOf(5);

    /**
     * 买入时过户费费率
     */
    private final static BigDecimal transferFeeRate = BigDecimal.valueOf(0.00002);

    /**
     * 卖出时收取的印花税费率
     */
    private final static BigDecimal stampTaxRate = BigDecimal.valueOf(0.001);

    /**
     * 手续费
     * 万分之2.5，最低收取5元。四舍五入到小数点后面第二位
     *
     * @param buyMoney 每股的价格
     * @param buyCount 股数
     * @return 手续费
     */
    public static BigDecimal calcServiceCharge(BigDecimal buyMoney, Integer buyCount) {

        if (buyMoney == null || buyMoney.doubleValue() <= 0 || buyCount == null || buyCount == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal serviceCharge = buyMoney.multiply(BigDecimal.valueOf(buyCount)).multiply(serviceChargeRate);
        if (serviceCharge.compareTo(minServiceChargeRate) < 0) {
            serviceCharge = minServiceChargeRate;
        }
        serviceCharge = serviceCharge.setScale(2, BigDecimal.ROUND_HALF_UP);

        return serviceCharge;
    }

    /**
     * 过户费
     * 千分之0.2，向上取整到小数点后面2位
     *
     * @param buyMoney 每股的价格
     * @param buyCount 股数
     * @return 手续费
     */
    public static BigDecimal calcTransferFee(BigDecimal buyMoney, Integer buyCount, Boolean etf) {

        if (buyMoney == null || buyMoney.doubleValue() <= 0 || buyCount == null || buyCount == 0) {
            return BigDecimal.ZERO;
        }

        if (etf != null && etf) {
            return BigDecimal.ZERO;
        }

        BigDecimal transferFee = buyMoney.multiply(BigDecimal.valueOf(buyCount)).multiply(transferFeeRate);
        transferFee = transferFee.setScale(2, BigDecimal.ROUND_UP);

        return transferFee;
    }

    /**
     * 印花税
     *
     * @param sellMoney
     * @param sellCount
     * @return
     */
    public static BigDecimal calcStampTax(BigDecimal sellMoney, Integer sellCount) {

        if (sellMoney == null || sellMoney.doubleValue() <= 0 || sellCount == null || sellCount == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal stampTax = sellMoney.multiply(BigDecimal.valueOf(sellCount)).multiply(stampTaxRate);
        stampTax = stampTax.setScale(2, BigDecimal.ROUND_HALF_UP);

        return stampTax;
    }
}

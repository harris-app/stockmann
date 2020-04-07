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
    public static double calcServiceCharge(double buyMoney, int buyCount) {

        BigDecimal serviceCharge = BigDecimal.valueOf(buyMoney).multiply(BigDecimal.valueOf(buyCount)).multiply(serviceChargeRate);
        if (serviceCharge.compareTo(minServiceChargeRate) < 0) {
            serviceCharge = minServiceChargeRate;
        }
        serviceCharge = serviceCharge.setScale(2, BigDecimal.ROUND_HALF_UP);

        return serviceCharge.doubleValue();
    }

    /**
     * 过户费
     * 千分之0.2，向上取整到小数点后面2位
     *
     * @param buyMoney 每股的价格
     * @param buyCount 股数
     * @return 手续费
     */
    public static double calcTransferFee(double buyMoney, int buyCount, boolean etf) {

        if (etf) {
            return 0;
        }

        BigDecimal transferFee = BigDecimal.valueOf(buyMoney).multiply(BigDecimal.valueOf(buyCount)).multiply(transferFeeRate);
        transferFee = transferFee.setScale(2, BigDecimal.ROUND_UP);

        return transferFee.doubleValue();
    }

    /**
     * 印花税
     *
     * @param sellMoney
     * @param sellCount
     * @return
     */
    public static double calcStampTax(double sellMoney, int sellCount) {

        BigDecimal stampTax = BigDecimal.valueOf(sellMoney).multiply(BigDecimal.valueOf(sellCount)).multiply(stampTaxRate);
        stampTax = stampTax.setScale(2, BigDecimal.ROUND_HALF_UP);

        return stampTax.doubleValue();
    }
}

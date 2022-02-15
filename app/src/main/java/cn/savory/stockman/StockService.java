package cn.savory.stockman;

import java.math.BigDecimal;

public class StockService {

    public CalcResponse calc(CalcRequest request) {
        CalcResponse response = new CalcResponse();

        //买入 手续费
        BigDecimal buyServiceCharge = Calculator.calcServiceCharge(request.getBuyMoney(), request.getBuyCount());
        response.setBuyServiceCharge(buyServiceCharge);

        //买入 过户费
        BigDecimal buyTransferFee = Calculator.calcTransferFee(request.getBuyMoney(), request.getBuyCount(), request.getEtf());
        response.setBuyTransferFee(buyTransferFee);

        //卖出 手续费
        BigDecimal sellServiceCharge = Calculator.calcServiceCharge(request.getSellMoney(), request.getSellCount());
        response.setSellServiceCharge(sellServiceCharge);

        //卖出 过户费
        BigDecimal sellTransferFee = Calculator.calcTransferFee(request.getSellMoney(), request.getSellCount(), request.getEtf());
        response.setSellTransferFee(sellTransferFee);

        //卖出 印花税
        BigDecimal sellStampTax = Calculator.calcStampTax(request.getSellMoney(), request.getSellCount());
        response.setSellStampTax(sellStampTax);

        //手续费 合计
        BigDecimal totalFee = buyServiceCharge.add(buyTransferFee).add(sellServiceCharge).add(sellTransferFee).add(sellStampTax);
        response.setTotalFee(totalFee);

        //做T差价
        BigDecimal totalWin = null;
        if (positive(request.getBuyMoney()) && positive(request.getBuyCount()) && positive(request.getSellMoney()) && positive(request.getSellCount())) {
            BigDecimal buyTotal = request.getBuyMoney().multiply(BigDecimal.valueOf(request.getBuyCount()));
            BigDecimal sellTotal = request.getSellMoney().multiply(BigDecimal.valueOf(request.getSellCount()));
            totalWin = sellTotal.subtract(buyTotal).subtract(totalFee);
        }
        response.setTotalWin(totalWin);

        return response;
    }

    private boolean positive(BigDecimal value) {
        return value != null && value.doubleValue() > 0;
    }

    private boolean positive(Integer value) {
        return value != null && value > 0;
    }
}

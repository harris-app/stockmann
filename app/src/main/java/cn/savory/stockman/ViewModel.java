package cn.savory.stockman;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

public class ViewModel extends BaseObservable {

    /**
     * 开盘价
     */
    public final ObservableField<String> baseMoney = new ObservableField<>();

    /**
     * 买入单价
     */
    public final ObservableField<String> buyMoney = new ObservableField<>();

    /**
     * 买入股数
     */
    public final ObservableField<String> buyCount = new ObservableField<>();

    /**
     * 卖出单价
     */
    public final ObservableField<String> sellMoney = new ObservableField<>();

    /**
     * 卖出股数
     */
    public final ObservableField<String> sellCount = new ObservableField<>();

    /**
     * 是否是ETF
     */
    public final ObservableField<Boolean> etf = new ObservableField<>();


    /**
     * 买入手续费
     */
    public final ObservableField<String> buyServiceCharge = new ObservableField<>();

    /**
     * 买入过户费
     */
    public final ObservableField<String> buyTransferFee = new ObservableField<>();

    /**
     * 卖出手续费
     */
    public final ObservableField<String> sellServiceCharge = new ObservableField<>();

    /**
     * 卖出过户费
     */
    public final ObservableField<String> sellTransferFee = new ObservableField<>();

    /**
     * 卖出印花税
     */
    public final ObservableField<String> sellStampTax = new ObservableField<>();

    /**
     * 额外成本
     */
    public final ObservableField<String> totalFee = new ObservableField<>();

    /**
     * 做T赚的差价
     */
    public final ObservableField<String> totalWin = new ObservableField<>();

    /**
     * | 买入单价- 基础单价 | / 基础单价
     */
    public final ObservableField<String> buyDelta = new ObservableField<>();

    /**
     * | 买入单价- 基础单价 | / 基础单价
     */
    public final ObservableField<String> sellDelta = new ObservableField<>();
}

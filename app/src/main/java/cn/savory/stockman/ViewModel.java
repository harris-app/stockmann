package cn.savory.stockman;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

public class ViewModel extends BaseObservable {

    public final ObservableField<String> buyMoney = new ObservableField<>();

    public final ObservableField<String> buyCount = new ObservableField<>();

    public final ObservableField<String> sellMoney = new ObservableField<>();

    public final ObservableField<String> sellCount = new ObservableField<>();

    public final ObservableField<Boolean> etf = new ObservableField<>();


    public final ObservableField<String> buyServiceCharge = new ObservableField<>();

    public final ObservableField<String> buyTransferFee = new ObservableField<>();

    public final ObservableField<String> sellServiceCharge = new ObservableField<>();

    public final ObservableField<String> sellTransferFee = new ObservableField<>();

    public final ObservableField<String> sellStampTax = new ObservableField<>();


    public final ObservableField<String> totalFee = new ObservableField<>();

    public final ObservableField<String> totalWin = new ObservableField<>();
}

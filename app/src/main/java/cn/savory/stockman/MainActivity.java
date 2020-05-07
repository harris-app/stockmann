package cn.savory.stockman;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

import com.google.common.base.Strings;

import java.math.BigDecimal;

import cn.savory.stockman.databinding.LayoutActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    LayoutActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.layout_activity_main);

        ViewModel viewModel = new ViewModel();
        viewModel.buyMoney.addOnPropertyChangedCallback(propertyChangedCallback);
        viewModel.buyCount.addOnPropertyChangedCallback(propertyChangedCallback);
        viewModel.sellMoney.addOnPropertyChangedCallback(propertyChangedCallback);
        viewModel.etf.addOnPropertyChangedCallback(propertyChangedCallback);
        binding.setViewModel(viewModel);
    }

    private final Observable.OnPropertyChangedCallback propertyChangedCallback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            ViewModel viewModel = binding.getViewModel();

            BigDecimal buyMoney = toBigDecimal(viewModel.buyMoney.get());
            Integer buyCount = toInteger(viewModel.buyCount.get()) * 100;
            BigDecimal sellMoney = toBigDecimal(viewModel.sellMoney.get());
            Integer sellCount = toInteger(viewModel.buyCount.get()) * 100;
            Boolean etf = viewModel.etf.get();

            //买入 手续费
            BigDecimal buyServiceCharge = Calculator.calcServiceCharge(buyMoney, buyCount);
            viewModel.buyServiceCharge.set(fromBigDecimal(buyServiceCharge));

            //买入 过户费
            BigDecimal buyTransferFee = Calculator.calcTransferFee(buyMoney, buyCount, etf);
            viewModel.buyTransferFee.set(fromBigDecimal(buyTransferFee));

            //卖出 手续费
            BigDecimal sellServiceCharge = Calculator.calcServiceCharge(sellMoney, sellCount);
            viewModel.sellServiceCharge.set(fromBigDecimal(buyServiceCharge));

            //卖出 过户费
            BigDecimal sellTransferFee = Calculator.calcTransferFee(sellMoney, sellCount, etf);
            viewModel.sellTransferFee.set(fromBigDecimal(buyTransferFee));

            //卖出 印花税
            BigDecimal sellStampTax = Calculator.calcStampTax(sellMoney, sellCount);
            viewModel.sellStampTax.set(fromBigDecimal(sellStampTax));

            //手续费 合计
            BigDecimal totalFee = buyServiceCharge.add(buyTransferFee).add(sellServiceCharge).add(sellTransferFee).add(sellStampTax);
            viewModel.totalFee.set(fromBigDecimal(totalFee));

            //做T差价
            BigDecimal totalWin = null;
            if (positive(buyMoney) && positive(buyCount) && positive(sellMoney) && positive(sellCount)) {
                BigDecimal buyTotal = buyMoney.multiply(BigDecimal.valueOf(buyCount));
                BigDecimal sellTotal = sellMoney.multiply(BigDecimal.valueOf(sellCount));
                totalWin = sellTotal.subtract(buyTotal).subtract(totalFee);
            }
            viewModel.totalWin.set(fromBigDecimal(totalWin));

//            if (positive(baseMoney) && positive(buyMoney) && positive(buyCount)) {
//                BigDecimal buyDelta = buyMoney.subtract(baseMoney).divide(baseMoney).setScale(2, ROUND_HALF_UP);
//
//            }

        }
    };

    private String fromBigDecimal(BigDecimal value) {
        if (value == null) {
            return "0";
        }

        return String.valueOf(value);
    }

    private BigDecimal toBigDecimal(String text) {

        if (Strings.isNullOrEmpty(text)) {
            return BigDecimal.ZERO;
        }

        try {
            return BigDecimal.valueOf(Double.parseDouble(text));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer toInteger(String text) {

        if (Strings.isNullOrEmpty(text)) {
            return 0;
        }

        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private boolean positive(BigDecimal value) {
        return value != null && value.doubleValue() > 0;
    }

    private boolean positive(Integer value) {
        return value != null && value > 0;
    }
}

package cn.savory.stockman;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

import com.google.common.base.Strings;

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
        viewModel.sellCount.addOnPropertyChangedCallback(propertyChangedCallback);
        viewModel.etf.addOnPropertyChangedCallback(propertyChangedCallback);
        binding.setViewModel(viewModel);
    }

    private final Observable.OnPropertyChangedCallback propertyChangedCallback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            ViewModel viewModel = binding.getViewModel();

            Double buyMoney = toDouble(viewModel.buyMoney.get());
            Integer buyCount = toInteger(viewModel.buyCount.get());
            Double sellMoney = toDouble(viewModel.sellMoney.get());
            Integer sellCount = toInteger(viewModel.sellCount.get());
            Boolean etf = viewModel.etf.get();

            //买入 手续费
            Double buyServiceCharge = Calculator.calcServiceCharge(buyMoney, buyCount);
            viewModel.buyServiceCharge.set(fromDouble(buyServiceCharge));

            //买入 过户费
            Double buyTransferFee = Calculator.calcTransferFee(buyMoney, buyCount, etf);
            viewModel.buyTransferFee.set(fromDouble(buyTransferFee));

            //卖出 手续费
            Double sellServiceCharge = Calculator.calcServiceCharge(sellMoney, sellCount);
            viewModel.buyServiceCharge.set(fromDouble(buyServiceCharge));

            //卖出 过户费
            Double sellTransferFee = Calculator.calcTransferFee(sellMoney, sellCount, etf);
            viewModel.buyTransferFee.set(fromDouble(buyTransferFee));

            //卖出 印花税
            Double sellStampTax = Calculator.calcStampTax(sellMoney, sellCount);
            viewModel.buyTransferFee.set(fromDouble(buyTransferFee));

            //手续费 合计
            Double totalFee = buyServiceCharge + buyTransferFee + sellServiceCharge + sellTransferFee + sellStampTax;
            viewModel.totalFee.set(fromDouble(totalFee));

            //做T差价
            Double totalWin = null;
            if (buyMoney != null && buyCount != null && sellMoney != null && sellCount != null) {
                totalWin = sellMoney * sellCount - buyMoney * buyCount - totalFee;
            }
            viewModel.totalWin.set(fromDouble(totalWin));
        }
    };

    private String fromDouble(Double value) {
        if (value == null) {
            return "0";
        }

        return String.valueOf(value);
    }

    private Double toDouble(String text) {

        if (Strings.isNullOrEmpty(text)) {
            return null;
        }

        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer toInteger(String text) {

        if (Strings.isNullOrEmpty(text)) {
            return null;
        }

        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

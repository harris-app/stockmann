package cn.savory.stockman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.common.base.Strings;

import cn.savory.stockman.databinding.LayoutActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    LayoutActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.layout_activity_main);
        binding.setViewModel(new ViewModel());
        binding.setActivity(this);
    }

    public void calc() {
        ViewModel viewModel = binding.getViewModel();
        if (viewModel == null) {
            return;
        }

        Double buyServiceCharge = Calculator.calcServiceCharge(toDouble(viewModel.buyMoney.get()), toInteger(viewModel.buyCount.get()));
        viewModel.buyServiceCharge.set(String.valueOf(buyServiceCharge));
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

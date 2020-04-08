package cn.savory.stockman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

import android.os.Bundle;
import android.text.Editable;
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

        ViewModel viewModel = new ViewModel();
        viewModel.buyMoney.addOnPropertyChangedCallback(propertyChangedCallback);
        viewModel.buyCount.addOnPropertyChangedCallback(propertyChangedCallback);
        viewModel.sellMoney.addOnPropertyChangedCallback(propertyChangedCallback);
        viewModel.sellCount.addOnPropertyChangedCallback(propertyChangedCallback);
        binding.setViewModel(viewModel);
    }

    private final Observable.OnPropertyChangedCallback propertyChangedCallback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            ViewModel viewModel = binding.getViewModel();

            Double buyServiceCharge = Calculator.calcServiceCharge(toDouble(viewModel.buyMoney.get()), toInteger(viewModel.buyCount.get()));
            viewModel.buyServiceCharge.set(String.valueOf(buyServiceCharge));
        }
    };

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

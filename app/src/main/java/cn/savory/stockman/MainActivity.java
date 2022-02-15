package cn.savory.stockman;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.common.base.Strings;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private StockService stockService = new StockService();

    private EditText buyMoneyEditText;
    private EditText buyCountEditText;
    private EditText sellMoneyEditText;
    private SwitchCompat eftSwitchCompat;

    private TextView buyServiceChargeTextView;
    private TextView buyTransferFeeTextView;
    private TextView sellServiceChargeTextView;
    private TextView sellTransferFeeTextView;
    private TextView sellStampTaxTextView;
    private TextView totalFeeTextView;
    private TextView totalWinTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        buyMoneyEditText = findViewById(R.id.buyMoney);
        buyCountEditText = findViewById(R.id.buyCount);
        sellMoneyEditText = findViewById(R.id.sellMoney);
        eftSwitchCompat = findViewById(R.id.etf);

        buyServiceChargeTextView = findViewById(R.id.buyServiceCharge);
        buyTransferFeeTextView = findViewById(R.id.buyTransferFee);
        sellServiceChargeTextView = findViewById(R.id.sellServiceCharge);
        sellTransferFeeTextView = findViewById(R.id.sellTransferFee);
        sellStampTaxTextView = findViewById(R.id.sellStampTax);
        totalFeeTextView = findViewById(R.id.totalFee);
        totalWinTextView = findViewById(R.id.totalWin);

        buyMoneyEditText.addTextChangedListener(textWatcher);
        sellMoneyEditText.addTextChangedListener(textWatcher);
        buyCountEditText.addTextChangedListener(textWatcher);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            process();
        }
    };

    private void process() {
        CalcRequest request = prepareRequest();

        CalcResponse response = stockService.calc(request);

        buyServiceChargeTextView.setText(fromBigDecimal(response.getBuyServiceCharge()));
        buyTransferFeeTextView.setText(fromBigDecimal(response.getBuyTransferFee()));
        sellServiceChargeTextView.setText(fromBigDecimal(response.getSellServiceCharge()));
        sellTransferFeeTextView.setText(fromBigDecimal(response.getSellTransferFee()));
        sellStampTaxTextView.setText(fromBigDecimal(response.getSellStampTax()));
        totalFeeTextView.setText(fromBigDecimal(response.getTotalFee()));
        totalWinTextView.setText(fromBigDecimal(response.getTotalWin()));
    }


    private CalcRequest prepareRequest() {
        CalcRequest request = new CalcRequest();

        BigDecimal buyMoney = toBigDecimal(buyMoneyEditText.getText().toString());
        request.setBuyMoney(buyMoney);

        Integer buyCount = toInteger(buyCountEditText.getText().toString()) * 100;
        request.setBuyCount(buyCount);

        BigDecimal sellMoney = toBigDecimal(sellMoneyEditText.getText().toString());
        request.setSellMoney(sellMoney);

        Integer sellCount = toInteger(buyCountEditText.getText().toString()) * 100;
        request.setSellCount(sellCount);

        Boolean etf = eftSwitchCompat.isChecked();
        request.setEtf(etf);

        return request;
    }

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
}

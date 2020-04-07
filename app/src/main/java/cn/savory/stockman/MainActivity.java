package cn.savory.stockman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText buyMoneyEditText;
    EditText buyCountEditText;
    EditText sellMoneyEditText;
    EditText sellCountEditText;
    Button calculateButton;
    TextView totalDeltaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latout_activity_main);

        buyMoneyEditText = findViewById(R.id.buyMoneyEditText);
        buyCountEditText = findViewById(R.id.buyCountEditText);
        sellMoneyEditText = findViewById(R.id.sellMoneyEditText);
        sellCountEditText = findViewById(R.id.sellCountEditText);
        calculateButton = findViewById(R.id.calculateButton);
        totalDeltaEditText = findViewById(R.id.totalDeltaTextView);

        calculateButton.setOnClickListener(onClickListener);
    }

    final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            double buyMoney = readDouble(buyMoneyEditText);
            int buyCount = readInt(buyCountEditText);

            double sellMoney = readDouble(sellMoneyEditText);
            int sellCount = readInt(sellCountEditText);

            double buyServiceCharge = Calculator.calcServiceCharge(buyMoney, buyCount);
            double buyTransferFee = Calculator.calcTransferFee(buyMoney, buyCount, false);

            double sellServiceCharge = Calculator.calcServiceCharge(sellMoney, sellCount);
            double sellTransferFee = Calculator.calcTransferFee(sellMoney, sellCount, false);
            double sellStampTax = Calculator.calcStampTax(sellMoney, sellCount);

            double totalFee = buyServiceCharge + buyTransferFee +
                    sellServiceCharge + sellTransferFee + sellStampTax;
            totalDeltaEditText.setText(String.valueOf(totalFee));
        }
    };

    private double readDouble(EditText editText) {

        Editable text = editText.getText();
        if (text == null || text.length() == 0) {
            return 0;
        }

        try {
            return Double.parseDouble(text.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private int readInt(EditText editText) {

        Editable text = editText.getText();
        if (text == null || text.length() == 0) {
            return 0;
        }

        try {
            return Integer.parseInt(text.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}

package com.example.trifonsheykin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.text.TextWatcher;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    public static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    public static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0;
    private double percent = 0.15;

    private TextView amountTextView;
    private TextView percentTextView; // Для вывода процента чаевых
    private TextView tipTextView; // Для вывода вычисленных чаевых
    private TextView totalTextView; // Для вычисленной общей суммы

    //private TextWatcher amountEditTextWatcher;
    //private SeekBar.OnSeekBarChangeListener seekBarListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTextView = (TextView) findViewById(R.id.amountTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        tipTextView.setText(currencyFormat.format(0)); // Заполнить 0
        totalTextView.setText(currencyFormat.format(0)); // Заполнить 0

        EditText amountEditText =(EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        SeekBar percentSeekBar = findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);



    }


    // Вычисление и вывод чаевых и общей суммы
    private void calculate() {
        // Форматирование процентов и вывод в percentTextView
        percentTextView.setText(percentFormat.format(percent));// Вычисление чаевых и общей суммы
        double tip = billAmount * percent;
        double total = billAmount + tip;
        // Вывод чаевых и общей суммы в формате денежной величины
        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));


    }

    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percent = progress / 100.0;
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            try{
                billAmount = Double.parseDouble(s.toString())/100.0;
                amountTextView.setText(currencyFormat.format(billAmount));

            }catch(NumberFormatException e){
                amountTextView.setText("");
                billAmount = 0.0;

            }
            calculate();

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

}

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int coffeesQuantity = 2;

    public void submitOrderSummary() {
        CheckBox cb = (CheckBox) findViewById(R.id.cb);
        CheckBox choco = (CheckBox) findViewById(R.id.choco);
        EditText et = (EditText) findViewById(R.id.editText);
        String txt = et.getText().toString();
        boolean ass = cb.isChecked();
        boolean ass2 = choco.isChecked();

        int price = coffeesQuantity * 5;
        if (ass)
            price += 1 * coffeesQuantity;
        if (ass2)
            price += 2 * coffeesQuantity;

        String zzz = "Name: " + txt +
                "\ncream?" + ass +
                "\nchoco?" + ass2 +
                "\nQuantity: " + coffeesQuantity +
                "\nPrice: $" + price +
                "\nThank you!";
        summaryDisplay(zzz);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:adawdaw@asdas.bg")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "order this madafaka " + txt);
        intent.putExtra(Intent.EXTRA_TEXT, zzz);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        updateQuantity(coffeesQuantity);
        submitOrderSummary();
    }

    public void plusB(View view) {
        if (coffeesQuantity == 100) {
            Toast.makeText(this, "TOO MUCH COFFEE", Toast.LENGTH_LONG).show();
            return;
        }
        coffeesQuantity++;
        updateQuantity(coffeesQuantity);
    }

    public void minusB(View view) {
        if (coffeesQuantity <= 1) {
            Toast.makeText(this, "zero coffee??? why", Toast.LENGTH_LONG).show();
        } else {
            coffeesQuantity--;
            updateQuantity(coffeesQuantity);

        }
    }

    private void updateQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity);
        quantityTextView.setText("" + number);
    }

    private void summaryDisplay(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.priceView);
        priceTextView.setText(message);
    }

}

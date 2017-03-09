package cz.jankrist.codeone.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import cz.jankrist.codeone.R;

/**
 * Created by jankristdev@gmail.com on 07.03.2017.
 */

public class CodeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_BARCODE = "ex_barcode";
    private Barcode barcode = null;

    private TextView tvTitle;
    private TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_detail);

        tvTitle = (TextView) findViewById(R.id.code_title);
        tvContent = (TextView) findViewById(R.id.code_content);

        if (getIntent() != null) {
            barcode = getIntent().getParcelableExtra(EXTRA_BARCODE);
            if (barcode != null) {
                setBarcode(barcode);
            }
        }

        if(barcode != null){
            tvTitle.setText(barcode.format + "");
            tvContent.setText(barcode.displayValue);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.code_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save : saveBarcodeAndFinish();
        }
        return false;
    }

    private void saveBarcodeAndFinish() {
        if(barcode != null){
            Toast.makeText(this, "Storing barcode " + barcode.rawValue, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void setBarcode(Barcode barcode) {
        this.barcode = barcode;
    }


}

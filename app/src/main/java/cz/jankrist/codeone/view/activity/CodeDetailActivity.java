package cz.jankrist.codeone.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cz.jankrist.codeone.R;

/**
 * Created by jankristdev@gmail.com on 07.03.2017.
 */

public class CodeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_detail);
    }
}

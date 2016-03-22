package org.android.derbibliothek;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BorrowingBookActivity extends AppCompatActivity {

    int state=0;
    TextView studentName;
    TextView bookTitle;
    TextView saveStatus;
    Button scanName;
    Button scanTitle;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowing_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        studentName = (TextView) findViewById(R.id.studentName);
        bookTitle = (TextView) findViewById(R.id.bookTitle);
        saveStatus = (TextView) findViewById(R.id.saveStatus);
        scanName = (Button) findViewById(R.id.scanName);
        scanTitle = (Button) findViewById(R.id.scanTitle);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        scanName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = 1;
                IntentIntegrator integrator = new IntentIntegrator(BorrowingBookActivity.this);
                integrator.initiateScan();
            }
        });


        scanTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = 2;
                IntentIntegrator integrator = new IntentIntegrator(BorrowingBookActivity.this);
                integrator.initiateScan();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = 3;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String scanResultContent = scanResult.getContents();
            if (state == 1){



                //studentName.setText(result);

                scanName.setVisibility(View.GONE);
            }else if(state == 2){
                // bookTitle.setText(result);
                scanTitle.setVisibility(View.GONE);
            }
        }
        // else continue with any other code you need in the method

    }
}

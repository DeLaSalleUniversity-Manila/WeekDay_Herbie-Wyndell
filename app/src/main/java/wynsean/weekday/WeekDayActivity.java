package wynsean.weekday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WeekDayActivity extends AppCompatActivity {

    private TextView tv_out;
    private int month, day, year, finaldate;
    private String day_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_day);
    }

    private void computeDay() {
        int c = year/100;
        int y = year - 100*c;

        if (month==1) {
            month = 13;
            year = year - 1;
            c = year/100;
            y = year - 100*c;
        }
        else if (month==2) {
            month = 14;
            year = year - 1;
            c = year/100;
            y = year - 100*c;
        }

        finaldate = (day + ((26*(month+1))/10) + y + (y/4) + (c/4) + (5*c)) % 7;

        if (finaldate == 0) day_text = "Saturday";
        else if (finaldate == 1) day_text = "Sunday";
        else if (finaldate == 2) day_text = "Monday";
        else if (finaldate == 3) day_text = "Tuesday";
        else if (finaldate == 4) day_text = "Wednesday";
        else if (finaldate == 5) day_text = "Thursday";
        else if (finaldate == 6) day_text = "Friday";
    }

    private void getInputValues() {
        EditText et_month = (EditText) findViewById(R.id.editTextMonth);
        EditText et_day = (EditText) findViewById(R.id.editTextDate);
        EditText et_year = (EditText) findViewById(R.id.editTextYear);

        month = Integer.parseInt(et_month.getText().toString());
        day = Integer.parseInt(et_day.getText().toString());
        year = Integer.parseInt(et_year.getText().toString());
    }

    public void onClickCompute(View view) {
        getInputValues();
        computeDay();
        printDay();
    }
    public void printDay() {
        tv_out = (TextView) findViewById(R.id.textViewOutput);
        tv_out.setText((day_text));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_week_day, menu);
        return true;
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
}

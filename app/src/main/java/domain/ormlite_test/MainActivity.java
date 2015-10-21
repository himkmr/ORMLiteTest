package domain.ormlite_test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db = null;
    DB_connect dbc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Context newctx = this;
        dbc = new DB_connect(newctx);
        db = dbc.getWritableDatabase();
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

    public void enterWords(View v) {
        String text = ((EditText) findViewById(R.id.editText)).getText().toString();
        String desc = ((EditText) findViewById(R.id.editText2)).getText().toString();
        dbc.insert_Row(db, text, desc);
        ((EditText) findViewById(R.id.editText)).setText("");
        ((EditText) findViewById(R.id.editText2)).setText("");
    }

    public void showWords(View v) {
        Cursor c = dbc.getAllTitles(db);
        c.moveToFirst();
        String dic = "";
        while (c.isAfterLast() == false) {
            String word = c.getString(c.getColumnIndex("KEY_WORD"));
            String desc = c.getString(c.getColumnIndex("KEY_DEFINITION"));
            dic = dic + word + " " + desc + "\n";
            c.moveToNext();
        }
        ((EditText) findViewById(R.id.editText3)).setText(dic);
    }
}

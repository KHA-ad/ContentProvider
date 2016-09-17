package cn.edu.bistu.cs.se.accesswords;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="WordsProviderTag";
    private ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resolver = this.getContentResolver();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button btnAll=(Button)findViewById(R.id.btn_all);
        Button btnAdd=(Button)findViewById(R.id.btn_add);
        Button btnDelete=(Button)findViewById(R.id.btn_delete);
        Button btnAllDelete=(Button)findViewById(R.id.btn_allDelete);
        Button btnUpdate=(Button)findViewById(R.id.btn_update);
        Button btnSearch=(Button)findViewById(R.id.btn_search);

        //为每个按钮设置监听器
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = resolver.query(Words.Word.CONTENT_URI,
                        new String[] { Words.Word._ID, Words.Word.COLUMN_NAME_WORD,Words.Word.COLUMN_NAME_MEANING,Words.Word.COLUMN_NAME_SAMPLE},
                        null, null, null);
                if (cursor == null){
                    Toast.makeText(MainActivity.this,"没有找到记录",Toast.LENGTH_LONG).show();
                    return;
                }
                String msg = "";
                if (cursor.moveToFirst()){
                    do{
                        msg += "ID:" + cursor.getInt(cursor.getColumnIndex(Words.Word._ID)) + ",";
                        msg += "单词：" + cursor.getString(cursor.getColumnIndex(Words.Word.COLUMN_NAME_WORD))+ ",";
                        msg += "含义：" + cursor.getString(cursor.getColumnIndex(Words.Word.COLUMN_NAME_MEANING)) + ",";
                        msg += "示例：" + cursor.getString(cursor.getColumnIndex(Words.Word.COLUMN_NAME_SAMPLE)) + "\n";
                    }while(cursor.moveToNext());
                }

                Log.v(TAG,msg);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strWord="Korea";
                String strMeaning="韩国";
                String strSample="Korea lies to the east of China.";
                ContentValues values = new ContentValues();

                values.put(Words.Word.COLUMN_NAME_WORD, strWord);
                values.put(Words.Word.COLUMN_NAME_MEANING, strMeaning);
                values.put(Words.Word.COLUMN_NAME_SAMPLE, strSample);

                Uri newUri = resolver.insert(Words.Word.CONTENT_URI, values);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id="3";
                Uri uri = Uri.parse(Words.Word.CONTENT_URI_STRING + "/" + id);
                int result = resolver.delete(uri, null, null);
            }
        });
        btnAllDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolver.delete(Words.Word.CONTENT_URI, null, null);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id="3";
                String strWord="lemon";
                String strMeaning="柠檬";
                String strSample="A lemon is an acid fruit.";
                ContentValues values = new ContentValues();

                values.put(Words.Word.COLUMN_NAME_WORD, strWord);
                values.put(Words.Word.COLUMN_NAME_MEANING, strMeaning);
                values.put(Words.Word.COLUMN_NAME_SAMPLE, strSample);

                Uri uri = Uri.parse(Words.Word.CONTENT_URI_STRING + "/" + id);
                int result = resolver.update(uri, values, null, null);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id="3";
                Uri uri = Uri.parse(Words.Word.CONTENT_URI_STRING + "/" + id);
                Cursor cursor = resolver.query(Words.Word.CONTENT_URI,
                        new String[] { Words.Word._ID, Words.Word.COLUMN_NAME_WORD, Words.Word.COLUMN_NAME_MEANING,Words.Word.COLUMN_NAME_SAMPLE},
                        null, null, null);
                if (cursor == null){
                    Toast.makeText(MainActivity.this,"没有找到记录",Toast.LENGTH_LONG).show();
                    return;
                }

                //找到记录，这里简单起见，使用Log输出

                String msg = "";
                if (cursor.moveToFirst()){
                    do{
                        msg += "ID:" + cursor.getInt(cursor.getColumnIndex(Words.Word._ID)) + ",";
                        msg += "单词：" + cursor.getString(cursor.getColumnIndex(Words.Word.COLUMN_NAME_WORD))+ ",";
                        msg += "含义：" + cursor.getInt(cursor.getColumnIndex(Words.Word.COLUMN_NAME_MEANING)) + ",";
                        msg += "示例" + cursor.getFloat(cursor.getColumnIndex(Words.Word.COLUMN_NAME_SAMPLE)) + "\n";
                    }while(cursor.moveToNext());
                }

                Log.v(TAG,msg);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

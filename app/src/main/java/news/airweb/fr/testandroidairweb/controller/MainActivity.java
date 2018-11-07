package news.airweb.fr.testandroidairweb.controller;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import news.airweb.fr.testandroidairweb.AirwebNewsWebService;
import news.airweb.fr.testandroidairweb.MyAdapter;
import news.airweb.fr.testandroidairweb.R;
import news.airweb.fr.testandroidairweb.model.News;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ListView mListView;
    AirwebNewsWebService wb = new AirwebNewsWebService();
    ListView dataList;
    public static TextView mainText;
    List<News> list = new ArrayList<>();
    List<News> listDefault = new ArrayList<>();
    News mNews = new News();
    String jsonString ="test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNews.setTitle(jsonString);
        list.add(mNews);



        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                // Instructions consommatrices en temps
                System.out.println("list1 : "+list);
                list.clear();
                System.out.println("list2 : "+list);

                list.addAll(wb.getPoints());

                System.out.println("list3 : "+list);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("THREAD1");
                        System.out.println("list : "+list);
                        System.out.println("list[0] content : "+list.get(0).getContent());

                        final RecyclerView rv = (RecyclerView) findViewById(R.id.list);
                        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        System.out.println("list0 : "+list);
                        final MyAdapter mAdapter = new MyAdapter(list);
                        rv.setAdapter(mAdapter);
                    }
                });
            }
        });
        t.start();

      /*  try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println("ERROR !! ERROR !!");
            e.printStackTrace();
        }
*/
        System.out.println("list : =>  "+list);

    }
}

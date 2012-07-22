package com.devRef;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.devRef.adapters.APIArrayAdapter;
import com.devRef.config.Config;
import com.devRef.entities.APIMenuItem;
import com.example.R;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity {
    public static final String INSTANCE_STATE_API_ARRAY_LIST_NAME = "API_ARRAY_LIST";
    private ArrayList<APIMenuItem> APIs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if (savedInstanceState != null) {
            APIs = savedInstanceState.getParcelableArrayList(INSTANCE_STATE_API_ARRAY_LIST_NAME);
        }

        if (APIs == null) {
            new APIReferenceAsyncTask().execute(Config.DEV_URL + Config.MAIN_LINK);
        } else {
            if (!APIs.isEmpty()) {
                setAPIListView();
            }
        }
    }

    private void setAPIListView() {
        if (!APIs.isEmpty()) {
            ListView lv = (ListView) findViewById(R.id.listViewAPI);
            APIArrayAdapter adapter = new APIArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, APIs);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    APIMenuItem item = (APIMenuItem) adapterView.getAdapter().getItem(position);
                    new APIReferenceAsyncTask().execute(Config.DEV_URL + item.getLink());
                    Log.d("New link", Config.DEV_URL + item.getLink());
                }
            });

            lv.setAdapter(adapter);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(INSTANCE_STATE_API_ARRAY_LIST_NAME, APIs);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        I don't need to do this at this moment, use onCreate(Bundle) for now on.
//        APIs = savedInstanceState.getParcelableArrayList(INSTANCE_STATE_API_ARRAY_LIST_NAME);
    }

    public class APIReferenceAsyncTask extends AsyncTask<String, Void, Void> {
        Elements packagesLinks;

        @Override
        protected Void doInBackground(String... strings) {
            try {
                Log.d("Parse" + Config.DEBUG_NAME, "Start");

                Document doc = Jsoup.connect(strings[0]).get();
                packagesLinks = doc.select("#packages-nav ul > li > a");

                Log.d("Parse" + Config.DEBUG_NAME, "Done");
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            APIs = new ArrayList<APIMenuItem>();

            for( Element packageItem : packagesLinks ){
                APIs.add(new APIMenuItem(packageItem.attr("href"), packageItem.text()));
            }
            setAPIListView();
        }
    }
}
package com.dudulu.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dudulu.app.network.VolleyPool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GameActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id)
        {
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private ListView listView;
        private ListViewAdapter listViewAdapter;
        private ArrayList<RowItem> parentList;
        private TextView textView;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_game, container, false);

            parentList = new ArrayList<RowItem>();
            listView = (ListView)rootView.findViewById(R.id.listView);
            listViewAdapter = new ListViewAdapter(this.getActivity(),R.layout.listview_item,parentList, VolleyPool.getImageLoader());
            listView.setAdapter(listViewAdapter);

            RequestQueue requestQueue = VolleyPool.getRequestQueue();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://192.168.1.110:1337",
                    null,
                    createSuccessListener(),
                    createErrorListener()
                    );
            requestQueue.add(jsonObjectRequest);

            return rootView;
        }

        private Response.Listener<JSONObject> createSuccessListener() {
            return new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.v("Network","request success!");
                        JSONArray feeds = response.getJSONArray("data");
                        RowItem rowItem = new RowItem();
                        for (int i = 0; i < feeds.length(); i++) {
                            JSONObject feed = feeds.getJSONObject(i);
                            Item item = new Item(
                                    feed.getString("thumb"),
                                    feed.getString("title"),
                                    feed.getString("subtitle")
                            );
                            switch (i%3) {
                                case 0 :
                                    rowItem.setItem0(item);
                                    break;
                                case 1 :
                                    rowItem.setItem1(item);
                                    break;
                                case 2 :
                                    rowItem.setItem2(item);
                                    parentList.add(rowItem);
                                    rowItem = new RowItem();
                                    break;
                            }
                        }
                        listViewAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Log.v("Network","request success but parse error!");
                    }
                }
            };
        }

        private Response.ErrorListener createErrorListener() {
            return new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v("Network","request error"+error.getMessage());
                }
            };
        }


    }
}

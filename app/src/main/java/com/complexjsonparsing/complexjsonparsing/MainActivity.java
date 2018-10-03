package com.complexjsonparsing.complexjsonparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    TextView t1,t2,t3,t4,t5,t6;
    String url = "http://www.coxtunes.com/json.json";
    String aa1 = "";
    String aa2 = "";
    String aa3 = "";
    String aa4 = "";
    String aa5 = "";
    String aa6 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.text1);
        t2 = findViewById(R.id.text2);
        t3 = findViewById(R.id.text3);
        t4 = findViewById(R.id.text4);
        t5 = findViewById(R.id.text5);
        t6 = findViewById(R.id.text6);

        fetch();
    }

    private void fetch() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i <response.length(); i++)
                {
                    try
                    {
                        JSONObject object = (JSONObject) response.get(i);
                        aa1 = aa1 + object.getString("bn")+"\n\n";
                        aa2 = aa2 + object.getString("en")+"\n\n";


                        JSONArray jsonArray1= object.getJSONArray("pron");
                        for (int a = 0; a < jsonArray1.length(); a++)
                        {
                            aa3 = aa3 + jsonArray1.getString(a)+", ";

                        }
                        JSONArray jsonArray2 = object.getJSONArray("bn_syns");
                        for (int b = 0; b < jsonArray2.length(); b++)
                        {
                            aa4 = aa4 + jsonArray2.getString(b)+", ";

                        }
                        JSONArray jsonArray3 = object.getJSONArray("en_syns");
                        for (int c = 0; c < jsonArray3.length(); c++)
                        {
                            aa5 = aa5 + jsonArray3.getString(c)+", ";

                        }
                        JSONArray jsonArray4 = object.getJSONArray("sents");
                        for (int d = 0; d < jsonArray4.length(); d++)
                        {
                            aa6 = aa6 +  jsonArray4.getString(d)+", ";

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                t1.setText(aa1+aa2);
                t2.setText(aa5+"\n");
                t3.setText(aa4+"\n");
                t4.setText(aa3+"\n");
                t5.setText(aa6+"\n");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        com.complexjsonparsing.complexjsonparsing.AppController.getInstance().addToRequestQueue(jsonArrayRequest);


    }
}

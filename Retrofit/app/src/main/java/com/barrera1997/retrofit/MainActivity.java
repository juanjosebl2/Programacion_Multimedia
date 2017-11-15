package com.barrera1997.retrofit;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    static final String BASE_URL = "http://api.chucknorris.io/";
    Controller controller;
    TextView text;
    Button boton1;
    Button boton2;
    ListView lista;
    //List<Chuck> datos;
    private ArrayList<Chuck> datos;
    RecyclerView rvChuck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();

        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("",
                res.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("TAB2",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        text = (TextView) findViewById(R.id.textView);
        boton1 = (Button) findViewById(R.id.button);
        boton2 = (Button) findViewById(R.id.button2);
        rvChuck = (RecyclerView) findViewById(R.id.recycleView);
        rvChuck.setHasFixedSize(true);


        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //controller.start();
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                ChuckService chuckService = retrofit.create(ChuckService.class);
                Call <Chuck> call = chuckService.getChuck();
                call.enqueue(new Callback<Chuck>() {
                    @Override
                    public void onResponse(Call<Chuck> call, Response<Chuck> response) {
                        if(response.isSuccessful()) {
                            Chuck chiste = response.body();
                            //System.out.println(chiste.getValue()); un texto en la vista
                            //Log.i("Aqui esta el chiste --->", chiste.getValue());
                            text.setText("Aqui esta el chiste --->" + chiste.getValue());

                        } else {
                            System.out.println(response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<Chuck> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                Chuck2Service chuck2Service = retrofit.create(Chuck2Service.class); 

                Call <ArrayList<Chuck>> call = chuck2Service.getChuck();
                call.enqueue(new Callback<ArrayList<Chuck>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Chuck>> call, Response<ArrayList<Chuck>> response) {
                        if(response.isSuccessful()) {
                            datos = response.body();
                            //System.out.println(chiste.getValue()); un texto en la vista
                            //Log.i("Aqui esta el chiste --->", chiste.getValue());
                            //text.setText("Aqui esta el chiste --->" + chiste.get(0).getValue());
                            datos = new ArrayList<Chuck>();
                            for(int i=0; i<10; i++)
                                datos.add(new Chuck("Chiste"));

                            final ChuckAdapter adaptador = new ChuckAdapter(datos);

                            rvChuck.setAdapter(adaptador);

                            //rvChuck.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                            //recView.setLayoutManager(new GridLayoutManager(this,3));

                            //rvChuck.addItemDecoration(
                                    //new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

                            rvChuck.setItemAnimator(new DefaultItemAnimator());

                        } else {
                            System.out.println(response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Chuck>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });

    }
}

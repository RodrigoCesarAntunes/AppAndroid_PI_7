package com.example.apppi7;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apppi7.api.IApiProduto;
import com.example.apppi7.model.Produto;
import com.example.apppi7.views_code.AdapterProduto;
import com.example.apppi7.views_code.CardProduto;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView container;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private static Context instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this.getBaseContext();
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        container = (RecyclerView) findViewById(R.id.linearContainer);
        container.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        container.setLayoutManager(layoutManager);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CarregarLista();
    }

    private void CarregarLista() {


        Retrofit instanciaRetrofit = new Retrofit.Builder()
                .baseUrl("https://oficinacordova.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IApiProduto api =
                instanciaRetrofit.create(IApiProduto.class);

        Call<List<Produto>> chamada = api.GetProduto();

        Callback<List<Produto>> Callback = new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {

                List<Produto> produtos = response.body();
                ArrayList<CardProduto> cardProdutos = new ArrayList<CardProduto>();

                if (response.isSuccessful() && produtos != null) {
                    String imgUrl = "https://oficinacordova.azurewebsites.net/android/rest/produto/image/";
                    for (Produto produto : produtos) {
                        //CriarCard(produto, imgUrl);
                        cardProdutos.add(new CardProduto(imgUrl + produto.getIdProduto(), Float.toString(produto.getPrecProduto()), produto.getNomeProduto(), produto));
                    }
                    adapter = new AdapterProduto(cardProdutos);
                    container.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {

                String teste = t.getMessage();


            }
        };

        chamada.enqueue(Callback);



    }

    private void PopularProdutos()
    {
        Produto produto = new Produto();
        produto.setNomeProduto("Ração");
        produto.setPrecProduto(4.14f);
        produto.setIdProduto(1);
        String img = "https://cdnv2.moovin.com.br/petcenterexpress/imagens/produtos/original/racao-golden-formula-frango-e-arroz-filhotes-3kg-eb5fd2056a518a2b51151fec0fd65f7b.png";
        CriarCard(produto, img);

    }


    private void CriarCard(Produto produto, String urlImg)
    {
        CardView card = (CardView) LayoutInflater.from(this).inflate(R.layout.card, container, false);
        ImageView imagem = (ImageView) card.findViewById(R.id.imgCard);
        TextView txtPreco = card.findViewById(R.id.txtPreco);
        TextView txtTitulo = card.findViewById(R.id.txtProduto);
        String url = "https://oficinacordova.azurewebsites.net/android/rest/produto/image/" + produto.getIdProduto();

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(urlImg + produto.getIdProduto(), imagem);

        txtPreco.setText(Float.toString(produto.getPrecProduto()));
        txtTitulo.setText(produto.getNomeProduto());



        container.addView(card);

    }

    public static Context getContext()
    {
        return instance;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

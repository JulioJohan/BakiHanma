package com.example.recyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.recyclerview.R;
import com.example.recyclerview.adaptador.RecyclerAdapter;
import com.example.recyclerview.model.ItemList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener {
    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapter adapter;
    private List<ItemList> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initValues();
        initListener();
    }

    private void initViews(){
        rvLista = findViewById(R.id.rvLista);
        svSearch = findViewById(R.id.svSearch);
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new RecyclerAdapter(items, this);
        rvLista.setAdapter(adapter);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private List<ItemList> getItems() {
        List<ItemList> itemLists = new ArrayList<>();
        itemLists.add(new ItemList("Baki Hanma", " Ha crecido mucho, de ser el campeón del torneo, a poder enfrentarse a dos de los presos del corredor de la muerte que se escaparon al mismo tiempo.", R.drawable.baki));
        itemLists.add(new ItemList("Yujiro Hanma", " es el personaje más fuerte de la serie Baki, y se dice que su fuerza es igual a la de todo un ejército o más.", R.drawable.yujirohanma));
        itemLists.add(new ItemList("Kaioh Kaku", "solía ser un monstruo descomunal, como otros personajes de esta lista. Cuando se volvió 90, se dio cuenta de que el camino al verdadero poder pasaba por lograr lógica de las artes marciales.", R.drawable.kaiohkaku));
        itemLists.add(new ItemList("Kaioh Retsu ", "Fue un luchador consumado que alcanzó el rango de Kaioh, lo suficientemente fuerte como para vencer al Monte Toba en menos de un minuto y a Katsumi con solo un golpe.", R.drawable.retsu));
        itemLists.add(new ItemList("Doppo Orochi", " es un gran maestro del décimo dan de Karate Shinshinkai, esposo de Natsue Orochi y padre adoptivo de Katsumi Orochi. También es el sensei de Kiyosumi Katou y Atsushi Suedo.", R.drawable.doppo));
        itemLists.add(new ItemList("Biscuit Oliva", "es lo suficientemente poderoso como para hacer que el gobierno de los Estados Unidos satisfaga todos sus deseos, incluso mientras está encarcelado.", R.drawable.biscuit));
        itemLists.add(new ItemList("Hanayama", "es conocido como la Yakuza más fuerte de Japón y uno de los personajes más fuertes de la serie..", R.drawable.hanayama));

        return itemLists;
    }

    @Override
    public void itemClick(ItemList item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemDetail", item);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}

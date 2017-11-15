package net.sgoliver.android.listviewopt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private TextView lblEtiqueta;
    private ListView lstOpciones;
    private ImageView imagen;


    private Titular[] datos =
            new Titular[]{
                    new Titular(imagen, "Machado"),
                    new Titular(imagen, "Machado"),
                    new Titular(imagen, "Machado"),
                    new Titular(imagen, "Grillo")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = (ImageView) findViewById(R.id.LblTitulo);
        lblEtiqueta = (TextView)findViewById(R.id.LblEtiqueta);
        lstOpciones = (ListView)findViewById(R.id.LstOpciones);

        //Cabecera
        //View header = getLayoutInflater().inflate(R.layout.list_header, null);
        //lstOpciones.addHeaderView(header);

        //Adaptador
        AdaptadorTitulares adaptador =
                new AdaptadorTitulares(this, datos);

        lstOpciones.setAdapter(adaptador);

        //Eventos
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                //Alternativa 1:
                //String opcionSeleccionada =
                        //((Titular)a.getItemAtPosition(position)).getTitulo();
                String opcionSeleccionada2 =
                        ((Titular)a.getItemAtPosition(position)).getSubtitulo();

                //Alternativa 2:
                //String opcionSeleccionada =
                //		((TextView)v.findViewById(R.id.LblTitulo))
                //			.getText().toString();

                //lblEtiqueta.setText("Opción seleccionada: " + opcionSeleccionada + " " + opcionSeleccionada2);
                lblEtiqueta.setText("Opción seleccionada: " + " " + opcionSeleccionada2);
                lanzar(v);
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

    class AdaptadorTitulares extends ArrayAdapter<Titular> {

        AdaptadorTitulares(Context context, Titular[] datos) {
            super(context, R.layout.listitem_titular, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;
            ViewHolder holder;

            if(item == null)
            {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.listitem_titular, null);

                holder = new ViewHolder();
                holder.titulo = (ImageView)item.findViewById(R.id.LblTitulo);
                holder.subtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);

                item.setTag(holder);
            }
            else
            {
                holder = (ViewHolder)item.getTag();
            }

            //holder.titulo.setText(datos[position].getTitulo());
            holder.subtitulo.setText(datos[position].getSubtitulo());

            return(item);
        }
    }

    static class ViewHolder {
        ImageView titulo;
        TextView subtitulo;
    }

    public void lanzar(View View){
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);
    }
}

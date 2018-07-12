package proyectalarma.app.ivonnehdez.proyectoalarma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import app.Alarma;
import app.ListaAlarma;

public class MainActivity extends AppCompatActivity {

    public static ListaAlarma alarmas = new ListaAlarma();
    public static app.Alarma despertador;

    Button siguiente;
    ListView lista;
    myAdapter ada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        siguiente = findViewById(R.id.btnalarma);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivarAlarma.class);
                MainActivity.this.startActivity(i);
            }
        });

        lista = findViewById(R.id.listAlarma);

        ada = new  myAdapter();
        lista.setAdapter(ada);

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getBaseContext(), Eliminar.class);
                despertador = alarmas.getList().get(position);

                startActivity(i);

                return true;
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(ada != null)
            ada.notifyDataSetChanged();

    }


    public void change(View v){
        ada.notifyDataSetChanged();
    }

    class myAdapter extends BaseAdapter {

        private LayoutInflater infla = LayoutInflater.from(getBaseContext());

        @Override
        public int getCount() {
            return alarmas.getList().size();
        }

        @Override
        public Object getItem(int position) {
            return alarmas.getList().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        TextView txtHora;
        TextView dia;
        TextView modo;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            try{



                if(convertView == null) {


                    convertView = this.infla.inflate(R.layout.item, parent, false);

                    txtHora = ((TextView)convertView.findViewById(R.id.txtHora));
                    dia = ((TextView)convertView.findViewById(R.id.txtdia));
                    modo = ((TextView)convertView.findViewById(R.id.txtmodo));

                    //txtHora.setText(it.max);
                    //dia.setText(it.fecha.toString());
                    //modo.setText(it.min);

                }else {

                    Alarma it = alarmas.getList().get(position);

                    //txtTitulito.setText(it.titulo);
                    //txtcants.setText(it.cantidad);
                    //txtdate.setText((CharSequence) it.fecha);
                    //txtmax.setText(it.max);
                    //txtmin.setText(it.min);
                }

            }catch(Exception e){
                e.printStackTrace();
                return null;
            }

            return convertView;
        }

    }

}

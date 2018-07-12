package proyectalarma.app.ivonnehdez.proyectoalarma;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActivarAlarma extends AppCompatActivity implements View.OnClickListener {

    Button btnHora, btnGuardar;
    EditText editHora, editNom;
    TextView editmnsj;
    CheckBox r1,r2,r3,r4,r5,r6,r7;
    Spinner spinmet;
    private int hora, min;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activar_alarma);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnHora = findViewById(R.id.btnHora);
        editmnsj = findViewById(R.id.mnsj);
        editHora = findViewById(R.id.editHora);
        btnHora.setOnClickListener(this);
        spinmet = findViewById(R.id.spinMet);

        r1 = findViewById(R.id.lunes);
        r2 = findViewById(R.id.martes);
        r3 = findViewById(R.id.miercoles);
        r4 = findViewById(R.id.jueves);
        r5 = findViewById(R.id.viernes);
        r6 = findViewById(R.id.sabado);
        r7 = findViewById(R.id.domingo);

        View.OnClickListener lista = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String opcion = "";
                switch(view.getId()) {
                    case R.id.lunes:
                        opcion = "Lunes";
                        break;
                    case R.id.martes:
                        opcion = "Martes";
                        break;
                    case R.id.miercoles:
                        opcion = "Miercoles";
                        break;
                    case R.id.jueves:
                        opcion = "Jueves";
                        break;
                    case R.id.viernes:
                        opcion = "Viernes";
                        break;
                    case R.id.sabado:
                        opcion = "Sabado";
                        break;
                    case R.id.domingo:
                        opcion = "Domingo";
                        break;
                }
                editmnsj.setText(opcion);
            }
        };
        r1.setOnClickListener(lista);
        r2.setOnClickListener(lista);
        r3.setOnClickListener(lista);
        r4.setOnClickListener(lista);
        r5.setOnClickListener(lista);
        r6.setOnClickListener(lista);
        r7.setOnClickListener(lista);


        Spinner spinner = findViewById(R.id.spinMet);


        String[] metodo = {"Sonido","Luz encendida","Sonido y Luz"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, metodo));


    }

    @Override
    public void onClick(View v) {

        if(v==btnHora){
            final Calendar c= Calendar.getInstance();
            hora =c.get(Calendar.HOUR_OF_DAY);
            min =c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    editHora.setText(hourOfDay+":"+minute);

                }
            } , hora,min, false);
            timePickerDialog.show();
        }
    }

    public void guardaralarma(View v){

        if(r1.isChecked()){
            Toast.makeText(ActivarAlarma.this, "Valor" + r1, Toast.LENGTH_SHORT).show();

        }

    }

    private void subirAlarma() {
        String hora = editHora.getText().toString();

        Map<String, Object> data = new HashMap<>();
        data.put("hora", hora);
        data.put("dias", hora);
        data.put("tipoAlarma", hora);
        db.collection("Eventos").add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(ActivarAlarma.this, "Alarma guardada exitosamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ActivarAlarma.this, "Error al subir alarma", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

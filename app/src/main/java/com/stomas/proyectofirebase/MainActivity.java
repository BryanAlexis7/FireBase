package com.stomas.proyectofirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextType;
    private Button btnSubmit;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // Vincular los elementos del layout
        editTextName = findViewById(R.id.editTextName);
        editTextType = findViewById(R.id.editTextType);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Configurar el evento click del botón
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String type = editTextType.getText().toString();

                if (name.isEmpty() || type.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear un mapa con los datos
                final Data petData = new Data(name, type);

                // Enviar los datos a Firestore
                db.collection("mascotas")
                        .add(petData)
                        .addOnSuccessListener(documentReference ->
                                Toast.makeText(MainActivity.this, "Datos enviados correctamente", Toast.LENGTH_SHORT).show()
                        )
                        .addOnFailureListener(e ->
                                Toast.makeText(MainActivity.this, "Error al enviar datos: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                        );
            }
        });
    }

    // Clase para manejar los datos
    private static class Data {
        private String name;
        private String type;

        public Data(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }
}

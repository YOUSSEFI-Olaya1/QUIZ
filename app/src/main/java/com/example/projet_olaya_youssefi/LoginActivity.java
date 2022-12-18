package com.example.projet_olaya_youssefi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //déclaration des deux champs de la page sign in
    EditText username, password;

    //le boutton btnlogin ("sign in") pour accéder à l'application
    //le boutton btnlogin1 pour revenir à la page registration
    Button btnlogin,btnlogin1;

    //déclaration de la base de donnée
    DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        // on a utilisé findViewById pour rechercher la vue enfant ayant l’ID qu'on a fourni dans l’argument.
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        btnlogin1= (Button) findViewById(R.id.btnsignin2);
        DB = new DbHelper(this);

                /*si on clique sur le bouton btnlogin qui s'affiche sous forme "sign in"
        dans notre page on va se dériger vers notre application*/
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //la méthode getText() est utilisée pour récupérer les données des champs remplis
                String user = username.getText().toString();
                String pass = password.getText().toString();

                //Si un des champs n'est pas rempli alors on va avoir une erreur
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{

                    //vérifier en utilisant son nom et son mot de passe si un utilisateur éxiste déja dans la base de donnée
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);

                    /*si oui l'utilisateur existe on va accéder à notre application*/
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                        /*sinon on va avoir des erreurs*/
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //le boutton btnlogin1 pour revenir à la page registration
        btnlogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });

        }
    }

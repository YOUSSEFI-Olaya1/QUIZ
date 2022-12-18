package com.example.projet_olaya_youssefi;
        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
public class login extends AppCompatActivity {

    //déclaration des trois champs de la page registration
    EditText username, password, repassword;

    //le boutton signup ("Existing user! Go to Sign in page") pour accéder à l'application
    //le boutton signin pour se dériger vers la page login
    Button signup, signin;

    //déclaration de la base de donnée
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // on a utilisé findViewById pour rechercher la vue enfant ayant l’ID qu'on a fourni dans l’argument.
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DbHelper(this);

        /*si on clique sur le bouton signup qui s'affiche sous forme "Register"
        dans notre page on va se dériger vers notre application*/
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //la méthode getText() est utilisée pour récupérer les données des champs remplis
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                //Si un des champs n'est pas rempli alors on va avoir une erreur
                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){

                        //vérifier en utilisant son nom si un utilisateur éxiste déja dans la base de donnée
                        Boolean checkuser = DB.checkusername(user);

                        //Si non alors on va insérer son nom et son mot de passe dans la base de donnée
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);

                            /* si l'insértion est éffectue correctement alors on va avoir le message suivant :
                            "Registered successfully"
                            par la suite on va se dériger vers notre application*/
                            if(insert==true){
                                Toast.makeText(login.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(login.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }


                        //Si la méthode checkusername(user) retourn vrai c'est à dire que l'utilisateur existe déja
                        else{
                            Toast.makeText(login.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }

                        //si on a trompé en tapant le nouveau mot de passe on va avoir le message suivant"Passwords not matching"
                    }else{
                        Toast.makeText(login.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });


        /*si on clique sur le bouton signin qui s'affiche sous forme
        "Existing user! Go to Sign in page" dans notre page on va se deriger vers la page sign in*/
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
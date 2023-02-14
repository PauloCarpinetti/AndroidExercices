package com.example.appfirebase;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ImageView imageCelular;
    private Button buttonUpload;
    private Button buttonDownload;
    private ImageView imagePadrao;

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------Upload de Imagens

        buttonUpload = findViewById(R.id.buttonUpload);
        imageCelular = findViewById(R.id.imageCelular);
        buttonDownload = findViewById(R.id.buttonDownload);
        imagePadrao = findViewById(R.id.imageView2);

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //configura imagem para ser salva em memoria
            imageCelular.setDrawingCacheEnabled(true);
            imageCelular.buildDrawingCache();

            // recupera bitmap da imagem (imagem a ser carregada)

            Bitmap bitmap = imageCelular.getDrawingCache();

            // comprimir bitmap para png/jpeg

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

            // converte o baos para pixels brutos em uma matriz de bytes
            byte[] dadosImagem = baos.toByteArray();

            // define nós para o FirebaseStorage

            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            StorageReference imagens = storageReference.child("imagens");
            //nome da imagem
                String nomeArquivo = UUID.randomUUID().toString();
            StorageReference imageRef = imagens.child( nomeArquivo + ".jpeg");

            // fazer upload imagem

                UploadTask uploadTask = imageRef.putBytes( dadosImagem );

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Upload da imagem falhou: "
                                + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //Uri url = taskSnapshot.getDownloadUrl();  versoes antigas firebase
                        imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri url = task.getResult();
                                Toast.makeText(MainActivity.this,
                                        "Sucesso ao fazer upload!" + url.toString()
                                        , Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

            }
        });

        //------------------Download e exclusão de Imagens

        //exclusao

        //...
        // começa em configura imagem para ser salva em memoria
        /*
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference imagens = storageReference.child("imagens");
        //nome da imagem
        String nomeArquivo = UUID.randomUUID().toString();
        StorageReference imageRef = imagens.child( nomeArquivo + ".jpeg");

        imageRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,
                        "Erro ao deletar arquivo: ",
                        Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MainActivity.this,
                        "Sucesso ao deletar arquivo: ",
                        Toast.LENGTH_LONG).show();
            }
        });
        */

        // download de arquivo

        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                //configura imagem para ser salva em memoria
                imageCelular.setDrawingCacheEnabled(true);
                imageCelular.buildDrawingCache();

                // recupera bitmap da imagem (imagem a ser carregada)

                Bitmap bitmap = imageCelular.getDrawingCache();

                // comprimir bitmap para png/jpeg

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

                // converte o baos para pixels brutos em uma matriz de bytes
                byte[] dadosImagem = baos.toByteArray();


                 */
                // define nós para o FirebaseStorage

                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens");
                //nome da imagem
                //String nomeArquivo = UUID.randomUUID().toString();
                StorageReference imageRef = imagens.child("celular.jpeg");

                // download imagem

                Glide.with(MainActivity.this).load( imageRef )
                        .into(imagePadrao);

            }

        });




        // cadastro de usuario

       /* auth.createUserWithEmailAndPassword(
                "paulosouza@gmail.com", "senha123")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i("CreateUser", "Sucesso ao cadastrar usuário!");
                        }else {
                            Log.i("CreateUser", "Erro ao cadastrar usuário!");
                        }
                    }
                });

        auth.createUserWithEmailAndPassword(
                        "robertosouza@gmail.com", "senha456")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i("CreateUser", "Sucesso ao cadastrar usuário!");
                        }else {
                            Log.i("CreateUser", "Erro ao cadastrar usuário!");
                        }
                    }
                }); */

        // logar usuario
        /*
        auth.signInWithEmailAndPassword(
                "paulosouza@gmail.com", "senha123")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i("SignInUser", "Sucesso ao logar usuário!");
                        }else {
                            Log.i("SignInUser", "Erro ao logar usuário!");
                        }
                    }
                });
           */


        // verifica usuario logado
        /*
        if (auth.getCurrentUser() != null){
            Log.i("CurrentUser", "Usuário logado!");
        } else {
            Log.i("CurrentUser", "Usuário não logado!");
        }
            */

        //Deslogar usuario
        //auth.signOut();

        // gerar identificador unico do elemento, método push()

        //DatabaseReference usuarios = reference.child("usuarios");
        //Usuario usuario = new Usuario();
        //usuario.setNome("Lívia");
        //usuario.setSobreNome("Souza");
        //usuario.setEmail("liviasouza@gmail.com");

        //usuarios.push().setValue( usuario );

       // Filtros de pesquisa

        /*
        DatabaseReference usuarios = reference.child("usuarios");
        DatabaseReference usuarioPesquisa = usuarios.child("-NM9_CmqdwB5-HwYJ3ha");

        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Usuario dadosUsuario = snapshot.getValue(Usuario.class);
                //Log.i("Dados usuário: ", "nome: " + dadosUsuario.getNome() );
                Log.i("Dados usuario: ", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


         */

        /*
        DatabaseReference usuarios = reference.child("usuarios");
        Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Lívia");

        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Usuario dadosUsuario = snapshot.getValue(Usuario.class);
                //Log.i("Dados usuário: ", "nome: " + dadosUsuario.getNome() );
                Log.i("Dados usuario: ", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */

        // incluir no EventListener
         //limita pesquisa para 2 primeiros registros
        // Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2);

        //limita pesquisa para 2 ultimos registros
        // Query usuarioPesquisa = usuarios.orderByKey().limitToLast(2);


        /*
            Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(40); maior ou igual 40

            Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(40); menor ou igual 40

            Query usuarioPesquisa = usuarios.orderByChild("idade")
            .startAt(18).endAt(40); maior ou igual 18 e menor ou igual 40

            Query usuarioPesquisa = usuarios.orderByChild("nome")
            .startAt("L")
            .endAt("L" + "\uf8ff" ); usuarios somente que começem com L

         */


         // recupera dados do firebase
        /*
        DatabaseReference usuarios = reference.child("usuarios");
        DatabaseReference produtos = reference.child("produtos");

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //String value = snapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);

                Log.i("FIREBASE", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        */


        // adicionar dados no firebase
        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        DatabaseReference usuarios = reference.child("usuarios");

        Usuario usuario1 = new Usuario();
        usuario1.setNome("Roberto");
        usuario1.setSobreNome("Souza");
        usuario1.setEmail("robertoouza@gmail.com");

        usuarios.child("002").setValue(usuario1);

        DatabaseReference produtos = reference.child("produtos");

        Produto produto1 = new Produto();
        produto1.setNome("IPhone X");
        produto1.setDescricao("Smartphone");
        produto1.setPreco(5999.00);

        produtos.child("001").setValue( produto1 );

        Produto produto2 = new Produto();
        produto2.setNome("DELL Inspiron");
        produto2.setDescricao("Notebook");
        produto2.setPreco(6999.00);

        produtos.child("002").setValue( produto2 );

         */



    }
}
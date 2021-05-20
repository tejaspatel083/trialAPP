package com.example.rest_api_demo;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.rest_api_demo.firebasecallbacks.FirestoreWrite;
import com.example.rest_api_demo.firebasecallbacks.LoginCallback;
import com.example.rest_api_demo.firebasecallbacks.ReadCallback;
import com.example.rest_api_demo.firebasecallbacks.RegisterCallback;
import com.example.rest_api_demo.models.Person;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseComponents {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private Activity activity;
    NavController navController;
    Person person;


    public FirebaseComponents() {
    }

    public FirebaseComponents(FirebaseFirestore firebaseFirestore, FirebaseUser firebaseUser) {
        this.firebaseFirestore = firebaseFirestore;
        this.firebaseUser = firebaseUser;
    }

    public FirebaseComponents(FirebaseAuth firebaseAuth, Activity activity) {
        this.firebaseAuth = firebaseAuth;
        this.activity = activity;
    }

    public FirebaseComponents(FirebaseFirestore firebaseFirestore, FirebaseAuth firebaseAuth, Activity activity) {
        this.firebaseFirestore = firebaseFirestore;
        this.firebaseAuth = firebaseAuth;
        this.activity = activity;
    }

    public void loginUser(String email, String password, LoginCallback loginCallback) {


        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                loginCallback.loginCallback(true);
            } else {
                loginCallback.loginCallback(false);
            }
        });


    }

    public void readFireStore(ReadCallback readCallback) {


        firebaseFirestore = FirebaseFirestore.getInstance();

        DocumentReference documentReference = firebaseFirestore.collection("User").document(firebaseUser.getUid());

        documentReference.get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();

                Log.d("FirebaseFireStore", documentSnapshot.getString("Name"));

                person = new Person(documentSnapshot.getString("Email"), documentSnapshot.getString("Name"), documentSnapshot.getString("Location"));

                readCallback.onCallBack(person);


            } else {


                Log.d("FirebaseComponents", task.getException().toString());

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public void createUser(com.example.rest_api_demo.models.Person person, RegisterCallback registerCallback) {


        firebaseAuth.createUserWithEmailAndPassword(person.getEmail(), person.getPassword()).addOnCompleteListener(task -> {


            if (task.isSuccessful()) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                writeFireStore(person, firebaseUser, success -> registerCallback.registerSuccess(success));

            } else {
                Toast.makeText(activity.getApplicationContext(), "Registration Error", Toast.LENGTH_SHORT).show();
            }
        });




    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void writeFireStore (Person person, FirebaseUser firebaseUser, FirestoreWrite firestoreWrite){

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("Name", person.getName());
        userMap.put("Email", person.getEmail());
        userMap.put("location", person.getLocation());

        firebaseFirestore.collection("User").document(firebaseUser.getUid())
                .set(userMap).addOnCompleteListener(activity, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(activity, "Registration Successful", Toast.LENGTH_SHORT).show();
                firestoreWrite.fireStoreWrite(true);
            } else {

                Toast.makeText(activity, "firestore error!", Toast.LENGTH_SHORT).show();
                firestoreWrite.fireStoreWrite(false);
            }
        });
    }
}

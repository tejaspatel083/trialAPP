package com.example.rest_api_demo.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rest_api_demo.DashActivity;
import com.example.rest_api_demo.FirebaseComponents;
import com.example.rest_api_demo.R;
import com.example.rest_api_demo.firebasecallbacks.LoginCallback;
import com.example.rest_api_demo.utils.NetworkCheck;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginFragment extends Fragment {


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    Button btn_login;
    EditText edt_email,edt_password;
    FirebaseComponents firebaseComponents;
    NavController navController;

    TextView txt_register;

    public loginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser!=null)
        {
            updateUI();
            Toast.makeText(getActivity().getApplicationContext(), "User already Signin", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseComponents = new FirebaseComponents(firebaseAuth,getActivity());
        navController = Navigation.findNavController(getActivity(),R.id.login_host_fragment);

        btn_login = view.findViewById(R.id.btn_login);
        edt_email = view.findViewById(R.id.edt_login_email);
        edt_password = view.findViewById(R.id.edt_login_password);
        txt_register = view.findViewById(R.id.txt_register);

        btn_login.setOnClickListener(view1 -> {

            NetworkCheck networkCheck = new NetworkCheck();

            if (networkCheck.checkNetwork(getActivity()))
            {
                firebaseComponents.loginUser(edt_email.getText().toString(), edt_password.getText().toString(), loginSuccess -> {


                    if (loginSuccess == true) {
                        firebaseUser = firebaseAuth.getCurrentUser();
                        updateUI();

                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Authentication Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else
            {
                Snackbar snackbar = Snackbar.make(view,"No Internet",Snackbar.LENGTH_INDEFINITE);
                snackbar.show();
            }

        });

        txt_register.setOnClickListener(view2 -> {
            navController.navigate(R.id.registerFragment);
        });

    }

    private void updateUI() {

        Toast.makeText(getActivity().getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(getActivity(), DashActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
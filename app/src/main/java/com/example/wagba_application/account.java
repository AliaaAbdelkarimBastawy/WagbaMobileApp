package com.example.wagba_application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link account#newInstance} factory method to
 * create an instance of this fragment.
 */
public class account extends Fragment {
    FirebaseAuth mAuth;
    ConstraintLayout EditAccount;
    Button Logout;
    Button Update;
    AlertDialog.Builder builder;
    TextView email;
    TextView name;
    Intent intent03;
    Intent intent04;
    public static final String PREFS_NAME = "MyPreferenceFile";
     static String EMAILL;
    static String PASWORD;
    UniversalUserDatabase_3 uniDB;
    UniversalDao_3 uniDao;
    static int MAXLOGINATTEMPTS = 5;
    static String TAG = "USERLOGINFO";
    long userId;
    String NameToPut;
    EditText Number;
    EditText CollegeID;
    EditText Name;

    User_3 currentUser = new User_3();
    int loginAttempts = 0;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public account() {
        // Required empty public constructor
    }

    public account(String email, String password) {

         account.EMAILL = email;
         account.PASWORD = password;

    }

    private boolean login(String userName, String password) {
        long userId;
        if (++loginAttempts >= MAXLOGINATTEMPTS) return false;
        if (!((userId = uniDao.verifyUserLogin(userName,password)) > 0)) return false;
        currentUser = uniDao.getUserById(userId);
        loginAttempts = 0;
        return true;
    }
    private boolean forceLogin(String userName, String password, boolean crashIfTooManyAttempts) {
        while (!login(userName,password)) {
            if (loginAttempts >= MAXLOGINATTEMPTS) {
                if (crashIfTooManyAttempts)
                    throw new RuntimeException("Too Many Login Attempts - Goodbye");
                return false;
            }
        }
        return true;
    }
    private void logCurrentUserLog() {
        for(UserLog_3 ul: uniDao.getUserLogs(currentUser.getUserId())) {
            Log.d(TAG,ul.timestamp + " User = " + currentUser.userName + "Log = " + ul.logData);
        }
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment account.
     */
    // TODO: Rename and change types and number of parameters
    public static account newInstance(String param1, String param2) {
        account fragment = new account();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        System.out.println("ON Pause");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_account, container, false);
        Logout= view.findViewById(R.id.LogoutBtn);
        builder = new AlertDialog.Builder(view.getContext());
        name = view.findViewById(R.id.Name);
        uniDB = UniversalUserDatabase_3.getInstance(getContext());
        uniDao = uniDB.getAllDao();
        System.out.println( account.EMAILL);
        System.out.println( account.PASWORD);
        userId =  uniDao.verifyUserLogin( account.EMAILL,account.PASWORD);
        System.out.println(userId);
        currentUser = uniDao.getUserById(userId);
        Name = view.findViewById(R.id.Username);
        Number = view.findViewById(R.id.NumberEditText);
        CollegeID =view.findViewById(R.id.CollegeIDEditText);
        Update = view.findViewById(R.id.Updatebtn);
        System.out.println("ON CREATE VIEW");
        if (currentUser!= null)
        {
            System.out.println(currentUser.name);
            System.out.println(currentUser.number);
            System.out.println(currentUser.collegeID);
        }
        else
        {
            System.out.println("Current user is NULL");

        }


        if (currentUser != null)
        {
            for(UserLog_3 ul: uniDao.getUserLogs(currentUser.getUserId())) {
                Name.setText(currentUser.name);
                Number.setText(currentUser.number);
                CollegeID.setText(currentUser.collegeID);
            }
        }


        // Initialize the view model instance

//        mAllData = profileDataViewModel.getAllProfileData();
//        System.out.println("VALUEE1111" + mAllData.getValue());
//

        mAuth = FirebaseAuth.getInstance();
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Logout?")
                        .setMessage("Are you sure you want to logout?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mAuth.signOut();
                                intent04 = new Intent(view.getContext(),
                                        LoginActivity.class);
                                view.getContext().startActivity(intent04);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NameEdit = Name.getText().toString();
                String IDEdit = CollegeID.getText().toString();
                String NumberEdit = Number.getText().toString();
                uniDao.Update(NumberEdit, NameEdit, IDEdit,  uniDao.verifyUserLogin(EMAILL,PASWORD));
            }
        });

        return view;
    }
}
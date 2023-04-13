package com.example.myresponseautoapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;

/*Fragment 2 qui affiche le messages existants et messages saisis
 *Conversion Arraylist en string JSON et stocker dans shared preferences
 * Load array erreur (en commetaire pour éviter le crash)
*/
public class Message extends Fragment {
    //instance of shared preferences used to store data of the app
    SharedPreferences sharedPreferences;
    //instance of Arraylist that contains list of messages stored in class listMessage
    ArrayList<listMessage> messageList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_message, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
        EditText editText=view.findViewById(R.id.editTextData);
        Button button= view.findViewById(R.id.buttonAddData);
        //ArrayList<String> retrievedList = loadArrayList(getContext(), "message_data");
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
            
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  textView=editText.getText().toString();
                messageList.add(new listMessage(textView));
                saveArrayList(textView);

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new ListAdapter(getContext(),messageList));
    }

    public ArrayList<String> loadArrayList(Context context, String message_data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson= new Gson();
        String json=sharedPreferences.getString("message_data",null);
        Type type=new TypeToken<ArrayList<listMessage>>(){}.getType();
        return gson.fromJson(json, type);
    }

    private void saveArrayList(String textView) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson= new Gson();
        String json=gson.toJson(messageList);
        editor.putString("message_data",json);
        editor.apply();
    }


}


package com.example.myresponseautoapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

//Pager Adapter pour les fragments. Chaque ouverture de l'app, il affiche le fragment
public class PagerAdapter extends FragmentStateAdapter {
    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    //permet de 'modifier des positions'
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Contact();
            case 1:
                return new Message();
            case 2:
                return new Response();
            default:
                return new Contact();
        }
    }

    @Override
    public int getItemCount() {
        //retourne le nombre de Fragment
        return 3;
    }
}

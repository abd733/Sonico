package com.example.sonicochat.tabslayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : return new Tab0();
            case 1 : return new Tab1();
            case 2 : return new Tab2();
            case 3 : return new Tab3();
            case 4 : return new Tab4();
            case 5 : return new Tab5();
            case 6 : return new Tab6();
            case 7 : return new Tab7();
            case 8 : return new Tab8();
            case 9 : return new Tab9();
            case 10 : return new Tab10();
            default: return new Tab0();
        }
    }

    @Override
    public int getItemCount(){
        return 11;
    }
}



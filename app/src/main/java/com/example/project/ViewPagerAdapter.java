package com.example.project;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private static final int CARD_ITEM_SIZE = 3;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position){
            case 0: fragment = new ReminderTab(); break;
            case 1: fragment = new EmergencyTab(); break;
            case 2: fragment = new ChatTab(); break;
            default:fragment = new HomeFragment(); break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return CARD_ITEM_SIZE;
    }
}

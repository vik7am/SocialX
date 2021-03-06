package com.example.socialx;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {

    Fragment signupFragment;
    Fragment loginFragment;

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            loginFragment = new Login();
            return loginFragment;
        }
        else{
            signupFragment = new Signup();
            return signupFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

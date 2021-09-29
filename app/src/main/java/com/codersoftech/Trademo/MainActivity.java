package com.codersoftech.Trademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {

    // initilize the variable
    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign variable
        bottomNavigation=findViewById(R.id.bottom_navigation);

        //add bottom navigation menu
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_portfolio));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_account));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //initilize fragment
                Fragment fragment =null;

                switch (item.getId()){
                    case 1:
                        fragment =new HomeFragment();
                        break;
                    case 2:
                        fragment= new PortfolioFragment();
                        break;
                    case 3:
                        fragment =  new AccountFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });
        //set home fragment selected initially
        bottomNavigation.show(1,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //make toast
                //Toast.makeText(getApplicationContext(),"you clicked"+item.getId(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
    }
}
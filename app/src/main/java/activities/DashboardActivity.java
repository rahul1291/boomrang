package activities;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import Boomerang.R;
import adapters.SideBarAdapter;
import commonutils.SyncAlarmClass;
import fragments.ContactUs;
import fragments.MyDashBoard;
import fragments.MyFiles;
import fragments.Settings;
import fragments.UserProfile;


public class DashboardActivity extends FragmentActivity implements AdapterView.OnItemClickListener,View.OnClickListener{
    public static SlidingPaneLayout slidingpane;
    public static FragmentManager fragmentManager;
    ListView lv_drawer;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        try{
            System.out.println("inside on resume");
            boolean IsAutoSync=getSharedPreferences("Login", 0).getBoolean("IsAutoSync",false);
            if(IsAutoSync){
                int time=getSharedPreferences("Login", 0).getInt("SyncInterval",0);
                SyncAlarmClass.FireAlarm(this,time);
            }
            lv_drawer=(ListView)findViewById(R.id.lv_drawer);
            lv_drawer.setOnItemClickListener(this);

            slidingpane=(SlidingPaneLayout)findViewById(R.id.slidingpane);

            SideBarAdapter adapter=new SideBarAdapter(this);
            lv_drawer.setAdapter(adapter);

            ActionBar bar = getActionBar();
            bar.setDisplayShowHomeEnabled(false);
            bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            bar.setCustomView(R.layout.action_bar_layout);

            ((ImageView)findViewById(R.id.iv_logout)).setOnClickListener(this);
            ((TextView)findViewById(R.id.tv_email)).setText(getSharedPreferences("Login", 0).getString("emailID", ""));
            ((ImageView)findViewById(R.id.iv_toggle)).setOnClickListener(this);
            onItemClick(null,null,3,0);

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**************************************************************************************************************************/
    /***
     * This method is used for replacing one fragment with another
     * @param id
     * -id of the container containing fragments
     * @param fragment
     * -fragment to replace
     * @param tag
     * -tag associated with the fragment
     */
    public void FragmentTransactions(int id, android.app.Fragment fragment,String tag)
    {
        Fragment _fragment = getFragmentManager().findFragmentByTag(tag);
        fragmentManager = getFragmentManager();

        if (null == _fragment) {
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.setCustomAnimations(
                    R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                    R.animator.card_flip_left_in, R.animator.card_flip_left_out);
            fragmentTransaction.replace(id, fragment, tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } else {
            fragmentManager.popBackStack(tag, 0);
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (slidingpane.isOpen()) {
            slidingpane.closePane();
        }
               switch (position){
                   case 0:
                       finish();
                       break;
                   case 1:
                       try{
                           FragmentTransactions(R.id.fragment_container,new UserProfile(),"myprofile");
                       }
                       catch (Exception e){
                           e.printStackTrace();
                       }

                       break;
                   case 2:
                       try{
                           FragmentTransactions(R.id.fragment_container,new MyFiles(),"myfiles");
                       }
                       catch (Exception e){
                           e.printStackTrace();
                       }

                       break;
                   case 3:
                       try{
                           FragmentTransactions(R.id.fragment_container,new MyDashBoard(),"dashboard");
                       }
                       catch (Exception e){
                           e.printStackTrace();
                       }
                       break;
                   case 4:
                       try{
                           FragmentTransactions(R.id.fragment_container,new ContactUs(),"contactus");
                       }
                       catch (Exception e){
                           e.printStackTrace();
                       }
                       break;
                   case 5:
                       try{
                           FragmentTransactions(R.id.fragment_container,new Settings(),"settings");
                       }
                       catch (Exception e){
                           e.printStackTrace();
                       }
                       break;
                   case 6:
                       try{
                           getSharedPreferences("Login",0).edit().clear().commit();
                           Intent logout = new Intent(this, MainActivity.class);
                           logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                   | Intent.FLAG_ACTIVITY_NEW_TASK
                                   | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(logout);
                       }
                       catch (Exception e){
                           e.printStackTrace();
                       }
                       break;
               }
    }

    @Override
    public void onBackPressed() {

       if(slidingpane.isOpen()){
           slidingpane.closePane();
        }
        else{
            if (getFragmentManager().getBackStackEntryCount() > 1) {
                getFragmentManager().popBackStack();
            } else {
                System.out.println("activity finish");
                SyncAlarmClass.StopAlarm();
                finish();
            }
        }


    }

    @Override
    public void onClick(View v) {
          switch (v.getId()) {
              case R.id.iv_logout:
                  onItemClick(null,null,6,0);

                  break;
              case R.id.iv_toggle:
                  if(slidingpane.isOpen()) {
                      slidingpane.closePane();
                  }
                  else {
                  slidingpane.openPane();
                  }
                  break;
              default:
                  break;
          }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    /**************************************************************************************************************************/
}

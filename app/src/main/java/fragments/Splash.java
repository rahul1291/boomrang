package fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import Boomerang.R;
import activities.DashboardActivity;
import activities.MainActivity;

/**
 * Created by rahul on 3/4/2015.
 */
public class Splash extends android.app.Fragment {

    View v=null;
    ImageView iv_logo;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        SharedPreferences prefs=activity.getSharedPreferences("Login",0);
        if (!prefs.getString("UserID", "").equalsIgnoreCase("")) {
            getActivity().finish();
            Intent i = new Intent(getActivity(), DashboardActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try{
         v=inflater.inflate(R.layout.fragment_splash,null);
            iv_logo=(ImageView)v.findViewById(R.id.iv_logo);
            iv_logo.postDelayed(new Runnable() {
                @Override
                public void run() {
                    YoYo.with(Techniques.FadeIn).duration(2000).playOn(iv_logo);
                    iv_logo.setVisibility(View.VISIBLE);
                }
            },1000);

            Thread thread=new Thread(){
                @Override
                public void run() {
                    super.run();
                    try{
                         sleep(3000);
                        ((MainActivity)getActivity()).FragmentTransactions(R.id.fragment_place,new Login());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
            };
            thread.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return v;
    }
}

package ge.idevelopers.Lifti.app;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HowTopay extends AppCompatActivity {
    LinearLayout crash_conta_safety;
    LinearLayout about_us_safety;
    LinearLayout partners_safety;
    LinearLayout how_pay_safety;
    LinearLayout lift_safety;
    //MENU
    Animation animation_first;
    Animation animation_two;
    LinearLayout humburger_11;
    LinearLayout humburger_21;
    LinearLayout humburger_31;
    LinearLayout humburger_41;
    Animation test;
    boolean isShow = false;
    Animation fadein;
    Animation rotateback;
    int l;
    Animation rotatetwoback;
    PercentRelativeLayout bgView;

    //MENU
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_topay);
        crash_conta_safety = (LinearLayout) findViewById(R.id.crash_conta_rules);
        about_us_safety = (LinearLayout) findViewById(R.id.about_us_rules);
        lift_safety = (LinearLayout) findViewById(R.id.lift_savefty_rules);
        how_pay_safety= (LinearLayout) findViewById(R.id.how_pay_rules);
        partners_safety= (LinearLayout) findViewById(R.id.partners_rules);

        //MENU
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.RecyclerView3);
        RelativeLayout humburger = (RelativeLayout) findViewById(R.id.humburger_main3);
        final RelativeLayout linearAbout2 = (RelativeLayout) findViewById(R.id.linear3);
        final RelativeLayout mainAbout = (RelativeLayout) findViewById(R.id.DrawerLayout3);
        humburger_11 = (LinearLayout) findViewById(R.id.humburger_13);
        humburger_21 = (LinearLayout) findViewById(R.id.humburger_23);
        humburger_31 = (LinearLayout) findViewById(R.id.humburger_33);
        humburger_41 = (LinearLayout) findViewById(R.id.humburger_43);
        bgView = (PercentRelativeLayout) findViewById(R.id.bgView3);
        test = AnimationUtils.loadAnimation(this, R.anim.test);
        animation_first = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animation_two = AnimationUtils.loadAnimation(this, R.anim.rotatetwo);
        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        rotateback = AnimationUtils.loadAnimation(this, R.anim.rotateback);
        rotatetwoback = AnimationUtils.loadAnimation(this, R.anim.rotatetwoback);




        humburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShow) {
                    bgView.setVisibility(View.VISIBLE);
                    l = mainAbout.getMeasuredWidth();
                    final ObjectAnimator oa_y = ObjectAnimator.ofFloat(linearAbout2, "y", (float) (l * 0.08));
                    final ObjectAnimator oa = ObjectAnimator.ofFloat(linearAbout2, "x", -(float) ((l) * 0.70));
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(oa, oa_y);
                    animatorSet.start();
                    humburger_41.startAnimation(test);
                    humburger_31.startAnimation(test);
                    humburger_11.startAnimation(animation_first);
                    humburger_21.startAnimation(animation_two);
                    isShow=true;

                } else {
                    final ObjectAnimator oa_y = ObjectAnimator.ofFloat(linearAbout2, "y", 0);
                    final ObjectAnimator oa = ObjectAnimator.ofFloat(linearAbout2, "x", 0);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(oa, oa_y);
                    animatorSet.start();
                    humburger_41.startAnimation(fadein);
                    humburger_31.startAnimation(fadein);
                    humburger_11.startAnimation(rotateback);
                    humburger_21.startAnimation(rotatetwoback);
                    isShow=false;
                    bgView.setVisibility(View.GONE);
                }
            }
        });


        mainAbout.setOnTouchListener(new OnSwipeTouchListener() {
            @Override
            public void onSwipeLeft() {
                l = mainAbout.getMeasuredWidth();
                final ObjectAnimator oa_y = ObjectAnimator.ofFloat(linearAbout2, "y", (float) (l * 0.08));
                final ObjectAnimator oa = ObjectAnimator.ofFloat(linearAbout2, "x", -(float) ((l) * 0.70));
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(oa, oa_y);

                if (!isShow) {
                    humburger_41.startAnimation(test);
                    humburger_31.startAnimation(test);
                    humburger_11.startAnimation(animation_first);
                    humburger_21.startAnimation(animation_two);
                    isShow = true;
                    bgView.setVisibility(View.VISIBLE);
                }
                animatorSet.start();
                super.onSwipeLeft();
            }

            @Override
            public void onSwipeRight() {

                final ObjectAnimator oa_y = ObjectAnimator.ofFloat(linearAbout2, "y", 0);
                final ObjectAnimator oa = ObjectAnimator.ofFloat(linearAbout2, "x", 0);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(oa, oa_y);
                if (isShow){
                    humburger_41.startAnimation(fadein);
                    humburger_31.startAnimation(fadein);
                    humburger_11.startAnimation(rotateback);
                    humburger_21.startAnimation(rotatetwoback);
                    isShow = false;
                }
                animatorSet.start();
                bgView.setVisibility(View.GONE);
                super.onSwipeRight();
            }
        });

        crash_conta_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HowTopay.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        about_us_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HowTopay.this,AboutUs.class);
                startActivity(i);
                finish();

            }
        });
        how_pay_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ObjectAnimator oa_y = ObjectAnimator.ofFloat(linearAbout2, "y", 0);
                final ObjectAnimator oa = ObjectAnimator.ofFloat(linearAbout2, "x", 0);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(oa, oa_y);
                if (isShow){
                    humburger_41.startAnimation(fadein);
                    humburger_31.startAnimation(fadein);
                    humburger_11.startAnimation(rotateback);
                    humburger_21.startAnimation(rotatetwoback);
                    isShow = false;
                }
                animatorSet.start();
                bgView.setVisibility(View.GONE);
            }
        });
        partners_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HowTopay.this,Partners.class);
                startActivity(i);
                finish();
            }
        });
        lift_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HowTopay.this,Savefty.class);
                startActivity(i);
                finish();
            }
        });
        //MENU
    }
}

package ge.idevelopers.Lifti.app;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Savefty extends AppCompatActivity {
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
    float mx;
    float my;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savefty);
        crash_conta_safety = (LinearLayout) findViewById(R.id.crash_conta_safety);
        about_us_safety = (LinearLayout) findViewById(R.id.about_us_safety);
        lift_safety = (LinearLayout) findViewById(R.id.lift_savefty_safety);
        how_pay_safety = (LinearLayout) findViewById(R.id.how_pay_safety);
        partners_safety = (LinearLayout) findViewById(R.id.partners_safety);

        //MENU
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.RecyclerView1);
        RelativeLayout humburger = (RelativeLayout) findViewById(R.id.humburger_main1);
        final RelativeLayout safeLayout2 = (RelativeLayout) findViewById(R.id.linear1);
        final RelativeLayout mainLayout2 = (RelativeLayout) findViewById(R.id.DrawerLayout1);
        humburger_11 = (LinearLayout) findViewById(R.id.humburger_11);
        humburger_21 = (LinearLayout) findViewById(R.id.humburger_21);
        humburger_31 = (LinearLayout) findViewById(R.id.humburger_31);
        humburger_41 = (LinearLayout) findViewById(R.id.humburger_41);
        bgView = (PercentRelativeLayout) findViewById(R.id.bgView1);
        test = AnimationUtils.loadAnimation(this, R.anim.test);
        animation_first = AnimationUtils.loadAnimation(Savefty.this, R.anim.rotate);
        animation_two = AnimationUtils.loadAnimation(this, R.anim.rotatetwo);
        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        rotateback = AnimationUtils.loadAnimation(this, R.anim.rotateback);
        rotatetwoback = AnimationUtils.loadAnimation(this, R.anim.rotatetwoback);


        humburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShow) {
                    bgView.setVisibility(View.VISIBLE);
                    l = mainLayout2.getMeasuredWidth();
                    final ObjectAnimator oa_y = ObjectAnimator.ofFloat(safeLayout2, "y", (float) (l * 0.08));
                    final ObjectAnimator oa = ObjectAnimator.ofFloat(safeLayout2, "x", -(float) ((l) * 0.70));
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(oa, oa_y);
                    animatorSet.start();
                    humburger_41.startAnimation(test);
                    humburger_31.startAnimation(test);
                    humburger_11.startAnimation(animation_first);
                    humburger_21.startAnimation(animation_two);
                    isShow = true;

                } else {
                    final ObjectAnimator oa_y = ObjectAnimator.ofFloat(safeLayout2, "y", 0);
                    final ObjectAnimator oa = ObjectAnimator.ofFloat(safeLayout2, "x", 0);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(oa, oa_y);
                    animatorSet.start();
                    humburger_41.startAnimation(fadein);
                    humburger_31.startAnimation(fadein);
                    humburger_11.startAnimation(rotateback);
                    humburger_21.startAnimation(rotatetwoback);
                    isShow = false;
                    bgView.setVisibility(View.GONE);
                }
            }
        });


        mainLayout2.setOnTouchListener(new OnSwipeTouchListener() {
            @Override
            public void onSwipeLeft() {
                l = mainLayout2.getMeasuredWidth();
                final ObjectAnimator oa_y = ObjectAnimator.ofFloat(safeLayout2, "y", (float) (l * 0.08));
                final ObjectAnimator oa = ObjectAnimator.ofFloat(safeLayout2, "x", -(float) ((l) * 0.70));
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

                final ObjectAnimator oa_y = ObjectAnimator.ofFloat(safeLayout2, "y", 0);
                final ObjectAnimator oa = ObjectAnimator.ofFloat(safeLayout2, "x", 0);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(oa, oa_y);
                if (isShow) {
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
                Intent i = new Intent(Savefty.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        about_us_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Savefty.this, AboutUs.class);
                startActivity(i);
                finish();
            }
        });
        how_pay_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Savefty.this, HowTopay.class);
                startActivity(i);
                finish();
            }
        });
        partners_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Savefty.this, Partners.class);
                startActivity(i);
                finish();
            }
        });
        lift_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ObjectAnimator oa_y = ObjectAnimator.ofFloat(safeLayout2, "y", 0);
                final ObjectAnimator oa = ObjectAnimator.ofFloat(safeLayout2, "x", 0);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(oa, oa_y);
                if (isShow) {
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
        //MENU
        //ScrolImage

    }

        //ScrollImageEnd
    }

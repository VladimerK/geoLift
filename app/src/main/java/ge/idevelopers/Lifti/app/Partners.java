package ge.idevelopers.Lifti.app;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Partners extends AppCompatActivity {
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

    private List<PartnerRes> partnerList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PartnerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partners);
        crash_conta_safety = (LinearLayout) findViewById(R.id.crash_conta_partners);
        about_us_safety = (LinearLayout) findViewById(R.id.about_us_partners);
        lift_safety = (LinearLayout) findViewById(R.id.lift_savefty_partners);
        how_pay_safety= (LinearLayout) findViewById(R.id.how_pay_partners);
        partners_safety= (LinearLayout) findViewById(R.id.partners_partners);

        //MENU
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.RecyclerView4);
        RelativeLayout humburger = (RelativeLayout) findViewById(R.id.humburger_main4);
        final RelativeLayout linearAbout2 = (RelativeLayout) findViewById(R.id.linear4);
        final RelativeLayout mainAbout = (RelativeLayout) findViewById(R.id.DrawerLayout4);
        humburger_11 = (LinearLayout) findViewById(R.id.humburger_14);
        humburger_21 = (LinearLayout) findViewById(R.id.humburger_24);
        humburger_31 = (LinearLayout) findViewById(R.id.humburger_34);
        humburger_41 = (LinearLayout) findViewById(R.id.humburger_44);
        bgView = (PercentRelativeLayout) findViewById(R.id.bgView4);
        test = AnimationUtils.loadAnimation(this, R.anim.test);
        animation_first = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animation_two = AnimationUtils.loadAnimation(this, R.anim.rotatetwo);
        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        rotateback = AnimationUtils.loadAnimation(this, R.anim.rotateback);
        rotatetwoback = AnimationUtils.loadAnimation(this, R.anim.rotatetwoback);
        final  View lurjixazi_crash = findViewById(R.id.lurjixazi4_crush);
        final View lurjixazi_pay = findViewById(R.id.lurjixazi4_pay);
        final View lurjixazi_about = findViewById(R.id.lurjixazi4_about);
        final View lurjixazi_safety = findViewById(R.id.lurjixazi4_savefty);
        final View part_blue = findViewById(R.id.part_blue);
        TextView menu_crush = (TextView) findViewById(R.id.menucrush3);
        TextView menu_about = (TextView) findViewById(R.id.menuabout3);
        TextView menu_save = (TextView) findViewById(R.id.menusave3);
        TextView menu_pay = (TextView) findViewById(R.id.menupay3);
        TextView menu_part = (TextView) findViewById(R.id.menupartner3);
        Typeface type = Typeface.createFromAsset(getAssets(),"bpgphonesans.ttf");
        menu_crush.setTypeface(type);
        menu_about.setTypeface(type);
        menu_save.setTypeface(type);
        menu_pay.setTypeface(type);
        menu_part.setTypeface(type);
        linearAbout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isShow) {
                    final ObjectAnimator oa_y = ObjectAnimator.ofFloat(linearAbout2, "y", 0);
                    final ObjectAnimator oa = ObjectAnimator.ofFloat(linearAbout2, "x", 0);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(oa, oa_y);
                    animatorSet.start();
                    humburger_41.startAnimation(fadein);
                    humburger_31.startAnimation(fadein);
                    humburger_11.startAnimation(rotateback);
                    humburger_21.startAnimation(rotatetwoback);
                    isShow = false;
                    linearAbout2.setBackgroundColor(Color.argb(255,23,32,39));
                }
                return false;
            }
        });
        linearLayout.setOnTouchListener(new OnSwipeTouchListener() {
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
            linearAbout2.setBackgroundResource(R.drawable.rounded);
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
            linearAbout2.setBackgroundColor(Color.argb(255,23,32,39));
        }
        animatorSet.start();
        bgView.setVisibility(View.GONE);
        super.onSwipeRight();
    }
});




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
                    linearAbout2.setBackgroundResource(R.drawable.rounded);

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
                    linearAbout2.setBackgroundColor(Color.argb(255,23,32,39));
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
                    linearAbout2.setBackgroundResource(R.drawable.rounded);
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
                    linearAbout2.setBackgroundColor(Color.argb(255,23,32,39));
                }
                animatorSet.start();
                bgView.setVisibility(View.GONE);
                super.onSwipeRight();
            }
        });
        crash_conta_safety.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        crash_conta_safety.setBackgroundColor(Color.argb(130,0,0,0));
                        partners_safety.setBackgroundColor(Color.argb(0,0,0,0));
                        part_blue.setVisibility(View.GONE);
                        lurjixazi_crash.setVisibility(View.VISIBLE);
                        Intent i = new Intent(Partners.this,MainActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.alpagjf,R.anim.facead);
                        finish();

                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        crash_conta_safety.setBackgroundColor(parseInt(null));
                        partners_safety.setBackgroundColor(Color.argb(130,0,0,0));
                        part_blue.setVisibility(View.VISIBLE);
                        lurjixazi_crash.setVisibility(View.GONE);
                        break;
                    }
                }
                return true;
            }
        });

        about_us_safety.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        about_us_safety.setBackgroundColor(Color.argb(130,0,0,0));
                        partners_safety.setBackgroundColor(Color.argb(0,0,0,0));
                        part_blue.setVisibility(View.GONE);
                        lurjixazi_about.setVisibility(View.VISIBLE);
                        Intent i = new Intent(Partners.this,AboutUs.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.alpagjf,R.anim.facead);
                        finish();

                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        about_us_safety.setBackgroundColor(parseInt(null));
                        partners_safety.setBackgroundColor(Color.argb(130,0,0,0));
                        part_blue.setVisibility(View.VISIBLE);
                        lurjixazi_about.setVisibility(View.GONE);
                        break;
                    }
                }
                return true;
            }
        });
        how_pay_safety.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        how_pay_safety.setBackgroundColor(Color.argb(130,0,0,0));
                        partners_safety.setBackgroundColor(Color.argb(0,0,0,0));
                        part_blue.setVisibility(View.GONE);
                        lurjixazi_pay.setVisibility(View.VISIBLE);
                        Intent i = new Intent(Partners.this,HowTopay.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.alpagjf,R.anim.facead);
                        finish();
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        how_pay_safety.setBackgroundColor(parseInt(null));
                        partners_safety.setBackgroundColor(Color.argb(130,0,0,0));
                        part_blue.setVisibility(View.VISIBLE);
                        lurjixazi_pay.setVisibility(View.GONE);
                        break;
                    }
                }
                return true;
            }
        });
        partners_safety.setOnClickListener(new View.OnClickListener() {
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
                    linearAbout2.setBackgroundColor(Color.argb(255,23,32,39));
                }
                animatorSet.start();
                bgView.setVisibility(View.GONE);
            }
        });
        lift_safety.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        lift_safety.setBackgroundColor(Color.argb(130,0,0,0));
                        partners_safety.setBackgroundColor(Color.argb(0,0,0,0));
                        part_blue.setVisibility(View.GONE);
                        lurjixazi_safety.setVisibility(View.VISIBLE);
                        Intent i = new Intent(Partners.this,Savefty.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.alpagjf,R.anim.facead);
                        finish();

                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:{
                        lift_safety.setBackgroundColor(parseInt(null));
                        partners_safety.setBackgroundColor(Color.argb(130,0,0,0));
                        part_blue.setVisibility(View.VISIBLE);
                        lurjixazi_safety.setVisibility(View.GONE);
                        break;
                    }
                }
                return true;
            }
        });
        //MENU

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayout recviewd = (LinearLayout) findViewById(R.id.recviewd);
        ScrollView linear4 = (ScrollView) findViewById(R.id.srolipart);
        linear4.setOnTouchListener(new OnSwipeTouchListener() {
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
                    linearAbout2.setBackgroundResource(R.drawable.rounded);
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
                    linearAbout2.setBackgroundColor(Color.argb(255,23,32,39));
                }
                animatorSet.start();
                bgView.setVisibility(View.GONE);
                super.onSwipeRight();
            }
        });

        recyclerView.setOnTouchListener(new OnSwipeTouchListener() {
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
                    linearAbout2.setBackgroundResource(R.drawable.rounded);
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
                    linearAbout2.setBackgroundColor(Color.argb(255,23,32,39));
                }
                animatorSet.start();
                bgView.setVisibility(View.GONE);
                super.onSwipeRight();
            }
        });
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isShow) {
                    final ObjectAnimator oa_y = ObjectAnimator.ofFloat(linearAbout2, "y", 0);
                    final ObjectAnimator oa = ObjectAnimator.ofFloat(linearAbout2, "x", 0);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(oa, oa_y);
                    animatorSet.start();
                    humburger_41.startAnimation(fadein);
                    humburger_31.startAnimation(fadein);
                    humburger_11.startAnimation(rotateback);
                    humburger_21.startAnimation(rotatetwoback);
                    isShow = false;
                    linearAbout2.setBackgroundColor(Color.argb(255,23,32,39));
                }
                return false;
            }
        });

        mAdapter = new PartnerAdapter(partnerList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {
        /*int imageArray[] = new int[20];
        for (int i = 0; i < 20; i++)
            imageArray[i] = getDrawableId(getApplicationContext(),"R.drawable." + img_[i]);*/
        PartnerRes movie = new PartnerRes("საქართველოს კულტურისა და ძეგლთა დაცვის სამინისტრო",R.drawable.img_1);
        partnerList.add(movie);
        movie = new PartnerRes("IMG PLAZA",R.drawable.img_2);
        partnerList.add(movie);
        movie = new PartnerRes("ლევან სამხარაულის სახელობის სასამართლო ექპერტიზის ეროვნული ბიურო",R.drawable.img_3);
        partnerList.add(movie);
        movie = new PartnerRes("აქსისი",R.drawable.img_4);
        partnerList.add(movie);
        movie = new PartnerRes("BRITISH EMBASSY TBILISI",R.drawable.img_5);
        partnerList.add(movie);
        movie = new PartnerRes("GRATO",R.drawable.img_6);
        partnerList.add(movie);
        movie = new PartnerRes("სამგორი მედი",R.drawable.img_7);
        partnerList.add(movie);
        movie = new PartnerRes("HAUSART",R.drawable.img_8);
        partnerList.add(movie);
        movie = new PartnerRes("ჯი პი აი",R.drawable.img_9);
        partnerList.add(movie);
        movie = new PartnerRes("პროკრედიტ ბანკი",R.drawable.img_10);
        partnerList.add(movie);
        movie = new PartnerRes("ENERGO-PRO GEORGIA",R.drawable.img_11);
        partnerList.add(movie);
        movie = new PartnerRes("საქართველოს იუსტიციის უმაღლესი საბჭო",R.drawable.img_12);
        partnerList.add(movie);
        movie = new PartnerRes("ლიბერთი ბანკი",R.drawable.img_13);
        partnerList.add(movie);
        movie = new PartnerRes("გორგია",R.drawable.img_14);
        partnerList.add(movie);
        movie = new PartnerRes("ჯო ენის სახელობის სამედიცინო ცენტრი",R.drawable.img_15);
        partnerList.add(movie);
        movie = new PartnerRes("EAST-POINT TBILISI",R.drawable.img_16);
        partnerList.add(movie);
        movie = new PartnerRes("თბილისის საკრებულო",R.drawable.img_17);
        partnerList.add(movie);
        movie = new PartnerRes("BRITISH PETROLEUM",R.drawable.img_18);
        partnerList.add(movie);
        movie = new PartnerRes("VERTEX",R.drawable.img_19);
        partnerList.add(movie);
        movie = new PartnerRes("iDEVELOPERS",R.drawable.img_20);
        partnerList.add(movie);
        mAdapter.notifyDataSetChanged();
    }
    public int getDrawableId(Context context, String name){
        return context.getResources().getIdentifier(name,"drawable", context.getPackageName());
    }
}

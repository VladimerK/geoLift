package ge.idevelopers.Lifti.app;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Calendar;

import ge.idevelopers.Lifti.app.connections.RetrofitApi;
import ge.idevelopers.Lifti.app.connections.RetrofitSingleTone;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private ImageView ivCal;
    private EditText etInput;
    private LinearLayout llInput;
    private ImageView ivCal2;
    private EditText etInputNumber;
    private LinearLayout llInputNumber;
    private Button button;
    private Button btn;
    private LinearLayout activity_main;
    private DrawerLayout fRoot;
    private RelativeLayout rightDrawer;
    public ImageView image_2;
    //MENU
    Animation animation_first;
    Animation animation_two;
    LinearLayout humburger_1;
    LinearLayout humburger_2;
    LinearLayout humburger_3;
    LinearLayout humburger_4;
    Animation test;
    boolean isShow = false;
    Animation fadein;
    Animation rotateback;
    int l;
    Animation rotatetwoback;
    PercentRelativeLayout bgView;

    //MENU


    LocationManager locationManager;
    LocationListener locationListener;
    String provider;
    public static final String INPUTNUMBERLIFT = "ნომერი უნდა შედგებოდეს 9 ციფრისგან !";
    public static final String INPUTNUMBER = "ლიფტის ნომერი უნდა შედგებოდეს 3 ციფრისგან !";
    public static final String NO_NUMBER = "შეიყვანეთ ნომერი !";
    public static final String NOINTERNET = "ჩართეთ ინტერნეტი";
    public static final String NOINTERNET_POSSITIVE = "თავიდან სცადე";
    public static final String DIAX = "დიახ";
    public static final String LAST_NUMBER = "last number";

    double latitude;
    double longtitude;
    boolean gps_enabled = false;
    boolean network_enabled = false;
    public byte st_bk = 0;
    static final Integer LOCATION = 0x1;
    static final Integer CALL = 0x2;

    private LinearLayout crash_conta;
    LinearLayout about_us;
    LinearLayout lift_savefty;
    LinearLayout how_pay;
    LinearLayout partners;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide);
        initView();
        loadStepPref();
        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION);
        askForPermission(Manifest.permission.CALL_PHONE, CALL);
        crash_conta.setBackgroundColor(Color.argb(130,0,0,0));
       /* if (etInput.length() == 3){
            hideKeyboard();
        }*/
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2 == 3) {
                    saveStepPref("liftNum", etInput.getText().toString());
                    etInput.clearFocus();
                    etInputNumber.requestFocus();
                    etInputNumber.setCursorVisible(true);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(etInputNumber,InputMethodManager.SHOW_IMPLICIT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etInput.length() == 3) {
                    saveStepPref("liftNum", etInput.getText().toString());
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(etInputNumber,InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });
        etInputNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i2 == 9) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etInputNumber.getWindowToken(), 0);
                    saveStepPref("editStepLength", etInputNumber.getText().toString());
                }
            }


            @Override
            public void afterTextChanged(Editable editable) {
                saveStepPref("editStepLength", etInputNumber.getText().toString());
            }
        });

        //MENU

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.RecyclerView);
        RelativeLayout humburger = (RelativeLayout) findViewById(R.id.humburger_main);
        final RelativeLayout linearLayout2 = (RelativeLayout) findViewById(R.id.linear);
        final RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.DrawerLayout);
        humburger_1 = (LinearLayout) findViewById(R.id.humburger_1);
        humburger_2 = (LinearLayout) findViewById(R.id.humburger_2);
        humburger_3 = (LinearLayout) findViewById(R.id.humburger_3);
        humburger_4 = (LinearLayout) findViewById(R.id.humburger_4);
        bgView = (PercentRelativeLayout) findViewById(R.id.bgView);
        test = AnimationUtils.loadAnimation(this, R.anim.test);
        animation_first = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate);
        animation_two = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotatetwo);
        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        rotateback = AnimationUtils.loadAnimation(this, R.anim.rotateback);
        rotatetwoback = AnimationUtils.loadAnimation(this, R.anim.rotatetwoback);




        humburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShow) {
                    bgView.setVisibility(View.VISIBLE);
                    l = mainLayout.getMeasuredWidth();
                    final ObjectAnimator oa_y = ObjectAnimator.ofFloat(linearLayout2, "y", (float) (l * 0.08));
                    final ObjectAnimator oa = ObjectAnimator.ofFloat(linearLayout2, "x", -(float) ((l) * 0.70));
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(oa, oa_y);
                    animatorSet.start();
                    humburger_4.startAnimation(test);
                    humburger_3.startAnimation(test);
                    humburger_1.startAnimation(animation_first);
                    humburger_2.startAnimation(animation_two);
                    isShow=true;

                } else {
                    final ObjectAnimator oa_y = ObjectAnimator.ofFloat(linearLayout2, "y", 0);
                    final ObjectAnimator oa = ObjectAnimator.ofFloat(linearLayout2, "x", 0);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(oa, oa_y);
                    animatorSet.start();
                    humburger_4.startAnimation(fadein);
                    humburger_3.startAnimation(fadein);
                    humburger_1.startAnimation(rotateback);
                    humburger_2.startAnimation(rotatetwoback);
                    isShow=false;
                    bgView.setVisibility(View.GONE);
                }
            }
        });


        mainLayout.setOnTouchListener(new OnSwipeTouchListener() {
            @Override
            public void onSwipeLeft() {
                l = mainLayout.getMeasuredWidth();
                final ObjectAnimator oa_y = ObjectAnimator.ofFloat(linearLayout2, "y", (float) (l * 0.08));
                final ObjectAnimator oa = ObjectAnimator.ofFloat(linearLayout2, "x", -(float) ((l) * 0.70));
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(oa, oa_y);

                if (!isShow) {
                    humburger_4.startAnimation(test);
                    humburger_3.startAnimation(test);
                    humburger_1.startAnimation(animation_first);
                    humburger_2.startAnimation(animation_two);
                    isShow = true;
                    bgView.setVisibility(View.VISIBLE);
                }
                animatorSet.start();
                super.onSwipeLeft();
            }

            @Override
            public void onSwipeRight() {

                final ObjectAnimator oa_y = ObjectAnimator.ofFloat(linearLayout2, "y", 0);
                final ObjectAnimator oa = ObjectAnimator.ofFloat(linearLayout2, "x", 0);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(oa, oa_y);
                if (isShow){
                    humburger_4.startAnimation(fadein);
                    humburger_3.startAnimation(fadein);
                    humburger_1.startAnimation(rotateback);
                    humburger_2.startAnimation(rotatetwoback);
                    isShow = false;
                }
                animatorSet.start();
                bgView.setVisibility(View.GONE);
                super.onSwipeRight();
            }
        });

          crash_conta.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  final ObjectAnimator oa_y = ObjectAnimator.ofFloat(linearLayout2, "y", 0);
                  final ObjectAnimator oa = ObjectAnimator.ofFloat(linearLayout2, "x", 0);
                  AnimatorSet animatorSet = new AnimatorSet();
                  animatorSet.playTogether(oa, oa_y);
                  if (isShow){
                      humburger_4.startAnimation(fadein);
                      humburger_3.startAnimation(fadein);
                      humburger_1.startAnimation(rotateback);
                      humburger_2.startAnimation(rotatetwoback);
                      isShow = false;
                  }
                  animatorSet.start();
                  bgView.setVisibility(View.GONE);
              }
          });
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AboutUs.class);
                startActivity(i);
            }
        });
        how_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,HowTopay.class);
                startActivity(i);
            }
        });
        partners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Partners.class);
                startActivity(i);
            }
        });
        lift_savefty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Savefty.class);
                startActivity(i);
            }
        });


        //MENU

    }

    private void initView() {
        ivCal = (ImageView) findViewById(R.id.ivCal);
        etInput = (EditText) findViewById(R.id.etInput);
        llInput = (LinearLayout) findViewById(R.id.llInput);
        ivCal2 = (ImageView) findViewById(R.id.ivCal2);
        etInputNumber = (EditText) findViewById(R.id.etInputNumber);
        llInputNumber = (LinearLayout) findViewById(R.id.llInputNumber);
        btn = (Button) findViewById(R.id.btnGamodzaxeba);
        activity_main = (LinearLayout) findViewById(R.id.activity_main);
     //   fRoot = (DrawerLayout) findViewById(R.id.fRoot);
        image_2 = (ImageView) findViewById(R.id.image_2);
   crash_conta= (LinearLayout) findViewById(R.id.crash_conta);
      about_us = (LinearLayout) findViewById(R.id.about_us);
        lift_savefty = (LinearLayout) findViewById(R.id.lift_savefty);
        how_pay= (LinearLayout) findViewById(R.id.how_pay);
       partners= (LinearLayout) findViewById(R.id.partners);
       /* locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            makeUseOfNewLocation(location);
        } else {
            Toast.makeText(this, "Failed to send location", Toast.LENGTH_SHORT).show();
        }

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }*/

      /*  sliderBtn = (SlideButton) findViewById(R.id.sliderBtn);
        sliderBtn.setOnClickListener(this);*/
    }

    private void makeUseOfNewLocation(Location location) {
        latitude = location.getLatitude();
        longtitude = location.getLongitude();

    }

    private void sendSos() {
        if (etInputNumber.getText().toString().equals("")) {
            makeDialog(NO_NUMBER, DIAX);
        } else if (etInputNumber.getText().length() != 9) {
            makeDialog(INPUTNUMBERLIFT, DIAX);
        }
        else {
            SendClas goObject = new SendClas(latitude, longtitude, etInput.getText().toString(), etInputNumber.getText().toString(), st_bk);
            Log.i("l", goObject.toString());
            //=========================================================================
            RetrofitApi api = RetrofitSingleTone.getInstance().getRetrofitApi();
            final Call<SendClas> send = api.send(goObject);
            send.enqueue(new Callback<SendClas>() {
                @Override
                public void onResponse(Call<SendClas> call, Response<SendClas> response) {
                }

                @Override
                public void onFailure(Call<SendClas> call, Throwable t) {
                    SentDialog("მოთხოვნა გაიგზვანა",DIAX);
                }
            });
            //=========================================================================
        }
    }

    public void callOperator() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "557002838"));
        startActivity(intent);
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

    }

    public void checkLocation() {
        final MainActivity context = this;

        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage("თუ გსურთ მოთხოვნი გადაგზავნა ჩართეთ GPS");
            dialog.setPositiveButton("open location settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            AlertDialog alert = dialog.create();

            alert.show();
            Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
            pbutton.setTextColor(Color.parseColor("#007AFF"));
            Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
            nbutton.setTextColor(Color.parseColor("#007AFF"));
        }
    }


    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean checkInternetConnection() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
            return true;
        }
        return false;


    }

    public void makeDialog(String message, String positiveButtonText) {


        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this)
                .setTitle("შეცდომა !")
                .setMessage(message)
                .setIcon(R.drawable.crashcallicon)
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete

                    }
                });
//        builder1.show();

        AlertDialog alert = builder1.create();

        alert.show();
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.parseColor("#007AFF"));

    }

    public void SentDialog(String message, String positiveButtonText) {


        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
        .setTitle("წარმატება !")
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete

                    }
                });
//        builder1.show();

        AlertDialog alert = builder1.create();

        alert.show();
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.parseColor("#007AFF"));

    }

    public void sosClick(View view) {

        hideKeyboard();

        if (!checkInternetConnection()) {
            makeDialog(NOINTERNET, NOINTERNET_POSSITIVE);
            return;
        }
        if (etInput.getText().toString().equals("")) {
            makeDialog(NO_NUMBER, DIAX);
        } else if (etInput.getText().length() != 9) {
            makeDialog(INPUTNUMBER, DIAX);
        } else {
            // LiftiApplication.getInstance().setSharedString(LAST_NUMBER, etInput.getText().toString());

            //=========================================================================
            if (!checkInternetConnection()) {
                makeDialog(NOINTERNET, NOINTERNET_POSSITIVE);
                return;
            } else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("ნამდვილად გსურთ ოპერატორი ამ ნომერზე " + etInput.getText().toString() + " დაგიკავშირდეთ ?");
                dialog.setPositiveButton("კი", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        sendSos();
                    }
                });
                dialog.setNegativeButton("არა", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
                AlertDialog alert = dialog.create();

                alert.show();
                Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                pbutton.setTextColor(Color.parseColor("#007AFF"));
                Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                nbutton.setTextColor(Color.parseColor("#007AFF"));
                //=========================================================================
            }
        }

        //=========================================================================
    }

    public void checkSettings() {
        //=========================================================================
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED) {


            //=========================================================================
            if (checkInternetConnection()) {
                initLocationManager();
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                        != PackageManager.PERMISSION_GRANTED) {

                    checkLocation();
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            78);
                }
            } else {
                makeDialog(NOINTERNET, NOINTERNET_POSSITIVE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CHANGE_NETWORK_STATE},
                    77);
        }
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        } else {
           // Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }


    public void gamodzaxeba(View v) {
        if (image_2.getVisibility() == View.VISIBLE) {
            st_bk = 0;
            Calendar currentTime = Calendar.getInstance();
            Calendar schoolTime = Calendar.getInstance();
            Calendar schoolClosedTime = Calendar.getInstance();
            Calendar closed = Calendar.getInstance();
            Calendar open = Calendar.getInstance();
            schoolTime.set(Calendar.HOUR_OF_DAY, 10);
            schoolTime.set(Calendar.MINUTE, 0);
            schoolTime.set(Calendar.SECOND, 0);
            schoolTime.set(Calendar.MILLISECOND, 0);
            schoolClosedTime.set(Calendar.HOUR_OF_DAY, 19);
            schoolClosedTime.set(Calendar.MINUTE, 0);
            schoolClosedTime.set(Calendar.SECOND, 0);
            schoolClosedTime.set(Calendar.MILLISECOND, 0);
            schoolClosedTime.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
            schoolClosedTime.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
            open.set(Calendar.HOUR_OF_DAY, 10);
            open.set(Calendar.MINUTE, 0);
            open.set(Calendar.SECOND, 0);
            open.set(Calendar.MILLISECOND, 0);
            closed.set(Calendar.HOUR_OF_DAY, 19);
            closed.set(Calendar.MINUTE, 0);
            closed.set(Calendar.SECOND, 0);
            closed.set(Calendar.MILLISECOND, 0);
            if (currentTime.after(schoolTime) && currentTime.before(schoolClosedTime) && currentTime.after(open) && currentTime.before(closed)) {
                if (!checkInternetConnection()) {

                    if (etInput.getText().toString().equals("")) {
                        sendSos();
                    } else if (etInput.getText().toString().length() != 3) {
                        makeDialog(INPUTNUMBER, DIAX);
                    } else {
                        sendSos();
                    }

                }
            }
            else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setMessage("გსურთ ოპერატორთან დარეკვა?");
                dialog.setPositiveButton("დიახ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        callOperator();
                    }
                });
                dialog.setNegativeButton("არა", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
                AlertDialog alert = dialog.create();

                alert.show();
                Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                pbutton.setTextColor(Color.parseColor("#007AFF"));
                Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                nbutton.setTextColor(Color.parseColor("#007AFF"));
            }


        } else if (image_2.getVisibility() == View.INVISIBLE) {
            st_bk = 1;
            Calendar currentTime = Calendar.getInstance();
            Calendar schoolTime = Calendar.getInstance();
            Calendar schoolClosedTime = Calendar.getInstance();
            Calendar closed = Calendar.getInstance();
            Calendar open = Calendar.getInstance();
            schoolTime.set(Calendar.HOUR_OF_DAY, 10);
            schoolTime.set(Calendar.MINUTE, 0);
            schoolTime.set(Calendar.SECOND, 0);
            schoolTime.set(Calendar.MILLISECOND, 0);
            schoolClosedTime.set(Calendar.HOUR_OF_DAY, 19);
            schoolClosedTime.set(Calendar.MINUTE, 0);
            schoolClosedTime.set(Calendar.SECOND, 0);
            schoolClosedTime.set(Calendar.MILLISECOND, 0);
            schoolClosedTime.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
            schoolClosedTime.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
            open.set(Calendar.HOUR_OF_DAY, 10);
            open.set(Calendar.MINUTE, 0);
            open.set(Calendar.SECOND, 0);
            open.set(Calendar.MILLISECOND, 0);
            closed.set(Calendar.HOUR_OF_DAY, 19);
            closed.set(Calendar.MINUTE, 0);
            closed.set(Calendar.SECOND, 0);
            closed.set(Calendar.MILLISECOND, 0);
            if (currentTime.after(schoolTime) && currentTime.before(schoolClosedTime) && currentTime.after(open) && currentTime.before(closed)) {
                if (!checkInternetConnection()) {

                    if (etInput.getText().toString().equals("")) {
                        sendSos();
                    } else if (etInput.getText().toString().length() != 3) {
                        makeDialog(INPUTNUMBER, DIAX);
                    } else {
                        sendSos();
                    }
                }
            }

            else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setMessage("გსურთ ოპერატორთან დარეკვა?");
                dialog.setPositiveButton("დიახ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        callOperator();
                    }
                });
                dialog.setNegativeButton("არა", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
                AlertDialog alert = dialog.create();

                alert.show();
                Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                pbutton.setTextColor(Color.parseColor("#007AFF"));
                Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                nbutton.setTextColor(Color.parseColor("#007AFF"));
            }

        }
    }

    public void initLocationManager() {

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        //makeUseOfNewLocation(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    private void loadStepPref() {
        EditText inputStepLength = (EditText) findViewById(R.id.etInputNumber);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String value = sp.getString("editStepLength", null);
        inputStepLength.setText(value);
        EditText liftNum = (EditText) findViewById(R.id.etInput);
        String numLift = sp.getString("liftNum", null);
        liftNum.setText(numLift);
    }

    private void saveStepPref(String key, String value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.apply();
        edit.commit();
    }

}

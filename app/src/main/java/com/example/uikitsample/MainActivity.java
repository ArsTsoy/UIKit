package com.example.uikitsample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kotlin.bottom_sheet.ChocoBottomSheet;
import com.kotlin.dialog.ChocoDialogInterface;
import com.kotlin.dialog.advanced_dialog.ChocoAdvancedDialog;
import com.kotlin.dialog.simple_dialog.ChocoSimpleDialog;
import com.kotlin.in_app_notification.InAppNotification;
import com.kotlin.in_app_notification.OnNotificationClickListener;

public class MainActivity extends AppCompatActivity {
    Button payment_button;
    Button payment_button2;
    Button action_button;

    private final String imageURL = "https://img.s3.chocolife.me/static/upload/images/deal/for_wide_plate/38525.jpg?1549341629";
    private final String imageURL2 = "https://cdn.pixabay.com/photo/2017/08/30/01/05/milky-way-2695569_960_720.jpg";
    private final String imageURL3 = "https://img.s3.chocolife.me/static/upload/images/deal/for_wide_plate/38525.jpg?1549341629";
    private final String imageURL4 = "https://img.s3.chocolife.me/static/upload/images/deal/for_wide_plate/45237.jpg?1549434914";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        payment_button = findViewById(R.id.payment_button);
        payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showEditDialog();
                showNotification(v);
            }
        });
        payment_button2 = findViewById(R.id.payment_button2);
        payment_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDialog();
            }
        });

        action_button = findViewById(R.id.action_button);
        action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });
    }

    private void showNotification(final View v) {
        InAppNotification snack = InAppNotification.Companion.make(v, "Оплата прошла успешно", "Спасибо за пользованием нашим приложением", R.color.main_green, R.drawable.image2);
        snack.setIndefinite(true);
        snack.setOnNotificationClickListener(new OnNotificationClickListener() {
            @Override
            public void onNotificationClick() {
                Toast.makeText(v.getContext(), "Click", Toast.LENGTH_SHORT).show();
            }
        });
        snack.show();
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();

        ChocoAdvancedDialog editNameDialogFragment = new ChocoAdvancedDialog.Builder()
                .setText("SТекст какойк нибуль" )
                .setTitle("ыфпрвмфоивылтфыдавфывфа")
                .setLeftButtonText("фролатфдьабжфыафыа", new ChocoDialogInterface.OnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(getApplicationContext(), "Left Click", Toast.LENGTH_SHORT).show();
                        Log.i("mListener", "click");
                    }
                })
                .setRightButtonText("Action tcyvub", new ChocoDialogInterface.OnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(getApplicationContext(), "васрприолфдтьафыафыафыафыа", Toast.LENGTH_SHORT).show();
                    }
                })
                .setURLImage(imageURL)
                .create();


        editNameDialogFragment.show(fm, "fragment_edit_name");
    }


    private void showBottomSheet(){
        ChocoBottomSheet bottomSheetDialog = ChocoBottomSheet.Companion.newInstance("someTitle2", "someText2fyahsjf\nsdasfasfasfaf\nsdfasfafsasfafasf\nsafsfasfasf\nsfasfafasf");
        bottomSheetDialog.show(getSupportFragmentManager(), "Custom Bottom Sheet");
    }

    private void showSimpleDialog(){
        FragmentManager fm = getSupportFragmentManager();
        ChocoSimpleDialog editNameDialogFragment = new ChocoSimpleDialog.Builder()
                .setText("SТекст какойк нибуль" )
                .setTitle("ыфпрвмфоивылтфыдавфывфа")
                .setLeftButtonText("фролатфдьабжфыафыа", new ChocoDialogInterface.OnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(getApplicationContext(), "Left Click", Toast.LENGTH_SHORT).show();
                        Log.i("mListener", "click");
                    }
                })
                .setRightButtonText("Action 2", new ChocoDialogInterface.OnClickListener() {
                    @Override
                    public void onClick() {
                        Toast.makeText(getApplicationContext(), "васрприолфдтьафыафыафыафыа", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

//    public void showNotification(View view) {
//        InAppNotification inAppNotification = InAppNotification.make(view,"Интернет отсутствует", "Тут тост что все хорошо", R.drawable.image2, R.color.main_green);
//        inAppNotification.setIndefinite(true);
//        inAppNotification.show();
//    }

}

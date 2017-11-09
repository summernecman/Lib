package com.siweisoft.service.ui.user.login;

//by summer on 17-08-24.

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.ToastUtil;
import com.siweisoft.service.R;

public class LoginAct extends BaseUIActivity<BaseUIOpe, BaseDAOpe> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        String[] permissions = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.VIBRATE,
                Manifest.permission.INTERNET,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.WAKE_LOCK,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.RECEIVE_BOOT_COMPLETED,
                Manifest.permission.DISABLE_KEYGUARD
        };


        if (checkPermissionAllGranted(permissions)) {
            dothing();
            return;
        }

        ActivityCompat.requestPermissions(this, permissions, 0);

    }

    private void dothing() {
        FragmentUtil2.getInstance().addNoAnim(activity, R.id.act_base_root, new LoginFrag());
//        FragmentUtil2.getInstance().add(activity, R.id.act_base_root,new WelcomeFrag());
    }

    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String s : permissions) {
            if (ContextCompat.checkSelfPermission(this, s) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            boolean isallgranted = true;
            for (int grnat : grantResults) {
                if (grnat != PackageManager.PERMISSION_GRANTED) {
                    isallgranted = false;
                    break;
                }
            }

            if (isallgranted) {
                dothing();
            } else {
                ToastUtil.getInstance().showLong(this, "请到应用权限管理中找到此应用并手动打开权限");
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
                this.finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        if (FragmentUtil2.getInstance().getFragMap().get(R.id.act_base_root) == null) {
            finish();
        } else if (FragmentUtil2.getInstance().getFragMap().get(R.id.act_base_root).size() == 1) {
            FragmentUtil2.getInstance().clear();
            this.finish();
        } else {
            FragmentUtil2.getInstance().removeTopRightNow(activity, R.id.act_base_root);
        }
    }

}

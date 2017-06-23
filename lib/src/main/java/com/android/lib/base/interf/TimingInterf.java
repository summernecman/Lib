package com.android.lib.base.interf;

/**
 * Created by ${viwmox} on 2016-06-17.
 */
public interface TimingInterf {

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {

                    Thread.sleep(1 * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    void Timing();
}

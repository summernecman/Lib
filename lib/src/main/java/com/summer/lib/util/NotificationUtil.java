package com.summer.lib.util;


/**
 * Created by ${viwmox} on 2016-06-02.
 */
public class NotificationUtil {
    private static NotificationUtil instance;

    public static final String NOTIFICATION_APP = "NOTIFICATION_APP";

    private NotificationUtil() {

    }

    public static NotificationUtil getInstance() {
        if (instance == null) {
            instance = new NotificationUtil();
        }
        return instance;
    }
//
//    public void showJPushNotification(FragmentActivity context) {
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_enter);
//        builder.setContent(remoteViews);
//        LibAplication appAplication = (LibAplication) context.getApplicationContext();
//        Intent appIntent = new Intent(context, AppReceiver.class);
//        appIntent.putExtra(ValueConstant.DATA_DATA,NOTIFICATION_APP);
//
//
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);
//        builder.setAutoCancel(false);
//        builder.setSmallIcon(R.drawable.ic_launcher);
//        builder.setOngoing(true);
//        Notification notification = builder.build();
//        notificationManager.notify(11, notification);
//
//
//    }
}

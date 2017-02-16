package com.example.paul.tab_abd_list;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;


public class AppCheck extends Service {

    private final int UPDATE_INTERVAL = 100;
    private Timer timer = new Timer();
    public static boolean ChatHead = false;
    public static ArrayList<String> Packages = new ArrayList<>();
    public ArrayList<String> From_Dates = new ArrayList<>();
    public ArrayList<String> To_Dates = new ArrayList<>();

    public AppCheck() {

    }

    private final Handler toastHandler = new Handler() {


        @Override
        public void handleMessage(final Message msg) {
            final Bundle Data = msg.getData();

            //Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
            //call a intent


            try {

                Intent intent = new Intent(AppCheck.this, Overlap.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //intent.putExtras(Data);
                startActivity(intent);
            } catch (Exception e) {
                Log.e("KUET_CSE_AppLock", "Calling Error");
            }
        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {
        Toast.makeText(this, "Started!", Toast.LENGTH_LONG);
        try {
             ArrayList<String> Selected_Apps = intent.getStringArrayListExtra("Packages");
            String From_Date = intent.getStringExtra("From_Date");
            String To_Date = intent.getStringExtra("To_Date");
            int i;
            for (i = 0; i < Selected_Apps.size(); i++) {
                Packages.add(Selected_Apps.get(i));
                From_Dates.add(From_Date);
                To_Dates.add(To_Date);
            }
/*
            if (Packages != null)
            {

                int k;
                int j;
                for (k = 0; k < Selected_Apps.size(); k++)
                {
                    boolean foundit = false;
                    for (j = 0; j < Packages.size(); j++)
                    {
                        if (Packages.get(j).equals(Selected_Apps.get(k)))
                        {
                            foundit = true;
                        }
                    }

                    if (foundit == false)
                    {
                        Packages.add(Selected_Apps.get(k));
                        From_Dates.add(From_Date);
                        To_Dates.add(To_Date);
                    }
                }

            }
            else {
                int k;
                Packages = new ArrayList<>();
                From_Dates = new ArrayList<>();
                To_Dates = new ArrayList<>();
                for (k = 0; k < Selected_Apps.size(); k++)
                {

                        Packages.add(Selected_Apps.get(k));
                        From_Dates.add(From_Date);
                        To_Dates.add(To_Date);

                }
            }
            */

            //Save packages
            /*
            String ser = SerializeObject.objectToString(Packages);
            if (ser != null && !ser.equalsIgnoreCase("")) {
                SerializeObject.WriteSettings(getBaseContext(), ser, "myobject.dat");
            } else {
                SerializeObject.WriteSettings(getBaseContext(), "", "myobject.dat");
            }
            //Save from times
            ser = SerializeObject.objectToString(From_Dates);
            if (ser != null && !ser.equalsIgnoreCase("")) {
                SerializeObject.WriteSettings(getBaseContext(), ser, "myobject1.dat");
            } else {
                SerializeObject.WriteSettings(getBaseContext(), "", "myobject1.dat");
            }
            //Save to times
            ser = SerializeObject.objectToString(To_Dates);
            if (ser != null && !ser.equalsIgnoreCase("")) {
                SerializeObject.WriteSettings(getBaseContext(), ser, "myobject2.dat");
            } else {
                SerializeObject.WriteSettings(getBaseContext(), "", "myobject2.dat");
            }
            */

        }
        catch(Exception e)
        {
            Log.e("KUET_CSE_AppLock", "On Start Error");
        }
        timer.scheduleAtFixedRate(new MyTimerTask(intent, this) {

            @Override
            public void run() {


                // String Current_time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                //int current_time = Integer.parseInt(Current_time);

                //  String start = I.getStringExtra("From_Date");
                //String end = I.getStringExtra("To_Date")


                Date currentTime = new Date();
                //String current = Date_Utility.Date_To_String(currentTime);

                //String From_Date = I.getStringExtra("From_Date");
                //String To_Date = I.getStringExtra("To_Date");

                //Date from = Date_Utility.String_To_Date(From_Date);
                //Date to = Date_Utility.String_To_Date(To_Date);


                //if (current_time >= start_time && current_time <= end_time) {
                //if (currentTime.getHours() > from.getHours() && currentTime.getHours() < to.getHours()) {

                    Context C = getApplicationContext();
                    ActivityManager activityManager = (ActivityManager) C.getSystemService(Context.ACTIVITY_SERVICE);
                    List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
                    if (appProcesses == null) {

                    }

                    //ArrayList<String> Packages = new ArrayList<String>();


                    // final String packageName;

                    boolean Running = false;
                    int k;
/*
                    try{

                        if (Packages == null)
                        {
                            String ser = SerializeObject.ReadSettings(getBaseContext(), "myobject.dat");
                            if (ser != null && !ser.equalsIgnoreCase("")) {
                                Object obj = SerializeObject.stringToObject(ser);
                                // Then cast it to your object and
                                if (obj instanceof ArrayList) {
                                    // Do something
                                    Packages = (ArrayList<String>)obj;
                                    Log.d("KUET_CSE", Packages.get(0));
                                }
                            }
                        }
                        if (From_Dates == null)
                        {
                            String ser = SerializeObject.ReadSettings(getBaseContext(), "myobject1.dat");
                            if (ser != null && !ser.equalsIgnoreCase("")) {
                                Object obj = SerializeObject.stringToObject(ser);
                                // Then cast it to your object and
                                if (obj instanceof ArrayList) {
                                    // Do something
                                    From_Dates = (ArrayList<String>)obj;
                                    Log.d("KUET_CSE", From_Dates.get(0));
                                }
                            }
                        }
                        if (To_Dates == null)
                        {
                            String ser = SerializeObject.ReadSettings(getBaseContext(), "myobject2.dat");
                            if (ser != null && !ser.equalsIgnoreCase("")) {
                                Object obj = SerializeObject.stringToObject(ser);
                                // Then cast it to your object and
                                if (obj instanceof ArrayList) {
                                    // Do something
                                    To_Dates = (ArrayList<String>)obj;
                                    Log.d("KUET_CSE", To_Dates.get(0));
                                }
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        Log.e("KUET_CSE_AppLock", "Loading Error");
                    }
*/
                    try {


                        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                            Log.d("ProcessRunning", appProcess.processName);
                            for (k = 0; k < Packages.size(); k++) {


                                Date F_T = Date_Utility.String_To_Date(From_Dates.get(k));
                                Date T_T = Date_Utility.String_To_Date(To_Dates.get(k));

                                boolean LockIt = false;
                                if (currentTime.getHours() > F_T.getHours() && currentTime.getHours() < T_T.getHours())
                                {
                                    LockIt = true;
                                }
                                else if (F_T.getHours() == currentTime.getHours() && T_T.getHours() == currentTime.getHours())
                                {
                                    if (currentTime.getMinutes() >= F_T.getMinutes() && currentTime.getMinutes() <= T_T.getMinutes()) {
                                        LockIt = true;
                                    }
                                }

                                if (LockIt == true) {
                                    if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(Packages.get(k))) {


                                        if (ChatHead == false) {
                                            ChatHead = true;

                                            Message msg = new Message();
                                            Bundle b = new Bundle();
                                            b.putString("package", appProcess.processName);
                                            msg.setData(b);

                                            toastHandler.sendMessage(msg);
                                        }
                                        break;


                                    }
                                }
                                //}
                            }

                        }

                    } catch (Exception e) {
                        Log.e("KUET_CSE_AppLock", "Lock Error : ");
                    }
                    //}
                /*} else if ((currentTime.getHours() == from.getHours() && currentTime.getHours() == to.getHours())) {
                    if (currentTime.getMinutes() >= from.getMinutes() && currentTime.getMinutes() <= to.getMinutes()) {
                        Context C = getApplicationContext();
                        ActivityManager activityManager = (ActivityManager) C.getSystemService(Context.ACTIVITY_SERVICE);
                        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
                        if (appProcesses == null) {

                        }

                        //ArrayList<String> Packages = new ArrayList<String>();


                        // final String packageName;

                        boolean Running = false;
                        int k;


                        try {


                            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                                Log.d("ProcessRunning", appProcess.processName);
                                for (k = 0; k < Packages.size(); k++) {


                                    if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(Packages.get(k))) {
                                        AppCheck App_S = (AppCheck) S;


                                        if (ChatHead == false) {
                                            ChatHead = true;

                                            Message msg = new Message();
                                            Bundle b = new Bundle();
                                            b.putString("package", appProcess.processName);
                                            msg.setData(b);

                                            toastHandler.sendMessage(msg);
                                        }
                                        break;


                                    }
                                    //}
                                }

                            }

                        } catch (Exception e) {

                        }
                    }
                }*/


                //   Log.d("Service", "Running");
            }

        }, 0, UPDATE_INTERVAL);
        return START_STICKY;
    }






  public void onDestroy() {
      super.onDestroy();
      if (timer != null) {
          timer.cancel();
      }
  }


    private void stopService() {
        if (timer != null) timer.cancel();
    }
 @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

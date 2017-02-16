package com.example.paul.tab_abd_list;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sumaiya Mashfi on 12/8/2015.
 */
public class AppAdapter extends ArrayAdapter<ApplicationInfo> {

    private List<ApplicationInfo> applist = null;
    private Context context;
    private PackageManager packageManager;

    public AppAdapter(Context context, int resource, List<ApplicationInfo> objects) {
        super(context, resource, objects);

        this.context = context;
        this.applist = objects;
        packageManager = context.getPackageManager();
    }

    public int getCount() {
        return ((null != applist)? applist.size() : 0);
    }

    public ApplicationInfo getItem(int position) {
        return ((null != applist) ? applist.get(position) : null);
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position , View convertView, ViewGroup parent) {
        View view = convertView;

        if(null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.list_item,null);
        }

        ApplicationInfo data = applist.get(position);

        if(null != data) {
            TextView appName = (TextView) view.findViewById(R.id.app_name);
            TextView packageName = (TextView) view.findViewById(R.id.app_package);
            ImageView iconView = (ImageView) view.findViewById(R.id.app_icon);

            appName.setText(data.loadLabel(packageManager));
            packageName.setText(data.packageName);
            iconView.setImageDrawable(data.loadIcon(packageManager));
        }
        return view;
    }
}

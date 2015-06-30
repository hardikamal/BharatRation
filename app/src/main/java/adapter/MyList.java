package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.ashinetech.bharatration.R;

import java.util.ArrayList;

import model.Heading;

/**
 * Created by Vignesh on 30-06-2015.
 */
public class MyList extends ArrayAdapter<Heading>
{
    ArrayList<Heading> heading;
    int layoutResourceId;
    Context context;
    public MyList(Context context, int layoutResourceId,ArrayList<Heading> heading)
    {
        super(context,layoutResourceId, heading);
        this.layoutResourceId = layoutResourceId;
        this.heading = heading;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        if(convertView==null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        Heading h1= heading.get(position);
        TextView textViewItem = (TextView) convertView.findViewById(R.id.textViewItem);

        textViewItem.setText(h1.getTitle());
        return convertView;
    }
}

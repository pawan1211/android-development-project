package com.example.my_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class PDF_adapter extends ArrayAdapter<File> {
    Context context;
  ViewHolder viewHolder;
ArrayList<File> al_pdf;
public  class ViewHolder
{
    TextView tv_filename;
}
public PDF_adapter( Context context,ArrayList<File> al_pdf)
{

    super(context,R.layout.adapter_pdf,al_pdf);
    this.context=context;
    this.al_pdf=al_pdf;
}

public int getItemViewType(int position)
{
    return position;
}
    public int getViewTypeCount()
    {
        if(al_pdf.size()>0)
        {
            return al_pdf.size();
        }
        else
        {
            return 1;
        }
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if(convertView==null)
        {
         convertView= LayoutInflater.from(getContext()).inflate(R.layout.adapter_pdf,parent,false);
            ViewHolder viewHolder= new ViewHolder();
            viewHolder.tv_filename=(TextView)convertView.findViewById(R.id.tv_image);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.tv_filename.setText(al_pdf.get(position).getName());
        return convertView;
    }

}



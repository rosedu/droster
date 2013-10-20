package com.example.cityguide;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private ArrayList<String> dates;
	private ArrayList<String> cities;
	private ArrayList<String> guides;
	private Activity context;
	
	public MyAdapter(ArrayList<String> _cities, ArrayList<String> _dates, ArrayList<String> _guides, Activity _context) {
		cities = _cities;
		dates = _dates;
		guides = _guides;
		context=_context;
	}

	@Override
	public int getCount() {
		return cities.size();
	}

	@Override
	public Object getItem(int arg0) {
		return dates.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View element;
		 
		if (convertView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			element= (View)inflater.inflate(R.layout.list_view_item, null);
		
			MyTag tag = new MyTag();
			tag.textView1 =(TextView)element.findViewById(R.id.textView);
			tag.textView2 =(TextView)element.findViewById(R.id.textView2);
			tag.textView3 =(TextView)element.findViewById(R.id.textView3);
			element.setTag(tag);
		}
		else element = convertView;
			MyTag tag = (MyTag)element.getTag();	
		
			tag.textView1.setText(cities.get(position));
			tag.textView2.setText(dates.get(position));
			tag.textView3.setText(guides.get(position));
		
		return element;
	}

}

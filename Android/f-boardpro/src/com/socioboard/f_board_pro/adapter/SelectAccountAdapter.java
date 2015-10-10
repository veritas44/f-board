package com.socioboard.f_board_pro.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.socioboard.f_board_pro.R;
import com.socioboard.f_board_pro.database.util.ModelUserDatas;
import com.socioboard.f_board_pro.imagelib.ImageLoader;

public class SelectAccountAdapter extends BaseAdapter implements
		CompoundButton.OnCheckedChangeListener {
	Handler handler = new Handler();
	private Context context;
	public SparseBooleanArray sparseBooleanArray;
	public TextView counterText;
	public int count ;
	private ArrayList<ModelUserDatas> navDrawerItems;
	ImageLoader imageloader;

	public SelectAccountAdapter(int count, ArrayList<ModelUserDatas> navDrawerItems,Context context,TextView counterText, SparseBooleanArray sparseBooleanArray) {

		this.count = count ;
		
		this.context = context ;

		this.navDrawerItems = navDrawerItems;
		
		this.counterText = counterText;
		imageloader      = new ImageLoader(this.context);
		this.sparseBooleanArray = sparseBooleanArray;
 
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			
			LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convertView = mInflater.inflate(R.layout.usercheck, parent, false);
		}

		ImageView profilePic = (ImageView) convertView.findViewById(R.id.profile_pic);

		TextView text = (TextView) convertView.findViewById(R.id.user_name);
		
		RelativeLayout test_Rlt = (RelativeLayout) convertView.findViewById(R.id.test_Rlt);
		
		imageloader.DisplayImage(navDrawerItems.get(position).getUserimage(), profilePic);
		
		text.setText(navDrawerItems.get(position).getUsername());

		final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox1);
		
		
		test_Rlt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(checkBox.isChecked())
				{
					checkBox.setChecked(false);
				}else
				{
					checkBox.setChecked(true);
				}

			}
		});

		checkBox.setTag(position);
		checkBox.setOnCheckedChangeListener(this);
		checkBox.setChecked(sparseBooleanArray.get(position));
		

		return convertView;
	}

	public void myprint(Object msg) {

		System.out.println(msg.toString());

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		sparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
		
		if (isChecked) {
			count++;
		} else {
			count--;
		}
		
	 	counterText.setText("Selected : "+count);
	}
	 
}

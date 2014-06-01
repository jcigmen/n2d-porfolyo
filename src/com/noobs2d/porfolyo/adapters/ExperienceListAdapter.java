package com.noobs2d.porfolyo.adapters;

import java.util.ArrayList;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.noobs2d.porfolyo.R;
import com.noobs2d.porfolyo.models.Experience;

public class ExperienceListAdapter extends BaseAdapter {

    /** Tag used for the inflated view on {@link ExperienceListAdapter#getView(int, View, ViewGroup)}.
     * Basically just contains all the components on the layout adapter_experience.xml */
    private class ExperienceTag {

	public TextView company;
	public TextView duration;
	public TextView title;
    }

    private ArrayList<Experience> experiences;
    private LayoutInflater inflater;

    public ExperienceListAdapter(LayoutInflater inflater, ArrayList<Experience> experiences) {
	this.inflater = inflater;
	this.experiences = experiences;
    }

    @Override
    public int getCount() {
	return experiences.size();
    }

    @Override
    public Experience getItem(int position) {
	return experiences.get(position);
    }

    @Override
    public long getItemId(int position) {
	return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	ExperienceTag tag = null;
	if (convertView == null) {
	    convertView = inflater.inflate(R.layout.adapter_experience, parent, false);
	    tag = new ExperienceTag();
	    tag.title = (TextView) convertView.findViewById(R.id.experienceTitle);
	    tag.company = (TextView) convertView.findViewById(R.id.experienceCompany);
	    tag.duration = (TextView) convertView.findViewById(R.id.experienceDuration);
	    convertView.setTag(tag);
	} else
	    tag = (ExperienceTag) convertView.getTag();

	// put the value of the current item into the tag
	Experience experience = getItem(position);
	tag.title.setText(experience.title);
	tag.company.setText(experience.company);
	tag.duration.setText(experience.duration);

	// setup the background to be either bluish or white
	if (position % 2 == 0)
	    convertView.setBackgroundColor(0x3329a4eb);
	else
	    convertView.setBackgroundColor(Color.WHITE);

	return convertView;
    }

}

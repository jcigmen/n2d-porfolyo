package com.noobs2d.porfolyo.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.noobs2d.porfolyo.R;
import com.noobs2d.porfolyo.adapters.ExperienceListAdapter;
import com.noobs2d.porfolyo.models.Experience;

public class ExperienceSectionFragment extends ListFragment {

    private ArrayList<Experience> experiences;

    public ExperienceSectionFragment() {
	experiences = new ArrayList<Experience>();
    }

    public Experience extractExperienceFromUnformattedString(String unformattedString) {
	// each string MUST be split into three indexes
	final int TITLE = 0, COMPANY = 1, DURATION = 2;

	String[] bundle = unformattedString.split("\\|", -1); // -1 so it doesn't have a limit
	String title = bundle[TITLE];
	String company = bundle[COMPANY];
	String duration = bundle[DURATION];
	Experience experience = new Experience(company, duration, title);

	return experience;
    }

    public ArrayList<Experience> getExperiences() {
	return experiences;
    }

    public boolean isExperienceValid(Experience experience) {
	boolean valid = !experience.title.equalsIgnoreCase("");
	valid &= experience.title.length() >= 5;
	valid &= !experience.company.equalsIgnoreCase("");
	valid &= experience.company.length() >= 5;
	valid &= !experience.duration.equalsIgnoreCase("");
	valid &= experience.duration.length() >= 5;
	return valid;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);

	// so the list doesn't get multiplied even if the instance of this fragment
	// is retained in a variable in the activity
	if (getListAdapter() == null) {
	    retrieveExperiences();
	    setListAdapter(new ExperienceListAdapter(getLayoutInflater(savedInstanceState), experiences));

	    removeListDividers();
	}
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
	// TODO set the list adapter
	super.onListItemClick(l, v, position, id);
    }

    /** Turns the String array of experience data from {@link R.array.experiences} into an {@link Experience} 
     * instance then adds them into {@link ExperienceSectionFragment#experiences}. */
    public void retrieveExperiences() throws ArrayIndexOutOfBoundsException, StringIndexOutOfBoundsException {
	String[] experiencesArray = getResources().getStringArray(R.array.experiences1);
	// each item in the array is split by a |
	// each item is an item in an Experience instance
	for (int i = 0; i < experiencesArray.length; i++) {
	    Experience experience = extractExperienceFromUnformattedString(experiencesArray[i]);
	    if (isExperienceValid(experience))
		experiences.add(experience);
	}
    }

    private void removeListDividers() {
	getListView().setDivider(null);
	getListView().setDividerHeight(0);
    }

}

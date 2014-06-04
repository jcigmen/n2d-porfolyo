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

    protected ArrayList<Experience> experiences;

    public ExperienceSectionFragment() {
	experiences = new ArrayList<Experience>();
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
    public void onListItemClick(ListView listView, View view, int position, long id) {
	super.onListItemClick(listView, view, position, id);
	// TODO add a DialogFragment upon clicking an item
    }

    private void removeListDividers() {
	getListView().setDivider(null);
	getListView().setDividerHeight(0);
    }

    /** Turns the String array of experience data from {@link R.array.experiences} into an {@link Experience} 
     * instance then adds them into {@link ExperienceSectionFragment#experiences}. */
    protected void retrieveExperiences() throws ArrayIndexOutOfBoundsException, StringIndexOutOfBoundsException {
	String[] experiencesArray = getResources().getStringArray(R.array.experiences);
	// each item in the array is split by a |
	// each item is an item in an Experience instance
	for (int i = 0; i < experiencesArray.length; i++) {
	    Experience experience = new Experience(experiencesArray[i]);
	    if (isExperienceValid(experience))
		experiences.add(experience);
	}
    }

}

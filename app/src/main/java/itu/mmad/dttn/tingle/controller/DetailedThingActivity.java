package itu.mmad.dttn.tingle.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

import itu.mmad.dttn.tingle.controller.Fragments.SimpleListFragment_Landscape;
import itu.mmad.dttn.tingle.controller.Fragments.ThingFragment;

/**
 * Activity to display detailed things and list
 */
public class DetailedThingActivity extends BaseActivity
{

    public static final String EXTRA_THING_ID = "itu.mmad.dttn.tingle.thing_id";

    public static Intent newIntent(Context packageContext, UUID thingId)
    {
        Intent intent = new Intent(packageContext, DetailedThingActivity.class);
        intent.putExtra(EXTRA_THING_ID, thingId);
        return intent;
    }

    @Override
    protected Fragment createPortraitFragment()
    {
        return createThingFragment();
    }

    @Override
    protected Fragment createLeftFragment()
    {
        return createThingFragment();
    }

    @Override
    protected Fragment createRightFragment()
    {
        return new SimpleListFragment_Landscape();
    }


    private ThingFragment createThingFragment()
    {
        UUID thingId = (UUID) getIntent().getSerializableExtra(EXTRA_THING_ID);
        return ThingFragment.newInstance(thingId);
    }

    /**
     * Updates view when new item is selected
     */
    public void updateSelectedItemView(UUID id)
    {
        replaceLeftFragmentLandscape(ThingFragment.newInstance(id));
    }


}

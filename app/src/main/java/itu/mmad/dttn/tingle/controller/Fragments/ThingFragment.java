package itu.mmad.dttn.tingle.controller.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import itu.mmad.dttn.tingle.R;
import itu.mmad.dttn.tingle.TingleApplication;
import itu.mmad.dttn.tingle.controller.BaseActivity;
import itu.mmad.dttn.tingle.model.TempThingToStore;
import itu.mmad.dttn.tingle.model.Thing;
import itu.mmad.dttn.tingle.model.database.ThingsDatabase;
import itu.mmad.dttn.tingle.model.utils.PictureUtils;

/**
 * Represents a detailed view of thing
 */
public class ThingFragment extends Fragment {

    private static final String ARG_THING_ID = "thing_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 1;
    private static final int REQUEST_PHOTO = 2;

    private Thing mThing;
    private TempThingToStore mTempThing;
    private File mPhotoFile;

    private EditText mWhatField;
    private EditText mWhereField;
    private EditText mDescriptionField;
    private Button mDateButton;
    private Button mBarcodeButton;
    private ImageView mPhotoView;
    private ImageButton mPhotoButton;


    public static ThingFragment newInstance(UUID thingId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_THING_ID, thingId);
        ThingFragment fragment = new ThingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID thingId = (UUID) getArguments().getSerializable(ARG_THING_ID);
        ThingsDatabase database = ((BaseActivity) getActivity()).getDatabase();
        mThing = database.get(thingId);
        mTempThing = new TempThingToStore();
        mPhotoFile = ((BaseActivity) getActivity()).getDatabase().getPhotoFile(mThing);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thing, parent, false);
        setTextFields(v);
        setButtons(v);
        setPhotoView(v);
        return v;

    }

    private void setTextFields(View v) {
        mWhatField = (EditText) v.findViewById(R.id.what_text);
        mWhatField.setText(mThing.getWhat());
        mWhatField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTempThing.setWhat(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mWhereField = (EditText) v.findViewById(R.id.where_text);
        mWhereField.setText(mThing.getWhere());
        mWhereField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTempThing.setWhere(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mDescriptionField = (EditText) v.findViewById(R.id.description_EditBox);
        mDescriptionField.setText(mThing.getDescription());
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTempThing.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case REQUEST_DATE:
                handleDatepickerResult(data);
                break;
            case Activity.RESULT_CANCELED:
                makeToast(R.string.scan_fail);
                break;
            case REQUEST_PHOTO:
                updatePhotoView();
                break;
            case Activity.RESULT_OK:
                makeToast(R.string.ok);

            default:
                makeToast(R.string.wrong_resultCode);

        }


    }


    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());

            mPhotoView.setImageBitmap(bitmap);
        }
    }


    private void handleDatepickerResult(Intent data) {
        Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
        mThing.setDate(date);
        updateDate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back_button:
                goBack();
                return true;
            case R.id.save_button:
                saveChanges();
                return true;
            case R.id.share_button:
                shareThing();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareThing() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, getShareableThingText());
        i = Intent.createChooser(i, getString(R.string.share_thing));
        startActivity(i);
    }

    private void saveChanges() {
        if (mTempThing.isHasChanged()) {

            //Override items
            if (mTempThing.getWhat() != null) mThing.setWhat(mTempThing.getWhat());

            if (mTempThing.getWhere() != null) mThing.setWhere(mTempThing.getWhere());

            if (mTempThing.getDate() != null) mThing.setDate(mTempThing.getDate());

            if (mTempThing.getDescription() != null)
                mThing.setDescription(mTempThing.getDescription());

            if (mTempThing.getBarcode() != null) mThing.setBarcode(mTempThing.getBarcode());


            //save changes
            boolean isSaved = ((BaseActivity) getActivity()).getDatabase().update(mThing);

            //Validate
            if (isSaved) {
                makeToast(R.string.saved);
            } else makeToast(R.string.something_Went_Wrong);
        } else makeToast(R.string.no_changes);


    }

    private void makeToast(int s) {
        Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    private void goBack() {
        getActivity().finish();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_thing, menu);
    }

    private void setButtons(View v) {
        mDateButton = (Button) v.findViewById(R.id.thing_date_button);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mThing.getDate());
                dialog.setTargetFragment(ThingFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });


        mBarcodeButton = (Button) v.findViewById(R.id.barcode_button);
        mBarcodeButton.setEnabled(false);

        mPhotoButton = (ImageButton) v.findViewById(R.id.camera_button);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean isCanTake = isCanTakePicture(captureImage);
        mPhotoButton.setEnabled(isCanTake);
        if (isCanTake) {
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

    }


    private boolean isCanTakePicture(Intent captureImage) {
        PackageManager packageManager = TingleApplication.getApplication(getActivity()).getPackageManager();
        return mPhotoFile != null && captureImage.resolveActivity(packageManager) != null;
    }

    private void setPhotoView(View v) {
        mPhotoView = (ImageView) v.findViewById(R.id.thing_photo);
        updatePhotoView();
    }

    private void updateDate() {
        mDateButton.setText(mThing.getDate().toString());
    }

    private String getShareableThingText() {

        if (mThing.getDescription() == null) {
            return getString(R.string.sharereport_without_description, mThing.getWhat(), mThing.getWhere(), mThing.getDate().toString());
        } else
            return getString(R.string.sharereport_with_description, mThing.getWhat(), mThing.getWhere(), mThing.getDate().toString(), mThing.getDescription());
    }


}

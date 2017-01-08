package de.andreasschrade.androidtemplate.ui.quote;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import de.andreasschrade.androidtemplate.R;
import de.andreasschrade.androidtemplate.dummy.DummyContent;
import de.andreasschrade.androidtemplate.ui.base.BaseActivity;
import de.andreasschrade.androidtemplate.ui.base.BaseFragment;

/**
 * Shows the quote detail page.
 *
 * Created by Andreas Schrade on 14.12.2015.
 */
public class ArticleDetailFragment extends BaseFragment {

    /**
     * The argument represents the dummy item ID of this fragment.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content of this fragment.
     */
    private DummyContent.DummyItem dummyItem;

    @Bind(R.id.quote)
    TextView quote;

    @Bind(R.id.author)
    TextView author;

    @Bind(R.id.completed_status)
    TextView completed_status;

    @Bind(R.id.actual_completed_status)
    TextView actual_completed_status;


    @Bind(R.id.reward)
    TextView reward;

    @Bind(R.id.reward_value)
    TextView reward_value;


    @Bind(R.id.backdrop)
    ImageView backdropImg;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // load dummy item by using the passed item ID.
            dummyItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
        String s = this.getActivity().getIntent().getStringExtra("edit");
        if(s != null  && s.equals("true")) {
            FloatingActionButton f = (FloatingActionButton) this.getActivity().findViewById(R.id.appbar);
            f.setVisibility(View.INVISIBLE);
        }

//        } else if(s != null  && s.equals("false")){
//            FloatingActionButton f = (FloatingActionButton)this.getActivity().findViewById(R.id.fab1);
//            f.setVisibility(View.INVISIBLE);
//        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_article_detail);

        if (!((BaseActivity) getActivity()).providesActivityToolbar()) {
            // No Toolbar present. Set include_toolbar:
            ((BaseActivity) getActivity()).setToolbar((Toolbar) rootView.findViewById(R.id.toolbar));
        }

        if (dummyItem != null) {
            loadBackdrop();
//            LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
//            String provider = locationManager.getBestProvider(new Criteria(), true);
//            if ( ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
//
////                ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
////                        locationManager. );
//            }
//            Location locations = locationManager.getLastKnownLocation(provider);
//            List<String> providerList = locationManager.getAllProviders();
//            if(null!=locations && null!=providerList && providerList.size()>0){
//                double longitude = locations.getLongitude();
//                double latitude = locations.getLatitude();
//                Geocoder geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
//                try {
//                    List<Address> listAddresses = geocoder.getFromLocation(latitude, longitude, 1);
//                    if(null!=listAddresses&&listAddresses.size()>0){
//                        String location = listAddresses.get(0).getAddressLine(0);
//                        Log.d("Issue",location);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
            collapsingToolbar.setTitle(dummyItem.title);
            author.setText(dummyItem.author);
            quote.setText(dummyItem.content);
            completed_status.setText("Completed Status");
            actual_completed_status.setText(dummyItem.completed_status);
            reward.setText("Bounty");
            reward_value.setText(dummyItem.reward_value);
        }

        return rootView;
    }

    private void loadBackdrop() {
        Glide.with(this).load(dummyItem.photoId).centerCrop().into(backdropImg);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.sample_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // your logic
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static ArticleDetailFragment newInstance(String itemID) {
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        Bundle args = new Bundle();
        args.putString(ArticleDetailFragment.ARG_ITEM_ID, itemID);
        fragment.setArguments(args);
        return fragment;
    }

    public ArticleDetailFragment() {}
}

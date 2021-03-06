package com.huhx0015.screencolortester.presentation.presenters.implementations;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import com.huhx0015.screencolortester.constants.ColorConstants;
import com.huhx0015.screencolortester.domain.repositories.implementations.ColorRepositoryImpl;
import com.huhx0015.screencolortester.presentation.presenters.ColorPresenter;
import com.huhx0015.screencolortester.presentation.ui.activities.FullColorActivity;
import com.huhx0015.screencolortester.presentation.ui.view.ColorView;
import java.util.ArrayList;

/**
 * Created by Michael Yoon Huh on 5/18/2017.
 */

public class ColorPresenterImpl implements ColorPresenter {

    /** CLASS VARIABLES ________________________________________________________________________ **/

    // PRESENTER VARIABLES:
    private ColorView mView;
    private ColorRepositoryImpl mRepository;

    /** CONSTRUCTOR METHODS ____________________________________________________________________ **/

    public ColorPresenterImpl(ColorView view) {
        this.mView = view;
        this.mRepository = new ColorRepositoryImpl();
    }

    /** PRESENTER METHODS ______________________________________________________________________ **/

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void stop() {}

    @Override
    public void destroy() {
        mRepository.clearColorList();
        mRepository = null;
    }

    @Override
    public void onError(String message) {}

    @Override
    public void initView() {
        mView.showView();
    }

    @Override
    public ColorRepositoryImpl getRepository() {
        return mRepository;
    }

    @Override
    public void onColorListLoaded() {
        if (mRepository.getAllColors() == null) {
            mRepository.initColorList();
        }
        mView.showColorList();
    }

    @Override
    public void testButtonClicked(Context context) {
        Intent fullColorIntent = new Intent(context, FullColorActivity.class);
        fullColorIntent.putParcelableArrayListExtra(ColorConstants.BUNDLE_COLOR_LIST,
                new ArrayList<Parcelable>(mRepository.getAllColors()));
        fullColorIntent.putExtra(ColorConstants.BUNDLE_TEST_MODE, true);
        context.startActivity(fullColorIntent);
    }
}

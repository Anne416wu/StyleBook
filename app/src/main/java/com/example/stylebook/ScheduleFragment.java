package com.example.stylebook;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.stylebook.db.Match;
import com.example.stylebook.db.MatchAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {
    private float mDensity;
    private int mHiddenViewMeasuredHeight;
    private ImageView miv;
    private LinearLayout onvisibleclick;
    private MatchAdapter todayAdapter,moreAdapter;
    private Match[] today = {
            new Match("match1", R.drawable.match_default1),
            new Match("match2", R.drawable.match_default2),
            };
    private Match[] more = {
            new Match("match1", R.drawable.match1),
            new Match("match2", R.drawable.match2),
            new Match("match3", R.drawable.match3),
            new Match("match4", R.drawable.match4),
            new Match("match5", R.drawable.match5),
            new Match("match6", R.drawable.match6),};
    private List<Match> todaylist = new ArrayList<>();
    private List<Match> morelist = new ArrayList<>();
    private void initCloth(Match[] today,List<Match> todaylist){
        todaylist.clear();
        for (int i=0;i<today.length;i++){
            todaylist.add(today[i]);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.schedule_fragment,container,false);
        initCloth(today,todaylist);
        RecyclerView listView = view.findViewById(R.id.display_list);
        GridLayoutManager layoutManager1 = new GridLayoutManager(getContext(),2);
        listView.setLayoutManager(layoutManager1);
        todayAdapter= new MatchAdapter(todaylist);
        listView.setAdapter(todayAdapter);

        initCloth(more,morelist);
        final RecyclerView mHiddenLayout = (RecyclerView) view.findViewById(R.id.match_hidden);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        mHiddenLayout.setLayoutManager(layoutManager);
        moreAdapter = new MatchAdapter(morelist);
        mHiddenLayout.setAdapter(moreAdapter);
        mHiddenLayout.setVisibility(mHiddenLayout.GONE);
        miv = (ImageView) view.findViewById(R.id.expand_activities_button);
        mDensity = getResources().getDisplayMetrics().density;
        mHiddenViewMeasuredHeight = (int) (mDensity*240+0.5);
        onvisibleclick = (LinearLayout)view.findViewById(R.id.onclickvisible);
        onvisibleclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHiddenLayout.getVisibility() == View.GONE) {
                    animateOpen(mHiddenLayout);
                    animationIvOpen();
                } else {
                    animateClose(mHiddenLayout);
                    animationIvClose();
                }
            }
        });
        return view;
    }
    private void animateOpen(View v) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0,
                mHiddenViewMeasuredHeight);
        animator.start();

    }

    private void animationIvOpen() {
        RotateAnimation animation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        miv.startAnimation(animation);
    }

    private void animationIvClose() {
        RotateAnimation animation = new RotateAnimation(180, 0,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animation.setFillAfter(true);
        animation.setDuration(100);
        miv.startAnimation(animation);
    }

    private void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
}

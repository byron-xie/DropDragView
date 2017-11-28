package com.yiibai.dragndropdemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.*;

/**
 * 易教教程 - 专注于IT教程和实例
 *
 * @link http://www.yiibai.com/android/android_drag_and_drop.html
 */

public class MainActivity extends Activity {
    ImageView ima;
    private static final String IMAGEVIEW_TAG = "Android Logo";
    String msg = "aaaaaaaaaaaaaaa";
    private ViewGroup mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // init ...
        ima = (ImageView)findViewById(R.id.iv_logo);
        // Sets the tag
        ima.setTag(IMAGEVIEW_TAG);
        mRootView = (ViewGroup)findViewById(R.id.ft_layout);

        ima.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());

                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);

                // Instantiates the drag shadow builder.
                View.DragShadowBuilder myShadow = new DragShadowBuilder(ima);

                // Starts the drag
//                mRootView.startDragAndDrop(
//                v.startDragAndDrop(dragData,  // the data to be dragged
//                        myShadow,  // the drag shadow builder
//                        null,      // no need to use local data
//                        0 );
                v.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );
                return true;
            }
        });

        mRootView.setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction())
                {
                    case DragEvent.ACTION_DRAG_STARTED:

                        Log.d(msg, "mRootView is DragEvent.ACTION_DRAG_STARTED");
                        // Do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(msg, "mRootView is DragEvent.ACTION_DRAG_ENTERED");
                        int x_cord = (int) event.getX();
                        mRootView.setBackgroundColor(Color.GRAY);
                        int y_cord = (int) event.getY();
                        break;
                    case DragEvent.ACTION_DRAG_EXITED :
                        Log.d(msg, "mRootView is DragEvent.ACTION_DRAG_EXITED");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        Toast.makeText(MainActivity.this,"取消删除",Toast.LENGTH_SHORT).show();
                        mRootView.setBackgroundColor(Color.GRAY);
                        break;

                    case DragEvent.ACTION_DRAG_LOCATION  :
                        Log.d(msg, "mRootView is DragEvent.ACTION_DRAG_LOCATION");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        mRootView.setBackgroundColor(Color.RED);
                        return false;
                    case DragEvent.ACTION_DRAG_ENDED   :
                        Log.d(msg, "mRootView is DragEvent.ACTION_DRAG_ENDED");
                        // Do nothing
                        return false;
                    case DragEvent.ACTION_DROP:
                        Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                        mRootView.setBackgroundColor(Color.GRAY);
                        Log.d(msg, " mRootView ACTION_DROP event");
                        // Do nothing
                        break;
                    default: break;
                }
                return true;
            }
        });
        // Create and set the drag event listener for the View
        ima.setOnDragListener( new OnDragListener(){
            @Override
            public boolean onDrag(View v,  DragEvent event){
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                switch(event.getAction())
                {
                    case DragEvent.ACTION_DRAG_STARTED:

                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");
                        // Do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        break;
                    case DragEvent.ACTION_DRAG_EXITED :
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        v.setLayoutParams(layoutParams);
                        break;

                    case DragEvent.ACTION_DRAG_LOCATION  :
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED   :
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
                        // Do nothing
                        break;
                    case DragEvent.ACTION_DROP:
                        Log.d(msg, "ACTION_DROP event");
                        // Do nothing
                        break;
                    default: break;
                }
                return true;
            }
        });
    }
}

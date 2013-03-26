package org.ocsim.test;

import org.oscim.core.GeoPoint;
import org.oscim.overlay.Overlay;
import org.oscim.view.MapView;

import android.util.Log;
import android.view.MotionEvent;

public class EventsOverlay extends Overlay {
	private final static String TAG = EventsOverlay.class.getName();

	public EventsOverlay(MapView mapView) {
		super(mapView);
	}
	@Override
	public boolean onDown(final MotionEvent e) {
		/* translate touch point to GeoPoint */
		GeoPoint p = mMapView.getMapViewPosition().fromScreenPixels(e.getX(), e.getY());
		Log.d(TAG, "touched " + p.toString());
		return false;
	}

//	@Override
//	public void onUpdate(MapPosition mapPosition, boolean changedPos){
//		GeoPoint p = mMapView.getCenter();
//		Log.d(TAG, "map position " + p.toString());
//	}

//	@Override
//	public boolean onTouchEvent(MotionEvent e) {
//		Log.d(TAG, e.toString());
//		return false;
//	}
}

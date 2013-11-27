package org.ocsim.test;

import org.oscim.core.MapPosition;
import org.oscim.layers.tile.vector.MapTileLayer;
import org.oscim.theme.InternalRenderTheme;
import org.oscim.tilesource.TileSource;
import org.oscim.tilesource.oscimap4.OSciMap4TileSource;
import org.oscim.view.MapView;

import android.os.Bundle;
import android.view.Menu;

public class MapActivity extends org.oscim.android.MapActivity {

	private MapView mMap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		mMap = (MapView) findViewById(R.id.mapView);
		mMap.setClickable(true);
		mMap.setFocusable(true);

		TileSource tileSource = new OSciMap4TileSource();
		tileSource.setOption("url", "http://city.informatik.uni-bremen.de/tiles/vtm");

		MapTileLayer l = mMapView.setBaseMap(tileSource);

		l.setRenderTheme(InternalRenderTheme.DEFAULT);
		//l.setRenderTheme(InternalRenderTheme.TRONRENDER);

		//mMap.setBackgroundMap(new BitmapTileLayer(mMap, MapQuestAerial.INSTANCE));

		//mMapView.getLayerManager().add(new GenericOverlay(mMapView, new GridRenderLayer(mMapView)));

		MapPosition p = new MapPosition();
		p.setZoomLevel(14);
		p.setPosition(53.08, 8.83);
		mMapView.setMapPosition(p);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_map, menu);
		return true;
	}
}

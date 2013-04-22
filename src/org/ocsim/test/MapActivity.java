package org.ocsim.test;

import org.oscim.database.MapDatabases;
import org.oscim.database.MapOptions;
import org.oscim.layers.tile.TileLayer;
import org.oscim.theme.InternalRenderTheme;
import org.oscim.view.MapView;

import android.os.Bundle;
import android.view.Menu;

public class MapActivity extends org.oscim.view.MapActivity {

	private MapView mMap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		mMap = (MapView) findViewById(R.id.mapView);

		// Tile overlay
		TileLayer over = new TileLayer(mMap);
		over.setMapDatabase(new MapOptions(MapDatabases.TEST_READER));
		over.setRenderTheme(InternalRenderTheme.TRONRENDER);
		mMap.getOverlayManager().add(over);

		// Base Layer
		MapOptions options = new MapOptions(MapDatabases.PBMAP_READER);
		options.put("url", "http://city.informatik.uni-bremen.de:80/osmstache/test/");
		// options.put("url",
		// "http://city.informatik.uni-bremen.de:80/osci/map-live/");

		TileLayer l = mMap.setBaseMap(options);

		// for now other TileLayer need to be added before the baseLayer
		// as the last loaded RenderTheme provides the background color
		l.setRenderTheme(InternalRenderTheme.DEFAULT);

		// mMap.getOverlayManager().add(new GenericOverlay(mMap,new
		// GridOverlay(mMap)));

		mMap.setClickable(true);
		mMap.setFocusable(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_map, menu);
		return true;
	}
}

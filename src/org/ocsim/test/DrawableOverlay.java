package org.ocsim.test;

import org.oscim.core.MapPosition;
import org.oscim.overlay.ItemizedOverlay;
import org.oscim.overlay.Overlay;
import org.oscim.overlay.OverlayItem.HotspotPlace;
import org.oscim.renderer.GLRenderer.Matrices;
import org.oscim.renderer.layer.SymbolItem;
import org.oscim.renderer.layer.SymbolLayer;
import org.oscim.renderer.overlays.BasicOverlay;
import org.oscim.view.MapView;

public class DrawableOverlay extends Overlay {

	class DrawableLayer extends BasicOverlay {

		private boolean initialized;

		public DrawableLayer(MapView mapView) {
			super(mapView);

			SymbolItem it = new SymbolItem();

			// draw item at center
			it.billboard = false;
			it.x = 0;
			it.y = 0;
			it.drawable = mapView.getContext().getResources().getDrawable(R.drawable.ic_launcher);


			ItemizedOverlay.boundToHotspot(it.drawable, HotspotPlace.CENTER);

			SymbolLayer l = new SymbolLayer();
			l.addSymbol(it);

			layers.textureLayers = l;
		}

		@Override
		public void update(MapPosition curPos, boolean positionChanged,
				boolean tilesChanged, Matrices matrices) {

			// keep position constant (or update layer relative to new position)
			//mMapPosition.copy(curPos);

			if (!initialized) {
				// fix at initial position, (see RenderLayer.setMatrix, how it is used)
				mMapPosition.copy(curPos);

				//updateMapPosition();

				initialized= true;

				// render symbol to texture and create  vertex data
				((SymbolLayer)layers.textureLayers).prepare();

				// compile VBOs uploaded and drawn (i.e. flag to call 'BasicOverlay.compile()')
				newData = true;
			}

			// this should allow to draw the item at any lat/lon
			// (set center to be at lat/lon):
			//mMapPosition.setFromLatLon(53.1, 8.8, mMapPosition.zoomLevel);
		}
	}

	public DrawableOverlay(MapView mapView) {
		super(mapView);

		mLayer = new DrawableLayer(mapView);

	}

}

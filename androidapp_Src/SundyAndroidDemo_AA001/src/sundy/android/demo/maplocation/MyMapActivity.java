package sundy.android.demo.maplocation;

import sundy.android.demo.R;
import sundy.android.demo.configration.CommonConstants;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;



public class MyMapActivity extends MapActivity {

	private MapController mapController  ;
	private GeoPoint geoPoint ;
	private MapView mMapView ;
	private LocationManager locationManager ;
	private Location curLocation ;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		setContentView(R.layout.layout_mymap)  ;
		
		/**
		 * ��λ��ز���
		 * 
		 * 
		 * 
		 */
		
		//�õ�locationmanager
		locationManager = (LocationManager)getSystemService(LOCATION_SERVICE)  ;
		
		//�趨�ƶ����� , removeLocationUpdatedsƥ��
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, new LocationListener() {
			
			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location arg0) {
				// TODO Auto-generated method stub
				if(arg0 != null)
					updateMapWithLocation(arg0.getLatitude(), arg0.getLongitude()) ;
			}
		}) ;
		
		
		
		
		/**
		 * ��ͼ��ز���
		 * 
		 * 
		 * 
		 */
		
		//����mapView
		mMapView = (MapView)findViewById(R.id.mapview1)  ;
		
		
		mMapView.setStreetView(false) ;
		mMapView.setSatellite(false) ;
		mMapView.setTraffic(true) ;
		mMapView.setBuiltInZoomControls(true) ;
		mMapView.setEnabled(true) ;
		mMapView.setClickable(true) ;
		mMapView.displayZoomControls(true) ;
		
		
		
		mapController = mMapView.getController()  ;
		
		
		//�õ���ǰλ��
		//�õ���ǰ��λ
		curLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)  ;
		try {
			if(curLocation != null)
				updateMapWithLocation(curLocation.getLatitude(), curLocation.getLongitude())  ;
			else
			//�õ��ɶ��츮�㳡λ��
			updateMapWithLocation(30.659259, 104.065762)  ;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		mMapView.getOverlays().add(new homeOverlay())  ;
	}
	
	/**���µ�ͼ��ʾ�����ݾ�γ��
	 * @param lat ����
	 * @param lng ά��
	 */
	private void updateMapWithLocation(Double lat,Double lng)
	{
		Double latDouble = lat*1E6  ;
		Double lngDouble = lng*1E6 ;
		geoPoint = new GeoPoint(latDouble.intValue(), lngDouble.intValue()) ;
		mapController.animateTo(geoPoint) ;
		mapController.setZoom(mMapView.getMaxZoomLevel()-1) ;
		mapController.setCenter(geoPoint) ;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Log.i(CommonConstants.LOGCAT_TAG_NAME, "�˵������") ;
		switch(item.getItemId())
		{
		case 1:
			Log.i(CommonConstants.LOGCAT_TAG_NAME, "��ͨģʽ") ;
			mMapView.setTraffic(true) ;
			mMapView.setSatellite(false) ;
			mMapView.setStreetView(false) ;
			break ;
		case 2:
			Log.i(CommonConstants.LOGCAT_TAG_NAME, "����ģʽ") ;
			mMapView.setSatellite(true) ;
			mMapView.setTraffic(false) ;
			mMapView.setStreetView(false) ;
			break ;
		case 3:
			Log.i(CommonConstants.LOGCAT_TAG_NAME, "�־�ģʽ") ;
			mMapView.setStreetView(true) ;
			mMapView.setSatellite(false) ;
			mMapView.setTraffic(false) ;
			break ;
		default:
			mMapView.setTraffic(true) ;
			break ;
		}
		return true;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 0, "��ͨģʽ")  ;
		menu.add(0, 2, 1, "��ͼģʽ")  ;
		menu.add(0, 3, 2, "�־�ģʽ")  ;
		return super.onCreateOptionsMenu(menu);
	}


	class homeOverlay extends Overlay
	{

		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {
			// TODO Auto-generated method stub
			super.draw(canvas, mapView, shadow, when);
			Paint paint = new Paint() ;
			Point outPoint = new Point();
			mapView.getProjection().toPixels(geoPoint, outPoint) ;
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.mapoverlay)  ;
			canvas.drawBitmap(bmp, outPoint.x, outPoint.y, paint)  ;
			return true ;
			
		}
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return true;
	}

}


package com.reactlibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;
import com.indooratlas.android.sdk.IARegion;

public class RNIndoorManagerModule extends ReactContextBaseJavaModule {

  private IALocationManager locationManager;

  public RNIndoorManagerModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  private void sendEvent(ReactContext reactContext,
                         String eventName,
                         @Nullable WritableMap params) {
    reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(eventName, params);
  }

  @ReactMethod
  public void initService() {
    getCurrentActivity().runOnUiThread(new Runnable() {
      @Override
      public void run() {
        locationManager = IALocationManager.create(getReactApplicationContext());
        locationManager.registerRegionListener(new IARegion.Listener() {
          @Override
          public void onEnterRegion(IARegion iaRegion) {
            String id = iaRegion.getId();
            WritableMap params = Arguments.createMap();
            params.putString("id", id);
            sendEvent(getReactApplicationContext(), "enterRegion", params);
          }

          @Override
          public void onExitRegion(IARegion iaRegion) {
            String id = iaRegion.getId();
            WritableMap params = Arguments.createMap();
            params.putString("id", id);
            sendEvent(getReactApplicationContext(), "exitRegion", params);
          }
        });
        locationManager.requestLocationUpdates(IALocationRequest.create(), new IALocationListener() {
          @Override
          public void onLocationChanged(IALocation location) {
            WritableMap params = Arguments.createMap();
            params.putDouble("lat", location.getLatitude());
            params.putDouble("lng", location.getLongitude());
            params.putString("atlasId", location.getRegion().getId());
            sendEvent(getReactApplicationContext(), "locationChanged", params);
          }

          @Override
          public void onStatusChanged(String s, int i, Bundle bundle) {

          }
        });
      }
    });

  }

  @Override
  public String getName() {
    return "RNIndoorManager";
  }
}
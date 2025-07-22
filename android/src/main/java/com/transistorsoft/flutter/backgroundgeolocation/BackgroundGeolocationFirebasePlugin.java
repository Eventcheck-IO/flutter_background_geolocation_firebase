package com.transistorsoft.flutter.backgroundgeolocation;

import android.content.Context;
import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;

/**
 * Updated for Android V2 embedding.
 */
public class BackgroundGeolocationFirebasePlugin
    implements FlutterPlugin, ActivityAware {

  private Context applicationContext;
  private BinaryMessenger messenger;

  // Called when the plugin is attached to the Flutter engine.
  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
    this.applicationContext = binding.getApplicationContext();
    this.messenger = binding.getBinaryMessenger();
    BackgroundGeolocationFirebaseModule.getInstance()
        .onAttachedToEngine(applicationContext, messenger);
  }

  // Called when the plugin is detached from the Flutter engine.
  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    BackgroundGeolocationFirebaseModule.getInstance()
        .onDetachedFromEngine();
    this.messenger = null;
    this.applicationContext = null;
  }

  // ActivityAware callbacks for when an Activity is available:
  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    BackgroundGeolocationFirebaseModule.getInstance()
        .setActivity(binding.getActivity());
  }

  @Override
  public void onReattachedToActivityForConfigChanges(
      @NonNull ActivityPluginBinding binding) {
    BackgroundGeolocationFirebaseModule.getInstance()
        .setActivity(binding.getActivity());
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
    // No-op
  }

  @Override
  public void onDetachedFromActivity() {
    BackgroundGeolocationFirebaseModule.getInstance()
        .setActivity(null);
  }
}

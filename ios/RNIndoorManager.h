
#import <React/RCTBridgeModule.h>

#import <React/RCTEventEmitter.h>

@import IndoorAtlas;

#ifndef RNIndoorManager_h
#define RNIndoorManager_h
#endif /* RNIndoorManager_h */

@interface RNIndoorManager: RCTEventEmitter <RCTBridgeModule, IALocationManagerDelegate>
@property (nonatomic) IALocationManager *locationManager;
@end



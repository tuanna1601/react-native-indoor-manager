
#import "RNIndoorManager.h"

@implementation RNIndoorManager
RCT_EXPORT_MODULE();

- (NSArray<NSString *> *)supportedEvents
{
    return @[@"locationChanged"];
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_METHOD(initService: (NSString *)apiKey apiSecret:(NSString *)apiSecret) {
    self.locationManager = [IALocationManager sharedInstance];
    self.locationManager.delegate = self;
    [self.locationManager setApiKey:apiKey andSecret:apiSecret];
    [self.locationManager startUpdatingLocation];
}

- (void)indoorLocationManager:(IALocationManager *)manager didUpdateLocations:(NSArray *)locations
{
    (void) manager;
    CLLocation *l = [(IALocation *)locations.lastObject location];
    IARegion *f = [(IALocation *)locations.lastObject region];
    if ([f type] == kIARegionTypeFloorPlan) {
        [self sendEventWithName:@"locationChanged" body:@{@"lat": [NSNumber numberWithDouble:l.coordinate.latitude],
                                                          @"lng": [NSNumber numberWithDouble:l.coordinate.longitude],
                                                          @"atlasId": [f identifier]
                                                          }];
    }
}
@end


# react-native-indoor-manager

## Getting started

`$ npm install react-native-indoor-manager --save`

### Mostly automatic installation

`$ react-native link react-native-indoor-manager`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-indoor-manager` and add `RNIndoorManager.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNIndoorManager.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNIndoorManagerPackage;` to the imports at the top of the file
  - Add `new RNIndoorManagerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-indoor-manager'
  	project(':react-native-indoor-manager').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-indoor-manager/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-indoor-manager')
  	```


## Usage
```javascript
import { NativeEventEmitter } from 'react-native';
import IndoorManager from 'react-native-indoor-manager';

// Create a React Native NativeEventEmitter
const indoorEventEmitter = new NativeEventEmitter(IndoorManager);
// Add listener
indoorEventEmitter.addListener('locationChanged', location => {
	// do things
});
// init Indoor Atlas service
IndoorManager.initService(API_KEY, API_SECRET);
```
  
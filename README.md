# FoodPlacesNearby

App shows nearby food places on map and in list.

## Setup

### 1. Create `local.properties`
Create `~/local.properties` file:
```
sdk.dir=<SDK_LOCATION>
```

### 2. Get ArcGIS Runtime API Key
Please, check [guide](https://developers.arcgis.com/android/get-started/#3-get-an-api-key)

### 3. Get Google Maps API Key
Please, check [guide](https://developers.google.com/maps/documentation/android-sdk/get-api-key#creating-api-keys)

### 4. Add keys to local.properties
Add next lines with your keys to `~/local.properties` file:
```
MAPS_API_KEY=<API_KEY>
ARC_GIS_API=<API_KEY>
```

## Build app
Run build gradle command:
```
build gradle
```

import React, {useEffect, useState} from 'react';
import greenPin from "../../../assets/greenPin.svg";
import redPin from "../../../assets/redPin.svg";
import orangePin from "../../../assets/orangePin.svg";
import {GoogleMap, Marker} from '@react-google-maps/api';
import classNames from "classnames";
import "./googleMapComponent.scss";

const GoogleMapComponent = ({
                                formik,
                                mapCenter = {lat: 52.15052, lng: 20.10678},
                                markerPosition,
                                setMarkerPosition,
                                disabled,
                                zoom = 14,
                                onLocationChange,
                                onMapLoad,
                                isLoaded,
                                onReverseGeocode,
                                className,
                                markerAction
                            }) => {

    const [map, setMap] = useState(null);

    useEffect(() => {
        if (map) {
            if (mapCenter) {
                map.setCenter(mapCenter);
                map.setZoom(zoom);
            } else if (markerPosition.length > 0) {
                const bounds = new window.google.maps.LatLngBounds();
                markerPosition.forEach((marker) => {
                    bounds.extend(new window.google.maps.LatLng(marker.lat, marker.lng));
                });
                map.fitBounds(bounds);
            }
        }
    }, [map, markerPosition, mapCenter]);

    if (!isLoaded) {
        return null;
    }

    const getMarkerIcon = (rentalStatus) => {
        switch (rentalStatus) {
            case "Rented":
                return {
                    url: greenPin, scaledSize: new window.google.maps.Size(40, 40),
                };
            case  "Repair":
                return {
                    url: redPin, scaledSize: new window.google.maps.Size(40, 40),
                };
            case "Free":
                return {
                    url: orangePin, scaledSize: new window.google.maps.Size(40, 40),
                };
            default:
                return {
                    url: orangePin, scaledSize: new window.google.maps.Size(40, 40),
                };
        }
    }

    const handleMapClick = (e) => {
        if (disabled) {
            return null
        }
        const latLng = e.latLng;
        const lat = latLng.lat();
        const lng = latLng.lng();
        setMarkerPosition([{lat, lng}]);
        formik.setFieldValue("latitude", parseFloat(lat).toFixed(6));
        formik.setFieldValue("longitude", parseFloat(lng).toFixed(6));

        if (onLocationChange) {
            onLocationChange(lat, lng);
        }

        if (map) {
            map.setZoom(18);
        }

        const geocoder = new window.google.maps.Geocoder();
        geocoder.geocode({location: {lat, lng}}, (results, status) => {
            if (status === "OK" && results[0]) {
                const preciseResults = results.filter((r) => r.geometry.location_type === "ROOFTOP");
                const selectedResult = preciseResults.length > 0 ? preciseResults[0] : results[0];
                const componentMapping = {
                    locality: "city",
                    postal_code: "postalCode",
                    route: "street",
                    street_number: "buildingNumber",
                };
                let newAddressValues = {street: "", buildingNumber: "", city: "", postalCode: ""};
                selectedResult.address_components.forEach((component) => {
                    component.types.forEach((key) => {
                        if (componentMapping[key]) {
                            newAddressValues[componentMapping[key]] = component.long_name;
                        }
                    })
                })
                if (onReverseGeocode) {
                    onReverseGeocode(newAddressValues);
                }
            }
        });
    }

    const handleLoad = (map) => {
        setMap(map);
        if (onMapLoad) onMapLoad(map);
    };

    return (
        <GoogleMap
            onLoad={handleLoad}
            center={mapCenter}
            zoom={zoom}
            onClick={handleMapClick}
            mapContainerClassName={classNames("map", className)}
            options={{
                gestureHandling: "greedy",
                zoomControl: true,
                scrollwheel: true,
                disableDoubleClickZoom: false,
                draggable: true,
                panControl: true,
                streetViewControl: false
            }}
        >
            {markerPosition.map((marker, index) => (
                <Marker
                    key={index}
                    position={{lat: marker.lat, lng: marker.lng}}
                    icon={getMarkerIcon(marker.rentalStatus)}
                    onClick={() => markerAction(marker.id)}
                />))
            }
        </GoogleMap>
    );
};

export default GoogleMapComponent;
import React, {useEffect, useRef, useState} from 'react';
import "./locationsearch.scss"
import {ReactComponent as searchIcon} from "../../../assets/search.svg";
import Icon from "../../commonElements/icon/Icon";
import classNames from "classnames";


const LocationSearch = ({
                            formik,
                            setMarkerPosition,
                            disabled,
                            map
                        }) => {
    const inputRef = useRef(null);
    const autocompleteRef = useRef(null);
    const [debouncedQuery, setDebouncedQuery] = useState('');


    useEffect(() => {
        const delayDebounce = setTimeout(() => {
            setDebouncedQuery(inputRef.current.value);
        }, 2500);

        return () => clearTimeout(delayDebounce);
    }, [inputRef.current?.value]);


    useEffect(() => {
        if (!inputRef.current || !window.google || !window.google.maps.places) return;
        autocompleteRef.current = new window.google.maps.places.Autocomplete(inputRef.current, {
            types: ['geocode'],
        })

        autocompleteRef.current.addListener("place_changed", () => {
            const place = autocompleteRef.current.getPlace();
            if (!place || !place.geometry || !place.geometry.location) {
                return;
            }
            const lat = place.geometry.location.lat();
            const lng = place.geometry.location.lng();
            setMarkerPosition(lat, lng);
            formik.setFieldValue("city", "")
            formik.setFieldValue("buildingNumber", "")
            formik.setFieldValue("street", "")
            formik.setFieldValue("apartmentNumber", "")

            formik.setFieldValue("latitude", parseFloat(lat.toFixed(6)));
            formik.setFieldValue("longitude", parseFloat(lng.toFixed(6)));
            if (map) {
                map.setZoom(18);
            }
            const componentMapping = {
                locality: "city",
                postal_code: "postalCode",
                route: "street",
                street_number: "buildingNumber",

            };
            place.address_components.forEach((component) => {
                component.types.forEach((key) => {
                    if (componentMapping[key]) {
                        formik.setFieldValue(componentMapping[key], component.long_name);
                    }
                })
            })
        });
    })

    return (
        <div className={classNames("search-box", {disabled: disabled})}>
            <Icon icon={searchIcon}/>
            <input
                ref={inputRef}
                type="text"
                placeholder="Search for a location..."
                className="search-location"
                disabled={disabled}
            />
        </div>
    );
};

export default LocationSearch;
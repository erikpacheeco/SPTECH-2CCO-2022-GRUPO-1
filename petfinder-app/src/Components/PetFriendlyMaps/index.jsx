import React, { useEffect, useState } from 'react'
import { GoogleMap, Marker, useJsApiLoader } from '@react-google-maps/api';
import api from "../../Api.js";
import "./pet-friendly.css"

const containerStyle = {
    width: '90%',
    height: '90%'
};

const center = {
    lat: -3.745,
    lng: -38.523
};

export default function MapsTest() {

    useEffect(() => {
        navigator.geolocation.getCurrentPosition(getPosition, console.log)
        api.get("/usuarios/locais").then((res) => {
            setTimeout(()=>{
                setLocais(res.data)
            },500)
        })
    }, [])

    const [lat, setLat] = useState()
    const [lng, setLng] = useState()
    const [locais, setLocais] = useState([])

    function getPosition(position) {
        setLat(position.coords.latitude)
        setLng(position.coords.longitude)
    }

    const [zoom, setZoom] = useState(10)
    const [key, setKey] = useState(1)

    useEffect(() => {
        setTimeout(() => {
            setZoom(15)
            setKey(2)
        }, 500)
    })

    const { isLoaded } = useJsApiLoader({
        id: 'google-map-script',
        googleMapsApiKey: "AIzaSyBVXstcQ1tF95i1nKUAdzIqRv1vjp6Ve08"
    })

    const [map, setMap] = React.useState(null)

    const onLoad = React.useCallback(function callback(map) {
        const bounds = new window.google.maps.LatLngBounds(center);
        map.fitBounds(bounds);

        setMap(map)
    }, [])

    const onUnmount = React.useCallback(function callback(map) {
        setMap(null)
    }, [])

    return isLoaded ? (
        <GoogleMap
            mapContainerStyle={containerStyle}
            center={{ lat: lat, lng: lng }}
            zoom={zoom}
            onLoad={onLoad}
            onUnmount={onUnmount}
            options={{
                styles: [{
                    elementType: "labels",
                    featureType: "poi.business",
                    stylers: [{ visibility: "off" }]
                }]
            }}
        >
            <Marker key={key} position={{lat: -23.558121,lng: -46.661614}} options={{label:{text:"Você está aqui", className:"pet-friendly-maps"}}}/>
            {
                locais.map((l, index) => {
                    return(<Marker key={index+1} position={{ lat: Number(l.latitude), lng: Number(l.longitude)}} options={{label:{text:l.nome, className:"pet-friendly-maps"}}}/>)
                })
            }
        </GoogleMap>
    ) : <></>
}
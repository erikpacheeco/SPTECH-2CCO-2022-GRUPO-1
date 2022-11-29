import React, { useState } from 'react'
import { useEffect } from 'react';

export default function PetShopMaps() {

  useEffect(()=>{
    navigator.geolocation.getCurrentPosition(getPosition,console.log)
  }, [])

  const [lat, setLat] = useState()
  const [lng, setLng] = useState()

  function getPosition(position){
    setLat(position.coords.latitude)
    setLng(position.coords.longitude)
  }

  return (
    <iframe id="google_max" width="100%" height="500" frameborder="0" src={`https://www.google.com/maps/embed/v1/search?key=AIzaSyCBDr8QFM4u7DEHVsXGKtcHeHdcHjaK0J4&q=pet+stores&zoom=12&center=${lat},${lng}&language=pt-BR`}/>
  )
}
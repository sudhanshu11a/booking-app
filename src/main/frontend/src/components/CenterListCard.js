import { React, useEffect, useState} from 'react';
import {CenterDetailsCard} from '../components/CenterDetailsCard';
import ('./CenterListCard.css');

export const CenterListCard = (props) => {
    const [centers, setCenters] = useState([]);
    useEffect(
        () => {
         const fetchAllCenters = async () => {

            if(!(props.districtId==='non')){
                const response = await fetch(`http://localhost:8080/centers/delhi/${props.districtId}/18`);
                const data = await response.json();
                setCenters(data);
            }
         };
         fetchAllCenters();
        }, [props.districtId]
    );

    
    return (
        <div className="CenterListCard">
            {centers!=null?centers.map(center => <CenterDetailsCard key={center.centerId} center={center}/>):"No Content"}
        </div>
        
    );
}
import { React} from 'react';
import {CenterDetailsCard} from '../components/CenterDetailsCard';
import ('./CenterListCard.css');

export const CenterListCard = (props) => {
    
    return (
        <div className="CenterListCard">
            {props.centers.map(center => <CenterDetailsCard key={center.centerId} center={center}/>)}
        </div>
        
    );
}
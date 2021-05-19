import { React } from 'react';
import { SessionListCard } from './SessionListCard';
import ('./CenterDetailsCard.css');

export const CenterDetailsCard = ({center}) => {

      return (
        <div className="CenterDetailsCard">
            
            <div className="header-section">
                <h3>{center.districtName}</h3>
                <h3>{center.name}</h3>
                <SessionListCard session={center.sessions} />
            </div>
        </div>
        
    );
}
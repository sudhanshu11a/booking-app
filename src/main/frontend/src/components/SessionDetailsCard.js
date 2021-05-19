import { React} from 'react';
import ('./SessionDetailsCard.css');

export const SessionDetailsCard = ({session}) => {
    
    return (
        <div className="SessionDetailsCard">
          <h5>date : {session.date} </h5> 
          <h5>Availability: {session.availableCapacity}</h5>
          <h5>Min Age: {session.minAgeLimit}</h5>
        </div>
        
    );
}
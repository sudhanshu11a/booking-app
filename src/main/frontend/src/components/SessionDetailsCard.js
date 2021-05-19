import { React} from 'react';
import ('./SessionDetailsCard.css');

export const SessionDetailsCard = ({session}) => {
    
    return (
        <div className="SessionDetailsCard">
          <div><h5>date : {session.date} </h5> </div>
          <div className="VaccineWithAge">
            <div>{session.vaccine}</div>
            <div>Min age : {session.minAgeLimit}</div>
          </div>
          <div className="AvailabilityDiv"><h5>Availability: {session.availableCapacity}</h5>
            <div className="AvailabilityDoseDiv">
              <h5>Dose 1: {session.availableCapacityDose1}</h5>
              <h5>Dose 2: {session.availableCapacityDose2}</h5>
            </div>
          </div>      
        </div>
        
    );
}
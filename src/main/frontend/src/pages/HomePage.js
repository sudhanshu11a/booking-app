import { React, useState} from 'react';
import { CenterListCard } from '../components/CenterListCard';
import { FormDetailsCard } from '../components/FormDetailsCard';

export const HomePage = () => {
    const [newDistrict, setNewDistrict] = useState('non');
    const [district, setDistrict] = useState('non');
    const handleDistrict = (districtValue) => {
        setDistrict(districtValue);
    };

    const updateCenters = () => {
        setNewDistrict(district);
    };

    
    return (
        <div className="HomePage">
            <div className="header-section">
                <h3>Vaccine Availability Portal</h3>
                
                Before checking the Availability, please login into the CoWn app for faster remaining process. 
                <div>For Booking : 
                <a href="https://www.cowin.gov.in/home" target="_blank" rel="noopener noreferrer" >CoWin</a>
                </div>
                <FormDetailsCard onCheckStatus={updateCenters} onSelectDistrict={handleDistrict} />
                
                <CenterListCard districtId={newDistrict} />
            </div>
        </div>
        
    );
}
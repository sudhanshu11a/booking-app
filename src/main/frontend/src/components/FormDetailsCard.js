import { React } from 'react';
import { DistrictListCard } from './DistrictListCard';
import Box from '@material-ui/core/Box';
import Button from '@material-ui/core/Button';

export const FormDetailsCard = (props) => {
    
    const handleDistrict = (districtValue) => {
        props.onSelectDistrict(districtValue);
    }

    const updateCenters = () => {
        props.onCheckStatus();
    };

      return (
        <div className="FormDetailsCard">
            
            <div className="header-section">
            <form >
                <Box color="text.primary"> 
                    <DistrictListCard onSelectDistrict={handleDistrict}/>
                    <Button onClick={updateCenters} > Check Availability</Button>
                </Box>
            </form>    
            </div>
        </div>
        
    );
}
import { React, useEffect, useState} from 'react';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';

export const DistrictListCard = (props) => {
    const [districts, setDistricts] = useState([]);
    const [district, setDistrict] = useState('non');
    const districtChange = (event) => {
        setDistrict(event.target.value);
        props.onSelectDistrict(event.target.value);
      };

      useEffect(
        () => {
         const fetchAllDistricts = async () => {
            const response = await fetch(`http://localhost:8080/district/delhi/all`);
            const data = await response.json();
            setDistricts(data);

         };
         fetchAllDistricts();
        }, []
    );
    return (
        <div className="DistrictListCard" >
            <Select
            value={district}
            onChange={districtChange}
            >
                <MenuItem value="all">All</MenuItem>
                {
                districts.map(district => 
                    <MenuItem key={district.id} value={district.id}>{district.name}</MenuItem>
                )}
         
        </Select>
           </div>
        
    );
}
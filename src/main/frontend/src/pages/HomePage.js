import { React, useEffect, useState } from 'react';
import { CenterListCard } from '../components/CenterListCard';

export const HomePage = () => {

    const [centers, setCenters] = useState([]);
    useEffect(
        () => {
         const fetchAllCenters = async () => {
            const response = await fetch(`http://localhost:8080/check/delhi/all/18`);
            const data = await response.json();
            console.log(data);
            setCenters(data);

         };
         fetchAllCenters();
        }, []
    );

    return (
        <div className="HomePage">

            <div className="header-section">
                <CenterListCard centers={centers} />
            </div>
        </div>
        
    );
}
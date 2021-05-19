import { React} from 'react';
import {SessionDetailsCard} from '../components/SessionDetailsCard';
import ('./SessionListCard.css');

export const SessionListCard = (props) => {

    if(props.session){
        console.log(props.session);
    }
    
    return (
        <div className="SessionListCard" >
            {props.session.map(session => 
                <SessionDetailsCard key={session.session_id} session={session}/>)
            } 
           </div>
        
    );
}
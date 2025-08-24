import React from 'react';
import { useLoaderData } from 'react-router-dom';
import { Link } from 'react-router-dom';
import Heading from '../Heading/Heading.jsx';

function IplTeams() {
    const teams = useLoaderData();
    const [selectedTeam, setSelectedTeam] = React.useState(null);

    return (
        <>
            <Heading 
                title='IPL Teams' 
                placeholder="Search IPL Teams..."
                entityType="team" 
                setSelection={setSelectedTeam}
            />

            <div className='flex flex-wrap justify-center items-center gap-10 p-5'>
                {(selectedTeam? selectedTeam:teams).map((team, i) => (
                        <Link to={`/teams/player/${team.teamId}`} key={i}>
                            <img key={i}
                                src={team.logoUrl}
                                alt={team.teamName}
                                className='h-60 w-60 object-contain cursor-pointer hover:scale-105 transition-transform duration-200'
                            />
                        </Link>
                    ))
                }
            </div>
            <p className="text-center text-gray-600 mt-4">Click on a team to view more details.</p>
        </>
    );
}

export default IplTeams;

export const IplTeamsLoader = async () => {
    const response = await fetch(`${import.meta.env.VITE_BACKEND_URL}/api/teams`);
    if (!response.ok) {
        throw new Error('Could not fetch teams');
    }
    return response.json();
}
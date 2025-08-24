import { useState, useEffect } from 'react';
import { useLoaderData, useParams } from 'react-router-dom';
import Batting from './Tables/Batting';
import Bowling from './Tables/Bowling';
import Heading from './Heading/Heading';

function Players() {

    const preload = useLoaderData();
    const [role, setRole] = useState('batting');
    const [data, setData] = useState(preload);
    const [selectedPlayer, setSelectedPlayer] = useState(null);

    // Fetch players whenever role changes
    useEffect(() => {
        fetch(
            `${import.meta.env.VITE_BACKEND_URL}/api/players?role=${role}`
        )
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Could not fetch team players");
                }
                return response.json();
            })
            .then((data) => setData(data))
            .catch((err) => console.error(err));
    }, [role]);

    const handleRadioChange = (event) => {
        setRole(event.target.value); // this will trigger useEffect
        setSelectedPlayer(null);
    };

    return (
        <div>
            <Heading title='All Players' placeholder='Search Players' entityType='player' setSelection={setSelectedPlayer} role={role} />

            {/* Radio Buttons */}
            <div>
                <div className="flex justify-center space-x-4 mb-4">
                    <label className="inline-flex items-center">
                        <input type="radio" className="form-radio text-blue-600" name="role" value="batting" onChange={handleRadioChange} defaultChecked />
                        <span className="ml-2">Batting</span>
                    </label>
                    <label className="inline-flex items-center">
                        <input type="radio" className="form-radio text-blue-600" name="role" value="bowling" onChange={handleRadioChange} />
                        <span className="ml-2">Bowling</span>
                    </label>
                </div>
            </div>

            <div className='mx-4 my-2 overflow-x-auto'>
                {role === 'batting'
                    ? <Batting players={selectedPlayer !== null? [selectedPlayer] : data} />
                    : <Bowling players={selectedPlayer !== null? [selectedPlayer] : data} />}
            </div>
        </div>
    );
}

export default Players;

export const PlayersLoader = async () => {
    const response = await fetch(`${import.meta.env.VITE_BACKEND_URL}/api/players?role=batting`);

    if (!response.ok) {
        throw new Error('Could not fetch players');
    }

    return response.json();
};

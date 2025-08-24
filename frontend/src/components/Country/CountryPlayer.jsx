import {useState, useEffect} from 'react';
import { useLoaderData, useParams } from 'react-router-dom';
import Batting from '../Tables/Batting';
import Bowling from '../Tables/Bowling';
import Heading from '../Heading/Heading';

function CountryPlayer() {
    const { countryId } = useParams();
    const preload = useLoaderData();
    const [role, setRole] = useState('batting');
    const [data, setData] = useState(preload);
    const [selectedPlayer, setSelectedPlayer] = useState(null);

    // Fetch players whenever role changes
    useEffect(() => {
        fetch(
            `${import.meta.env.VITE_BACKEND_URL}/api/country/players?countryId=${countryId}&role=${role}`
        )
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Could not fetch country players");
                }
                return response.json();
            })
            .then((data) => setData(data))
            .catch((err) => console.error(err));
    }, [role, countryId]);

    const handleRadioChange = (event) => {
        setRole(event.target.value); // this will trigger useEffect
    };

    return (
        <div>
            <Heading title={`Players from ${data.length === 0? "Bangladesh" : data[0].countryName}`} placeholder='Search Players..' entityType='player' setSelection={setSelectedPlayer} role={role}/>

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

export default CountryPlayer;

export const CountryPlayerLoader = async ({ params }) => {
    const { countryId } = params;
    const response = await fetch(`${import.meta.env.VITE_BACKEND_URL}/api/country/players?countryId=${countryId}&role=batting`);
    if (!response.ok) {
        throw new Error('Could not fetch country players');
    }
    return response.json();
}

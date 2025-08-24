import React from 'react';
import { predictPerformance } from '../../predictPerformance';

function Bowling({ players }) {
    const [loadingStates, setLoadingStates] = React.useState({});
    const [prediction, setPrediction] = React.useState({});

    const handlePredictClick = async (player) => {
        const playerKey = player.playerId;
        setLoadingStates(prev => ({ ...prev, [playerKey]: true }));

        try {
            const role = 'batting';
            const predictedData = await predictPerformance(player, role);

            setPrediction(prev => ({ ...prev, [playerKey]: predictedData }));
        } catch (err) {
            console.error(err);
            alert("Something went wrong while predicting");
        } finally {
            setLoadingStates(prev => ({ ...prev, [playerKey]: false }));
        }
    };
    
    if(!players || players.length === 0) {
        return <p className="text-center text-gray-600">No bowling players found</p>;
    }
    if (Array.isArray(players) && players.length === 1 && players[0] === "") {
        return <p className="text-center text-gray-600">No stats found</p>;
    }

    return (
        <table className="min-w-full border border-gray-200">
            <thead className="bg-gray-100">
                <tr>
                    <th className="p-2 border">Player</th>
                    <th className="p-2 border">Team</th>
                    <th className="p-2 border">Country</th>
                    <th className="p-2 border">Matches</th>
                    <th className="p-2 border">Innings</th>
                    <th className="p-2 border">Wickets</th>
                    <th className="p-2 border">Overs</th>
                    <th className="p-2 border">Economy</th>
                    <th className="p-2 border">Bowling Avg</th>
                    <th className="p-2 border">Bowling SR</th>
                    <th className="p-2 border">4Ws</th>
                    <th className="p-2 border">5Ws</th>
                    <th className="p-2 border">4s conceded</th>
                    <th className="p-2 border">6s conceded</th>
                    <th className="p-2 border">Performance Prediction for next match</th>
                </tr>
            </thead>
            <tbody>
                {players.map((p, i) => (
                    <tr key={i} className="text-center">
                        <td className="p-2 border">{p.playerName}</td>
                        <td className="p-2 border">{p.teamName}</td>
                        <td className="p-2 border">{p.countryName}</td>
                        <td className="p-2 border">{p.matches}</td>
                        <td className="p-2 border">{p.innings}</td>
                        <td className="p-2 border">{p.wickets}</td>
                        <td className="p-2 border">{p.overs}</td>
                        <td className="p-2 border">{p.economy}</td>
                        <td className="p-2 border">{p.bowlingAvg}</td>
                        <td className="p-2 border">{p.bowlingStrikeRate}</td>
                        <td className="p-2 border">{p.fourWickets}</td>
                        <td className="p-2 border">{p.fiveWickets}</td>
                        <td className="p-2 border">{p.foursConceded}</td>
                        <td className="p-2 border">{p.sixesConceded}</td>
                        <td className="p-2 border">
                            {prediction[p.playerId] ?
                                <span className='text-green-600 font-semibold'>Predicted: {prediction[p.playerId]}%</span>
                                :
                                <button
                                    onClick={() => handlePredictClick(p)}
                                    className='bg-blue-600 px-4 py-1 text-white shadow-lg rounded hover:bg-blue-700'
                                >
                                    {loadingStates[p.playerId]? 'Predicting...' : 'Predict'}
                                </button>
                            }
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
}

export default Bowling;

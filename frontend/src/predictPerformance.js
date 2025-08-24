
export async function predictPerformance(player, role) {
    const dto = `${role}StatsDTO`;

    const response = await fetch('http://localhost:8001/api/predict/', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            role: role,
            [dto]: player
        }),
    });

    const data = await response.json();

    if (!response.ok) {
        throw new Error('Could not predict performance');
    }

    return data;
}



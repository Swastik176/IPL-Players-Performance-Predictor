import { Link, useLoaderData } from 'react-router-dom';
import Heading from '../Heading/Heading';
import React from 'react';

function Countries() {
    const countries = useLoaderData();
    const [selectedCountry, setSelectedCountry] = React.useState(null);

    return (
        <div>
            <Heading title='Countries' placeholder='Search Countries...' entityType='country' setSelection={setSelectedCountry}/>

            <div className='flex flex-wrap justify-center items-center gap-10 p-5'>
                {(selectedCountry? selectedCountry: countries)
                    .map((country, i) => (
                        <Link to={`/countries/player/${country.countryId}`} key={i}>
                            <img
                                src={country.flagUrl}
                                alt={country.countryName}
                                title={country.countryName}
                                className={`${country.countryId === 'C003' ? 'border border-black' : 'border-none'}h-40 
                                w-60 object-contain cursor-pointer hover:scale-105 transition-transform duration-200`}
                            />
                        </Link>
                    ))
                }
            </div>
            <p className="text-center text-gray-600 mt-4">Click on a country to view more details.</p>
        </div>
    );
}

export default Countries;

export const CountriesLoader = async () => {
    const response = await fetch('http://localhost:8001/api/countries');
    if (!response.ok) {
        throw new Error('Could not fetch countries');
    }
    return response.json();
}
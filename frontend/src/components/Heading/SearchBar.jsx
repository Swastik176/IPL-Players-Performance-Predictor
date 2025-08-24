import React, { useState, useEffect } from 'react';
import { FaSearch } from 'react-icons/fa';

function SearchBar({ placeholder, entityType, className, setSelection, role }) {
    const [searchTerm, setSearchTerm] = useState('');
    const [results, setResults] = useState([]);

    const fetchSearchResults = async (value) => {
        if (!value || value.trim() === '') {
            setResults([]);
            return;
        }

        try {
            const response = await fetch(`${import.meta.env.VITE_BACKEND_URL}/api/${entityType}/search?keyword=${value}`);
            if (!response.ok) {
                throw new Error('Search request failed for ' + entityType);
            };
            const data = await response.json();
            setResults(data);
        }
        catch (error) {
            console.error('Error fetching search results:', error);
        }
    };

    const handleInputChange = (event) => {
        setSearchTerm(event.target.value);
        fetchSearchResults(event.target.value);
    };

    const handleDropdownClick = (id) => {
        setSearchTerm('');
        setResults([]);

        if(entityType === 'player'){
            fetch(`${import.meta.env.VITE_BACKEND_URL}/api/${entityType}?playerId=${id}&role=${role}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Could not fetch " + entityType);
                }
                return response.text();
            })
            .then((data) => {
                if(!data){
                    setSelection("");
                    return;
                }
                setSelection(JSON.parse(data));
            })
            .catch((err) => console.error(err));
        }
        else if(entityType === 'team'){
            fetch(`${import.meta.env.VITE_BACKEND_URL}/api/${entityType}/${id}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Could not fetch " + entityType);
                }
                return response.json();
            })
            .then((data) => setSelection(data))
            .catch((err) => console.error(err));
        }
        else{
            fetch(`${import.meta.env.VITE_BACKEND_URL}/api/${entityType}/${id}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Could not fetch " + entityType);
                }
                return response.json();
            })
            .then((data) => setSelection(data))
            .catch((err) => console.error(err));
        }
    }

    return (
        <div className={`relative w-full max-w-sm mx-4`}>
            <div className={`relative flex items-center w-full max-w-sm mx-4`}>
                <FaSearch className="absolute left-3 text-blue-600" />
                <input
                    type="text"
                    value={searchTerm}
                    placeholder={placeholder || "Search..."}
                    onChange={(e) => handleInputChange(e)}
                    className="w-full pl-10 pr-4 py-2 text-sm border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
                />
                {
                    results.length > 0 && (
                        <ul className="absolute top-full left-0 w-full bg-white border border-gray-300 rounded-lg max-h-60 overflow-y-auto z-10">
                            {results.map((item, i) => (
                                <li 
                                    key={i} 
                                    className="border border-gray-200 border-0.5 px-4 py-2 hover:bg-gray-100 cursor-pointer"
                                    onClick={() =>handleDropdownClick(item[entityType === 'player' ? 'playerId' : entityType === 'team' ? 'teamId' : 'countryId'])}
                                >   
                                    {item[entityType === 'player' ? 'playerName' : entityType === 'team' ? 'teamName' : 'countryName']}
                                </li>
                            ))}
                        </ul>
                    )
                }
            </div>

        </div>
    );
}

export default SearchBar;

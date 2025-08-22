import React, { useEffect } from 'react';
import Slider from './Slider';
import iplLogo from '../../assets/ipl-logo.png';
import bcciLogo from '../../assets/bcci-logo.png';

// LandingPage component that serves as the main entry point for the landing page
function LandingPage() {
    const[index, setIndex] = React.useState(0);

    const quote = [
        "Where Legends are Made, Dreams Take Flight - Welcome to the IPL!",
        "Cricket is not just a game, it’s an emotion.",
        "Every run, every wicket, every moment counts",
        "Legends are made in the IPL, one match at a time",
        "Where talent meets opportunity",
        "From the streets to the stadium, every player has a story"
    ];

    useEffect(() => {
        const interval = setInterval(() => {
            setIndex((prev) => (prev + 1) % quote.length);
        }, 5000);
        return () => clearInterval(interval);
    });

    return (
        <>
            {/* Hero */}
            <div className='flex flex-wrap justify-around items-center bg-[#f3f4f6]'>
                {/* IPL Logo */}
                <div>
                    <img src={iplLogo} alt="IPL Logo" className="w-100 mx-auto mt-8" />
                </div>

                {/* BCCI Logo */}
                <div>
                    <img src={bcciLogo}
                        alt="BCCI Logo" className="w-100 mx-auto mt-8" />
                </div>
            </div>

            {/* Quote */}
            <div className='relative flex flex-wrap justify-center items-center p-4 m-4 text-center'>
                {quote.map((q, i) =><h2 key={i} className={`absolute text-2xl md:text-3xl font-semibold italic 
                    transition-opacity ease-in-out duration-500 ${i === index ? "opacity-100" : "opacity-0"}`}>
                    "{q}"
                </h2>
                )}
            </div>

            {/* Slider */}
            <Slider />
        </>
    );
}

export default LandingPage;

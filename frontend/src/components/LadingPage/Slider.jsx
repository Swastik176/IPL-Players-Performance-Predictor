import React, { useEffect, useState } from 'react';
import { BsChevronLeft, BsChevronRight } from 'react-icons/bs';
import winnerLogo from '../../assets/winner-logo1.png';

function Slider() {
    const [index, setIndex] = useState(0);

    const slides = [
        ['/trophy.jpg', 'IPL Trophy'],
        ['/winner-2008.jpg', 'Winner 2008: Rajasthan Royals'],
        ['/winner-2009.jpeg', 'Winner 2009: Deccan Chargers'],
        ['/winner-2010.jpeg', 'Winner 2010: Chennai Super Kings'],
        ['/winner-2011.webp', 'Winner 2011: Chennai Super Kings'],
        ['/winner-2012.webp', 'Winner 2012: Kolkata Knight Riders'],
        ['/winner-2013.jpg', 'Winner 2013: Mumbai Indians'],
        ['/winner-2014.webp', 'Winner 2014: Kolkata Knight Riders'],
        ['/winner-2015.webp', 'Winner 2015: Mumbai Indians'],
        ['/winner-2016.webp', 'Winner 2016: Sunrisers Hyderabad'],
        ['/winner-2017.webp', 'Winner 2017: Mumbai Indians'],
        ['/winner-2018.webp', 'Winner 2018: Chennai Super Kings'],
        ['/winner-2019.webp', 'Winner 2019: Mumbai Indians'],
        ['/winner-2020.webp', 'Winner 2020: Mumbai Indians'],
        ['/winner-2021.webp', 'Winner 2021: Chennai Super Kings'],
        ['/winner-2022.webp', 'Winner 2022: Gujarat Titans'],   
        ['/winner-2023.webp', 'Winner 2023: Chennai Super Kings'],
        ['/winner-2024.webp', 'Winner 2024: Chennai Super Kings'],
        ['/winner-2025.webp', 'Winner 2025: Royal Challengers Bangalore'],
    ];

    const nextSlide = () => {
        setIndex((prevIndex) => (prevIndex + 1) % slides.length);
    };
    const prevSlide = () => {
        setIndex((prevIndex) => (prevIndex - 1 + slides.length) % slides.length);
    }

    // Auto slide change every 5 seconds
    useEffect(() => {
        const interval = setInterval(nextSlide, 5000);
        return () => clearInterval(interval);
    }, []);

    return (
        <section className='flex justify-center h-[80vh] bg-white relative overflow-hidden group'>

            {/* winner logo */}
            <div className='absolute inset-0 justify-start'>
                <img src={winnerLogo} alt="winner-logo" className='w-50'/>
            </div>

            {/* Image */}
            <div className='absolute inset-0'>
                {
                    slides.map((slide, i) => (
                        <div key={i}>

                            <img
                                key={slide[0]}
                                src={`${slide[0]}`}
                                alt='IPL Winner Team'
                                className={`absolute inset-0 w-full h-full p-5 object-contain transition-opacity duration-1000 ease-in-out ${i === index ? "opacity-100" : "opacity-0"
                                    }`}
                            />
                            <p 
                                key={slide[1]}
                                className={`absolute opacity-0 bottom-5 left-1/2 -translate-x-1/2
                                text-white text-2xl font-bold px-3 py-1 rounded-lg shadow-lg
                                group-hover:${i === index ? "opacity-100" : "opacity-0"}`}>
                                {slide[1]}
                            </p>
                        </div>
                    ))
                }
            </div>

            {/* Left Arrow */}
            <button
                onClick={prevSlide}
                className="absolute opacity-0 group-hover:opacity-100 left-5 top-1/2 -translate-y-1/2 bg-black/40 text-white p-3 rounded-full hover:bg-black/70 transition"
            >
                <BsChevronLeft size={24} />
            </button>

            {/* Right Arrow */}
            <button
                onClick={nextSlide}
                className="absolute opacity-0 group-hover:opacity-100 right-5 top-1/2 -translate-y-1/2 bg-black/40 text-white p-3 rounded-full hover:bg-black/70 transition"
            >
                <BsChevronRight size={24} />
            </button>
        </section>
    );
}

export default Slider;

import React, { useState } from 'react';
import { Link, NavLink } from 'react-router-dom';
import { FiMenu, FiX } from "react-icons/fi";

function Header() {
    const [menuOpen, setMenuOpen] = useState(false);

    return (
        <header className='flex flex-wrap justify-between items-center bg-[rgb(10,88,202)] p-4 sticky top-0 z-50 shadow-lg'>
          {/* Logo */}
            <div className='text-3xl flex-1 text-white font-bold'>
                <Link to="/">
                    SwaG
                </Link>
            </div>

            {/* Title */}
            <div className='text-2xl flex-1 text-white font-semibold hidden md:block text-center'>
                IPL Stats and Performance Predictor
            </div>

            {/* Navigation Links */}
            <div className='hidden flex-1 lg:flex w-1/3 justify-end'>
                <ul className='flex space-x-4 text-white'>
                    <li>
                        <NavLink to="/" className={({ isActive }) => `${isActive ? 'text-yellow-300' : 'text-white'} 
                            hover:text-yellow-300`}>
                            Home
                        </NavLink>
                    </li>

                    <li>
                        <NavLink to="/teams" className={({ isActive }) => `${isActive ? 'text-yellow-300' : 'text-white'} 
                            hover:text-yellow-300`}>
                            IPL Teams
                        </NavLink>
                    </li>

                    <li>
                        <NavLink to="/countries" className={({ isActive }) => `${isActive ? 'text-yellow-300' : 'text-white'} 
                            hover:text-yellow-300`}>
                            Countries
                        </NavLink>
                    </li>

                    <li>
                        <NavLink to="/players" className={({ isActive }) => `${isActive ? 'text-yellow-300' : 'text-white'} 
                            hover:text-yellow-300`}>
                            All Players
                        </NavLink>
                    </li>
                </ul>
            </div>

            {/* Mobile Menu Button */}
            <div className="lg:hidden flex flex-1 justify-end">
                <button onClick={() => setMenuOpen((prev) => !prev)}>
                    {menuOpen ? (
                        <FiX className="text-white text-3xl cursor-pointer" />
                    ) : (
                        <FiMenu className="text-white text-3xl cursor-pointer" />
                    )}
                </button>
            </div>

            {/* Mobile Dropdown */}
            {menuOpen && (
                <div className="absolute top-16 left-0 w-full bg-[rgb(10,88,202)] p-4 lg:hidden z-50">
                    <ul className="flex flex-col space-y-4 text-white">
                        <li>
                            <NavLink
                                to="/"
                                className={({ isActive }) =>
                                    `${isActive ? "text-yellow-300" : "text-white"} hover:text-yellow-300`
                                }
                                onClick={() => setMenuOpen(false)}
                            >
                                Home
                            </NavLink>
                        </li>
                        <li>
                            <NavLink
                                to="/teams"
                                className={({ isActive }) =>
                                    `${isActive ? "text-yellow-300" : "text-white"} hover:text-yellow-300`
                                }
                                onClick={() => setMenuOpen(false)}
                            >
                                IPL Teams
                            </NavLink>
                        </li>
                        <li>
                            <NavLink
                                to="/countries"
                                className={({ isActive }) =>
                                    `${isActive ? "text-yellow-300" : "text-white"} hover:text-yellow-300`
                                }
                                onClick={() => setMenuOpen(false)}
                            >
                                Countries
                            </NavLink>
                        </li>
                        <li>
                            <NavLink
                                to="/players"
                                className={({ isActive }) =>
                                    `${isActive ? "text-yellow-300" : "text-white"} hover:text-yellow-300`
                                }
                                onClick={() => setMenuOpen(false)}
                            >
                                All Players
                            </NavLink>
                        </li>
                    </ul>
                </div>
            )}
        </header>
    );
}

export default Header;

import React from 'react';
import { NavLink } from 'react-router-dom';
import { FaInstagram, FaLinkedin, FaGithub } from 'react-icons/fa';

function Footer() {
    return (
        <footer className='flex flex-col flex-wrap relative overflow-hidden bg-black text-white h-80'>    

            {/* Menu */}
            <div className='flex flex-wrap justify-around m-4 mt-6 mb-6'>
                {/* Quick Links */}
                <div>
                    <h1 className='text-white text-2xl font-semibold'>Quick Links</h1>
                    <ul className='flex flex-col flex-wrap space-x-4 text-white'>
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

                {/* About Us */}
                <div className='flex flex-col flex-wrap max-w-sm'>
                    <h1 className='text-white text-2xl font-semibold'>About Us</h1>
                    <p className='text-white mb:2'>
                        SwaG is your go-to platform for all the player stats to player performances, we bring you the latest insights and predictions.
                    </p>
                </div>

                {/* Follow Us */}
                <div className='flex flex-col'>
                    <h1 className='text-white text-2xl font-semibold'>Follow Us</h1>
                    <ul className='flex flex-col space-x-4 text-white'>
                        <li>
                            <a href="https://github.com/swastik176" target="_blank" rel="noopener noreferrer" className='hover:text-yellow-300'>
                                <FaGithub className='inline' size={18} /> Github 
                            </a>
                        </li>
                        <li>
                            <a href="https://www.instagram.com/swastik__176/" target="_blank" rel="noopener noreferrer" className='hover:text-yellow-300'>
                                <FaInstagram className='inline' size={18} /> Instagram 
                            </a>
                        </li>
                        <li>
                            <a href="https://www.linkedin.com/in/swastik-gupta-040082252/" target="_blank" rel="noopener noreferrer" className='hover:text-yellow-300'>
                                <FaLinkedin className='inline' size={18} /> LinkedIn 
                            </a>
                        </li>
                    </ul>
                </div>
            </div>


            <div className='w-full self-end flex flex-wrap justify-center bg-[rgb(10,88,202)] text-white px-4 py-2'>
                <p>Copyright &copy; SwaG 2025 All Rights Reserved.</p>
            </div>

        </footer>
    );
}

export default Footer;

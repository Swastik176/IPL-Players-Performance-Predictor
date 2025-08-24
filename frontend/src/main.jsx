import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, createRoutesFromElements, Route, RouterProvider } from 'react-router-dom'
import './index.css'
import App from './App.jsx'
import LandingPage from './components/LadingPage/LandingPage.jsx'
import IplTeams from './components/Team/IplTeams.jsx'
import Players from './components/Players.jsx'
import Countries, { CountriesLoader } from './components/Country/Countries.jsx'
import CountryPlayers, { CountryPlayerLoader } from './components/Country/CountryPlayer.jsx'
import { IplTeamsLoader } from './components/Team/IplTeams.jsx'
import TeamPlayer, { TeamPlayerLoader } from './components/Team/TeamPlayer.jsx'

const route = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<App />}>
      <Route path="" element={<LandingPage/>}/>
      <Route loader={IplTeamsLoader} path="teams" element={<IplTeams/>}/>
      <Route loader={TeamPlayerLoader} path='/teams/player/:teamId' element={<TeamPlayer/>}/>
      <Route loader={CountriesLoader} path="countries" element={<Countries/>}/>
      <Route loader={CountryPlayerLoader} path="/countries/player/:countryId" element={<CountryPlayers />}/>       
      <Route path="players" element={<Players/>}/>
      <Route path="*" element={<div className='flex flex-wrap justify-center items-center w-full h-screen text-red-500 text-3xl font-bold p-5'><p>Not Found</p></div>}/>
    </Route>
  )
)

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={route}/>
  </StrictMode>
)

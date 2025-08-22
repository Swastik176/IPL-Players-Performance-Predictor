import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, createRoutesFromElements, Route, RouterProvider } from 'react-router-dom'
import './index.css'
import App from './App.jsx'
import LandingPage from './components/LadingPage/LandingPage.jsx'

const route = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<App />}>
      
      <Route path="" element={<LandingPage/>}/>
      <Route path="*" element={<div className='flex flex-wrap justify-center items-center w-full h-screen text-red-500 text-3xl font-bold p-5'><p>Not Found</p></div>}/>
    </Route>
  )
)

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={route}/>
  </StrictMode>
)

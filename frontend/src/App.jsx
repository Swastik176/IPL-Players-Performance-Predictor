import { useState } from 'react'
import { Outlet } from 'react-router-dom'
import Header from './components/Header'
import Footer from './components/Footer'
import ScrollToTop from './SlideToTop'
import './App.css'

function App() {
  return (
    <>
      <ScrollToTop />
      <Header />
      <Outlet />
      <Footer/>
    </>
  )
}

export default App

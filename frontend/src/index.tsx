import { createRoot } from 'react-dom/client'
import { Main } from './containers'
import React from 'react'
import './styles/main.css'

document.addEventListener('DOMContentLoaded', () => {
  const rootNode = document.getElementById('root') as HTMLElement
  const root = createRoot(rootNode)
  root.render(<Main></Main>)
})

import { createRoot } from 'react-dom/client'
import './index.css'
import { RouterProvider } from 'react-router-dom'
import { routes } from './app/routes/routes.tsx'
import { LoadingProvider } from './common/hooks/useLoading.tsx'

createRoot(document.getElementById('root')!).render(
  <LoadingProvider>
    <RouterProvider router={routes} />
  </LoadingProvider>
)
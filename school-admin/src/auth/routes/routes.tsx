import type { RouteObject } from 'react-router-dom';
import Login from '../pages/login/Login.tsx'

export const authRoute = [{
    path: '/login',
    Component: Login
}] satisfies RouteObject[];
import { createBrowserRouter } from "react-router-dom";
import App from "../../App";
import { authRoute } from "../../auth/routes/routes";
import ErrNotFound from "../pages/NotFound";

export const routes = createBrowserRouter([
    {
        path: '/',
        Component: App
    },
    {
        path: '*',
        Component: ErrNotFound
    },
    ...authRoute
]);

import { Link } from "react-router-dom";

function ErrNotFound() {

    return <>
        <div className="not-found-container">
            <div className="broken-chain">
                {/* You can replace this with an SVG or an image of a broken chain */}
                <svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                    <path d="M16 18l-6-6M12 12l-6-6M16 6a3 3 0 1 0-3-3a3 3 0 0 0 3 3zM6 18a3 3 0 1 0 3-3a3 3 0 0 0-3 3z" />
                </svg>
            </div>
            <h1 className="not-found-header">404</h1>
            <p className="not-found-text">Page Not Found</p>
            <p className="not-found-subtext">
                The page you are looking for might have been removed, had its name changed, or is temporarily unavailable.
            </p>
            <Link to="/" className="home-button">Go to Homepage</Link>
        </div>
    </>;
}

export default ErrNotFound;
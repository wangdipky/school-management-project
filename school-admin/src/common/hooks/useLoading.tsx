import React, { createContext, useContext, useState, type ReactNode } from "react";
import Loading from "../components/Loading";

interface LoadingContextType {
    showLoading(): any;
    hideLoading(): any;
}

const LoadingContext = createContext<LoadingContextType | null>(null);

export const LoadingProvider: React.FC<{ children: ReactNode }> = ({ children }) => {

    const [isLoading, setIsLoading] = useState(false);

    const showLoading = () => setIsLoading(true);
    const hideLoading = () => setIsLoading(false);

    return (
        <LoadingContext.Provider value={{ showLoading, hideLoading }}>
            {children}
            <Loading isLoading={isLoading} />
        </LoadingContext.Provider>
    );
};

export const useLoading = () => {

    const context = useContext(LoadingContext);
    if (!context) throw new Error("useLoading must be used within LoadingProvider");
    return context;
};
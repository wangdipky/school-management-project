import { Box, CircularProgress } from "@mui/material";
import { useState } from "react";

type ILoadingProps = {
    isLoading: boolean;
    time?: number;
}

export default function Loading({ isLoading, time = 200 }: ILoadingProps) {

    const [display, setDisplay] = useState('flex');
    setTimeout(() => {

        setDisplay(isLoading ? 'flex' : 'none')
    }, time);
    // const display = isLoading ? 'flex' : 'none';
    return (
        <Box
            sx={{
                position: "fixed",
                top: 0,
                left: 0,
                width: "100vw",
                height: "100vh",
                backgroundColor: "rgba(255,255,255,0.6)",
                display: display,
                alignItems: "center",
                justifyContent: "center",
                zIndex: 9999
            }}
        >
            <CircularProgress size={60} />
        </Box>
    )
};
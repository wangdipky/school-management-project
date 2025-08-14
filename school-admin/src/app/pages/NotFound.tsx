import { Box, Typography } from "@mui/material";
import img_notfound1 from '../assets/images/notfound1.png';
import { SYS_ERR_NOT_FOUND_TEXT1, SYS_ERR_NOT_FOUND_TEXT2, SYS_INFO_BTN_HOME } from "../const/AuthConst";
import { useLoading } from "../../common/hooks/useLoading";

function ErrNotFound() {

    const { showLoading } = useLoading();

    // Function area
    const handleGoHome = (path: string) => {

        showLoading();
        setTimeout(() => {
            window.location.href = path;
        }, 300);

    }

    // End function area
    return (
        <>
            <Box sx={{
                backgroundColor: '#FFFFFF',
                width: '100vw',
                height: '100vh'
            }}>
                <Box sx={{
                    justifyContent: 'center',
                    textAlign: 'center',
                    pb: 5
                }}>
                    <Typography variant='h2' sx={{
                        fontWeight: 'bold',
                    }}>{SYS_ERR_NOT_FOUND_TEXT1}</Typography>
                    <Typography variant='h2' sx={{
                        fontWeight: 'bold'
                    }}>{SYS_ERR_NOT_FOUND_TEXT2}</Typography>
                </Box>
                <Box sx={{
                    justifyContent: 'center',
                    textAlign: 'center',
                }}>
                    <img src={img_notfound1} />
                </Box>
                <Box sx={{
                    justifyContent: 'center',
                    textAlign: 'center',
                    pt: 5
                }}>
                    <button onClick={() => handleGoHome('/')} style={{
                        borderRadius: '25px',
                        border: 'solid 2px',
                        borderColor: 'black',
                        backgroundColor: '#FFEEEE'
                    }}>{SYS_INFO_BTN_HOME}</button>
                </Box>
            </Box>
        </>
    );
}

export default ErrNotFound;
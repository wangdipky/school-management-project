import { Box, Stack, Typography } from '@mui/material';
import loginBrg from '../../assets/images/login_brg.png';
import logoBook from '../../assets/images/logobook.png';
import { TITLE_BUTTON_LOGIN, TITLE_LOGIN_NAME } from '../../const/TitleConst';
import TextFieldElement from '../../../common/components/TextFieldElement';
import { useForm } from 'react-hook-form';
import type { ILoginModel } from '../../models/AuthModels';
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { LOGIN_MES_PASSWORD, LOGIN_MES_USERNAME } from '../../const/MessageConst';
import ButtonActionElement from '../../../common/components/ButtonActionElement';
import PasswordFieldElement from '../../../common/components/PasswordFieldElement';
import { login } from '../../service/AuthSerice';
import { ToastContainer } from "react-toastify";


function Login() {

    // Var

    // Validation
    const validation = yup
        .object({
            username: yup.string().required(LOGIN_MES_USERNAME),
            password: yup.string().required(LOGIN_MES_PASSWORD),
        })
        .required();

    const form = useForm({
        defaultValues: {
            username: '',
            password: ''
        },
        resolver: yupResolver(validation)
    });

    // Function area
    const handleSubmit = (data: ILoginModel) => {

        login(data).then(res => {
            console.log(res);
        }).catch(err => {

        });
    };

    // End Function area
    return (
        <>
            <ToastContainer />
            <Box sx={{
                backgroundImage: `url(${loginBrg})`,
                backgroundSize: 'cover',
                backgroundRepeat: 'no-repeat',
                backgroundPosition: 'center',
                minHeight: '100vh',
                zIndex: 0,
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center'
            }}>
                <Box sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    borderColor: '#979792',
                    border: 'solid 5px',
                    borderRadius: '5px',
                    width: '50%',
                    backgroundColor: '#ffffff',
                    alignItems: 'center',
                    justifyContent: 'center',
                }}>
                    <Box sx={{
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                        justifyContent: 'center',
                    }}>
                        <img width='15%' src={logoBook} />
                        <Typography variant='h3' sx={{
                            fontSize: {
                                lg: 30,
                                md: 20,
                                sm: 15,
                                xs: 10
                            }
                        }}>{TITLE_LOGIN_NAME}</Typography>
                    </Box>
                    <Box sx={{ width: '100%' }}>
                        <form onSubmit={form.handleSubmit(handleSubmit)}>
                            <Stack spacing={2} sx={{ p: 3 }}>
                                <TextFieldElement label='Username' name='username' control={form.control} />
                                <PasswordFieldElement label='Password' name='password' control={form.control} />
                                <ButtonActionElement type='submit' variant='outlined'
                                    sx={{
                                        background: 'linear-gradient(to right, #51ba62 50%, #276ab4 50%)',
                                        color: 'white'
                                    }}
                                >{TITLE_BUTTON_LOGIN}</ButtonActionElement>
                            </Stack>
                        </form>
                    </Box>
                </Box>
            </Box>
        </>
    )
}

export default Login;
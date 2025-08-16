import { IconButton, TextField } from "@mui/material";
import { Controller, type Control } from "react-hook-form";
import VisibilityIcon from '@mui/icons-material/Visibility';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import { useState } from "react";

type IPasswordFieldElementProps = {
    name: string;
    label?: string;
    control: Control<any>;
}

const PasswordFieldElement = ({ name, label, control }: IPasswordFieldElementProps) => {

    const [isShowPassword, setShowPassword] = useState<boolean>(false);

    return (
        <Controller
            name={name}
            control={control}
            render={({ field, fieldState }) => (
                <TextField
                    {...field}
                    type={isShowPassword ? 'text' : 'password'}
                    label={label}
                    variant='outlined'
                    error={!!fieldState.error}
                    helperText={fieldState.error?.message}
                    fullWidth
                    slotProps={{
                        input: {
                            endAdornment: <IconButton onClick={() => setShowPassword(!isShowPassword)}>{isShowPassword ? <VisibilityOffIcon /> : <VisibilityIcon />}</IconButton>
                        }
                    }}
                />
            )}
        />
    )
}

export default PasswordFieldElement;
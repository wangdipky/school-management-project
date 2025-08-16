import { TextField } from "@mui/material";
import { Controller, type Control } from "react-hook-form"

type ITextFieldElementProps = {
    name: string;
    label?: string;
    control: Control<any>;
}

const TextFieldElement = ({ name, label, control }: ITextFieldElementProps) => {

    return (
        <Controller
            name={name}
            control={control}
            render={({ field, fieldState }) => (
                <TextField
                    {...field}
                    label={label}
                    variant='outlined'
                    error={!!fieldState.error}
                    helperText={fieldState.error?.message}
                    fullWidth
                />
            )}
        />
    )
}

export default TextFieldElement;
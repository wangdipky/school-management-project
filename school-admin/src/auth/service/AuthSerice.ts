import AxiosBase from "../../common/api/AxiosCommon";
import type { ILoginModel } from "../models/AuthModels";

export function login(payload: ILoginModel) {
    return AxiosBase.post('/v1/auth/login', payload);
}
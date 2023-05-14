import { User } from "src/app/model/user.model";

export interface LoginResponse {
    token: string;
    user: any;
    
}
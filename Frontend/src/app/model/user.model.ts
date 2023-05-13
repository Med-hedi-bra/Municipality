export interface User {
    cin: string;
    codeMun: number;
    firstname: string;
    lastname: string;
    gender: string;
    dateOfBirth: Date;
    password: string;
    email: string;
    
    // ecause a user can have multiple role
}
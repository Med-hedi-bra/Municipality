export interface RegisterRequestPayload {
    cin: string;
    codeMun: number;
    firstname: string;
    lastname: string;
    gender: string;
    dateOfBirth: Date;
    password: string;
    email: string;
}
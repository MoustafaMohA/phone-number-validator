export interface CustomerModel {
    id?: number;
    name?: string;
    countryCode: string | undefined;
    country?: string;
    phone?: string;
    isValid: boolean;
}

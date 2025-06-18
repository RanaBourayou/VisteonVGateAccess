export interface AuthResponse {
  token: string;
  role: string;
  email?: string;
  firstName?: string;
  lastName?: string;
}
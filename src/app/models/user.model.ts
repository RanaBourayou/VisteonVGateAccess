export enum Role {
  ADMIN = 'ADMIN',
  RECEPTIONIST = 'RECEPTIONIST',
  REQUESTER = 'REQUESTER'
}

export interface User extends UserDetails {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  role: Role;
  phoneNumber: string;
  isActive: boolean;
  createdAt: Date;
  updatedAt: Date;
  passwordResetToken?: string;
  passwordResetTokenExpiry?: Date;
}

export interface UserDetails {
  getAuthorities(): any[];
  getPassword(): string;
  getUsername(): string;
  isAccountNonExpired(): boolean;
  isAccountNonLocked(): boolean;
  isCredentialsNonExpired(): boolean;
  isEnabled(): boolean;
}